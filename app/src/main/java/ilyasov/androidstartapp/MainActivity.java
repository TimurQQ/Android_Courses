package ilyasov.androidstartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Thread thread;
    String dataString = " ";
    FileManager manager = new FileManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataString = "\t" + s;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        OnClickListener saveClick= new OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setDataToFile(dataString, getBaseContext());
            }
        };

        Button saveBtn = (Button) findViewById(R.id.save_button);
        saveBtn.setOnClickListener(saveClick);

        OnClickListener BtnClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                startActivity(intent);
            }
        };
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(BtnClick);
        initThreadClick();
        initToastClick();
    }

    private void initThreadClick() {
        Button click = findViewById(R.id.thread);
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread(new RunnableTask());
                Log.d("RunnableLoop", String.valueOf(thread.getState()));
                thread.start();
                Log.d("RunnableLoop", String.valueOf(thread.getState()));
            }
        };
        click.setOnClickListener(listener);
    }

    private void initToastClick() {
        Button button = findViewById(R.id.toast_button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "Message" + thread.getState(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}