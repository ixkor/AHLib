package net.xkor.ahlib.helpers;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import net.xkor.ahlib.annotations.SaveToState;

import java.io.Serializable;
import java.lang.reflect.Field;

public class StateRestoreFragmentHelper extends FragmentHelper {
    private static final String PREFIX = "AHState_";

    public StateRestoreFragmentHelper(Fragment fragment) {
        super(fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            restoreMarkedObjectFields(getFragment(), savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveMarkedObjectFields(getFragment(), outState);
    }

    public static void saveMarkedObjectFields(Object object, Bundle state) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SaveToState.class)) {
                field.setAccessible(true);
                String stateName = PREFIX + field.getName();
                try {
                    if (field.getType().isAssignableFrom(String.class)) {
                        state.putString(stateName, (String) field.get(object));
                    } else if (field.getType().isAssignableFrom(Boolean.TYPE)) {
                        state.putBoolean(stateName, field.getBoolean(object));
                    } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                        state.putInt(stateName, field.getInt(object));
                    } else if (field.getType().isAssignableFrom(Float.TYPE)) {
                        state.putFloat(stateName, field.getFloat(object));
                    } else if (field.getType().isAssignableFrom(Long.TYPE)) {
                        state.putLong(stateName, field.getLong(object));
                    } else if (field.getType().isAssignableFrom(Byte.TYPE)) {
                        state.putByte(stateName, field.getByte(object));
                    } else if (field.getType().isAssignableFrom(Character.TYPE)) {
                        state.putChar(stateName, field.getChar(object));
                    } else if (field.getType().isAssignableFrom(Short.TYPE)) {
                        state.putShort(stateName, field.getShort(object));
                    } else if (field.getType().isAssignableFrom(Double.TYPE)) {
                        state.putDouble(stateName, field.getDouble(object));
                    } else if (field.getType().isAssignableFrom(Bundle.class)) {
                        state.putBundle(stateName, (Bundle) field.get(object));
                    } else if (field.getType().isAssignableFrom(Serializable.class)) {
                        state.putSerializable(stateName, (Serializable) field.get(object));
                    } else if (field.getType().isAssignableFrom(Parcelable.class)) {
                        state.putParcelable(stateName, (Parcelable) field.get(object));
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
    }

    public static void restoreMarkedObjectFields(Object object, Bundle state) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SaveToState.class)) {
                field.setAccessible(true);
                String stateName = PREFIX + field.getName();
                try {
                    if (field.getType().isAssignableFrom(String.class)) {
                        field.set(object, state.getString(stateName));
                    } else if (field.getType().isAssignableFrom(Boolean.TYPE)) {
                        field.setBoolean(object, state.getBoolean(stateName));
                    } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                        field.setInt(object, state.getInt(stateName));
                    } else if (field.getType().isAssignableFrom(Float.TYPE)) {
                        field.setFloat(object, state.getFloat(stateName));
                    } else if (field.getType().isAssignableFrom(Long.TYPE)) {
                        field.setLong(object, state.getLong(stateName));
                    } else if (field.getType().isAssignableFrom(Byte.TYPE)) {
                        field.setByte(object, state.getByte(stateName));
                    } else if (field.getType().isAssignableFrom(Character.TYPE)) {
                        field.setChar(object, state.getChar(stateName));
                    } else if (field.getType().isAssignableFrom(Short.TYPE)) {
                        field.setShort(object, state.getShort(stateName));
                    } else if (field.getType().isAssignableFrom(Double.TYPE)) {
                        field.setDouble(object, state.getDouble(stateName));
                    } else if (field.getType().isAssignableFrom(Bundle.class)) {
                        field.set(object, state.getBundle(stateName));
                    } else if (field.getType().isAssignableFrom(Serializable.class)) {
                        field.set(object, state.getSerializable(stateName));
                    } else if (field.getType().isAssignableFrom(Parcelable.class)) {
                        field.set(object, state.getParcelable(stateName));
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
    }
}
