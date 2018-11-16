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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_log extends AppCompatActivity {
    private Timer autoUpdate;
    private DatabaseManager dbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        dbm = DatabaseManager.getInstance();
        Spinner filter_menu = (Spinner) findViewById(R.id.filter_menu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filter_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_menu.setAdapter(adapter);
        addLog();
    }
    @Override
    public void onResume(){
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addLog();
                        //Toast.makeText(MainActivity.this, "refreshed view", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, 500, 10000);
    }
    public void addLog(){
//        TextView tv = new TextView(this);
//        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT));
//        tv.setText("asdfasdf");
//
//        TableRow tr = new TableRow(this);
//
//        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
//                TableLayout.LayoutParams.WRAP_CONTENT);
//
//        tr.setLayoutParams(trParams);
//
//
//        tr.addView(tv);
//
//        TableLayout mTableLayout = (TableLayout) findViewById(R.id.tableLayout);
//        mTableLayout.addView(tr, trParams);

        /*new stuff here*/


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
        ArrayList<String> triggers = new ArrayList<String>();
        ArrayList<Long> allDevicesDates = new ArrayList<Long>();

        /* grab every trigger from every device */
        for (int x = 0; x < devices.size(); x++) {
            ArrayList<String> triggersTemp = dbm.getTriggers(devices.get(x));
            Log.w("trigs", triggersTemp.toString());
            for (int y = 0; y < triggersTemp.size(); y++) {
                triggers.add(triggersTemp.get(y));
            }
        }

        /* convert timestamp to long*/
        for (int x = 0; x < triggers.size(); x++) {
            allDevicesDates.add(Long.parseLong(triggers.get(x)));
        }

        /* sort all triggers */
        if (allDevicesDates.size() != 0) {
            Collections.sort(allDevicesDates);
        }

        for (int x = 0; x < allDevicesDates.size(); x++) {
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
