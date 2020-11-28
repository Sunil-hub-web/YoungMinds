package com.egk.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;

import com.egk.egk.Egk_nav;
import com.egk.egk.R;

import org.json.JSONObject;

import androidx.core.app.NotificationCompat;


public class NotificationHelper {


    private Context mContext;
    public static NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    Intent resultIntent;
    public static final String NOTIFICATION_CHANNEL_ID = "com.egk.egk";


    public NotificationHelper(Context context) {
        mContext = context;
    }

    /**
     * Create and push the notification
     */
    public void createNotification(String title, String message)
    {



        /**Creates an explicit intent for an Activity in your app**/
        resultIntent = new Intent(mContext, Egk_nav.class);
        resultIntent.putExtra("intenti", "home");
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

//        Drawable drawable = ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher_round);
//
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        mBuilder = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_stat_name);
//        mBuilder.setLargeIcon(bitmap);
        mBuilder.setContentTitle(title)
//                .setTimeoutAfter(20000)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentText(message)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            Uri notificationsound = Uri.parse("android.resource://com.voom.ranjeet.boomdriver.fcm" + "/" + R.raw.mystery);

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(566132665 /* Request Code */, mBuilder.build());


    }


}
