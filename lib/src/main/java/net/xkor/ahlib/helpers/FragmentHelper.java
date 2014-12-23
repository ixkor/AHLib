package net.xkor.ahlib.helpers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentHelper {
    private Fragment fragment;

    public FragmentHelper(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
    }

    public void onPause() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
    }

    public void onAttach(Activity activity) {
    }

    public void onDestroy() {
    }

    public void onDestroyView() {
    }

    public void onDetach() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
    }

    public Fragment getFragment() {
        return fragment;
    }
}
