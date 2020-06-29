package com.keelim.hardware.making;

public class Open { // Builder 패턴으로 구성을 할 것 일단은 시스템 정보만
    //SYSTEM
    private String SYSTEM_RELEASE;
    private String SYSTEM_SDK_INT;
    private String SYSTEM_VERSION_CODENAME;
    private String SYSTEM_VERSION_INCREMENTAL;
    private String SYSTEM_BOARD;
    private String SYSTEM_BOOTLOADER;
    private String SYSTEM_DEVICE;
    private String SYSTEM_HARDWARE;
    private String SYSTEM_MANUFACTURER;

    /*WIFI*/
    private String WIFI_CONNECTION_INFO;
    private String WIFI_LINK_SPEED_UNITS;
    private String WIFI_SSID;

    /*BUILDCONFIG*/
    private String BUILD_APPLICATION_ID;
    private String BUILD_TYPE;
    private String BUILD_VERSION_CODE;
    private String BUILD_VERSION_NAME;




    public Open(OpenBuilder builder) {
        this.SYSTEM_RELEASE = SYSTEM_RELEASE;
        this.SYSTEM_SDK_INT = SYSTEM_SDK_INT;
        this.SYSTEM_VERSION_CODENAME = SYSTEM_VERSION_CODENAME;
        this.SYSTEM_VERSION_INCREMENTAL = SYSTEM_VERSION_INCREMENTAL;
        this.SYSTEM_BOARD = SYSTEM_BOARD;
        this.SYSTEM_BOOTLOADER = SYSTEM_BOOTLOADER;
        this.SYSTEM_DEVICE = SYSTEM_DEVICE;
        this.SYSTEM_HARDWARE = SYSTEM_HARDWARE;
        this.SYSTEM_MANUFACTURER = SYSTEM_MANUFACTURER;
    }
	
    //Builder Class
    public static class OpenBuilder {
 
        // required parameters
        private String SYSTEM_RELEASE;
        private String SYSTEM_SDK_INT;
        private String SYSTEM_VERSION_CODENAME;
        private String SYSTEM_VERSION_INCREMENTAL;
        private String SYSTEM_BOARD;
        private String SYSTEM_BOOTLOADER;
        private String SYSTEM_DEVICE;
        private String SYSTEM_HARDWARE;
        private String SYSTEM_MANUFACTURER;

        // optional parameters


        public OpenBuilder setSYSTEM_RELEASE(String SYSTEM_RELEASE) {
            this.SYSTEM_RELEASE = SYSTEM_RELEASE;
            return this;
        }

        public OpenBuilder setSYSTEM_SDK_INT(String SYSTEM_SDK_INT) {
            this.SYSTEM_SDK_INT = SYSTEM_SDK_INT;
            return this;
        }

        public OpenBuilder setSYSTEM_VERSION_CODENAME(String SYSTEM_VERSION_CODENAME) {
            this.SYSTEM_VERSION_CODENAME = SYSTEM_VERSION_CODENAME;
            return this;
        }

        public OpenBuilder setSYSTEM_VERSION_INCREMENTAL(String SYSTEM_VERSION_INCREMENTAL) {
            this.SYSTEM_VERSION_INCREMENTAL = SYSTEM_VERSION_INCREMENTAL;
            return this;
        }

        public OpenBuilder setSYSTEM_BOARD(String SYSTEM_BOARD) {
            this.SYSTEM_BOARD = SYSTEM_BOARD;
            return this;
        }

        public OpenBuilder setSYSTEM_BOOTLOADER(String SYSTEM_BOOTLOADER) {
            this.SYSTEM_BOOTLOADER = SYSTEM_BOOTLOADER;
            return this;
        }

        public OpenBuilder setSYSTEM_DEVICE(String SYSTEM_DEVICE) {
            this.SYSTEM_DEVICE = SYSTEM_DEVICE;
            return this;
        }

        public OpenBuilder setSYSTEM_HARDWARE(String SYSTEM_HARDWARE) {
            this.SYSTEM_HARDWARE = SYSTEM_HARDWARE;
            return this;
        }

        public OpenBuilder setSYSTEM_MANUFACTURER(String SYSTEM_MANUFACTURER) {
            this.SYSTEM_MANUFACTURER = SYSTEM_MANUFACTURER;
            return this;
        }

        public Open build(){
            return new Open(this);
        }
 
    }
 
}