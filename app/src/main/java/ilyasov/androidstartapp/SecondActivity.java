package ilyasov.androidstartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("MyName").toString();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(name);
    }
}