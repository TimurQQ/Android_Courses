package ilyasov.androidstartapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long square_sum = 0L;
        for (int i = 1; i < 10000; ++i) {
            square_sum = i * i;
        }
        TextView ForCircleOutput = (TextView) view.findViewById(R.id.ForCircleOutput);
        String res = "\tThe result of square sum from 1 to 10000: " + square_sum;
        ForCircleOutput.setText(res);
    }
}