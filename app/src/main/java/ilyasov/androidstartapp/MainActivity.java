package ilyasov.androidstartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity{
    private Thread thread;
    private String dataString = " ";
    private final FileManager manager = new FileManager();
    String pattern = "Sum of values between 2 and {} equals ";
    private TextView textNumber;
    private Callable callable;
    FutureTask future;
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager.registerCallBack(new FileManager.Callback() {
            @Override
            public void callingBack(String s) {
                Toast toast = Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        textNumber = findViewById(R.id.sumResult);

        EditText editText = findViewById(R.id.editTextTextPersonName);
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

        EditText numberText = findViewById(R.id.editTextNumber);
        numberText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                x = Integer.parseInt(String.valueOf(s));
                pattern = pattern.replaceFirst(
                        "\\{[^}{ ]*\\}",
                        "{" + String.valueOf(s) + "}");
                textNumber.setText(pattern);
            }

            @Override
            public void afterTextChanged(Editable s) {
                callable = getDataFromCallable();
                future = new FutureTask(callable);
                new Thread(future).start();
            }
        });

        OnClickListener saveClick= new OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setDataToFile(dataString, getBaseContext());
            }
        };

        Button saveBtn = findViewById(R.id.save_button);
        saveBtn.setOnClickListener(saveClick);

        OnClickListener BtnClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                startActivity(intent);
            }
        };
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(BtnClick);
        initThreadClick();
        initToastClick();
        initCountUpClick();
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

    private void initCountUpClick() {
        Button button = findViewById(R.id.countUpButton);
        button.setOnClickListener(new OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    textNumber.setText(pattern + future.get(1, TimeUnit.MILLISECONDS));
                } catch (ExecutionException | TimeoutException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Callable getDataFromCallable() {
        Callable callable = new Callable() {
            @Override
            public Long call() throws Exception {
                long result = 0L;
                for (int i = 2; i <= x; ++i) {
                    result += i;
                }
                return result;
            }
        };
        return callable;
    }
}