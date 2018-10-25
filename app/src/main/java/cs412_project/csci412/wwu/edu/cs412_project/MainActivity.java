package cs412_project.csci412.wwu.edu.cs412_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /* ===================to test database =======================*/
        EditText email = findViewById(R.id.test_email);
        EditText device = findViewById(R.id.test_device);
        EditText trigger = findViewById(R.id.test_trigger);
        String e = email.getText().toString();
        String d = device.getText().toString();
        String t = trigger.getText().toString();

        User test = new User("12","1838213","sutingk@wwu.edu");
        DatabaseManager dbm = new DatabaseManager();
        dbm.createUser(test);
    }

}
