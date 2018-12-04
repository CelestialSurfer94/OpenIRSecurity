package cs412_project.csci412.wwu.edu.cs412_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Activity_log extends AppCompatActivity {
    private Timer autoUpdate;
    private DatabaseManager dbm;
    private Spinner device_spinner;
    private ArrayAdapter<CharSequence> device_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        dbm = DatabaseManager.getInstance();

        //Find spinners for start date
        Spinner sDay = findViewById(R.id.start_day);
        Spinner sMonth = findViewById(R.id.start_month);
        Spinner sYear = findViewById(R.id.start_year);
        //Find spinner for end date
        Spinner eDay = findViewById(R.id.end_day);
        Spinner eMonth = findViewById(R.id.end_month);
        Spinner eYear = findViewById(R.id.end_year);

        device_spinner = findViewById(R.id.device_picker);
        device_names = new ArrayAdapter<CharSequence>(this, R.layout.device_spinner);
        //Create ArrayAdapters for Month, Day, and Year
        ArrayAdapter<CharSequence> days = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> months = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> years = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);

        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Sets Adapter for each Spinner
        sDay.setAdapter(days);
        eDay.setAdapter(days);
        sMonth.setAdapter(months);
        eMonth.setAdapter(months);
        sYear.setAdapter(years);
        eYear.setAdapter(years);

        addLog();
    }

    @Override
    public void onResume() {
        super.onResume();
//        autoUpdate = new Timer();
//        autoUpdate.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        addLog();
//                        //Toast.makeText(MainActivity.this, "refreshed view", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }, 500, 10000);
        addLog();
    }
    public void search(View v){
        addLog();
    }

    public void addLog() {
        ArrayList<Device> devices = dbm.getDevices();
        TableLayout alerts = findViewById(R.id.tableLayout);
        TableLayout.LayoutParams tlp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        TextView logTv;
        TableRow logRow;

        /* update view of triggers */
        alerts.removeAllViews();
        ArrayList<String> triggers = new ArrayList<>();
        ArrayList<Long> allDevicesDates = new ArrayList<>();

        if(devices != null){
            if(device_names != null ){
                device_names.clear();
            }
            for(Device d: devices){
                device_names.add(d.getName());
            }
            //device_names.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            device_spinner.setAdapter(device_names);
        }


        /* get device name */
        //String deviceChosen = "j";
        String deviceChosen = device_spinner.getSelectedItem().toString();


        ArrayList<Triggers> triggersTemp = dbm.getAllTriggers();
        for (int y = 0; y < triggersTemp.size(); y++) {
            /* found device, exit loop after grabbing triggers */
            if (triggersTemp.get(y).getName().equals(deviceChosen)) {
                for (int z = 0; z < triggersTemp.get(y).getTriggers().size(); z++) {
                    //triggerDevices.add(triggersTemp.get(y).getName());
                    triggers.add(triggersTemp.get(y).getTriggers().get(z));
                }
                y = triggersTemp.size()-1;
            }
        }

        /* convert timestamp to long*/
        for (int x = 0; x < triggers.size(); x++) {
            allDevicesDates.add(Long.parseLong(triggers.get(x)));
        }

        /* sort all triggers */
        if (allDevicesDates.size() != 0) {
            Collections.sort(allDevicesDates);
            Collections.reverse(allDevicesDates);
        }

        /* pick between a range of dates */
        String sDay =((Spinner) findViewById(R.id.start_day)).getSelectedItem().toString();
        String sMonth = ((Spinner)findViewById(R.id.start_month)).getSelectedItem().toString();
        String sYear = ((Spinner) findViewById(R.id.start_year)).getSelectedItem().toString();
        //Find spinner for end date
        String eDay = ((Spinner) findViewById(R.id.end_day)).getSelectedItem().toString();
        String eMonth = ((Spinner) findViewById(R.id.end_month)).getSelectedItem().toString();
        String eYear = ((Spinner) findViewById(R.id.end_year)).getSelectedItem().toString();


        String start = sMonth + "-" + sDay + "-" + sYear;
        String end = eMonth + "-" + eDay + "-" + eYear;
        SimpleDateFormat dateRange = new SimpleDateFormat("MM-dd-yyyy");
        long startDate = 0;
        try {
            startDate = dateRange.parse(start).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long endDate = 0;
        try {
            endDate = dateRange.parse(end).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }




        /* display views */
        Long dayToMs = TimeUnit.DAYS.toMillis(1);
        for (int x = 0; x < allDevicesDates.size(); x++) {
            /* if device is between ranges */
            if (startDate <= allDevicesDates.get(x) && endDate + dayToMs >= allDevicesDates.get(x)) {
                logTv = new TextView(this);
                logTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                logTv.setLayoutParams(rlp);
                cal.setTimeInMillis(allDevicesDates.get(x));
                logTv.setText(date.format(cal.getTime()));
                logRow = new TableRow(this);
                logRow.setLayoutParams(tlp);
                logRow.addView(logTv);
                alerts.addView(logRow, tlp);
            }
        }
    }
}
