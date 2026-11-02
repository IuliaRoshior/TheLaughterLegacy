package laughter.app;

import laughter.characters.CruelLaugh;
import laughter.characters.DreadLaugh;
import laughter.characters.MusicalLaugh;
import laughter.interfaces.LaughPower;
import laughter.types.BattleLaugh;
import laughter.types.ForcedSmileLaugh;
import laughter.types.InheritedLaugh;
import laughter.types.Laugh;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// Лаборатория: показывает все требования лабораторной на небольшом наборе объектов.
// Сама игра живёт во втором модуле (laughter.game).
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- конструкторы с параметрами и без (без параметров = Randomize) ---
        BattleLaugh luffy = new BattleLaugh("Luffy", "SHISHISHI!", 5, 60);
        DreadLaugh kaido = new DreadLaugh("Kaido", "WORORORO!", 5, 70, 25);
        CruelLaugh teach = new CruelLaugh("Teach", "ZEHAHAHAHA!", 5, 55, 15, 28);
        MusicalLaugh brook = new MusicalLaugh("Brook", "YOHOHOHO!", 5, 20, 200);
        ForcedSmileLaugh toko = new ForcedSmileLaugh("Toko", "HAHAHA...", 5, "sad", 20);
        InheritedLaugh robin = new InheritedLaugh("Robin", "FUFUFU!", 5, 80);
        CruelLaugh randomCruel = new CruelLaugh();
        BattleLaugh randomBattle = new BattleLaugh();

        // --- массив базового класса ---
        Laugh[] all = new Laugh[8];
        all[0] = luffy;
        all[1] = kaido;
        all[2] = teach;
        all[3] = brook;
        all[4] = toko;
        all[5] = robin;
        all[6] = randomCruel;
        all[7] = randomBattle;

        robin.inheritFrom(luffy);

        // --- массив интерфейса: заполняем вручную по instanceof ---
        int fightersCount = 0;
        for (int i = 0; i < all.length; i++) {
            if (all[i] instanceof LaughPower) {
                fightersCount++;
            }
        }
        LaughPower[] fighters = new LaughPower[fightersCount];
        int k = 0;
        for (int i = 0; i < all.length; i++) {
            if (all[i] instanceof LaughPower) {
                fighters[k] = (LaughPower) all[i];
                k++;
            }
        }

        System.out.println("Created laughs: " + Laugh.getCount() + " (max waves = " + Laugh.MAX_WAVES + ")");
        System.out.println("Fighters (LaughPower): " + fighters.length);
        System.out.println();

        // --- print + уникальные функции каждого класса через instanceof и приведение типов ---
        for (int i = 0; i < all.length; i++) {
            System.out.println("=== " + all[i].getOwnerName() + " ===");
            all[i].print();

            // порядок важен: сначала самый "нижний" класс
            if (all[i] instanceof CruelLaugh) {
                CruelLaugh cruel = (CruelLaugh) all[i];
                int lost = cruel.mock(luffy);
                System.out.println(cruel.getOwnerName() + " mocks Luffy, morale -" + lost);
            } else if (all[i] instanceof DreadLaugh) {
                DreadLaugh dread = (DreadLaugh) all[i];
                int lost = dread.frighten(luffy);
                System.out.println(dread.getOwnerName() + " frightens Luffy, morale -" + lost);
            } else if (all[i] instanceof MusicalLaugh) {
                MusicalLaugh music = (MusicalLaugh) all[i];
                music.resonate(luffy, 2);
                int gift = music.transmitTo(luffy);
                System.out.println("Brook supports Luffy, morale +" + gift);
            } else if (all[i] instanceof BattleLaugh) {
                BattleLaugh battle = (BattleLaugh) all[i];
                battle.chargeUp(5);
                int lost = battle.activate(kaido);
                System.out.println(battle.getOwnerName() + " hits Kaido, morale -" + lost);
            } else if (all[i] instanceof ForcedSmileLaugh) {
                ForcedSmileLaugh forced = (ForcedSmileLaugh) all[i];
                forced.revealEmotion();
                forced.tryToStop();
            } else if (all[i] instanceof InheritedLaugh) {
                InheritedLaugh inherited = (InheritedLaugh) all[i];
                int gift = inherited.transmitTo(toko);
                System.out.println("Robin supports Toko, morale +" + gift);
                inherited.resonate(inherited, 3);
            }
            System.out.println();
        }

        // --- работа через интерфейс ---
        System.out.println("--- Everyone from LaughPower[] attacks Kaido ---");
        for (int i = 0; i < fighters.length; i++) {
            int lost = fighters[i].activate(kaido);
            System.out.println("Hit! Kaido morale -" + lost + ", now " + kaido.getMorale());
        }
        System.out.println();

        // --- ввод с клавиатуры (переопределён в каждом классе) ---
        System.out.print("Edit the random CruelLaugh by keyboard? (y/n): ");
        String answer = sc.nextLine().trim();
        if (answer.equals("y")) {
            randomCruel.input(sc);
            randomCruel.print();
        }

        // --- запись в файл (переопределена в каждом классе) ---
        try {
            PrintWriter out = new PrintWriter("laughs.txt");
            for (int i = 0; i < all.length; i++) {
                all[i].save(out);
                out.println();
            }
            out.close();
            System.out.println("Saved to laughs.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write file.");
        }
    }
}
