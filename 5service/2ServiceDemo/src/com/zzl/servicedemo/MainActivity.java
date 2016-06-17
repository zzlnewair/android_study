package com.zzl.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.zzl.servicedemo.MyService.MyBinder;
public class MainActivity extends Activity {

	private MyService         myService;
    private Intent            myServiceIntent;
   
    private Intent            myIntentServiceIntent;
    private Intent            myIntentService2Intent;

    private Button            startServiceBtn;
    private Button            stopServiceBtn;
    private Button            startIntentServiceBtn;

    private Button            boundServiceBtn;
    private Button            operateBoundServiceBtn;
    private Button            getBoundServiceProBtn;
    private Button            unboundServiceBtn;

    private Button            startAIDLServiceBtn;
    private Button            stopAIDLServiceBtn;


    private ServiceConnection con = new ServiceConnection() {

                                      /**
                                       * Called when a connection to the Service has been lost
                                       */
                                      @Override
                                      public void onServiceDisconnected(ComponentName name) {
                                          Toast.makeText(getBaseContext(), "Service disconnect", Toast.LENGTH_SHORT).show();
                                      }

                                      /**
                                       * Called when a connection to the Service has been established,
                                       */
                                      @Override
                                      public void onServiceConnected(ComponentName name, IBinder service) {
                                          myService = ((MyBinder)service).getService();
                                          Toast.makeText(getBaseContext(), "Service Connect", Toast.LENGTH_SHORT).show();
                                      }
                                  };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        myServiceIntent = new Intent(this, MyService.class);
      
        myIntentServiceIntent = new Intent(this, MyIntentService.class);
        myIntentService2Intent = new Intent(this, MyIntentService.class);

        startServiceBtn = (Button)findViewById(R.id.start_general_service);
        
        startServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myServiceIntent);
            }
        });

        stopServiceBtn = (Button)findViewById(R.id.stop_general_service);
        stopServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                stopService(myServiceIntent);
            }
        });

        startIntentServiceBtn = (Button)findViewById(R.id.start_intent_service);
        startIntentServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myIntentServiceIntent);
                startService(myIntentService2Intent);
            }
        });

        boundServiceBtn = (Button)findViewById(R.id.bound_service);
        boundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                bindService(myServiceIntent, con, Context.BIND_AUTO_CREATE);
            }
        });

        operateBoundServiceBtn = (Button)findViewById(R.id.operate_bound_service);
        operateBoundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    Toast.makeText(MainActivity.this, R.string.operate_value_success+
                    		Integer.toString(myService.increaseCount()),Toast.LENGTH_SHORT).show();
                } else {
                	Toast.makeText(MainActivity.this, R.string.bind_service_tip, Toast.LENGTH_SHORT).show();
                }
            }
        });

        getBoundServiceProBtn = (Button)findViewById(R.id.get_bound_service_pro);
        getBoundServiceProBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                	Toast.makeText(MainActivity.this, R.string.operate_value_tip+Integer.toString(myService.getCount()),Toast.LENGTH_SHORT).show();
                } else {
                	Toast.makeText(MainActivity.this, R.string.bind_service_tip, Toast.LENGTH_SHORT).show();
                }
            }
        });

        unboundServiceBtn = (Button)findViewById(R.id.unbound_service);
        unboundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    unbindService(con);
                    myService = null;
                }
            }
        });

     
		
	}

	
}
