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
    Armor gambeson = new Armor("Gambeson", ArmorType.PADDED, 3);
    Armor steel = new Armor("Steel Plate", ArmorType.PLATE, 7);
    Armor cloak = new Armor("Cloak", ArmorType.PADDED, 4);
    Random rand = new Random();
    int possibleArmorOrWeapon = rand.nextInt(2);
    Player player;


    public Player generateAIPlayer(){
        int possiblePlayers = rand.nextInt(3);
        switch(possiblePlayers){
            case 0:
                generateRandomRanger();
                break;
            case 1:
                generateRandomWarrior();
                break;
            case 2:
                generateRandomWizard();
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
        return name = randomAINamesArrayList.get(possibleNames).toString();
    }

    public Armor generateRandWizardArmor(){
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

    public Armor generateRandWarriorArmor(){
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

    public Armor generateRandRangerArmor(){
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

    public Weapon generateRandRangerWeapon(){
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

    public Weapon generateRandWarriorWeapon(){
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


    public Player generateRandomRanger(){
        randName = generateRandomName();
        randWeapon = generateRandRangerWeapon();
        randArmor = generateRandRangerArmor();

        Player AIRanger = new Ranger(randName, 25, 7, 4, 12, 6, 15, randWeapon, randArmor, Icons.O,  false);
        return AIRanger;
    }

    public Player generateRandomWarrior(){
        randName = generateRandomName();
        randWeapon = generateRandWarriorWeapon();
        randArmor = generateRandWarriorArmor();

        Player AIWarrior = new Warrior(randName, 20, 30, 25, 10, 15, 4, randWeapon, Icons.P, randArmor, false);
        return AIWarrior;
    }

    public Player generateRandomWizard(){
        Spell fireBallSpell = new Spell(5, 4, 4, SpellType.FIRE);
        Spell healingSpell = new Spell(5, 0, 0, SpellType.HEAL);
        Spell shieldSpell = new Spell(4, 6, 0, SpellType.SHIELD);
        Spell[] spells = {fireBallSpell, healingSpell, shieldSpell};
        randName = generateRandomName();
        randArmor = generateRandWizardArmor();

        Player AIWizard = new Wizard(randName, 40, 20, 15, 12, 25, 5, spells, Icons.T, randArmor, false);
        return AIWizard;
    }
}
