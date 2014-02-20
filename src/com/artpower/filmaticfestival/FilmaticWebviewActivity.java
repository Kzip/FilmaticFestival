package com.artpower.filmaticfestival;



import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
 
public class FilmaticWebviewActivity extends Activity {
 
	private WebView webView;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
 
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://client.frankshanley.com/filmatic/");
 
	}
 
}
