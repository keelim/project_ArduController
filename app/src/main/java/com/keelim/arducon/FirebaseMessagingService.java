package com.keelim.arducon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FirebaseMessagingService extends Service {
    public FirebaseMessagingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}