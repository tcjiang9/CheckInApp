package io.intrepid.tjiang.checkinapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        restartActivity(context);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://hooks.slack.com")
                .build();
        SlackService slackService = restAdapter.create(SlackService.class);

        String userInput = ":bulbasaur: :squirtle:";
        SlackMessage slackMessage = new SlackMessage();
        makeSlackMessage(slackMessage, userInput, "TC Jiang", ":charmander:");

        slackService.postSlackMessage(BuildConfig.webhook_url, slackMessage, new Callback<Object>() {
            @Override
            public void success(Object object, Response response) {
                Log.v("BroadCastReceiver", "Successfully posted");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.v("BroadCastReceiver", "failed posted");
            }
        });
    }

    private void restartActivity(Context context) {
        Intent restartIntent = new Intent(context, MainActivity.class);
        restartIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(restartIntent);
    }

    private void makeSlackMessage(SlackMessage slackMessage, String text, String username, String icon) {
        slackMessage.text = text;
        slackMessage.icon_emoji = icon;
        slackMessage.username = username;
    }
}
