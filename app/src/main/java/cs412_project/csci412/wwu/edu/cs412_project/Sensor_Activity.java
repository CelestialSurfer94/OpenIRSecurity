package cs412_project.csci412.wwu.edu.cs412_project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Sensor_Activity extends AppCompatActivity {
    private DatabaseManager dbm;
    private Device device = null;
    private Timer autoUpdate;
    private String deviceName;
    private TextView devicetv;
    private boolean stopUpdatingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        deviceName = getIntent().getStringExtra("devicename");
        devicetv = findViewById(R.id.text_view_id);
        dbm = DatabaseManager.getInstance();
        stopUpdatingView = false;

        /* find device by the String device name */
        for (int i = 0; i < dbm.getDevices().size(); i++) {
           // Log.w("tester",dbm.getUser().getDevices().get(i).getName());
            if (dbm.getDevices().get(i).getName().equals(deviceName)) {
                device = dbm.getDevices().get(i);
            }
        }
        if (device != null) {
            devicetv.setText(device.getName());
        }
        updateView();
    }
    @Override
    public void onResume(){
        super.onResume();
        TableLayout alerts = (TableLayout) findViewById(R.id.tableLayout);
        alerts.removeAllViews();
        stopUpdatingView = false;
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                if(stopUpdatingView){
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateView();
                        //Toast.makeText(MainActivity.this, "refreshed view", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, 600, 5000);
    }

    @Override
    protected void onPause(){
        super.onPause();
        TableLayout alerts = (TableLayout) findViewById(R.id.tableLayout);
        alerts.invalidate();
        autoUpdate.cancel();
        stopUpdatingView = true;
    }

    /* ERROR: updates slowly and shows the last one before updating ===============================*/
    public void updateView() {

        ArrayList<String> triggers = dbm.getTriggers(device);
        Toast.makeText(this, device.getName() + triggers.size(), Toast.LENGTH_SHORT).show();
        TableLayout alerts = findViewById(R.id.tableLayout);
        TableLayout.LayoutParams tlp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TextView logTv;
        TableRow logRow;

        /* update view of triggers */
        alerts.removeAllViews();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

        ArrayList<Long> sortedTriggers = new ArrayList<Long>();
        for (int x = 0; x < triggers.size(); x++) {
            sortedTriggers.add(Long.parseLong(triggers.get(x)));
        }
        /* sort all triggers */
        if (sortedTriggers.size() != 0) {
            Collections.sort(sortedTriggers);
            Collections.reverse(sortedTriggers);
        }


        for (int x = 0; x < sortedTriggers.size(); x++) {
            logTv = new TextView(this);
            logTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            logTv.setLayoutParams(rlp);
            cal.setTimeInMillis(sortedTriggers.get(x));
            logTv.setText(date.format(cal.getTime()));
            logRow = new TableRow(this);
            logRow.setLayoutParams(tlp);
            logRow.addView(logTv);
            alerts.addView(logRow, tlp);
        }


    }
}