package cs412_project.csci412.wwu.edu.cs412_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Sensor_Activity extends AppCompatActivity {
    private DatabaseManager dbm;
    private Device device = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        String deviceName = getIntent().getStringExtra("devicename");
        TextView devicetv = findViewById(R.id.text_view_id);
        dbm = DatabaseManager.getInstance();
        /* find device by the devicename */
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

    public void updateView() {
        ArrayList<String> triggers = dbm.getTriggers(device);
        TableLayout alerts = findViewById(R.id.tableLayout);
        TableLayout.LayoutParams tlp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TextView logTv;
        TableRow logRow;

        /* update view of triggers */
        alerts.removeAllViews();
        for (int x = 0; x < triggers.size(); x++) {
            logTv = new TextView(this);
            logTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            logTv.setLayoutParams(rlp);
            logTv.setText(triggers.get(x));
            logRow = new TableRow(this);
            logRow.setLayoutParams(tlp);
            logRow.addView(logTv);
            alerts.addView(logRow, tlp);
        }


    }
}
