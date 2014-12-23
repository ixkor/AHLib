package net.xkor.ahlib.helper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivityHelper {

    private FragmentActivity activity;

    public BaseActivityHelper(FragmentActivity activity) {
        this.activity = activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            StateRestoreHelper.restoreMarkedObjectFields(activity, savedInstanceState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        StateRestoreHelper.saveMarkedObjectFields(activity, outState);
    }
}
