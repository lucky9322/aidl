package com.zdd.aidlservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.ALog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.start_service)
    Button startService;
    @BindView(R.id.stop_service)
    Button stopService;
    @BindView(R.id.bind_service)
    Button bindService;
    @BindView(R.id.unbind_service)
    Button unbindService;

//    private MyService.MyBinder myBinder;
    private MyAIDLService myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ALog.d(TAG, Process.myPid());
    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.bind_service, R.id.unbind_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                startService(new Intent(MainActivity.this, MyService.class));
                break;
            case R.id.stop_service:
                stopService(new Intent(MainActivity.this, MyService.class));
                break;
            case R.id.bind_service:
//              BIND_AUTO_CREATE 表示在activity和service建立关联后自动创建service，这会使得MyService中的onCreate方法得到执行，但onStartCommand方法不会执行。
                bindService(new Intent(MainActivity.this, MyService.class), connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ALog.d(TAG, "onServiceConnected " + Process.myPid());
            myBinder = MyAIDLService.Stub.asInterface(iBinder);
//            myBinder.startDownload();
            try{
                int result = myBinder.plus(3, 5);
                String toUpperCase = myBinder.toUpperCase("hello world");
                ALog.d(TAG,"result "+result);
                ALog.d(TAG,"uppercase "+toUpperCase);
            }catch (Exception e){
                ALog.e(TAG,e.getMessage());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


}
