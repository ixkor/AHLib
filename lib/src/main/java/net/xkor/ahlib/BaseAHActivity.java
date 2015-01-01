package net.xkor.ahlib;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAHActivity extends ActionBarActivity {

    private BaseUiObjectHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHelper().onCreate(savedInstanceState);
        View view = getHelper().onCreateView(getLayoutInflater(), (ViewGroup) findViewById(android.R.id.content));
        if (view != null) {
            setContentView(view);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getHelper().onSaveInstanceState(outState);
    }

    protected BaseUiObjectHelper getHelper() {
        if (helper == null) {
            helper = createHelper();
        }
        return helper;
    }

    @Override
    public void onSupportContentChanged() {
        super.onSupportContentChanged();
        getHelper().onViewCreated(findViewById(android.R.id.content));
    }

    protected BaseUiObjectHelper createHelper() {
        return new BaseUiObjectHelper(this);
    }
}
