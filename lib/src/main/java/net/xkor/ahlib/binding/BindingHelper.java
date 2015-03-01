package net.xkor.ahlib.binding;

import android.view.View;
import android.widget.TextView;

import net.xkor.ahlib.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BindingHelper {

    private Map<Class<?>, ViewDataSetter> viewDataSetters = new HashMap<>();

    private static BindingHelper defaultInstance = new BindingHelper();

    public static BindingHelper getDefaultInstance() {
        return defaultInstance;
    }

    public BindingHelper() {
        addViewDataSetter(View.class, new ViewDataSetter());
        addViewDataSetter(TextView.class, new TextViewDataSetter());
    }

    public void bindView(View view, Object object) throws IllegalAccessException, InvocationTargetException {
        for (Field field : object.getClass().getDeclaredFields()) {
            ViewData viewData = field.getAnnotation(ViewData.class);
            if (viewData != null) {
                field.setAccessible(true);
                setViewData(viewData.type(), field.get(object), view.findViewById(viewData.viewResId()));
            }
        }
        for (Method method : object.getClass().getDeclaredMethods()) {
            ViewData viewData = method.getAnnotation(ViewData.class);
            if (viewData != null) {
                method.setAccessible(true);
                setViewData(viewData.type(), method.invoke(object), view.findViewById(viewData.viewResId()));
            }
        }
    }

    private void setViewData(DataType type, Object value, View view) {
        if (view == null) return;

        Class<?> viewClass = view.getClass();
        int superLevel = -1;
        ViewDataSetter viewDataSetter = null;
        for (Class<?> setterClass : viewDataSetters.keySet()) {
            if (!viewClass.isAssignableFrom(setterClass)) return;

            int level = 0;
            Class<?> superClass = viewClass;
            while (!setterClass.isInterface() ? superClass != setterClass :
                    Arrays.asList(superClass.getGenericInterfaces()).contains(setterClass)) {
                superClass = superClass.getSuperclass();
                level++;
            }

            if (superLevel == -1 || level < superLevel) {
                viewDataSetter = viewDataSetters.get(setterClass);
                superLevel = level;
            }
        }

        if (viewDataSetter != null) {
            viewDataSetter.setViewData(type, value, view);
        }
    }

    public <T extends View> void addViewDataSetter(Class<T> viewClass, ViewDataSetter viewDataSetter) {
        viewDataSetters.put(viewClass, viewDataSetter);
    }

    public <T extends View> void removeViewDataSetter(Class<T> viewClass) {
        viewDataSetters.remove(viewClass);
    }

    /**
     * Set value for object fields with {@link FindViewById} annotation.
     */
    public static void findViewsForFields(View rootView, Object object, Class<?> baseClass) {
        try {
            for (Field field : Utils.getFieldsWithAnnotation(object, FindViewById.class, baseClass)) {
                field.setAccessible(true);
                field.set(object, rootView.findViewById(field.getAnnotation(FindViewById.class).value()));
            }
        } catch (IllegalAccessException ignored) {
        }
    }

    /**
     * Clear value for object fields with {@link FindViewById} annotation.
     */
    public static void clearViewsForFields(Object object, Class<?> baseClass) {
        try {
            for (Field field : Utils.getFieldsWithAnnotation(object, FindViewById.class, baseClass)) {
                field.setAccessible(true);
                field.set(object, null);
            }
        } catch (IllegalAccessException ignored) {
        }
    }
}
