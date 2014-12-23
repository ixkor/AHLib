package net.xkor.ahlib;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.xkor.ahlib.helper.BaseFragmentHelper;

public class BaseAHFragment extends Fragment {

    private BaseFragmentHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHelper().onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getHelper().onSaveInstanceState(outState);
    }

    protected BaseFragmentHelper getHelper() {
        if (helper == null) {
            helper = createFragmentHelper();
        }
        return helper;
    }

    protected BaseFragmentHelper createFragmentHelper() {
        return new BaseFragmentHelper(this);
    }
}
