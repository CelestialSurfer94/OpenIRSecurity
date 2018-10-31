package cs412_project.csci412.wwu.edu.cs412_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_AddSensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsensor);
    }

    public void createDevice(View v) {
        EditText name_field = (EditText) findViewById(R.id.name_field);
        CheckBox enable_check = (CheckBox) findViewById(R.id.enable_check);
        CheckBox receive_check = (CheckBox) findViewById(R.id.receive_check);
        boolean armed = enable_check.isChecked();
        boolean receive = receive_check.isChecked();
        Device d = new Device(name_field.getText().toString());
        d.setArmed(armed);

        //TODO: Add sensor to user, once user is known

        //Resets EditText and CheckBox's so that user can add another device
        Toast.makeText(this, "Sensor Added", Toast.LENGTH_LONG).show();
        name_field.setText("Sensor Name");
        enable_check.setChecked(false);
        receive_check.setChecked(false);
    }

    public void goBack(View v) {
        this.finish();
    }
}
