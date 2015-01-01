package net.xkor.ahlib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAHFragment extends Fragment {

    private BaseUiObjectHelper helper;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getHelper().onViewCreated(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getHelper().onDestroyView();
    }

    protected BaseUiObjectHelper getHelper() {
        if (helper == null) {
            helper = createHelper();
        }
        return helper;
    }

    protected BaseUiObjectHelper createHelper() {
        return new BaseUiObjectHelper(this);
    }
}
