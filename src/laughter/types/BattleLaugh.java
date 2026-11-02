package laughter.types;

import laughter.interfaces.LaughPower;

import java.io.PrintWriter;
import java.util.Scanner;

public class BattleLaugh extends Laugh implements LaughPower {
    protected int impactPower;

    public int getImpactPower(){
        return impactPower;
    }

    public BattleLaugh(){
        super();
        this.impactPower = 0;
        randomizeOwn();
    }

    public BattleLaugh(String ownerName, String laughSound, long numberOfSoundWaves, int impactPower){
        super(ownerName, laughSound, numberOfSoundWaves);
        this.impactPower = impactPower;
    }

    // сила удара от 20 до 80
    private void randomizeOwn(){
        this.impactPower = 20 + (int) (Math.random() * 61);
    }
    @Override
    // переопределение Laugh.randomize(): сначала родитель, затем своё поле
    public void randomize(){
        super.randomize(); //родитель заполняет СВОИ поля (имя, звук, волны, мораль)
        randomizeOwn();
    }


    @Override
    public void print() {
        printCommon();
        System.out.println("Impact power: " + impactPower);
    }

    @Override
    public void input(Scanner sc){
        super.input(sc);
        this.impactPower = readInt(sc, "Impact power(0-100): ", 0, 100);
    }

    @Override
    public void save(PrintWriter out){
        super.save(out);
        out.println("Impact power: " + impactPower);
    }

    // уникальный метод: накопить силу удара
    public void chargeUp(int amount){
        impactPower += amount;
        if (impactPower > 100) {
            impactPower = 100;
        }
        if (impactPower < 0) {
            impactPower = 0;
        }
    }

    @Override
    public int activate(Laugh target){
        if (target == null) {
            return 0;
        }
        int damage = impactPower / 5; // базовый урон = пятая часть силы удара
        if (morale >= 50) {
            damage = damage + damage / 2;
        }

        // минус = удар по цели. Идёт через changeMorale, чтобы сработали границы
        target.changeMorale(-damage);
        // атакующий получает половину урона как прилив уверенности
        changeMorale(damage / 2);

        return damage;
    }
}
