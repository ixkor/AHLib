package net.xkor.ahlib.helpers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MultiHelper extends FragmentHelper {
    private FragmentHelper[] helpers;

    public MultiHelper(Fragment fragment, FragmentHelper... helpers) {
        super(fragment);
        this.helpers = helpers;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        for (FragmentHelper helper : helpers) {
            helper.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onPause() {
        for (FragmentHelper helper : helpers) {
            helper.onPause();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        for (FragmentHelper helper : helpers) {
            helper.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        for (FragmentHelper helper : helpers) {
            helper.onAttach(activity);
        }
    }

    @Override
    public void onDestroy() {
        for (FragmentHelper helper : helpers) {
            helper.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        for (FragmentHelper helper : helpers) {
            helper.onDestroyView();
        }
    }

    @Override
    public void onDetach() {
        for (FragmentHelper helper : helpers) {
            helper.onDetach();
        }
    }

    @Override
    public void onResume() {
        for (FragmentHelper helper : helpers) {
            helper.onResume();
        }
    }

    @Override
    public void onStart() {
        for (FragmentHelper helper : helpers) {
            helper.onStart();
        }
    }

    @Override
    public void onStop() {
        for (FragmentHelper helper : helpers) {
            helper.onStop();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        for (FragmentHelper helper : helpers) {
            helper.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (FragmentHelper helper : helpers) {
            helper.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        for (FragmentHelper helper : helpers) {
            helper.onViewStateRestored(savedInstanceState);
        }
    }

    public FragmentHelper[] getHelpers() {
        return helpers;
    }
}
