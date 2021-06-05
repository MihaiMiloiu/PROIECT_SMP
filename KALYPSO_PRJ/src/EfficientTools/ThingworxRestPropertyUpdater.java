package EfficientTools;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import org.json.JSONObject;

public class ThingworxRestPropertyUpdater {
    public ThingworxRestPropertyUpdater() {
    }

    private static void disableSSLCertificateChecking() throws KeyManagementException, NoSuchAlgorithmException {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
            }
        }};
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init((KeyManager[])null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public int restUpdateProperties(String serverUrl, String appKey, String thingName, JSONObject payload) throws IOException {
        String httpUrlString = serverUrl + "/Thingworx/Things/" + thingName + "/Properties/*";
        System.out.println("Performing HTTP PUT request to " + httpUrlString);
        System.out.println("Payload is " + payload);
        URL url = new URL(httpUrlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("appKey", appKey);
        OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
        out.write(payload.toString());
        out.close();
        httpURLConnection.getInputStream();
        return httpURLConnection.getResponseCode();
    }

    static {
        try {
            disableSSLCertificateChecking();
        } catch (Exception var1) {
            var1.printStackTrace();
        }

        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> {
            return true;
        });
    }
}
