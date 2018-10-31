package cs412_project.csci412.wwu.edu.cs412_project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by propers on 10/23/18.
 */

public class DatabaseManager {
    private FirebaseDatabase db;
    private static User user;

    public DatabaseManager() {
        db = FirebaseDatabase.getInstance();
    }

    public void setCurrentUser(User user) {
        this.user = user;

    }
    public void delCurrentUser(User user) {
        this.user = null;
    }

    public void createUser(User u) {
        DatabaseReference ref = db.getReference("users");
        ref.child(u.getId()).setValue(null);
        ref = db.getReference("users/" + u.getId());
        ref.child("email").setValue(u.getEmail());
        ref.child("token").setValue(u.getToken());
        ref.child("devices").setValue(null);
    /*
        String id = "ID3";
        ref.child(id).setValue("USER_Kalvin");
        DatabaseReference ref2 = db.getReference("users/"+id);
        ref2.child("device1").setValue("door_sensor");
        */
    }

    public void addDevice(Device device, String userID) {
        DatabaseReference ref = db.getReference("users/" + userID + "/devices");
        ref.child(device.getName()).setValue(null);
        ref = db.getReference("users/" + userID + "/devices/" + device.getName());
        ref.child("deviceName").setValue(device.getName());
        ref.child("isArmed").setValue(false);
        ref.child("isOnline").setValue(false);
        ref.child("wifiSSD").setValue("");
        ref.child("wifiPass").setValue("");
        ref.child("triggerEvents").setValue(null);
    }

    public void delDevice(Device device, String userID) {
        DatabaseReference ref = db.getReference("users/" + userID + "/devices/" + device.getName());
        ref.child(device.getName()).removeValue();
    }

    public void addTrigger(String trig, Device device, User user) {
        DatabaseReference ref = db.getReference("users/" + user.getId() + "/devices/" + device.getName() + "/triggerEvents");
        ref.child("trigger1").setValue("10.25.2018:14:45");
    }

    public void delTrigger(String trig, Device device, String userID) {
        DatabaseReference ref = db.getReference("users/" + userID + "/devices/" + device.getName() + "/triggerEvents");
        ref.child(trig).removeValue();
    }

    public void delUser(User user) {
        DatabaseReference ref = db.getReference("users");
        ref.child(user.getId()).removeValue();
    }


}
