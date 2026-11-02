package laughter.characters;

import laughter.interfaces.Intimidating;
import laughter.types.BattleLaugh;
import laughter.types.Laugh;

import java.io.PrintWriter;
import java.util.Scanner;

public class DreadLaugh extends BattleLaugh implements Intimidating {
    protected int fearLevel;

    public int getFearLevel(){
        return  fearLevel;
    }

    public DreadLaugh(){
        super();
        this.fearLevel = 0;
        randomizeOwn();
    }

    public DreadLaugh(String ownerName,
                      String laughSound,
                      long numberOfSoundWaves,
                      int impactPower,
                      int fearLevel){
        super(ownerName, laughSound, numberOfSoundWaves, impactPower);
        this.fearLevel = fearLevel;
    }
    private void randomizeOwn(){
        this.fearLevel = 10 + (int) (Math.random() * 21);
    }

    @Override
    public void randomize(){
        super.randomize();
        randomizeOwn();
    }

    @Override
    public void input(Scanner sc){
        super.input(sc);
        this.fearLevel = readInt(sc, "Fear level (0-100): ", 0, 100);
    }

    @Override
    public void save(PrintWriter out){
        super.save(out);
        out.println("Fear level: " + fearLevel);
    }

    @Override
    public int frighten(Laugh target){
        if (target == null) {
            return 0;
        }

        int power = fearLevel;
        // страх сильнее действует на уже подавленного
        if (target.getMorale() < 0) {
            power = power + power / 2;
        }

        target.changeMorale(-power);

        return power;
    }

    @Override
    public void print(){
        super.print();
        System.out.println("Fear level: " + fearLevel);
    }
}
