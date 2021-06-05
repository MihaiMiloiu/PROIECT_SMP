package Threading;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeThread implements Runnable {
    @Override
    public void run(){
        try{
            while (!Thread.currentThread().isInterrupted()) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("\nTime when package was sent: " + dtf.format(now) +"\n");
                //System.out.println("\nTime when package was sent: " + System.currentTimeMillis() +"\n");
                Thread.sleep(5000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
