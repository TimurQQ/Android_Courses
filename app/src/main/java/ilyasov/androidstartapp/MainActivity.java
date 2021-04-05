package ilyasov.androidstartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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