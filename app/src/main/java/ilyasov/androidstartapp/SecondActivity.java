package ilyasov.androidstartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    FileManager manager = new FileManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = (TextView) findViewById(R.id.textView);

        View.OnClickListener loadClick= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.getDataFromFile(textView, getBaseContext());
            }
        };

        Button loadBtn = (Button) findViewById(R.id.load_button);
        loadBtn.setOnClickListener(loadClick);

        View.OnClickListener reverseClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(new StringBuilder(textView.getText()).reverse().toString());
            }
        };

        Button reverseBtn = (Button) findViewById(R.id.reverse_button);
        reverseBtn.setOnClickListener(reverseClick);
    }
}