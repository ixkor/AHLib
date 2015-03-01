package net.xkor.ahlib.restoring;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;

import net.xkor.ahlib.Utils;
import net.xkor.ahlib.restoring.SaveToState;

import java.io.Serializable;
import java.lang.reflect.Field;

public class StateRestoreHelper {
    private static final String OBJECT_STATE_KEY = "OBJECT_STATE_KEY";

    /**
     * Save object fields with {@link SaveToState} annotation in bundle state.
     */
    public static void saveMarkedObjectFields(Object object, Bundle state, Class<?> baseClass) {
        ObjectState objectState = new ObjectState();
        try {
            for (Field field : Utils.getFieldsWithAnnotation(object, SaveToState.class, baseClass)) {
                field.setAccessible(true);
                objectState.put(field.getName(), field.get(object));
            }
        } catch (IllegalAccessException ignored) {
        }
        if (!objectState.isEmpty()) {
            state.putParcelable(OBJECT_STATE_KEY, objectState);
        }
    }

    /**
     * Restore object fields with {@link SaveToState} annotation from bundle state.
     */
    public static void restoreMarkedObjectFields(Object object, Bundle state, Class<?> baseClass) {
        ObjectState objectState = state.getParcelable(OBJECT_STATE_KEY);
        if (objectState == null || objectState.isEmpty())
            return;

        try {
            for (Field field : Utils.getFieldsWithAnnotation(object, SaveToState.class, baseClass)) {
                field.setAccessible(true);
                field.set(object, objectState.get(field.getName()));
            }
        } catch (IllegalAccessException ignored) {
        }
    }

    private static class ObjectState implements Parcelable {

        private ArrayMap<String, Object> map = new ArrayMap<>();

        public ObjectState(Parcel source) {
            source.readMap(map, null);
        }

        public ObjectState() {
        }

        public void put(String key, Object value) {
            map.put(key, value);
        }

        public Object get(String key) {
            return map.get(key);
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeMap(map);
        }

        public static final Creator<ObjectState> CREATOR =
                new Creator<ObjectState>() {
                    @Override
                    public ObjectState createFromParcel(Parcel source) {
                        return new ObjectState(source);
                    }

                    @Override
                    public ObjectState[] newArray(int size) {
                        return new ObjectState[size];
                    }
                };
    }
}
