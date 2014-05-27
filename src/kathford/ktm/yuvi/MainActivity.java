package kathford.ktm.yuvi;

import kathfrod.ktm.yuvi.lib.ServerRequest;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	TextView link;
	Button engcal;
	Button bbacal;
	// for dialogue box
	// private ProgressDialog pDialog;
	// creating the json parser class
	// JSONParser jsonParser = new JSONParser();

	// JSON element for responds of php scripts
	private static final String KEY_RESPONSE = "res";
	private static final String TAG_SUCCESS = "success";
	private static final String LOGIN_URL = "http://www.yubrajpoudel.com.np/register.php?action=";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		engcal = (Button) findViewById(R.id.callEng);
		bbacal = (Button) findViewById(R.id.callBba);
		link = (TextView) findViewById(R.id.weblink);
		engcal.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:015201911"));
				startActivity(callIntent);

			}
		});
		bbacal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:015006112"));
				startActivity(callIntent);
			}
		});
		link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						CollegeWebsite.class);

				String links = " http://www.kathford.edu.np/";
				intent.putExtra("link", links);
				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.logme:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			final View custom = getLayoutInflater().inflate(
					R.layout.login_student, null);
			custom.findViewById(R.id.signup).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							startActivity(new Intent(getApplicationContext(),
									NewstudentRegister.class));

						}
					});
			builder.setView(custom)
					.setPositiveButton("login",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									String name = ((EditText) custom
											.findViewById(R.id.stu_name))
											.getText().toString().trim();
									String pass = ((EditText) custom
											.findViewById(R.id.stu_pass))
											.getText().toString().trim();

									if (name.length() > 0 || pass.length() > 0) {
										/*
										 * if(name.equals("name1") &&
										 * pass.equals("pass1")){
										 * startActivity(new
										 * Intent(getApplicationContext(),
										 * StudentRoomActivity.class)); } else{
										 * 
										 * print("Invalid id or password"); }
										 */
										new AttemptLogin(name, pass).execute();
									} else {
										Toast.makeText(getApplicationContext(),
												"field cannot be empty ",
												Toast.LENGTH_LONG).show();
									}
								}

							}).setNegativeButton("Cancel", null).create()
					.show();

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void print(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Loading...");
		return dialog;
	}

	class AttemptLogin extends AsyncTask<String, String, String> {
		boolean success = false;
		String name, password;

		public AttemptLogin(String name, String pass) {
			this.name = name;
			this.password = pass;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(1);

		}

		@Override
		protected String doInBackground(String... arg0) {
			ServerRequest req = new ServerRequest();
			return req.requestGetHttp(LOGIN_URL + "login&name=" + name
					+ "&password=" + password);
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dismissDialog(1);
			try {
				JSONObject jObj = new JSONObject(result);
				if (jObj.get(KEY_RESPONSE).equals(TAG_SUCCESS)) {
					// success = true;
					if (jObj.get("isactive").equals("1")) {
						startActivity(new Intent(getApplicationContext(),
								StudentRoomActivity.class));
					} else {
						print("Your request is in pending");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
