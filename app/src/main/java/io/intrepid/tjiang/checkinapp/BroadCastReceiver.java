package io.intrepid.tjiang.checkinapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Todo: receive the Pending Intent the notification sends out on click
        Log.v("BroadCastReciever", "Received Intent");
        //class postToSlack class = new class
        //postToSlack class.start
    }
}
