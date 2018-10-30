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

        User test = new User("12","1838213","sutingk@wwu.edu");
        DatabaseManager dbm = new DatabaseManager();
        dbm.createUser(test);
        Device d1 = new Device("Device 1");
        dbm.addDevice(d1, test.getId());
        Device d2 = new Device("Device 2");
        //dbm.addDevice(d2, test.getId());
        dbm.delDevice(d2, test.getId());
        //dbm.addTrigger("trigger1", d1, test);
        dbm.delTrigger("trigger1", d1, test.getId());
        User test2 = new User("21", "1234123", "cramerg2@wwu.edu");
        //dbm.createUser(test2);
        //dbm.delUser(test2);
    }

}
