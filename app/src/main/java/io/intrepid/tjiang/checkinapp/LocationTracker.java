package io.intrepid.tjiang.checkinapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class LocationTracker implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener   {

    static final String LOGTAG = LocationTracker.class.getSimpleName();

    private GoogleApiClient googleApiClient;
    private Location lastLocation;

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

    public GoogleApiClient getGoogleApiClient() {
        return this.googleApiClient;
    }
}

