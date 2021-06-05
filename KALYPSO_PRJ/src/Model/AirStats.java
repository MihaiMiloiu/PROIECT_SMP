package Model;

import EfficientTools.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class AirStats {
    public static final double OXYGEN_LOWER_LEVEL = 72.0D;
    public static final double OXYGEN_HIGHER_LEVEL = 78.0D;
    public static final double MIN_TEMPERATURE = -40.0D;
    public static final double MAX_TEMPERATURE = 40.0D;
    public static final double MIN_PRESSURE = 500.0D;
    public static final double MAX_PRESSURE = 1000.0D;
    private static final int GAS_COUNT = 9;
    private double oxygen;
    private double nitrogen;
    private double argon;
    private double carbonDioxide;
    private double neon;
    private double helium;
    private double methan;
    private double krypton;
    private double others;

    public AirStats() {
        ArrayList<Double> gasses = this.generateRandomsToSum(100.0D, 8, this.randomOxygenLevel());
        this.oxygen = (Double)gasses.get(0);
        this.nitrogen = (Double)gasses.get(1);
        this.argon = (Double)gasses.get(2);
        this.carbonDioxide = (Double)gasses.get(3);
        this.neon = (Double)gasses.get(4);
        this.helium = (Double)gasses.get(5);
        this.methan = (Double)gasses.get(6);
        this.krypton = (Double)gasses.get(7);
        this.others = (Double)gasses.get(8);
    }

    public static double randomPressure() {
        return Tools.roundDouble(Tools.randomDouble(500.0D, 1000.0D), 1);
    }

    public static double randomTemperature() {
        return Tools.roundDouble(Tools.randomDouble(-40.0D, 40.0D), 1);
    }

    private double randomOxygenLevel() {
        return Tools.randomDouble(72.0D, 78.0D);
    }

    private ArrayList<Double> generateRandomsToSum(double targetSum, int numbers, double first) {
        first = Tools.roundDouble(first, 2);
        targetSum -= first;
        ArrayList<Double> load = new ArrayList();
        double sum = 0.0D;

        for(int i = 0; i < numbers; ++i) {
            double next = Tools.randomDouble(0.0D, targetSum) + 1.0D;
            load.add(next);
            sum += next;
        }

        double scale = targetSum / sum;
        sum = 0.0D;

        for(int i = 0; i < numbers; ++i) {
            load.set(i, Tools.roundDouble((Double)load.get(i) * scale, 2));
            sum += (Double)load.get(i);
        }

        sum = Tools.roundDouble(sum, 2);
        load.add(first);
        load.sort(Collections.reverseOrder());
        if (sum != targetSum) {
            load.set(0, Tools.roundDouble((Double)load.get(0) + targetSum - sum, 2));
        }

        return load;
    }

    public double getOxygen() {
        return this.oxygen;
    }

    public void setOxygen(double oxygen) {
        this.oxygen = oxygen;
    }

    public double getNitrogen() {
        return this.nitrogen;
    }

    public void setNitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public double getArgon() {
        return this.argon;
    }

    public void setArgon(double argon) {
        this.argon = argon;
    }

    public double getCarbonDioxide() {
        return this.carbonDioxide;
    }

    public void setCarbonDioxide(double carbonDioxide) {
        this.carbonDioxide = carbonDioxide;
    }

    public double getNeon() {
        return this.neon;
    }

    public void setNeon(double neon) {
        this.neon = neon;
    }

    public double getHelium() {
        return this.helium;
    }

    public void setHelium(double helium) {
        this.helium = helium;
    }

    public double getMethan() {
        return this.methan;
    }

    public void setMethan(double methan) {
        this.methan = methan;
    }

    public double getKrypton() {
        return this.krypton;
    }

    public void setKrypton(double krypton) {
        this.krypton = krypton;
    }

    public double getOthers() {
        return this.others;
    }

    public void setOthers(double others) {
        this.others = others;
    }
}
