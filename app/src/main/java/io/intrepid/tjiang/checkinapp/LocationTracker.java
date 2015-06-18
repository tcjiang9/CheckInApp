package io.intrepid.tjiang.checkinapp;

import android.content.Context;
import android.location.Location;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

public class LocationTracker implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener   {

    static final String LOGTAG = LocationTracker.class.getSimpleName();

    private OnLocationArrivedListener callback;

    private LocationRequest locationRequest;
    private Location lastLocation;
    private GoogleApiClient googleApiClient;

    private final double INTREPID_LONG = 42.366982;
    private final double INTREPID_LAT = -71.080364;

    private int testCounter = 0;

    public LocationTracker(Context context) {
        this.googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        callback = (OnLocationArrivedListener) context;
    }

    public interface OnLocationArrivedListener {
        public void onLocationArrived();
    }

    /**
     * Modifies lastLocation field to contain latitude and longitude at time of method call
     */
    public GoogleApiClient getGoogleApiClient() {
        return this.googleApiClient;
    }

    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    private boolean checkLocation() {
        if (testCounter == 3) { //this is a placeholder for determining if i'm within 50 ft of intrepid
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(LOGTAG, location.toString());
        testCounter ++;
        Log.v(LOGTAG, String.valueOf(testCounter));
        lastLocation = location;
        if (checkLocation()){
            callback.onLocationArrived();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public int getTestCounter() {
        return testCounter;
    }

    public void resetCounter() {
        testCounter = 0;
    }
}

