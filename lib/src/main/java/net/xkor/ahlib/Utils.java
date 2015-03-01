package net.xkor.ahlib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Utils {
    public static <T extends Annotation> ArrayList<Field> getFieldsWithAnnotation(Object object, Class<T> annotationClass, Class<?> lastSuperclass) {
        ArrayList<Field> result = new ArrayList<>();
        Class<?> clazz = object.getClass();
        do {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotationClass)) {
                    result.add(field);
                }
            }
            if (clazz == annotationClass) break;
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return result;
    }
}
