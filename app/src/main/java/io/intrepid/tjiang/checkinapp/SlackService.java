package io.intrepid.tjiang.checkinapp;

import retrofit.http.POST;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface SlackService {

    @POST("/services/{webhook_url}")
    void postSlackMessage(@Path(value = "webhook_url", encode = false) String urlkey,
                          @Body SlackMessage slackMessage,
                          Callback<Object> callback);
}