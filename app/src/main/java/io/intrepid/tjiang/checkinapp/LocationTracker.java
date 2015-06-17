package io.intrepid.tjiang.checkinapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.TimerTask;

public class LocationTracker extends TimerTask implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener   {

    static final String LOGTAG = LocationTracker.class.getSimpleName();

    private Location lastLocation;
    private GoogleApiClient googleApiClient;
    private int testCounter = 0;

    public LocationTracker(Context context) {
        this.googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);
        Log.v(LOGTAG, "This has connected");
        if (lastLocation != null) {
            Log.v(LOGTAG, String.valueOf(lastLocation.getLatitude()));
            Log.v(LOGTAG, String.valueOf(lastLocation.getLongitude()));
        }
        checkLocation();
        googleApiClient.disconnect();
    }

    private void checkLocation() {
        if (testCounter == 3) { //this is a placeholder for determining if i'm within 50 ft of intrepid
            //Todo send notification to service to stop self
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void run() {
        Log.v(LOGTAG, "This has run");
        testCounter++;
        updateLocation();
    }

    /**
     * Modifies lastLocation field to contain latitude and longitude at time of method call
     */
    public void updateLocation() {
        this.googleApiClient.connect();
    }

    public int getTestCounter() {
        return testCounter;
    }

    public void resetCounter() {
        testCounter = 0;
    }
}

