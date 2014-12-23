package net.xkor.ahlib;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import net.xkor.ahlib.helper.BaseActivityHelper;

public class BaseAHActivity extends ActionBarActivity {

    private BaseActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHelper().onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getHelper().onSaveInstanceState(outState);
    }

    protected BaseActivityHelper getHelper() {
        if (helper == null) {
            helper = createFragmentHelper();
        }
        return helper;
    }

    protected BaseActivityHelper createFragmentHelper() {
        return new BaseActivityHelper(this);
    }
}
