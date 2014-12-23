package net.xkor.ahlib.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.xkor.ahlib.BaseAHActivity;
import net.xkor.ahlib.BaseAHFragment;
import net.xkor.ahlib.annotation.ContainerLayout;
import net.xkor.ahlib.annotation.Layout;
import net.xkor.ahlib.annotation.SaveToState;


public class MainActivity extends BaseAHActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @ContainerLayout(value = R.layout.activity_main, contentFrameId = R.id.container)
    @Layout(R.layout.fragment_main)
    public static class PlaceholderFragment extends BaseAHFragment {

        @SaveToState
        private int counter;

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            view.findViewById(R.id.incButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter++;
                    TextView counterView = (TextView) getView().findViewById(R.id.counter);
                    counterView.setText("" + counter);
                }
            });

            TextView counterView = (TextView) view.findViewById(R.id.counter);
            counterView.setText("" + counter);
        }
    }
}
