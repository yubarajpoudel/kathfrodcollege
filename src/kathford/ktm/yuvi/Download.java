package kathford.ktm.yuvi;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import kathfrod.ktm.yuvi.lib.Constants;
import kathfrod.ktm.yuvi.lib.ServerRequest;
import kathfrod.ktm.yuvi.pushnitify.AlertDialogManager;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Download extends Activity {

	// button to show progress dialog
	Button btnShowProgress;
	
	// Progress Dialog
	private ProgressDialog pDialog;
	//ImageView my_image;
	// Progress dialog type (0 - for Horizontal progress bar)
	public static final int progress_bar_type = 0; 
	 AlertDialogManager alert = new AlertDialogManager();
	
	// File url to download

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);

		// show progress bar button
		btnShowProgress = (Button) findViewById(R.id.btnProgressBar);
		// Image view to show image after downloading
		//my_image = (ImageView) findViewById(R.id.my_image);
		/**
		 * Show Progress bar click event
		 * */
		btnShowProgress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String course = ((Spinner) findViewById(R.id.courses)).getSelectedItem().toString().trim();
				String category =((Spinner)findViewById(R.id.catg)).getSelectedItem().toString().trim();
				String semester =((Spinner)findViewById(R.id.sems)).getSelectedItem().toString().trim();
				//String url = Constants.file_url+"course="+course+"&category="+category+"&semester="+semester;
				// starting new Async Task
				//new DownloadFileFromURL().execute(url);
				new AttemptAccess(course,category,semester).execute();
			}
		});
	}

	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case progress_bar_type:
			pDialog = new ProgressDialog(this);
			pDialog.setMessage("Downloading file. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setCancelable(true);
			pDialog.show();
			return pDialog;
		default:
			return null;
		}
	}

	/*
	class DownloadFileFromURL extends AsyncTask<String, String, String> {
	 

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(progress_bar_type);
		}

		
		@Override
		protected String doInBackground(String... f_url) {
			int count;
	        try {
	            URL url = new URL(f_url[0]);
	            URLConnection conection = url.openConnection();
	            conection.connect();
	            // getting file length
	            int lenghtOfFile = conection.getContentLength();

	            // input stream to read file - with 8k buffer
	            InputStream input = new BufferedInputStream(url.openStream(), 8192);
	            
	            // Output stream to write file
	            OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");

	            byte data[] = new byte[1024];

	            long total = 0;

	            while ((count = input.read(data)) != -1) {
	                total += count;
	                // publishing the progress....
	                // After this onProgressUpdate will be called
	                publishProgress(""+(int)((total*100)/lenghtOfFile));
	                
	                // writing data to file
	                output.write(data, 0, count);
	            }

	            // flushing output
	            output.flush();
	            
	            // closing streams
	            output.close();
	            input.close();
	            
	        } catch (Exception e) {
	        	Log.e("Error: ", e.getMessage());
	        }
	        
	        return null;
		}
		
		
		protected void onProgressUpdate(String... progress) {
			// setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
       }

		
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after the file was downloaded
			dismissDialog(progress_bar_type);
			Toast.makeText(getApplicationContext(), "Download Completed", Toast.LENGTH_LONG).show();
			// Displaying downloaded image into image view
			// Reading image path from sdcard
			//String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
			// setting downloaded into image view
			//my_image.setImageDrawable(Drawable.createFromPath(imagePath));
		}

	} 
} 
	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Loading...");
		return dialog;
	}*/



	class AttemptAccess extends AsyncTask<String,String, String> {
		boolean success = false;
		String course, category, semester;

		public AttemptAccess(String course,String category, String semester) {
			this.course = course;
			this.category=category;
			this.semester = semester;
			
			
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(progress_bar_type);
			//showDialog(1);

		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			ServerRequest req = new ServerRequest();
			String url = Constants.file_url + "course=" + course + "&semester="
					+ semester + "&category=" + category ;

			// url = URLEncoder.encode(url);
			return req.requestGetHttp(url);

		}

		@Override
		protected void onPostExecute(String result) {
			int count;
			super.onPostExecute(result);
			//dismissDialog(1);
			try {
				JSONObject jObj = new JSONObject(result);
				if (jObj.get(Constants.KEY_RESPONSE).equals(
						Constants.TAG_SUCCESS)) {
					
					String path= Constants.download_url+jObj.getString("data");
					new AttemptDownload(path).execute();		            
		           
				
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		class AttemptDownload extends AsyncTask<String, String, String>
		{
		
		String paths;
		
		   public AttemptDownload(String paths)
		{
			   this.paths=paths;
			
		}

		   @Override
			protected void onPreExecute() {
				super.onPreExecute();
				showDialog(progress_bar_type);
				//showDialog(1);

			}

			@Override
			protected String doInBackground(String... params) {

				 int count;
			        try {
			            URL url = new URL(paths);
			            URLConnection conection = url.openConnection();
			            conection.connect();
			            // getting file length
			            int lenghtOfFile = conection.getContentLength();
			 
			            // input stream to read file - with 8k buffer
			            InputStream input = new BufferedInputStream(url.openStream(), 8192);
			 
			            // Output stream to write file
			            OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");
			 
			            byte data[] = new byte[1024];
			 
			            long total = 0;
			 
			            while ((count = input.read(data)) != -1) {
			                total += count;
			                // publishing the progress....
			                // After this onProgressUpdate will be called
			                publishProgress(""+(int)((total*100)/lenghtOfFile));
			 
			                // writing data to file
			                output.write(data, 0, count);
			            }
			 
			            // flushing output
			            output.flush();
			 
			            // closing streams
			            output.close();
			            input.close();
			 
			        } catch (Exception e) {
			            Log.e("Error: ", e.getMessage());
			        }
			 
				
				return null;
			}
			 protected void onProgressUpdate(String... progress) {
			        // setting progress percentage
			        pDialog.setProgress(Integer.parseInt(progress[0]));
			   }
			 
			    /**
			     * After completing background task
			     * Dismiss the progress dialog
			     * **/
			    @Override
			    protected void onPostExecute(String file_url) {
			        // dismiss the dialog after the file was downloaded
			        dismissDialog(progress_bar_type);
			        alert.showAlertDialog(Download.this, "Download Says",
		                    "The file has been downloaded", false);
			 
			        // Displaying downloaded image into image view
			        // Reading image path from sdcard
			       // String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
			        // setting downloaded into image view
			        //my_image.setImageDrawable(Drawable.createFromPath(imagePath));
			    }
		 
	}

	}
}
