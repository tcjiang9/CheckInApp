package io.intrepid.tjiang.checkinapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
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
        //Todo: send notification
        locationTracker.getGoogleApiClient().disconnect();
    }

    @Override
    public void onLocationArrived() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent postIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        Notification arrivedNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.intrepid_logo)
                .setContentTitle("You have arrived at Intrepid!")
                .setContentText("Touch to post to Slack")
                .setContentIntent(postIntent)
                .build();
        sendNotification(arrivedNotification);
        stopSelf();
    }

    private void sendNotification(Notification notification) {
        int notificationID = 001;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notification);
    }
}
