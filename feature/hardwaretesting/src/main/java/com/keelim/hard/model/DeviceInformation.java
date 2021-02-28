package com.keelim.hard.model;

import android.hardware.Sensor;

import java.util.HashMap;
import java.util.Map;

public class DeviceInformation {
    String name;
    String power;
    String resolution;
    String range;
    String vendor;
    String mindelay;

    public DeviceInformation(Sensor s) {
        this.name = s.getName();
        this.power = String.valueOf(s.getPower());
        this.resolution = String.valueOf(s.getResolution());
        this.range = String.valueOf(s.getMaximumRange());
        this.vendor = s.getVendor();
        this.mindelay = String.valueOf(s.getMinDelay());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getMindelay() {
        return mindelay;
    }

    public void setMindelay(String mindelay) {
        this.mindelay = mindelay;
    }

    public Map<String, String> toMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("power", power);
        result.put("resolution", resolution);
        result.put("range", range);
        result.put("vendor", vendor);
        result.put("mindelay", mindelay);
        return result;
    }
}

