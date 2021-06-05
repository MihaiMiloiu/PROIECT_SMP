package Model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;
import EfficientTools.Pair;
import EfficientTools.Tools;

public class Balloon {
        public static final int BALLOONS_NUMBER = 5;
        public static final String NAME_PREFIX = "HAB";
        private static final int MAX_SPEED = 150;
        private static final int MAX_WIND_SPEED = 150;
        private static final int ALTITUDE = 10000;
        private String name;
        private String serialNumber;
        private String launchDay;
        private String launchMonth;
        private String launchYear;
        private Double launchLat;
        private Double launchLong;

        public Balloon(String name, String serialNumber, String launchDay, String launchMonth, String launchYear, Double launchLat, Double launchLong) {
            this.name = name;
            this.serialNumber = serialNumber;
            this.launchDay = launchDay;
            this.launchMonth = launchMonth;
            this.launchYear = launchYear;
            this.launchLat = launchLat;
            this.launchLong = launchLong;
        }

        public Balloon(int nameIndex) {
            Object[] var10002 = new Object[]{nameIndex};
            this.name = "HAB" + String.format("%01d", var10002);
            this.serialNumber = Tools.generateUniqueID("balloon");
            LocalDate randomDay = Tools.randomDate();
            this.launchDay = randomDay.format(DateTimeFormatter.ofPattern("dd"));
            this.launchMonth = randomDay.format(DateTimeFormatter.ofPattern("MMMM"));
            this.launchYear = randomDay.format(DateTimeFormatter.ofPattern("yyyy"));
            Pair<Double, Double> randomLocation = Tools.randomLocationAroundPoint(45.944285D, 25.00943D, 250000);
            this.launchLat = (Double)randomLocation.getFirst();
            this.launchLong = (Double)randomLocation.getSecond();
        }

        public String generateSpeed() {
            return Integer.toString(Tools.randomInt(0, 150));
        }

        public String generateWindSpeed() {
            return Integer.toString(Tools.randomInt(0, 150));
        }

        public String generateAltitude() {
            return Integer.toString(Tools.randomInt(0, 10000));
        }

        public String getLaunchDate() {
            String var10000 = this.getLaunchDay();
            return var10000 + " " + this.getLaunchMonth() + " " + this.getLaunchYear();
        }

        public String getLaunchLocation() {
            Double var10000 = this.getLaunchLat();
            return var10000 + "," + this.getLaunchLong();
        }

        public String getCurrentLocation() {
            double var10000 = Tools.roundDouble(this.getLaunchLat() + 5.5D, 6);
            return var10000 + "," + Tools.roundDouble(this.getLaunchLong() + 6.5D, 6);
        }

        public JSONObject getJson() {
            AirStats airStats = new AirStats();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Name", this.getName());
            jsonObject.put("serialNumber", this.getSerialNumber());
            jsonObject.put("launchDate", this.getLaunchDate());
            jsonObject.put("launchLocation", this.getLaunchLocation());
            jsonObject.put("currentLocation", this.getCurrentLocation());
            jsonObject.put("currentSpeed", this.generateSpeed());
            jsonObject.put("windSpeed", this.generateWindSpeed());
            jsonObject.put("altitude", this.generateAltitude());
            jsonObject.put("airPressure", AirStats.randomPressure());
            jsonObject.put("airTemperature", AirStats.randomTemperature());
            jsonObject.put("oxygen", airStats.getOxygen());
            jsonObject.put("nitrogen", airStats.getNitrogen());
            jsonObject.put("argon", airStats.getArgon());
            jsonObject.put("carbonDioxide", airStats.getCarbonDioxide());
            jsonObject.put("neon", airStats.getNeon());
            jsonObject.put("helium", airStats.getHelium());
            jsonObject.put("methan", airStats.getMethan());
            jsonObject.put("krypton", airStats.getKrypton());
            jsonObject.put("others", airStats.getOthers());
            return jsonObject;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getLaunchDay() {
            return this.launchDay;
        }

        public void setLaunchDay(String launchDay) {
            this.launchDay = launchDay;
        }

        public String getLaunchMonth() {
            return this.launchMonth;
        }

        public void setLaunchMonth(String launchMonth) {
            this.launchMonth = launchMonth;
        }

        public String getLaunchYear() {
            return this.launchYear;
        }

        public void setLaunchYear(String launchYear) {
            this.launchYear = launchYear;
        }

        public Double getLaunchLat() {
            return this.launchLat;
        }

        public void setLaunchLat(Double launchLat) {
            this.launchLat = launchLat;
        }

        public Double getLaunchLong() {
            return this.launchLong;
        }

        public void setLaunchLong(Double launchLong) {
            this.launchLong = launchLong;
        }
}
