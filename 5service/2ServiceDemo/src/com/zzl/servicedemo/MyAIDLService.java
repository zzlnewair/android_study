package com.zzl.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

/**
 * MyAIDLService
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-9
 */
public class MyAIDLService extends Service {

    private int                  mCount;
    private MyAIDLInterface.Stub myBinder = new MyAIDLInterface.Stub() {

                                              @Override
                                              public void setCount(int count) throws RemoteException {
                                                  mCount = count;
                                              }

                                              @Override
                                              public int getCount() throws RemoteException {
                                                  return mCount;
                                              }
                                          };

    @Override
    public void onCreate() {
        mCount = 0;
        Toast.makeText(this, "Service onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, Integer.toString(mCount), Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    public int getCount() {
        return mCount;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
