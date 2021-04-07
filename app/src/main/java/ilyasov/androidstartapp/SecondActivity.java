package ilyasov.androidstartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity{
    private final FileManager manager = new FileManager();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        manager.registerCallBack(new FileManager.Callback() {
            @Override
            public void callingBack(String s) {
                textView.setText(s);
            }
        });

        View.OnClickListener loadClick= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.getDataFromFile(getBaseContext());
            }
        };

        Button loadBtn = findViewById(R.id.load_button);
        loadBtn.setOnClickListener(loadClick);

        View.OnClickListener reverseClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(new StringBuilder(textView.getText()).reverse().toString());
            }
        };

        Button reverseBtn = findViewById(R.id.reverse_button);
        reverseBtn.setOnClickListener(reverseClick);
    }
}