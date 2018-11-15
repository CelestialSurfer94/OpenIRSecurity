package cs412_project.csci412.wwu.edu.cs412_project;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by propers on 10/23/18.
 */

public class DatabaseManager {
    private FirebaseDatabase db;
    private User user;
    private static DatabaseManager instance;
    private ArrayList<Device> devices;
    private ArrayList<String> triggers;

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    private DatabaseManager() {
        db = FirebaseDatabase.getInstance();
        devices = new ArrayList<>();
        triggers = new ArrayList<>();
    }

    public void setCurrentUser(User user) {
        this.user = user;

    }

    public void delCurrentUser(User user) {
        this.user = null;
    }

    public User getUser() {
        return this.user;
    }

    public void createUser(User u) {
        DatabaseReference ref = db.getReference("users");
        ref.child(u.getId()).setValue(null);
        ref = db.getReference("users/" + u.getId());
        ref.child("email").setValue(u.getEmail());
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
        ref.child("isArmed").setValue(device.isArmed());
        ref.child("isOnline").setValue(false);
        ref.child("wifiSSD").setValue("");
        ref.child("wifiPass").setValue("");
        ref.child("triggerEvents").setValue(null);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String deviceName = (String)dataSnapshot.getValue();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void delDevice(Device device, String userID) {
        DatabaseReference ref = db.getReference("users/" + userID + "/devices/" + device.getName());
        ref.child(device.getName()).removeValue();

        //todo
    }

    public void addTrigger(String trig, Device device) {
        DatabaseReference ref = db.getReference("users/" + user.getId() + "/devices/" + device.getName() + "/triggerEvents");
        ref.child(trig).setValue(trig);
    }
    public void addTimestamp(Device device) {
        DatabaseReference ref = db.getReference("users/" + user.getId() + "/devices/" + device.getName() + "/triggerEvents");
        ref.child("Timestamp:").setValue(ServerValue.TIMESTAMP);
    }

    public void delTrigger(String trig, Device device, String userID) {
        DatabaseReference ref = db.getReference("users/" + userID + "/devices/" + device.getName() + "/triggerEvents");
        ref.child(trig).removeValue();
    }

    public void delUser(User user) {
        DatabaseReference ref = db.getReference("users");
        ref.child(user.getId()).removeValue();
    }

    // Retrieve all devices for a given user
    public ArrayList<Device> getDevices() {
        DatabaseReference ref = db.getReference("users/" + user.getId() + "/devices");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot newData = dataSnapshot;
                Iterable<DataSnapshot> deviceList = newData.getChildren();
                devices.clear();
                for (DataSnapshot data: deviceList) {
                    String devKey = data.getKey().toString();
                    Log.d("DEBUG", devKey);
                    Device d = new Device(devKey);
                    devices.add(d);
                    //DatabaseReference ref2 = db.getReference("users/" + user.getId() + "/devices/" + devKey + "isArmed");
                    //d.setArmed();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //DataSnapshot dataSnapshot = new DataSnapshot();
        //System.out.println(ref.);
        //ref.orderByChild().GetValueAsync();
       // DataSnapshot dataSnapshot =
        //        String
        //System.out.println(ref.orderByChild("deviceName").GetValueAsync());
        //TODO figure out whether or not it is better to use firebase listener
        // or to use a JSON Java query of a webpage -dagmar
        return devices;
    }

    public ArrayList<String> getTriggers(Device device) {
        DatabaseReference ref = db.getReference("users/" + user.getId() + "/devices/" + device.getName() + "/triggerEvents");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot newData = dataSnapshot;
                Iterable<DataSnapshot> deviceList = newData.getChildren();
                triggers.clear();
                for (DataSnapshot data: deviceList) {
                    String devKey = data.getValue().toString();
                    Log.d("DEBUGTrig", devKey);
                    triggers.add(devKey);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return triggers;



    }

    private void addDevice(String device){
        Device d = new Device(device);
        //todo:
        //store devices to sqllite server or sharedprefs?
        //then onCreate, query the devices, add them to the view since
        //same idea with the device triggers.
    }

    public void addDeviceToArray(ArrayList<Device> devices, Device d){
        devices.add(d);
    }

}
