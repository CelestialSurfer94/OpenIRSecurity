package cs412_project.csci412.wwu.edu.cs412_project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by propers on 10/23/18.
 */

public class DatabaseManager {
    private FirebaseDatabase db;


    public DatabaseManager() {
        db = FirebaseDatabase.getInstance();
    }
    public void createUser(User u) {
        DatabaseReference ref = db.getReference("users");
        ref.child(u.getId()).setValue(null);
        ref = db.getReference("users/"+u.getId());
        ref.child("email").setValue(u.getEmail());
        ref.child("token").setValue(u.getToken());
    /*
        String id = "ID3";
        ref.child(id).setValue("USER_Kalvin");
        DatabaseReference ref2 = db.getReference("users/"+id);
        ref2.child("device1").setValue("door_sensor");
        */
    }
    public void addDevice(String device) {

    }
    public void delDevice(String device) {

    }
    public void addTrigger(String trig) {

    }
    public void delTrigger(String trig) {

    }







}
