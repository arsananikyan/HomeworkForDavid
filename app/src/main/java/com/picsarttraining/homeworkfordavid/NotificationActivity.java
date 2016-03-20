package com.picsarttraining.homeworkfordavid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {


    private TextView secondsEditText;
    private View sendNotificationButton;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private boolean downloadingNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        downloadingNow = false;
        secondsEditText = (TextView) findViewById(R.id.seconds_edit_text);
        sendNotificationButton =  findViewById(R.id.send_notification_button);
        sendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!downloadingNow) {
                    downloadingNow = true;
                    imitateDownloading(Integer.parseInt(secondsEditText.getText().toString()));
                }
            }
        });
    }
    private void imitateDownloading(final int seconds)
    {
        final int millis = seconds * 1000;
        final double waitingMillis = millis/20.0;
        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final int id = 5;
        Intent resultIntent = new Intent(this, SwipeRefreshActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.side_nav_bar)
                        .setContentTitle("My notification")
                        .setContentText("Downloading")
                        .setContentIntent(resultPendingIntent);

        Thread downloadingThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int incr = 0; incr <= 100; incr+=5) {

                    mBuilder.setProgress(100, incr, false);

                    mNotifyManager.notify(id, mBuilder.build());

                    try {
                        Thread.sleep((long) waitingMillis);
                    } catch (InterruptedException e) {
                        Log.d("NotificationActivity", "sleep failure");
                    }
                }

                mBuilder.setContentText("Download complete")
                        .setProgress(0, 0, false);
                mNotifyManager.notify(id, mBuilder.build());
                downloadingNow = false;
            }
        });
        downloadingThread.start();
    }
}
