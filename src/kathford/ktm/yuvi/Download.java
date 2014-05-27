package kathford.ktm.yuvi;

import android.os.Bundle;

public class Download extends BaseActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        
        setHeader(getString(R.string.DownloadTitle));
        
    }

}
