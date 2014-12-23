package net.xkor.ahlib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.xkor.ahlib.helper.BaseFragmentHelper;

public abstract class BaseAHFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getHelper().onCreateView(inflater, container);
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
