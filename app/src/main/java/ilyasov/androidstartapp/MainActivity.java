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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
    ArrayList<Integer> a = new ArrayList<>();
    LinkedList<Integer> b = new LinkedList<>();

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
        addElemsForLists(a, b);
        searchElemsInList(a);
        searchElemsInList(b);
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

    private void addElemsForLists(List<Integer> a, List<Integer> b) {
        long start = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 1000000; ++i) {
            a.add(random.nextInt());
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        Log.d("LIST_TIME", "ArrayList Add: " + timeElapsed + "milliseconds");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; ++i) {
            b.add(random.nextInt());
        }
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        Log.d("LIST_TIME", "LinkedList Add: " + timeElapsed + "milliseconds");
    }

    private void searchElemsInList(List<Integer> x) {
        Random random = new Random();
        Log.d("LIST_TYPE", x.getClass().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    Log.d("RUN", x.getClass().toString());
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 100000; ++i) {
                        int index = random.nextInt(x.size() - 1);
                        int elem = x.get(index);
                    }
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    Log.d("LIST_TIME", x.getClass() + " GetByTheIndex: " + timeElapsed + "milliseconds");
            }
        });
        thread.start();
    }
}