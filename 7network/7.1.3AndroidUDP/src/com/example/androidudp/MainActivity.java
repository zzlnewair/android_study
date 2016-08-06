package com.example.androidudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText edtSendInfo,edtReceiveInfo,edtSendIP,edtSendPort,edtReceivePort; 
	private CheckBox chkSendHex,chkReceiveHex;
	private String sendInfo,receiveInfo;
	private byte[] buf;	
	private Button btnListen;
	private Boolean listenStatus=false;
	private DatagramSocket socket;
	public Handler receiveHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSendInfo = (EditText) findViewById(R.id.edtSendInfo);
        edtReceiveInfo=(EditText)findViewById(R.id.edtReceiveInfo);
    	edtSendIP=(EditText)findViewById(R.id.edtSendIP);
    	edtSendPort=(EditText)findViewById(R.id.edtSendPort);
    	edtReceivePort=(EditText)findViewById(R.id.edtReceivePort);
    	chkSendHex=(CheckBox)findViewById(R.id.chkSendHex);
    	chkReceiveHex=(CheckBox)findViewById(R.id.chkReceiveHex);
    	btnListen=(Button)findViewById(R.id.btnListen);
		
    	receiveHandler = new Handler()
		{										
			  public void handleMessage(Message msg)										
			  {		
				  edtReceiveInfo.setText(receiveInfo);	
			  }									
		};
    }
    //UDP数据发送线程
	public class udpSendThread extends Thread
	{
		@Override
		public void run()
		{
			Log.d("send","buf="+edtSendInfo.getText().toString());
			try 
			{
				if(chkSendHex.isChecked())
				{
					buf=hexStringToBytes (edtSendInfo.getText().toString());
				}
				else
				{
					buf=edtSendInfo.getText().toString().getBytes();
				}
				if(listenStatus==false)
				{
					socket = new DatagramSocket(Integer.parseInt(edtSendPort.getText().toString()));
				}
				InetAddress serverAddr = InetAddress.getByName(edtSendIP.getText().toString());
				DatagramPacket outPacket = new DatagramPacket(buf, buf.length,serverAddr, Integer.parseInt(edtSendPort.getText().toString()));  
				socket.send(outPacket);
				socket.close();
				Log.d("send","buf="+edtSendInfo.getText().toString());
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block 			
			}  
		}
	}
	//UDP数据接收线程
	public class udpReceiveThread extends Thread
	{	
		@Override  
		public void run() 
		{  
			try 
			{  
				socket = new DatagramSocket(Integer.parseInt(edtReceivePort.getText().toString()));
				listenStatus=true;
				while(listenStatus)
				{
					byte[] inBuf= new byte[1024];
					DatagramPacket inPacket=new DatagramPacket(inBuf,inBuf.length);
					socket.receive(inPacket);
					if(chkReceiveHex.isChecked())
					{
						receiveInfo = bytes2HexString(inBuf,inPacket.getLength());	
					}
					else
					{
						receiveInfo = new String (inPacket.getData());
					}
                	Message msg = new Message();
                	receiveHandler
                	.sendMessage(msg);				
				}				
			} catch (Exception e) 
			{  
				// TODO Auto-generated catch block 
			}  
		}  
	}
	//发送按钮单击事件
	public void SendButtonClick(View source)
	{
		new udpSendThread().start();  
	}
	//监听按钮点击事件
	public void ListenButtonClick(View source)
	{	
		if(listenStatus==false)
		{
			btnListen.setText("停止监听");
			new udpReceiveThread().start();
		}
		else
		{
			btnListen.setText("开始监听");	
			socket.close();
			listenStatus=false;
			new udpReceiveThread().interrupt();
		}
	}
	//16进制字符串转byte[]
    public static byte[] hexStringToBytes(String str)
    {  
        if (str == null || str.equals(""))
        {  
            return null;  
        }  
        String hexString=str.replace(" ","");  
        hexString = hexString.toUpperCase();  
        int length = hexString.length() / 2;  
        char[] hexChars = hexString.toCharArray();  
        byte[] d = new byte[length];  
        for (int i = 0; i < length; i++) 
        {  
            int pos = i * 2;  
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
        }  
        return d;  
    }
    private static byte charToByte(char c) 
    {  
        return (byte) "0123456789ABCDEF".indexOf(c);  
    }
    //byte[]转16进制字符串
	public static String bytes2HexString(byte[] b,int len) 
	{  
        String ret = "";  
        for (int i = 0; i < len; i++)
        {  
        	String hex = Integer.toHexString(b[ i ] & 0xFF);  
        	if (hex.length() == 1) 
        	{  
        		hex = '0' + hex;  
        	}  
        	ret += hex.toUpperCase()+" ";  
        }  
        return ret;  
    } 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
