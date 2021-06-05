package EfficientTools;

import Model.Balloon;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Tools {
    public static final double ROMANIA_LAT = 45.944285D;
    public static final double ROMANIA_LONG = 25.00943D;
    public static final int ROMANIA_RADIUS = 250000;
    public static final int COORDINATES_DECIMALS = 6;

    public Tools() {
    }

    public static int randomInt(int min, int max) {
        return (new Random()).nextInt(max - min) + min;
    }

    public static double randomDouble(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    public static double roundDouble(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal bd = BigDecimal.valueOf(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }

    public static String generateUniqueID(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString();
    }

    public static LocalDate randomDate() {
        long minDay = LocalDate.of(2010, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static Pair<Double, Double> randomLocationAroundPoint(double y0, double x0, int radius) {
        Random random = new Random();
        double radiusInDegrees = (double)((float)radius / 111000.0F);
        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 6.283185307179586D * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);
        double new_x = x / Math.cos(y0);
        double foundLongitude = roundDouble(new_x + x0, 6);
        double foundLatitude = roundDouble(y + y0, 6);
        return new Pair(foundLatitude, foundLongitude);
    }

    public static List<JSONObject> createBalloonJSONS() {
        Balloon[] balloons = new Balloon[5];

        for(int i = 0; i < 5; ++i) {
            balloons[i] = new Balloon(i + 1);
        }

        List<JSONObject> jsons = new ArrayList();

        Balloon[] var3 = balloons;
        int var4 = balloons.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Balloon balloon = var3[var5];
            jsons.add(balloon.getJson());
        }

        return jsons;
    }
}
