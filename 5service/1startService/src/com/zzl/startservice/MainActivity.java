package com.zzl.startservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import com.zzl.startservice.R;

public class MainActivity extends Activity {

    private Intent intent;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, MyService.class);
    }


    public void click(View v){
    	//显式启动服务
    	startService(intent);
    }
    public void click2(View v){
    	//关闭服务

    	stopService(intent);
    }
    
}
