package net.xkor.ahlib.binding;

import android.view.View;

public class ViewDataSetter {
    public boolean setViewData(DataType type, Object value, View view) {
        if (value == null) return false;

        switch (type) {
            case Auto:
                if (value.getClass() == Boolean.TYPE) {
                    view.setEnabled((boolean) value);
                    return true;
                }
                return false;
            case Enabled:
                view.setEnabled((boolean) value);
                return true;
            case Visible:
                if (value.getClass() == Boolean.TYPE) {
                    view.setVisibility(((boolean) value) ? View.VISIBLE : View.GONE);
                } else {
                    view.setVisibility((int) value);
                }
                return true;
        }
        return false;
    }
}
