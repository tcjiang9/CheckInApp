package io.intrepid.tjiang.checkinapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent restartIntent = new Intent(context, MainActivity.class);
        restartIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(restartIntent);

        Log.v("BroadCastReciever", "Received Intent");
        Gson gson = new GsonBuilder().create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://hooks.slack.com")
                //.setConverter(new GsonConverter(gson))
                .build();

        Log.v("BroadCastReceiver", "here");

        String userInput = "TC is here";
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.text = userInput;
        SlackService slackService = restAdapter.create(SlackService.class);
        slackService.postSlackMessage(slackMessage, new Callback<Void>() {
            @Override
            public void success(Void aVoid, Response response) {
                Log.v("BroadCastReceiver", "Successfully posted");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.v("BroadCastReceiver", "failed posted");
            }
        });
    }
}
