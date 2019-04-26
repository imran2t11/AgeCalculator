package com.example.master.servicedemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context;
    BroadcastReceiver updateUIReciver;
public MyService myboundService;
String val;
ServiceConnection serviceConnection=new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        myboundService = ((MyService.LocalBinder)service).getService();
        Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        myboundService = null;
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction("service.to.activity.transfer");
        updateUIReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //UI update here
                if (intent != null)
                    Toast.makeText(context, intent.getStringExtra("number").toString(), Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(updateUIReciver, filter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(myboundService==null)
        {
            doBindService();
            Intent myIntent = getIntent(); // this getter is just for example purpose, can differ
            if (myIntent !=null && myIntent.getExtras()!=null)
                val = myIntent.getExtras().getString("hey");
            Toast.makeText(MainActivity.this, "Paisi" +val, Toast.LENGTH_SHORT).show();
        }
    }

    public void doBindService() {
        Intent intent = null;
        intent = new Intent(this, MyService.class);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        }

    @Override
    protected void onPause() {
        //FIXME put back

        Log.d("activity", "onPause");
        if (myboundService != null) {
            unbindService(serviceConnection);
            myboundService = null;
        }
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myboundService = null;
    }
}
