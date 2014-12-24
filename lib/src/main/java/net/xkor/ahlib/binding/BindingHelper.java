package net.xkor.ahlib.binding;

import android.view.View;

import java.lang.reflect.Field;

public class BindingHelper {
    public static void bindView(View view, Object object) throws IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()) {
            ViewData viewData = field.getAnnotation(ViewData.class);
            if (viewData != null) {
                field.setAccessible(true);
                setViewData(viewData.type(), field.get(object), view.findViewById(viewData.viewResId()));
            }
        }
    }

    private static void setViewData(DataType type, Object value, View view) {

    }
}
