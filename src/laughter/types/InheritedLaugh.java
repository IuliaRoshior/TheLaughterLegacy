package laughter.types;

import laughter.interfaces.SoulResonance;
import laughter.interfaces.Transmittable;

import java.io.PrintWriter;
import java.util.Scanner;

public class InheritedLaugh extends Laugh implements Transmittable, SoulResonance {
    private Laugh sourceLaugh;
    private int memoryStrength;

    public Laugh getSourceLaugh(){
        return sourceLaugh;
    }

    public int getMemoryStrength(){
        return memoryStrength;
    }

    public void inheritFrom(Laugh source){
        if (source == null || source == this) {
            return;
        }
        this.sourceLaugh = source;
        this.laughSound = source.getLaughSound();
        setWaves(source.getVolumeOfEachWave());
    }


    @Override
    public int transmitTo(Laugh target){
        if (target == null || sourceLaugh == null) {
            return 0;
        }

        int effect = memoryStrength / 5;
        target.changeMorale(effect);
        return effect;
    }

    // Память превращает Эхо в восстановление морали (нужно минимум 2 Эха, тратится 1)
    public int resonate(Laugh target, int echo){
        if (target == null || sourceLaugh == null || echo < 2) {
            return 0;
        }

        int effect = memoryStrength / 2 + echo * 3;
        target.changeMorale(effect);

        System.out.println(ownerName + " remembers " + sourceLaugh.getOwnerName() + ": " + sourceLaugh.getLaughSound());
        System.out.println("Memory effect: +" + effect);
        return 1;
    }

    @Override
    public void print() {
        printCommon();
        System.out.print("Memory strength: " + memoryStrength + " | Source: ");
        if (sourceLaugh == null) {
            System.out.println("none");
        } else {
            System.out.println(sourceLaugh.getOwnerName());
        }
    }

    public InheritedLaugh(){
        super();
        this.sourceLaugh = null;
        this.memoryStrength = 0;
        randomizeOwn();
    }

    public InheritedLaugh(String ownerName,
                   String laughSound,
                   long numberOfSoundWaves,
                   int memoryStrength){
        super(ownerName, laughSound, numberOfSoundWaves);
        this.memoryStrength = memoryStrength;
        this.sourceLaugh = null;
    }

    private void randomizeOwn(){
        this.sourceLaugh = null;
        this.memoryStrength = 10 + (int) (Math.random() * 81);
    }

    @Override
    public void randomize(){
        super.randomize();
        randomizeOwn();
    }

    @Override
    public void input(Scanner sc){
        super.input(sc);
        this.memoryStrength = readInt(sc, "Memory strength (0-100): ", 0, 100);
    }

    @Override
    public void save(PrintWriter out){
        super.save(out);
        out.println("Memory strength: " + memoryStrength);
        if (sourceLaugh == null) {
            out.println("Source: none");
        } else {
            out.println("Source: " + sourceLaugh.getOwnerName());
        }
    }

}
