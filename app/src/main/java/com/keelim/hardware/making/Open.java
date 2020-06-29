package com.keelim.hardware.making;

public class Open { // Builder 패턴으로 구성을 할 것 일단은 시스템 정보만
    //required parameters
    private String RELEASE;
    private String SDK_INT;
    private String VERSION_CODENAME;
    private String VERSION_INCREMENTAL;
    private String BOARD;
    private String BOOTLOADER;
    private String DEVICE;
    private String HARDWARE;
    private String MANUFACTURER;

    public String getRELEASE() {
        return RELEASE;
    }

    public String getSDK_INT() {
        return SDK_INT;
    }

    public String getVERSION_CODENAME() {
        return VERSION_CODENAME;
    }

    public String getVERSION_INCREMENTAL() {
        return VERSION_INCREMENTAL;
    }

    public String getBOARD() {
        return BOARD;
    }

    public String getBOOTLOADER() {
        return BOOTLOADER;
    }

    public String getDEVICE() {
        return DEVICE;
    }

    public String getHARDWARE() {
        return HARDWARE;
    }

    public String getMANUFACTURER() {
        return MANUFACTURER;
    }

    public Open(OpenBuilder builder) {
        this.RELEASE = RELEASE;
        this.SDK_INT = SDK_INT;
        this.VERSION_CODENAME = VERSION_CODENAME;
        this.VERSION_INCREMENTAL = VERSION_INCREMENTAL;
        this.BOARD = BOARD;
        this.BOOTLOADER = BOOTLOADER;
        this.DEVICE = DEVICE;
        this.HARDWARE = HARDWARE;
        this.MANUFACTURER = MANUFACTURER;
    }
	
    //Builder Class
    public static class OpenBuilder {
 
        // required parameters
        private boolean RELEASE;
        private boolean SDK_INT;
        private boolean VERSION_CODENAME;
        private boolean VERSION_INCREMENTAL;
        private boolean BOARD;
        private boolean BOOTLOADER;
        private boolean DEVICE;
        private boolean HARDWARE;
        private boolean MANUFACTURER;

        // optional parameters


        public OpenBuilder setRELEASE(boolean RELEASE) {
            this.RELEASE = RELEASE;
            return this;
        }

        public OpenBuilder setSDK_INT(boolean SDK_INT) {
            this.SDK_INT = SDK_INT;
            return this;
        }

        public OpenBuilder setVERSION_CODENAME(boolean VERSION_CODENAME) {
            this.VERSION_CODENAME = VERSION_CODENAME;
            return this;
        }

        public OpenBuilder setVERSION_INCREMENTAL(boolean VERSION_INCREMENTAL) {
            this.VERSION_INCREMENTAL = VERSION_INCREMENTAL;
            return this;
        }

        public OpenBuilder setBOARD(boolean BOARD) {
            this.BOARD = BOARD;
            return this;
        }

        public OpenBuilder setBOOTLOADER(boolean BOOTLOADER) {
            this.BOOTLOADER = BOOTLOADER;
            return this;
        }

        public OpenBuilder setDEVICE(boolean DEVICE) {
            this.DEVICE = DEVICE;
            return this;
        }

        public OpenBuilder setHARDWARE(boolean HARDWARE) {
            this.HARDWARE = HARDWARE;
            return this;
        }

        public OpenBuilder setMANUFACTURER(boolean MANUFACTURER) {
            this.MANUFACTURER = MANUFACTURER;
            return this;
        }
		
        public Open build(){
            return new Open(this);
        }
 
    }
 
}