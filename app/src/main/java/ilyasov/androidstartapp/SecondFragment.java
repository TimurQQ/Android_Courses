package ilyasov.androidstartapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int i = 59;
        String concat = "";
        while (i % 20 != 0 && i >= 0) {
            concat += i--;
        }
        TextView WhileCircleOutput = (TextView) view.findViewById(R.id.WhileCircleOutput);
        String res = "\tConcat nums from 59 downto 0 while Num % 20 != 0:\n\t" + concat;
        WhileCircleOutput.setText(res);
    }
}