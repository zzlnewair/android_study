package zzl.retrofitdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 主界面
 *
 * @author zzl
 */
public class MainActivity extends Activity {

    private static final String BASE_URL = "http://apis.baidu.com/";
    private static final String API_KEY = "2ac3c7ce18f6226d267b55603827ec1b";//这里输入Api Key

    private Context context;

    private EditText et;
    private TextView tv;
    private ProgressBar mProgressBar;

    String phonenum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        context = this;
        et = (EditText) findViewById(R.id.edittext_activity_main);
        tv =(TextView) findViewById(R.id.tv);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        findViewById(R.id.button_activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    private void query() {
        mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        PhoneService phoneService = retrofit.create(PhoneService.class);
        phonenum=et.getText().toString();
        if((phonenum==null)||(phonenum==""))
            return ;
        phoneService.getResult(API_KEY,phonenum )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhoneResult>() {
                    @Override
                    public void onCompleted() {
                        Log.e("zzl", "获取成功");
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "获取成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("zzl", "获取失败");

                    }

                    @Override
                    public void onNext(PhoneResult phoneResult) {
                        tv.setText(phonenum+" 归属地:"+phoneResult.getRetData().getCity());
                    }
                });

    }

}