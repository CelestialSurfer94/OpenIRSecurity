package cs412_project.csci412.wwu.edu.cs412_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Activity_log extends AppCompatActivity {

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
        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv.setText("Hello World 2");

        TableRow tr = new TableRow(this);

        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);

        tr.setLayoutParams(trParams);


        tr.addView(tv);

        TableLayout mTableLayout = (TableLayout) findViewById(R.id.log_table);
        mTableLayout.addView(tr, trParams);
    }
}
