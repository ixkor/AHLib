package net.xkor.ahlib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.xkor.ahlib.binding.BindingHelper;
import net.xkor.ahlib.layouting.LayoutHelper;
import net.xkor.ahlib.restoring.StateRestoreHelper;

public class BaseUiObjectHelper {

    private Object uiObject;

    public BaseUiObjectHelper(Object uiObject) {
        this.uiObject = uiObject;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            StateRestoreHelper.restoreMarkedObjectFields(uiObject, savedInstanceState, null);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        StateRestoreHelper.saveMarkedObjectFields(uiObject, outState, null);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        return LayoutHelper.tryInflateWithAnnotation(inflater, container, uiObject);
    }

    public void onViewCreated(View view) {
        BindingHelper.findViewsForFields(view, uiObject, null);
    }

    public void onDestroyView() {
        BindingHelper.clearViewsForFields(uiObject, null);
    }
}
