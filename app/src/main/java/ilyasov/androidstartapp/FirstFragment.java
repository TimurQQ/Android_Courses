package ilyasov.androidstartapp;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long square_sum = 0L;
        for (int i = 1; i < 10000; ++i) {
            square_sum = i * i;
        }
        TextView ForCircleOutput = (TextView) view.findViewById(R.id.ForCircleOutput);
        String res = "\tThe result of square sum from 1 to 10000: " + square_sum;

        //Arrays Task
        int[] elements = {-1, 2, 3, 4, 5, -6, 7, 87, 8};
        StringBuilder oddIndexed = new StringBuilder(" ");
        StringBuilder evenIndexed = new StringBuilder(" ");
        int maxElement = elements[0];
        int minElement = elements[0];
        for (int i =0; i < elements.length; ++i) {
            if (i % 2 == 1) {
                oddIndexed.append(elements[i]).append(" ");
            }
            else {
                evenIndexed.append(elements[i]).append(" ");
            }
            if (elements[i] < minElement) {
                minElement = elements[i];
            }
            if (elements[i] > maxElement) {
                maxElement = elements[i];
            }
        }
        String array_string = "\t" + Arrays.toString(elements);
        ForCircleOutput.setText(String.join("\n\t", res,
                "elements: " + array_string, "Max Element: " + maxElement,
                "MinElement: " + minElement));
        Log.d("Even Indexed Numbers: ", String.valueOf(evenIndexed));
        Log.d("Odd indexed Numbers: ", String.valueOf(oddIndexed));
        //End of the Arrays Task
    }
}