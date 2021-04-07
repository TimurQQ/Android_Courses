package ilyasov.androidstartapp;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static android.content.Context.MODE_PRIVATE;

public class FileManager {
    private Callback callback;
    private final static String FILE_NAME = "file.txt";

    interface Callback{
        void callingBack(String s);
    }

    public void registerCallBack(Callback callback){
        this.callback = callback;
    }

    public void setDataToFile(String value, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(value.getBytes());
            callback.callingBack("Файл сохранен");
        } catch (IOException e) {
            callback.callingBack(e.getMessage());
        }
    }

    public void getDataFromFile(Context context) {
        try {
            FileInputStream fin = context.openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            callback.callingBack(text);
        } catch (IOException e) {}
    }
}
