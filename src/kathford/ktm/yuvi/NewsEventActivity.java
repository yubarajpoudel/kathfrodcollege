package kathford.ktm.yuvi;

import java.util.ArrayList;

import kathfrod.ktm.yuvi.lib.Constants;
import kathfrod.ktm.yuvi.lib.ServerRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class NewsEventActivity extends BaseActivity {
	ArrayList<News> newsfeed;
	BaseAdapter adapter;
    int skip=0;
    boolean request=false;
	public class News {
		String Message;
		String dates;
	}

	ArrayList<String> rowdata = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsevent);
		setHeader(getString(R.string.NewsEventTitle));

		newsfeed = new ArrayList<News>();
		adapter = new ArrayAdapter<News>(this,
				android.R.layout.activity_list_item, newsfeed) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.newslist_bg, null);
				}
				// News s = new News();
				News s = newsfeed.get(position);
				// ((TextView)findViewById(R.id.booknum)).setText(Integer.toString(position));
				TextView name = (TextView) convertView
						.findViewById(R.id.NewsText);
				name.setText(s.Message);
				TextView authors = (TextView) convertView
						.findViewById(R.id.DateText);
				authors.setText(s.dates);

				return convertView;
			}
		};
		ListView list = (ListView) findViewById(R.id.eventlist);
		list.setAdapter(adapter);

		new AttemptShow(skip).execute();
		
		((Button) findViewById(R.id.loadmore)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				request=true;
				if(request)
				{
				skip=skip+10;
				new AttemptShow(skip).execute();
				}
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Loading New Events....");
		return dialog;
	}

	class AttemptShow extends AsyncTask<String, String, String> {
		
		private int skips;

		public  AttemptShow(int skips)
		{
			this.skips=skips;
		}
		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(1);
		}

		@Override
		protected String doInBackground(String... arg0) {
			String path = Constants.LOGIN_URL+skips;
			ServerRequest req = new ServerRequest();
			return req.requestGetHttp(path);
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dismissDialog(1);
			try {
				Log.i("NewsEvent", "Result = " + result);
				JSONObject jObj = new JSONObject(result);
				JSONArray jArray = jObj.getJSONArray("data");
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject jsondata = jArray.getJSONObject(i);
					News resultrow = new News();
					resultrow.Message = jsondata.getString("newsfeed");
					resultrow.dates = jsondata.getString("date");
					newsfeed.add(resultrow);
				}
				Log.i("NewsEvent", "Total size = " + jArray.length());
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			adapter.notifyDataSetChanged();
			request=false;
		}

	}

}
