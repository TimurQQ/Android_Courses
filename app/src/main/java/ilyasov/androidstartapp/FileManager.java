package ilyasov.androidstartapp;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class FileManager {
    public void setDataToFile(String fileName, String value, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, MODE_PRIVATE);
            fos.write(value.getBytes());
            Toast toast = Toast.makeText(context, "Файл сохранен", Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            Toast toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void getDataFromFile(String fileName, TextView textView, Context context) {
        try {
            FileInputStream fin = context.openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (IOException e) {
            Toast toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
