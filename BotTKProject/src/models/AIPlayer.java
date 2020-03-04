package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class AIPlayer {
    String randName;
    Armor randArmor;
    Weapon randWeapon;
    int randMaxHealth;
    int randStamina;
    int randStrength;
    int randAgility;
    int randWisdom;
    Icon randIcon;
    Armor mail = new Armor("Chain Mail", ArmorType.MAIL, 5);
    Armor padded = new Armor("Gambeson", ArmorType.PADDED, 3);
    Armor steel = new Armor("Steel Plate", ArmorType.PLATE, 7);
    Random rand = new Random();
    Player player;


    public Player generateAIPlayer(){
        int possiblePlayers = rand.nextInt(3);
        switch(possiblePlayers){
            case 0:
                player = new Archer();
                break;
            case 1:
                player = new Warrior();
                break;
            case 2:
                player = new Wizard();
                break;
        }
        return player;
    }

    public String generateRandomName(){
        String name;
        RandomAINames values[] = RandomAINames.values();
        ArrayList<RandomAINames> randomAINamesArrayList = new ArrayList<>();
        for(RandomAINames value : values){
            randomAINamesArrayList.add(value);
        }
        int possibleNames = rand.nextInt(randomAINamesArrayList.size());
        int nameIndex = randomAINamesArrayList[possibleNames];
    }

    public Weapon generateRandomWeapon(Weapon weapon1, Weapon weapon2){
        int possibleWeapons = rand.nextInt()
    }

    public Armor generateRandomArmor(Armor armor1, Armor armor2){

    }

    public Archer generateRandomArcher(){
        randName = generateRandomName();

    }

    public Warrior generateRandomWarrior(){
        String name = generateRandomName();
        Armor armor = generateRandomArmor();
        Weapon weapon;
    }

    public Wizard generateRandomWizard(){
        String name = generateRandomName();
        Armor armor;
        Spell spells[];
    }
}
