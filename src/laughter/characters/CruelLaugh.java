package laughter.characters;

import laughter.types.Laugh;

import java.io.PrintWriter;
import java.util.Scanner;

public final class CruelLaugh extends DreadLaugh {
    private int crueltyLevel;

    public int getCrueltyLevel(){
        return crueltyLevel;
    }

    public void setCrueltyLevel(int crueltyLevel) {
        this.crueltyLevel = crueltyLevel;
    }

    public CruelLaugh(){
        super();
        this.crueltyLevel = 0;
        randomizeOwn();
    }

    public CruelLaugh(String ownerName,
                      String laughSound,
                      long numberOfSoundWaves,
                      int impactPower,
                      int fearLevel,
                      int crueltyLevel){
        super(ownerName, laughSound, numberOfSoundWaves, impactPower, fearLevel);
        this.crueltyLevel = crueltyLevel;
    }

    // жестокость от 10 до 40 (своё поле; fearLevel рандомизирует DreadLaugh)
    private void randomizeOwn(){
        this.crueltyLevel = 10 + (int) (Math.random() * 31);
    }

    // жестокий смех питается чужой слабостью
    public int mock(Laugh target) {
        if (target == null) {
            return 0;
        }

        int loss = crueltyLevel;
        if (target.getMorale() < 0) {
            loss = loss + loss / 4;
        }

        target.changeMorale(-loss);
        this.changeMorale(crueltyLevel / 10);

        return loss;
    }

    @Override
    public void randomize(){
        super.randomize();
        randomizeOwn();
    }

    @Override
    public void input(Scanner sc){
        super.input(sc);
        this.crueltyLevel = readInt(sc, "Cruelty level (0-100): ", 0, 100);
    }

    @Override
    public void save(PrintWriter out){
        super.save(out);
        out.println("Cruelty level: " + crueltyLevel);
    }

    @Override
    public void print(){
        super.print();
        System.out.println("Cruelty level: " + crueltyLevel);
    }
}
