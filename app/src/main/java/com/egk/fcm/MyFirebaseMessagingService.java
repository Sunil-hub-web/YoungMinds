package com.egk.fcm;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String title, body, intentcheck, jobid;
    NotificationHelper NotificationHelper;
    LocalBroadcastManager broadcaster;
    public static Handler handler = new Handler();

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...


        NotificationHelper = new NotificationHelper(this);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FCMService", "From: " + remoteMessage.getFrom());


        try {
            title = remoteMessage.getNotification().getTitle();
            body = remoteMessage.getNotification().getBody();
            NotificationHelper.createNotification(title, body);
        }catch (Exception e){

        }


        // Check if message contains a notification payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e("jodnm1", "Data Payload: " + remoteMessage.getData().toString());

            try {


                Log.d("PayFCMDAta", "Data Payload: " + remoteMessage.getData().get("payload"));

                JSONObject data = new JSONObject(remoteMessage.getData().get("payload"));

                handleDataMessage(data);

            } catch (Exception e) {
                Log.e("jodnm2", "Exception: " + e.getMessage());
            }
        }else{
            String jdt = "{\"activity\":\"\"}";
            try {
                JSONObject data = new JSONObject(jdt);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }




        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


    @Override
    public void onNewToken(String token) {
        Log.d("FCMID", "Refreshed token: " + token);

    }


    private void handleDataMessage(JSONObject json) {
        try {
            String activity = json.getString("activity");

            String jd = String.valueOf(json);
//            Intent intent = new Intent("MyData");
//            intent.putExtra("Status", "Received");
//            broadcaster.sendBroadcast(intent);

            Intent in = new Intent();
            in.putExtra("Status",jd);
            in.setAction("NOW");
//sendBroadcast(in);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(in);


            NotificationHelper.createNotification(title, body);

        } catch (Exception e) {
            Log.e("jodnm3", "push json: " + json.toString());

            Log.e("jodnm5", "Exception: " + e.getMessage());
        }
    }


}
