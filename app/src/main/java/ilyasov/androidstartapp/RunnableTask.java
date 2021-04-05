package ilyasov.androidstartapp;

import android.util.Log;
import java.util.concurrent.TimeUnit;

public class RunnableTask implements Runnable {

    @Override
    public void run() {
        long delay = 5;
        for (int i = 0; i <= 10; ++i) {
            try{
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                Log.d("InterruptedException", e.getMessage());
            }
        }
    }
}
