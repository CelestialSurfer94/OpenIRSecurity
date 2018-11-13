package cs412_project.csci412.wwu.edu.cs412_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Activity_log extends AppCompatActivity {
    private DatabaseManager dbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Spinner filter_menu = (Spinner) findViewById(R.id.filter_menu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filter_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_menu.setAdapter(adapter);
        addLog();
        addLog();
        addLog();
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
//        ArrayList<Device> devices = dbm.getDevices();
//        TableLayout alerts = findViewById(R.id.tableLayout);
//        TableLayout.LayoutParams tlp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
//        TableRow.LayoutParams rlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
//        TextView logTv;
//        TableRow logRow;
//
//        /* update view of triggers */
//        alerts.removeAllViews();
//        ArrayList<String> triggers = null;
//        ArrayList<Date> dateObjects;
//
//        /* grab every trigger from every device */
//        for (int x = 0; x < devices.size(); x++) {
//            triggers.addAll(devices.get(x).getTriggers());
//        }
//        /* sort */
//
//
//        /* display all triggers */
//        for (int y = 0; y < devices.size(); y++) {
//            logTv = new TextView(this);
//            logTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//            logTv.setLayoutParams(rlp);
//            logTv.setText(triggers.get(y));
//            logRow = new TableRow(this);
//            logRow.setLayoutParams(tlp);
//            logRow.addView(logTv);
//            alerts.addView(logRow, tlp);
//        }

    }
}
