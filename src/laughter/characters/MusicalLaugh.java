package laughter.characters;

import laughter.interfaces.SoulResonance;
import laughter.interfaces.Transmittable;
import laughter.types.BattleLaugh;
import laughter.types.Laugh;

import java.io.PrintWriter;
import java.util.Scanner;

public class MusicalLaugh extends BattleLaugh implements Transmittable, SoulResonance {
    private int tempoBpm;

    public int getTempoBpm(){
        return tempoBpm;
    }

    @Override
    public int resonate(Laugh target, int echo) {
        if (target == null || echo <= 0) {
            return 0;
        }

        int effect = Math.min(30, Math.max(0, tempoBpm / 10));

        target.changeMorale(effect);

        System.out.println(ownerName + ": " + laughSound);
        System.out.println("Musical effect: +" + effect + " (echo used: " + echo + ")");
        return echo;
    }


    @Override
    public int transmitTo(Laugh target) {
        if (target == null) {
            return 0;
        }

        int effect = tempoBpm / 20;
        target.changeMorale(effect);
        return effect;
    }

    public MusicalLaugh(){
        super();
        this.tempoBpm = 0;
        randomizeOwn();
    }

    public MusicalLaugh(String ownerName,
                        String laughSound,
                        long numberOfSoundWaves,
                        int impactPower,
                        int tempoBpm){
        super(ownerName, laughSound, numberOfSoundWaves, impactPower);
        this.tempoBpm = tempoBpm;
    }

private void randomizeOwn(){
    this.tempoBpm = 60 + (int) (Math.random() * 181);
}

@Override
public void randomize(){
    super.randomize();
    randomizeOwn();
}

@Override
public void input(Scanner sc){
    super.input(sc);
    this.tempoBpm = readInt(sc, "Tempo BPM (0-300): ", 0, 300);
}

@Override
public void save(PrintWriter out){
    super.save(out);
    out.println("Tempo BPM: " + tempoBpm);
}

@Override
public void print(){
    super.print();
    System.out.println("Tempo BPM: " + tempoBpm);
}
}
