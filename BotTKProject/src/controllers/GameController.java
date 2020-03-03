package controllers;

import lib.ConsoleIO;
import models.*;

public class GameController {
    private static Armor armor;
    private static Weapon weapon;


    public static Player createArcher(){
        Player archer;
        String name = ConsoleIO.promptForString("Please enter your player's name: ");
        String[] armorTypes = {"Mail", "Padded"};
        String[] weaponTypes = {"Bow"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, true);
        int weaponSelection = ConsoleIO.promptForMenuSelection(weaponTypes, true);
        switch(armorSelection){
            case 1:
                armor = new Armor("Chain Mail", ArmorType.MAIL, 4);
                break;
            case 2:
                armor = new Armor("Padded", ArmorType.PADDED, 3);
                break;
        }

        switch(weaponSelection){
            case 1:
                weapon = new Weapon("Bow", WeaponType.PEIRCE, 10, 6);
                break;
        }

        archer = new Archer(name, 16, 20, 10, 15, 15, 6, weapon, armor, Icons.O);
        return archer;
    }

    public static Player createWarrior(){
        Player warrior;
        String name = ConsoleIO.promptForString("Please enter your player's name: ");
        String[] armorTypes = {"Mail", "Plate"};
        String[] weaponTypes = {"Sword", "Spear"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, true);
        int weaponSelection = ConsoleIO.promptForMenuSelection(weaponTypes, true);
        switch(armorSelection){
            case 1:
                armor = new Armor("Chain Mail", ArmorType.MAIL, 4);
                break;
            case 2:
                armor = new Armor("Plate", ArmorType.PLATE, 5);
                break;
        }

        switch(weaponSelection){
            case 1:
                weapon = new Weapon("Sword", WeaponType.SLASH, 12, 2);
                break;
            case 2:
                weapon = new Weapon("Spear", WeaponType.PEIRCE, 11, 4);
        }

        warrior = new Warrior(name, 20, 30, 25, 10, 15, 4, weapon, Icons.P, armor);
        return warrior;
    }

    public static Player createWizard(){
        Spell fireBallSpell = new Spell(5, 4, 4, SpellType.FIRE);
        Spell healingSpell = new Spell(5, 0, 0, SpellType.HEAL);
        Spell shieldSpell = new Spell(4, 6, 0, SpellType.SHIELD);
        Spell[] spells = {fireBallSpell, healingSpell, shieldSpell};
        String name = ConsoleIO.promptForString("Please enter your player's name: ");
        Player wizard = new Wizard(name, 40, 20, 15, 12, 25, 5, spells, Icons.T, armor);
        return wizard;
    }

    public static void run(){

        String[] playerTypes = {"Archer", "Warrior", "Wizard"};
        Armor armor;
        int selectPlayerType = ConsoleIO.promptForMenuSelection(playerTypes, true);
        switch(selectPlayerType){
            case 1:
                createArcher();
                break;
            case 2:
                createWarrior();
                break;
            case 3:
                createWizard();
                break;
        }

    }

}
