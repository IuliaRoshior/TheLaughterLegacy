package laughter.types;

import java.io.PrintWriter;
import java.util.Scanner;

public class ForcedSmileLaugh extends Laugh {
    private String trueEmotion;
    private int curseLevel;

    public String revealEmotion(){
        changeMorale(15);
        System.out.println(ownerName + " laughs " + laughSound + " but really feels: " + trueEmotion);
        return trueEmotion;
    }

    public boolean tryToStop(){
        boolean success = (morale + 100) >= curseLevel * 2;
        if (success) {
            changeMorale(10);
            System.out.println(ownerName + " manages to stop the laugh. Silence...");
        } else {
            System.out.println(ownerName + " tries to stop, but the curse is too strong.");
        }
        return success;
    }

    public ForcedSmileLaugh(){
        super();
        this.trueEmotion = "";
        this.curseLevel = 0;
        randomizeOwn();
    }

    public ForcedSmileLaugh(String ownerName,
                     String laughSound,
                     long numberOfSoundWaves,
                     String trueEmotion,
                     int curseLevel){
        super(ownerName, laughSound, numberOfSoundWaves);
        this.trueEmotion = trueEmotion;
        this.curseLevel = curseLevel;
        applyEmotion();
    }

    // мораль зависит от настоящей эмоции
    private void applyEmotion(){
        switch (trueEmotion) {

            case "sad":
                this.morale = -70;
                break;

            case "happy":
                this.morale = 70;
                break;

            case "angry":
                this.morale = -40;
                break;

            default:
                this.morale = 0;
                break;
        }
    }

    private void randomizeOwn(){
        int k = (int) (Math.random() * 3);
        if (k == 0) {
            this.trueEmotion = "sad";
        } else if (k == 1) {
            this.trueEmotion = "happy";
        } else {
            this.trueEmotion = "angry";
        }
        this.curseLevel= 10 + (int) (Math.random() * 81);
        applyEmotion();
    }

    @Override
    public void randomize(){
        super.randomize();
        randomizeOwn();
    }

    @Override
    public void input(Scanner sc){
        super.input(sc);
        String emotion = "";
        while (!emotion.equals("sad") && !emotion.equals("happy") && !emotion.equals("angry")) {
            System.out.print("True emotion (sad/happy/angry): ");
            emotion = sc.nextLine().trim();
        }
        this.trueEmotion = emotion;
        this.curseLevel = readInt(sc, "Curse level (0-100): ", 0, 100);
    }

    @Override
    public void save(PrintWriter out){
        super.save(out);
        out.println("True emotion: " + trueEmotion);
        out.println("Curse level: " + curseLevel);
    }

    @Override
    public void print(){
        printCommon();
        System.out.println("True emotion: " + trueEmotion + " | Curse level: " + curseLevel);
    }
}
