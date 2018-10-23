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

        // create firebase entry here
        FirebaseDatabase db = FirebaseDatabase.getInstance();
       // DatabaseReference ref = db.getReference("users/ID1/email");
       // ref.setValue("TESTTTTTTT@gmail.com");


        DatabaseReference ref = db.getReference("users");

        String id = "ID3";
        ref.child(id).setValue("USER_Kalvin");
        DatabaseReference ref2 = db.getReference("users/"+id);
        ref2.child("device1").setValue("door_sensor");
//        String key = ref.child("users/"+id).push().getKey();
//        Log.d("keyyyyyyyyyy",key);



//        String key = ref.child("users").push().getKey();
//        User user = new User("1234", "My_Device");
//        Map<String, Object> userVals = user.toMap();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/users/" + key, userVals);
//        childUpdates.put("/user-posts/" + userId + "/" + key, userVals);




        /* ===================to test database =======================*/
    }

}
