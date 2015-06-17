package io.intrepid.tjiang.checkinapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

public class LocationService extends Service implements LocationTracker.OnLocationArrivedListener {
    private Timer timer;
    private LocationService callback;
    LocationTracker locationTracker;

    static final String LOGTAG = LocationService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(LOGTAG, "this service has started");
        locationTracker = new LocationTracker(this);
        locationTracker.getGoogleApiClient().connect();

        Log.v(LOGTAG, String.valueOf(locationTracker.getTestCounter()));
        if (locationTracker.getTestCounter() == 3) {
            locationTracker.resetCounter();
            //Todo: initialize a notification
        }
        //Todo: execute in intervals on a background thread.
        return Service.START_STICKY;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        locationTracker.getGoogleApiClient().disconnect();
    }

    @Override
    public void onLocationArrived() {
        //TODO
    }
}
