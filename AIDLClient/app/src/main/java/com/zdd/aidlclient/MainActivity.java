package com.zdd.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zdd.aidlservice.MyAIDLService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyAIDLService myBinder = MyAIDLService.Stub.asInterface(iBinder);
            try {
                int result = myBinder.plus(3, 5);
                String toUpperCase = myBinder.toUpperCase("hello world");
                Log.d(TAG, "result " + result);
                Log.d(TAG, "uppercase " + toUpperCase);
            } catch (Exception e) {
                Log.e(TAG, "==" + e.getMessage());
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.bind_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.zdd.aidlservice.MyAIDLService");
                intent.setPackage("com.zdd.aidlservice");
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
    }
}
