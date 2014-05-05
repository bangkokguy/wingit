package com.example.myapplication.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = R.string.local_service_started;

    private TextView mText;
    private Handler mHandler;
    private int i;
    private int j;

    public MyService() {
     /*   WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                50,50,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.RGBX_8888); // PixelFormat.TRANSLUCENT

        params.gravity = Gravity.CENTER;

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        TextView text = new TextView(this);
        text.setText("geci");
        wm.addView(text, params);*/
    }

    @Override
    public void onCreate() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = R.drawable.ic_launcher;
        CharSequence notiText = "Your notification from the service";
        long meow = System.currentTimeMillis();

        Notification notification = new Notification(icon, notiText, meow);

        Context context = getApplicationContext();
        CharSequence contentTitle = "Your notification";
        CharSequence contentText = "Some data has arrived!";
        //Intent notificationIntent = new Intent(this, YourActivityThatYouWantToLaunch.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        //notificationManager.notify(NOTIFICATION, notification);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                150,50,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.RGBX_8888); // PixelFormat.TRANSLUCENT

        params.gravity = Gravity.CENTER;

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        //TextView
        mText = new TextView(this);
        mText.setText("geci");
        wm.addView(mText, params);
        i = 0;
        j = 0;
        mHandler = new Handler();
        mHandler.post(mUpdate);
    }

    private Runnable mUpdate = new Runnable() {
        public void run() {
            //mText.setText("");
            mText.setTextColor(mText.getCurrentTextColor() + i);
            //mText.setText("" + i);
            mText.append("_");
            i++;
            if (j++ == 10) {j = 0;
                wm.removeView(mText);
                wm.addView(mText, params);
                mText.setTextColor(mText.getCurrentTextColor()+i);
                mText.setText("geci      ");
               } 
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
