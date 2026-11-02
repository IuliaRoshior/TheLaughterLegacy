package laughter.types;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Laugh {
    protected String ownerName;
    protected String laughSound;
    protected long numberOfSoundWaves;
    protected double[] volumeOfEachWave;
    protected int morale;
    protected int wavesUsed;
    private static int count;
    public static final int MAX_WAVES = 1000;//SNAKE_CASE
    private static final String[] RANDOM_NAMES = {"Nami", "Zoro", "Usopp", "Sanji", "Chopper", "Franky"};
    private static final String[] RANDOM_SOUNDS = {"HAHAHA!", "HEHEHE!", "KUKUKU!", "GYAHAHA!"};

    public String getOwnerName(){
        return  ownerName;
    }

    public String getLaughSound(){
        return  laughSound;
    }
    public static int getCount(){
        return count;
    }

    
    public long getNumberOfSoundWaves(){
        return numberOfSoundWaves;
    }

    public double getWave(int index){
        if (index < 0 || index >= volumeOfEachWave.length) {
            return 0;
        }
        return volumeOfEachWave[index];
    }

    public void setWaves(double[] waves){
        if (waves == null || waves.length > MAX_WAVES) {
            return;
        }
        this.volumeOfEachWave = new double[waves.length];
        for(int i = 0; i < waves.length; i++) {
            this.volumeOfEachWave[i] = waves[i];
        }
        this.numberOfSoundWaves = waves.length;
        this.wavesUsed = 0;
    }

    public int getWavesLeft() {
        return volumeOfEachWave.length - wavesUsed;
    }

    public double releaseWave(){
        if(wavesUsed >= volumeOfEachWave.length) {
            return 0;
        }
        double volume = volumeOfEachWave[wavesUsed];
        wavesUsed++;
        return volume;
    }

    public void restoreWaves() {
        wavesUsed = 0;
    }

    public int getWavesUsed(){
        return wavesUsed;
    }

    public double[] getVolumeOfEachWave(){
        double[] copy = new double[volumeOfEachWave.length];
        for (int i = 0; i < volumeOfEachWave.length; i++) {
           copy[i] = volumeOfEachWave[i];
        }

        return copy;
    }

    public int getMorale(){
        return morale;
    }

    Laugh(){
        this.ownerName = "UnKnown";
        this.laughSound = "UnKnown";
        this.numberOfSoundWaves = 0;
        volumeOfEachWave = new double[0];
        this.morale = 0;
        randomizeOwn();
        count++;
    }


    Laugh(String ownerName,
          String laughSound,
          long numberOfSoundWaves){
        this.ownerName = ownerName;
        this.laughSound = laughSound;
        if (numberOfSoundWaves < 0 || numberOfSoundWaves > MAX_WAVES){
            this.numberOfSoundWaves = 0;
            this.volumeOfEachWave = new double[0];
        } else {
            this.numberOfSoundWaves = numberOfSoundWaves;
            this.volumeOfEachWave = new double[(int) numberOfSoundWaves];
            fillWaves();
        }
        this.morale = 0;
        count++;
    }

    Laugh(Laugh other){
        this.ownerName = other.ownerName;
        this.laughSound = other.laughSound;
        this.numberOfSoundWaves = other.numberOfSoundWaves;
        this.morale = other.morale;
        this.wavesUsed = other.wavesUsed;
        this.volumeOfEachWave = new double[other.volumeOfEachWave.length];
        for(int i = 0; i < other.volumeOfEachWave.length; i++){
            volumeOfEachWave[i] = other.volumeOfEachWave[i];
        }
        count++;
    }

    private void fillWaves(){
        for(int i = 0; i < volumeOfEachWave.length; i++){
            volumeOfEachWave[i] = 30 + (int) (Math.random() * 71);
        }
    }

    private void randomizeOwn(){
        this.ownerName = RANDOM_NAMES[(int)(Math.random() * RANDOM_NAMES.length)];
        this.laughSound = RANDOM_SOUNDS[(int)(Math.random() * RANDOM_SOUNDS.length)];
        this.numberOfSoundWaves = 3 + (int) (Math.random() * 8);
        this.volumeOfEachWave = new double[(int) numberOfSoundWaves];
        fillWaves();
        this.morale = (int) (Math.random() * 41) - 20;
        this.wavesUsed = 0;
    }

    public void randomize(){
        randomizeOwn();
    }

    public void changeMorale(int amount) {

        this.morale += amount;

        if (this.morale > 100) {
            this.morale = 100;
        }

        if (this.morale < -100) {
            this.morale = -100;
        }
    }

    // ввод числа с проверкой диапазона
    public static int readInt(Scanner sc, String text, int min, int max){
        while (true){
            System.out.println((text));
            String line = sc.nextLine();
            try{
                int value = Integer.parseInt(line.trim());
                if(value >= min && value <= max) {
                    return value;
                }
            }
                catch (NumberFormatException e) {
                    // просто спросим ещё раз
                }
                System.out.println("Wrong value, try again");
            }
        }

    public void input(Scanner sc){
        System.out.print("Owner name: ");
        this.ownerName = sc.nextLine();
        System.out.print("Laugh sound: ");
        this.laughSound = sc.nextLine();
        int waves = readInt(sc, "Number of sound waves (0-" + MAX_WAVES + "): ", 0, MAX_WAVES);
        this.numberOfSoundWaves = waves;
        this.volumeOfEachWave = new double[waves];
        this.wavesUsed = 0;
        for (int i = 0; i < waves; i++) {
            this.volumeOfEachWave[i] = readInt(sc, "Volume of wave " + (i + 1) + " (0-100): ", 0, 100);
        }
        this.morale = readInt(sc, "Morale (-100..100): ", -100, 100);
    }

    public void save(PrintWriter out){
        out.println("Type: " + getClass().getSimpleName());
        out.println("Owner: " + ownerName);
        out.println("Laugh: " + laughSound);
        out.println("Waves: " + numberOfSoundWaves);
        out.print("Volumes:");
        for (int i = 0; i < volumeOfEachWave.length; i++) {
            out.print(" " + (int) volumeOfEachWave[i]);
        }
        out.println();
        out.println("Waves left: " + getWavesLeft());
        out.println("Morale: " + morale);
    }

    protected final void printCommon(){
        System.out.println("Owner: " + ownerName + " | Laugh: " + laughSound);
        System.out.println("Morale: " + morale + " | Waves: " + numberOfSoundWaves + " (left: " + getWavesLeft() + ")");
        System.out.print("Volumes:");
        for (int i = 0; i < volumeOfEachWave.length; i++) {
            System.out.print(" " + (int) volumeOfEachWave[i]);
        }
        System.out.println();
    }

    public abstract void print();


}
