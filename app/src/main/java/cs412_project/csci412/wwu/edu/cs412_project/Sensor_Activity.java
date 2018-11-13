package cs412_project.csci412.wwu.edu.cs412_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sensor_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        String deviceName = getIntent().getStringExtra("devicename");
        TextView devicetv = findViewById(R.id.text_view_id);
        devicetv.setText(deviceName);
    }
}
