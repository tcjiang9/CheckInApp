package io.intrepid.tjiang.checkinapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service {

    static final String LOGTAG = LocationService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(LOGTAG, "this service has started");
        LocationTracker locationTracker = new LocationTracker(this);
        locationTracker.updateLocation();
        //Todo: execute in intervals on a background thread.
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
