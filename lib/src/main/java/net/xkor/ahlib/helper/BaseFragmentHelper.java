package net.xkor.ahlib.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragmentHelper {

    private Fragment fragment;

    public BaseFragmentHelper(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            StateRestoreHelper.restoreMarkedObjectFields(fragment, savedInstanceState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        StateRestoreHelper.saveMarkedObjectFields(fragment, outState);
    }
}
