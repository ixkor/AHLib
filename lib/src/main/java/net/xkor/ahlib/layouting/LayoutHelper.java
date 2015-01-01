package net.xkor.ahlib.layouting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.xkor.ahlib.layouting.ContainerLayout;
import net.xkor.ahlib.layouting.Layout;

public class LayoutHelper {
    public static View tryInflateWithAnnotation(LayoutInflater inflater, ViewGroup container, Object uiObject) {
        View view = null;

        ContainerLayout containerAnnotation = uiObject.getClass().getAnnotation(ContainerLayout.class);
        if (containerAnnotation != null) {
            view = inflater.inflate(containerAnnotation.value(), container, false);
        }

        Layout layoutAnnotation = uiObject.getClass().getAnnotation(Layout.class);
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
