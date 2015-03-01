package net.xkor.ahlib.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import net.xkor.ahlib.BaseAHActivity;
import net.xkor.ahlib.BaseAHFragment;
import net.xkor.ahlib.binding.FindViewById;
import net.xkor.ahlib.layouting.Layout;
import net.xkor.ahlib.restoring.SaveToState;


@Layout(R.layout.activity_main)
public class MainActivity extends BaseAHActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Layout(R.layout.fragment_main)
    public static class PlaceholderFragment extends BaseAHFragment {

        @SaveToState
        private int counter;
        @FindViewById(R.id.counter)
        private TextView counterView;
        @FindViewById(R.id.incButton)
        private View incButton;

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            incButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter++;
                    counterView.setText("" + counter);
                }
            });

            counterView.setSaveEnabled(false);
            counterView.setText("" + counter);
        }
    }
}
