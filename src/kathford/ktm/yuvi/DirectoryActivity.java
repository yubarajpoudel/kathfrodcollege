package kathford.ktm.yuvi;

import java.util.ArrayList;

import kathfrod.ktm.yuvi.lib.Constants;
import kathfrod.ktm.yuvi.lib.ServerRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DirectoryActivity extends BaseActivity {
	ArrayList<CallDetails> calllist;
	BaseAdapter adapter;

	public class CallDetails {
		String fname;
		String lname;
		String subject;
		String phone; 
	}

	ArrayList<String> rowdata = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory);
		setHeader(getString(R.string.DirectoryTitle));

		calllist = new ArrayList<CallDetails>();
		adapter = new ArrayAdapter<CallDetails>(this,
				android.R.layout.activity_list_item, calllist) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.directorylist_bg, null);
				}
				// News s = new News();
				CallDetails s = calllist.get(position);
				// ((TextView)findViewById(R.id.booknum)).setText(Integer.toString(position));
				
				TextView firstname = (TextView) convertView
						.findViewById(R.id.fname);
				firstname.setText(s.fname);
				TextView lastname = (TextView) convertView
						.findViewById(R.id.lname);
				lastname.setText(s.lname);
				TextView phoned = (TextView) convertView
						.findViewById(R.id.teachphone);
				phoned.setText(s.phone);
				TextView faculty = (TextView) convertView
						.findViewById(R.id.faculty1);
				faculty.setText(s.subject);
				//TextView phones = (TextView) findViewById(R.id.teach_phone);
				//phones.setText(s.phone);
				//((TextView) findViewById(R.id.teach_phone)).setText(s.phone);

				return convertView;
			}
		};
		ListView list = (ListView) findViewById(R.id.callist);
		list.setAdapter(adapter);
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int postion, long arg3) {
				
				CallDetails a = calllist.get(postion);
				String tmp = "tel:" + a.phone; //text is the customer care number
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse(tmp));
				startActivity(callIntent);
				// TODO Auto-generated method stub
				return false;
			}
		});

		new AttemptShow().execute();

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Loading Directory List....");
		return dialog;
	}

	class AttemptShow extends AsyncTask<String, String, String> {
		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(1);
		}

		@Override
		protected String doInBackground(String... arg0) {
			ServerRequest req = new ServerRequest();
			return req.requestGetHttp(Constants.DLOGIN_URL);
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
					CallDetails resultrow = new CallDetails();
					resultrow.fname = jsondata.getString("fname");
					resultrow.lname = jsondata.getString("lname");
					resultrow.phone = jsondata.getString("phone");
					resultrow.subject = jsondata.getString("faculty");
					calllist.add(resultrow);
				}
				Log.i("DataNo", "Total size = " + jArray.length());
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			adapter.notifyDataSetChanged();
		}

	}

}
