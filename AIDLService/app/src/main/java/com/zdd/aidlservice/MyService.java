package com.zdd.aidlservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blankj.ALog;

/**
 * Project: AIDLService
 * Created by lucky on 2017/11/30.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

//    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        ALog.d(TAG, "onCreate" + Process.myPid());

        /**
         * 前台service
         */
//        Notification.Builder builder=new Notification.Builder(this);
//        builder.setSmallIcon(R.mipmap.ic_launcher).setTicker("有通知到来").setWhen(System.currentTimeMillis());
//
//        Intent notificationIntent=new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
//        builder.setContentIntent(pendingIntent);
//
//        Notification notification = builder.build();
//        startForeground(1,notification);


        /**
         *模拟耗时操作
         */
//
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ALog.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ALog.d(TAG, "onDestroy");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ALog.d(TAG, "onBind");
        return mBinder;
    }

    class MyBinder extends Binder {
        public void startDownload() {
            ALog.d(TAG, "startDownload  executed");
        }
    }

    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            ALog.d(TAG,"service plus");
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            ALog.d(TAG,"service toUpperCase");
            if (null != str) {
                return str.toUpperCase();
            }
            return null;
        }
    };
}
