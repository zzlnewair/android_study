package zzl.retrofitdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        PhoneService phoneService = retrofit.create(PhoneService.class);
        phonenum=et.getText().toString();
        if((phonenum==null)||(phonenum==""))
            return ;
        Call<PhoneResult> phoneResultCall = phoneService.getResult(API_KEY, phonenum);
        phoneResultCall.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()) {
                    Log.e("zzl", "获取成功");
					 mProgressBar.setVisibility(View.GONE);
                    PhoneResult phoneResult = response.body();
                    if (phoneResult != null) {
                        PhoneResult.RetDataEntity retDataEntity = phoneResult.getRetData();
                     //   Toast.makeText(context, retDataEntity.getCity(), Toast.LENGTH_SHORT).show();
                        tv.setText(phonenum+" 归属地:"+phoneResult.getRetData().getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {
                Log.e("zzl", "获取失败");
				mProgressBar.setVisibility(View.GONE);
            }
        });
    }

}