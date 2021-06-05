package Controller;

import EfficientTools.ThingworxRestPropertyUpdater;
import EfficientTools.Tools;
import Threading.AuxThread;
import Threading.GenerateSendJSONThread;
import Threading.TimeThread;

public class MainController {
    private static final String SERVER_URL = "https://pp-2103181001eo.devportal.ptc.io";
    private static final String APP_KEY = "da664595-68db-44ce-9af0-86640a601886";

    public MainController() {
    }

    public static void main(String[] args) {
        /*List<JSONObject> thingWorxJsons = Tools.createBalloonJSONS();

        for(int i = 0; i < thingWorxJsons.size(); ++i) {
            ThingworxRestPropertyUpdater rest = new ThingworxRestPropertyUpdater();

            try {
                String thingName = "HAB" + (i + 1);
                int response = rest.restUpdateProperties("https://pp-2103181001eo.devportal.ptc.io", "da664595-68db-44ce-9af0-86640a601886", thingName, (JSONObject)thingWorxJsons.get(i));
                System.out.println("Response Status = " + response + "\n");
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }*/
        GenerateSendJSONThread generateSendJSONThread = new GenerateSendJSONThread();
        Thread generationThread = new Thread(generateSendJSONThread);
        generationThread.start();

        AuxThread auxThread = new AuxThread();
        Thread uploadThread = new Thread(auxThread);
        uploadThread.start();

        TimeThread timeThread = new TimeThread();
        Thread displayTime = new Thread(timeThread);
        displayTime.start();

        try {
            generationThread.join();
            uploadThread.join();
            displayTime.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
