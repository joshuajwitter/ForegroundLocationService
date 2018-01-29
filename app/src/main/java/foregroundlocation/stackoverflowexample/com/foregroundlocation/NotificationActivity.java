package foregroundlocation.stackoverflowexample.com.foregroundlocation;

import android.app.Activity;

public class NotificationActivity extends Activity {

    @Override
    public void onResume() {

        // finish this activity and return to the last activity
        finish();

        super.onResume();
    }
}
