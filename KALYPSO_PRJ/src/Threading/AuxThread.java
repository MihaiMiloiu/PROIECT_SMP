package Threading;

import EfficientTools.ThingworxRestPropertyUpdater;
import EfficientTools.Tools;
import org.json.JSONObject;

import java.util.List;

public class AuxThread implements Runnable {

    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("\nNumber of sent packages: " + GenerateSendJSONThread.numberOfPacks +"\n");
                Thread.sleep(5000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
