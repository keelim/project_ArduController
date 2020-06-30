package com.keelim.hardware.making;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class Open { // Builder 패턴으로 구성을 할 것 일단은 시스템 정보만
    // 좀더 나은 코드가 있을 것 같다. 
    // 커스텀 뷰는 어떻게 작성을 하지 생각  -> 그냥 일반적인 리스트 뷰 작성인지 
    // 다른 옵션을 추가를 하여 작성을 하는 것인지

    private Context context;
    //SYSTEM
    private String SYSTEM_RELEASE = null;
    private String SYSTEM_SDK_INT = null;
    private String SYSTEM_VERSION_CODENAME = null;
    private String SYSTEM_VERSION_INCREMENTAL = null;
    private String SYSTEM_BOARD = null;
    private String SYSTEM_BOOTLOADER = null;
    private String SYSTEM_DEVICE = null;
    private String SYSTEM_HARDWARE = null;
    private String SYSTEM_MANUFACTURER = null;

    /*WIFI*/
    private String WIFI_CONNECTION_INFO = null;
    private String WIFI_STATE = null;
    private String WIFI_DHCP_INFO = null;


    public Open(OpenWifiBuilder openWifiBuilder) {
        context = openWifiBuilder.context;
    }

    public Open(OpenSystemBuilder openSystemBuilder) {
        context = openSystemBuilder.context;
    }

    private List<OpenListVIewItem> makingStringArray(String s) { //system, wifi, Build
        ArrayList<OpenListVIewItem> list = new ArrayList<>();

        switch (s) {
            case "System":
                if (SYSTEM_RELEASE != null)
                    list.add(new OpenListVIewItem("System", Build.VERSION.RELEASE));

                if (SYSTEM_SDK_INT != null)
                    list.add(new OpenListVIewItem("System", String.valueOf(Build.VERSION.SDK_INT)));


                if (SYSTEM_VERSION_CODENAME != null)
                    list.add(new OpenListVIewItem("System", Build.VERSION.CODENAME));

                if (SYSTEM_VERSION_INCREMENTAL != null)
                    list.add(new OpenListVIewItem("System", Build.VERSION.INCREMENTAL));

                if (SYSTEM_BOARD != null) list.add(new OpenListVIewItem("System", Build.BOARD));

                if (SYSTEM_BOOTLOADER != null)
                    list.add(new OpenListVIewItem("System", Build.BOOTLOADER));

                if (SYSTEM_DEVICE != null)
                    list.add(new OpenListVIewItem("System", Build.DEVICE));

                if (SYSTEM_HARDWARE != null)
                    list.add(new OpenListVIewItem("System", Build.HARDWARE));

                if (SYSTEM_MANUFACTURER != null)
                    list.add(new OpenListVIewItem("System", Build.MANUFACTURER));

                return list;

            case "WIFI":
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                if (WIFI_CONNECTION_INFO != null)
                    list.add(new OpenListVIewItem("WIFI", String.valueOf(wifi.getConnectionInfo())));

                if (WIFI_STATE != null)
                    list.add(new OpenListVIewItem("WIFI", String.valueOf(wifi.getWifiState())));

                if (WIFI_DHCP_INFO != null)
                    list.add(new OpenListVIewItem("WIFI", String.valueOf(wifi.getDhcpInfo())));
                ;

                return list;

            default:
                return null;
        }


    }

    @SafeVarargs
    public static List<String> plusArray(List<String>... arrays) {
        ArrayList<String> total = new ArrayList<>();
        if (arrays == null) return null;

        for (List<String> temp : arrays) {
            total.addAll(temp);
        }

        return total;
    }


    //Builder Class
    public static class OpenSystemBuilder {
        private Context context;
        // required parameters
        private boolean SYSTEM_RELEASE;
        private boolean SYSTEM_SDK_INT;
        private boolean SYSTEM_VERSION_CODENAME;
        private boolean SYSTEM_VERSION_INCREMENTAL;
        private boolean SYSTEM_BOARD;
        private boolean SYSTEM_BOOTLOADER;
        private boolean SYSTEM_DEVICE;
        private boolean SYSTEM_HARDWARE;
        private boolean SYSTEM_MANUFACTURER;

        public OpenSystemBuilder(Context context) {
            this.context = context;
        }
        // optional parameters


        public Open build() {
            return new Open(this);
        }

    }

    public static class OpenWifiBuilder {
        private Context context;
        private boolean WIFI_CONNECTION_INFO;
        private boolean WIFI_LINK_SPEED_UNITS;
        private boolean WIFI_SSID;

        public OpenWifiBuilder(Context context) {
            this.context = context;
        }

        public Open build() {
            return new Open(this);
        }
    }

}