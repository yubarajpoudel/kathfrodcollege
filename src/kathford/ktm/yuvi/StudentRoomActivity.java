package kathford.ktm.yuvi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentRoomActivity extends BaseActivity {




	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.studentroom);
		setHeader(getString(R.string.HomeActitvitytitle));
	}
	public void onButtonClicker(View v)
    {
     Intent intent;
 
     switch (v.getId()) {
  case R.id.newsevent:
   intent = new Intent(this, NewsEventActivity.class);
   startActivity(intent);
   break;
 
  case R.id.directory:
   intent = new Intent(this, DirectoryActivity.class);
   startActivity(intent);
   break;
 
  case R.id.download:
   intent = new Intent(this, Download.class);
   startActivity(intent);
   break;
 
  case R.id.library:
   intent = new Intent(this, LibraryActivity.class);
   startActivity(intent);
   break;
 
    default:
   break;
  }
    }
}
