package com.zzl.control;



import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
    WebView webView = null;
    static final String MIME_TYPE = "text/html";
    static final String ENCODING = "utf-8";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.webview);
	
	webView = (WebView) findViewById(R.id.webview);
	webView.loadDataWithBaseURL(null,"<a href='http://blog.csdn.net/xys289187120'>ª∂”≠∑√Œ ”ÍÀ…MOMOµƒ≤©øÕ</a>", MIME_TYPE, ENCODING, null);
	super.onCreate(savedInstanceState);
    }
}
