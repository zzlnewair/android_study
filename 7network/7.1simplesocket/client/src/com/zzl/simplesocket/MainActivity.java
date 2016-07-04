package com.zzl.simplesocket;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.net.InetSocketAddress;  
import java.net.Socket;  
import java.net.SocketTimeoutException;  
  
import android.app.Activity;  
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;  
import android.view.Menu;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.TextView;  
  
public class MainActivity extends Activity {  
    Socket socket = null;  
    String buffer = "";  
    TextView txt1;  
    Button send;  
    EditText ed1;  
    String geted1;  
    public Handler myHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == 0x11) {  
                Bundle bundle = msg.getData();  
                txt1.append("server:"+bundle.getString("msg")+"\n");  
            }  
        }  
  
    };  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        txt1 = (TextView) findViewById(R.id.txt1);  
        send = (Button) findViewById(R.id.send);  
        ed1 = (EditText) findViewById(R.id.ed1);  
        send.setOnClickListener(new OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
                geted1 = ed1.getText().toString();  
                txt1.append("client:"+geted1+"\n");  
                //�����߳� ����������ͺͽ�����Ϣ  
                new MyThread(geted1).start();  
            }  
        });  
  
    }  
  
    class MyThread extends Thread {  
  
        public String txt1;  
  
        public MyThread(String str) {  
            txt1 = str;  
        }  
  
        @Override  
        public void run() {  
            //������Ϣ  
            Message msg = new Message();  
            msg.what = 0x11;  
            Bundle bundle = new Bundle();  
            bundle.clear();  
            try {  
                //���ӷ����� ���������ӳ�ʱΪ5��  
                socket = new Socket();  
                socket.connect(new InetSocketAddress("192.168.1.101", 5580), 5000);  
                //��ȡ���������  
                OutputStream ou = socket.getOutputStream();  
                BufferedReader bff = new BufferedReader(new InputStreamReader(  
                        socket.getInputStream()));  
                //��ȡ������������Ϣ  
                String line = null;  
                buffer="";  
                while ((line = bff.readLine()) != null) {  
                    buffer = line + buffer;  
                }  
                  
                //�������������Ϣ  
                ou.write(("android �ͻ���"+geted1).getBytes("gbk"));  //"gbk"
               
                ou.flush();  
                bundle.putString("msg", buffer.toString());  
                msg.setData(bundle);  
                //������Ϣ �޸�UI�߳��е����  
                myHandler.sendMessage(msg);  
                //�رո������������  
                bff.close();  
                ou.close();  
                socket.close();  
            } catch (SocketTimeoutException aa) {  
                //���ӳ�ʱ ��UI������ʾ��Ϣ  
                bundle.putString("msg", "����������ʧ�ܣ����������Ƿ��");  
                msg.setData(bundle);  
                //������Ϣ �޸�UI�߳��е����  
                myHandler.sendMessage(msg);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);  
        return true;  
    }  
  
}  