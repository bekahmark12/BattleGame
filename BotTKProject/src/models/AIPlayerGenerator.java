package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class AIPlayerGenerator {
    private static String randName;
    private static Armor randArmor;
    private static Weapon randWeapon;
    private static Armor mail = new Armor("Chain Mail", ArmorType.MAIL, 5);
    private static Armor gambeson = new Armor("Gambeson", ArmorType.PADDED, 3);
    private static Armor steel = new Armor("Steel Plate", ArmorType.PLATE, 7);
    private static Armor cloak = new Armor("Cloak", ArmorType.PADDED, 4);
    private static Random rand = new Random();
    private static int possibleArmorOrWeapon = rand.nextInt(2);
    private static Player player;


    public static Player generateAIPlayer(Icons icon){
        Player aiPlayer = null;
        int possiblePlayers = rand.nextInt(2);
        switch(possiblePlayers){
            case 0:
                aiPlayer = generateRandomRanger(icon);
                break;
            case 1:
                aiPlayer =generateRandomWarrior(icon);
                break;
        }
        return aiPlayer;
    }

    public static String generateRandomName(){
        String name;
        RandomAINames values[] = RandomAINames.values();
        ArrayList<RandomAINames> randomAINamesArrayList = new ArrayList<>();
        for(RandomAINames value : values){
            randomAINamesArrayList.add(value);
        }
        int possibleNames = rand.nextInt(randomAINamesArrayList.size());
        return name = randomAINamesArrayList.get(possibleNames).toString();
    }

    public static Armor generateRandWizardArmor(){
        Armor armor = null;
        switch(possibleArmorOrWeapon){
            case 0:
                armor = gambeson;
                break;
            case 1:
                armor = cloak;
                break;
        }
        return armor;
    }

    public static Armor generateRandWarriorArmor(){
        Armor armor = null;
        switch(possibleArmorOrWeapon){
            case 0:
                armor = mail;
                break;
            case 1:
                armor = steel;
                break;
        }
        return armor;
    }

    public static Armor generateRandRangerArmor(){
        Armor armor = null;
        switch(possibleArmorOrWeapon){
            case 0:
                armor = mail;
                break;
            case 1:
                armor = gambeson;
                break;
        }
        return armor;
    }

    public static Weapon generateRandRangerWeapon(){
        Weapon weapon = null;
        switch(possibleArmorOrWeapon){
            case 0:
                weapon = new Weapon("Longbow", WeaponType.PEIRCE, 8, 5);
                break;
            case 1:
                weapon = new Weapon("Throwing Knives", WeaponType.PEIRCE, 9, 4);
                break;
        }
        return weapon;
    }

    public static Weapon generateRandWarriorWeapon(){
        Weapon weapon = null;
        switch(possibleArmorOrWeapon){
            case 0:
                weapon = new Weapon("Spear", WeaponType.PEIRCE, 4, 2);
                break;
            case 1:
                weapon = new Weapon("Sword", WeaponType.SLASH, 6, 1);
                break;
        }
        return weapon;
    }


    public static Player generateRandomRanger(Icons icon){
        randName = generateRandomName();
        randWeapon = generateRandRangerWeapon();
        randArmor = generateRandRangerArmor();

        Player AIRanger = new Ranger(randName, 25, 7, 4, 12, 6, 15, randWeapon, randArmor, icon,  false);
        return AIRanger;
    }

    public static Player generateRandomWarrior(Icons icon){
        randName = generateRandomName();
        randWeapon = generateRandWarriorWeapon();
        randArmor = generateRandWarriorArmor();

        Player AIWarrior = new Warrior(randName, 35, 5, 10, 6, 4, 6, randWeapon, icon, randArmor, false);
        return AIWarrior;
    }

    public Player generateRandomWizard(){
        ArrayList<Spell> spells = new ArrayList<>();
        Spell fireBall = new Spell(2, 3, 4, SpellType.FIRE);
        Spell heal = new Spell(5, 0, 5, SpellType.HEAL);
        Spell shield = new Spell(3, 0, 4, SpellType.SHIELD);
        spells.add(fireBall);
        spells.add(heal);
        spells.add(shield);
        randName = generateRandomName();
        randArmor = generateRandWizardArmor();

        Player AIWizard = new Wizard(randName, 40, 20, 15, 12, 25, 5, spells, Icons.B, randArmor, false);
        return AIWizard;
    }
}
