package kathford.ktm.yuvi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BaseActivity extends Activity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 
    public void setHeader(String title){
      /*ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
      View inflated = stub.inflate();
 
      TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
      txtTitle.setText(title);*/
    	try{
    		((TextView)findViewById(R.id.txtHeading)).setText(title);
    		setTitle(title);
    	}
    	catch(Exception e){}
    }
}
