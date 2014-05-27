package kathfrod.ktm.yuvi.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ServerRequest {

	public int METHOD_GET = 1;
	public int METHOD_POST = 2;
	

	/**
	 * post parameter contains List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 * nameValuePairs.add(new BasicNameValuePair("image",image_str));
	 **/
	
	public String requestGetHttp(String url) {
		return requestHttp(METHOD_GET, url, null);
	}
	
	public String requestGetHttp(String url, List<NameValuePair> parameters) {
		return requestHttp(METHOD_GET, url + "?" + URLEncodedUtils.format(parameters, "utf-8"), null);
	}

	public String requestPostHttp(String url, List<NameValuePair> parameters) {
		return requestHttp(METHOD_POST, url, parameters);
	}

	private String requestHttp(int method, String url, List<NameValuePair> postParameters) {
		Log.i("Contacto","url = " + url);
		BufferedReader in = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			if (method == METHOD_POST) {
				HttpPost postrequest = new HttpPost(url);
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
						postParameters);
				postrequest.setEntity(formEntity);
				HttpResponse postresponse = client.execute(postrequest);
				in = new BufferedReader(new InputStreamReader(postresponse
						.getEntity().getContent()));
			} else {
				HttpGet request = new HttpGet(new URI(url));
				HttpResponse response = client.execute(request);
				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
			}
			StringBuffer sb = new StringBuffer("");
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
			in.close();
			Log.i("EN","result = " + sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"type\":\"connection error\"}";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean isNetworkConnected(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false; // There are no active networks.
		} else return true;
	}
	
}
