package foregroundlocation.stackoverflowexample.com.foregroundlocation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // the identifier for the permissions request
    private static final int PERMISSION_REQUEST_ID = 1999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {

        if (!checkSystemPermissions()) {
            super.onResume();
            return;
        }

        super.onResume();
    }

    /**
     * Check that the user has the necessary permissions.
     */
    private boolean checkSystemPermissions() {

        Log.i(TAG, "Checking for the necessary permissions");

        final int granted = PackageManager.PERMISSION_GRANTED;

        // only for M and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // list of permissions to check
            final String permissionsToCheck[] = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WAKE_LOCK
            };

            // list of permissions still needed
            final List<String> permissionsStillNeeded = new ArrayList<>();

            // loop through all permissions to check, if any are not granted, add them
            // to the list of permissions that are still needed so they can be requested all
            // at once later
            for (String permission : permissionsToCheck) {

                if (granted != checkSelfPermission(permission)) {
                    permissionsStillNeeded.add(permission);
                }
            }

            // request permission(s), if necessary
            if (permissionsStillNeeded.size() > 0) {
                requestPermissions(permissionsStillNeeded.toArray(new String[permissionsStillNeeded.size()]),
                        PERMISSION_REQUEST_ID);
                return false;
            }
        }

        return true;
    }

    public void startLocationService(View view) {

        startService(new Intent(getApplicationContext(), ForegroundLocationService.class));
    }
}
