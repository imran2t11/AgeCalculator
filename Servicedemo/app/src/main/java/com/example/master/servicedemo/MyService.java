package com.example.master.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class MyService extends Service {
    public final IBinder mBinder = (IBinder) new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        mymethod();
        return mBinder;
    }

    public class LocalBinder {
        MyService getService() {
            return MyService.this;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void mymethod() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        Bundle b = new Bundle();
        b.putString("hey", "hello");
        intent.putExtras(b);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
