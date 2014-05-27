package kathford.ktm.yuvi;

import kathfrod.ktm.yuvi.lib.Constants;
import kathfrod.ktm.yuvi.lib.ServerRequest;

import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewstudentRegister extends BaseActivity {
	private static final String LOGIN_URL = "http://www.yubrajpoudel.com.np/register.php?action=";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newstudent);
		setHeader(getString(R.string.Register));
		((Button) findViewById(R.id.register))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						String name = ((EditText) findViewById(R.id.name))
								.getText().toString().trim();
						String lname = ((EditText) findViewById(R.id.lname))
								.getText().toString().trim();
						String pass = ((EditText) findViewById(R.id.pass))
								.getText().toString().trim();
						String Cpass = ((EditText) findViewById(R.id.cpass))
								.getText().toString().trim();
						String faculty = ((Spinner) findViewById(R.id.course))
								.getSelectedItem().toString().trim();
						if (name != "" && pass != "" && Cpass != ""
								&& faculty != "") {
							if (pass.equals(Cpass)) {
								new Attemptsave(name,lname, pass, faculty).execute();
							} else {
								Toast.makeText(getApplicationContext(),
										"Password Not Matched",
										Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Field is blank", Toast.LENGTH_SHORT)
									.show();
						}
					}
				});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Loading...");
		return dialog;
	}

	class Attemptsave extends AsyncTask<String,String, String> {
		boolean success = false;
		String name, password, faculty,lname;

		public Attemptsave(String name,String lname, String pass, String faculty) {
			this.name = name;
			this.lname=lname;
			this.password = pass;
			this.faculty = faculty;
			
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(1);

		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			ServerRequest req = new ServerRequest();
			String url = LOGIN_URL + "add&name=" + name + "&lname="
					+ lname + "&password=" + password + "&faculty=" + faculty;

			// url = URLEncoder.encode(url);
			return req.requestGetHttp(url);

		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dismissDialog(1);
			try {
				JSONObject jObj = new JSONObject(result);
				if (jObj.get(Constants.KEY_RESPONSE).equals(
						Constants.TAG_SUCCESS)) {

					// startActivity(new Intent(getApplicationContext(),
					// StudentRoomActivity.class));
					((TextView) findViewById(R.id.info))
							.setText("Hi "
									+ name
									+ "\nYour Id Has Been On Pending For The Verification It make take 2 working days for the verification thanks");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
