package Threading;

import EfficientTools.ThingworxRestPropertyUpdater;
import EfficientTools.Tools;
import Model.Balloon;
import org.json.JSONObject;

import java.util.List;

public class GenerateSendJSONThread implements Runnable{
    
    private static final String SERVER_URL = "https://pp-2103181001eo.devportal.ptc.io";
    private static final String APP_KEY = "da664595-68db-44ce-9af0-86640a601886";
    protected static List<JSONObject> thingWorxJsons;
    public static int numberOfPacks = 0;
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<JSONObject> thingWorxJsons = Tools.createBalloonJSONS();
                //thingWorxJsons = Tools.createBalloonJSONS();
                for (int i = 0; i < thingWorxJsons.size(); ++i) {

                    ThingworxRestPropertyUpdater rest = new ThingworxRestPropertyUpdater();
                    try {
                        String thingName = "HAB" + (i + 1);
                        int response = rest.restUpdateProperties("https://pp-2103181001eo.devportal.ptc.io", "da664595-68db-44ce-9af0-86640a601886", thingName, (JSONObject) thingWorxJsons.get(i));
                        System.out.println("Response Status = " + response + "\n");
                    } catch (Exception var6) {
                        var6.printStackTrace();
                    }
                }
                numberOfPacks++;
                Thread.sleep(5000);

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
