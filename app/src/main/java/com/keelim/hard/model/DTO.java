package com.keelim.hard.model;

import android.hardware.Sensor;

//            list.append("").append(i++).append(" name: ").append(s.name).append("\n")
//                    .append("power: ").append(s.power).append("\n").
//                    append("resolution: ").append(s.resolution).append("\n")
//                    .append("range: ").append(s.maximumRange).append("\n").
//                    append("vendor: ").append(s.vendor).append("\n")
//                    .append("min delay: ").append(s.minDelay).append("\n\n")
public class DTO {
    String name;
    String power;
    String resolution;
    String range;
    String vendor;
    String mindelay;

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

    public DTO(Sensor s) {
        this.name = s.getName();
        this.power = String.valueOf(s.getPower());
        this.resolution = String.valueOf(s.getResolution());
        this.range = String.valueOf(s.getMaximumRange());
        this.vendor = s.getVendor();
        this.mindelay = String.valueOf(s.getMinDelay());
    }
}

