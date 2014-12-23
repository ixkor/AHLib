package net.xkor.ahlib.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.xkor.ahlib.annotation.ContainerLayout;
import net.xkor.ahlib.annotation.Layout;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = null;

        ContainerLayout containerAnnotation = fragment.getClass().getAnnotation(ContainerLayout.class);
        if (containerAnnotation != null) {
            view = inflater.inflate(containerAnnotation.value(), container, false);
        }

        Layout layoutAnnotation = fragment.getClass().getAnnotation(Layout.class);
        if (layoutAnnotation != null) {
            View mainView = inflater.inflate(layoutAnnotation.value(), container, false);
            if (containerAnnotation != null) {
                ViewGroup contentView = (ViewGroup) view.findViewById(containerAnnotation.contentFrameId());
                contentView.addView(mainView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            } else {
                view = mainView;
            }
        }

        return view;
    }
}
