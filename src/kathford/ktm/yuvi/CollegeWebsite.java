package kathford.ktm.yuvi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CollegeWebsite extends Activity {
	

@SuppressLint("NewApi")
@Override

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collegewebsite);
	    
	   String path = getIntent().getExtras().getString("link","http://google.com");
	   
	   WebView browser =(WebView) findViewById(R.id.webview);
        
	   WebSettings webSettings = browser.getSettings();
	    webSettings.setJavaScriptEnabled(true);
	   browser.setWebViewClient(new WebClientClass());
	   browser.loadUrl(path);
	   
}

public class WebClientClass extends WebViewClient {
	  ProgressDialog pd = null;

	  @Override
	  public void onPageStarted(WebView view, String url, Bitmap favicon) {
	   super.onPageStarted(view, url, favicon);
	   pd = new ProgressDialog(CollegeWebsite.this);
	   pd.setTitle("Please wait");
	   pd.setMessage("Page is loading..");
	   pd.show();
	  }

	  @Override
	  public void onPageFinished(WebView view, String url) {
	   super.onPageFinished(view, url);
	   pd.dismiss();
	  }
	 }
}
	


