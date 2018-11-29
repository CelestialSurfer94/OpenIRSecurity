package cs412_project.csci412.wwu.edu.cs412_project;

import java.util.ArrayList;

/**
 * Created by propers on 10/25/18.
 */


public class Device {
    private String name;
    private ArrayList<String> triggers;
    private boolean isArmed;
    private boolean isOnline;
    private String wifiSSD;
    private String wifiPwd;

    public Device(String name) {
        this.name = name;
        triggers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTriggers() {
        return triggers;
    }

    public void setTriggers(ArrayList<String> triggers) {
        this.triggers = triggers;
    }

    /* adds trigger to front (most recent) */
    public void addTrigger(String trigger) {
        this.triggers.add(0, trigger);
    }

    /* would remove all occurences of the trigger (two triggers occuring at same time would be deleted */
    public void delTrigger(String trigger) {
        this.triggers.remove(trigger);
    }

    public boolean isArmed() {
        return isArmed;
    }

    public void setArmed(boolean armed) {
        isArmed = armed;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getWifiSSD() {
        return wifiSSD;
    }

    public void setWifiSSD(String wifiSSD) {
        this.wifiSSD = wifiSSD;
    }

    public String getWifiPwd() {
        return wifiPwd;
    }

    public void setWifiPwd(String wifiPwd) {
        this.wifiPwd = wifiPwd;
    }
}
