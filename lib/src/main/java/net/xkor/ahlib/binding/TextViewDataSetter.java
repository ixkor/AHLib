package net.xkor.ahlib.binding;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;

public class TextViewDataSetter extends ViewDataSetter {

    @Override
    public boolean setViewData(DataType type, Object value, View view) {
        if (super.setViewData(type, value, view)) return true;

        TextView textView = (TextView) view;
        switch (type) {
            case Auto:
                if (value == null || value.getClass().isAssignableFrom(CharSequence.class)) {
                    textView.setText((CharSequence) value);
                    return true;
                }
                return false;
            case Text:
                textView.setText((CharSequence) value);
                return true;
            case TextColor:
                if (value.getClass().isAssignableFrom(ColorStateList.class)) {
                    textView.setTextColor((ColorStateList) value);
                } else {
                    textView.setTextColor((int) value);
                }
                return true;
        }
        return false;
    }
}
