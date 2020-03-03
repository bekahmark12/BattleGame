package controllers;

import lib.ConsoleIO;
import models.*;

public class GameController {
    private static Armor armor;
    private static Weapon weapon;

    public static Player createArcher(){
        Player archer;
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

        archer = new Archer();
        return archer;
    }

    public static Player createWarrior(){
        Player warrior;
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

        warrior = new Warrior();
        return warrior;
    }

    public static Player createWizard(){
        Player wizard = new Wizard();
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
