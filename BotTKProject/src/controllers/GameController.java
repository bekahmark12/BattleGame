package controllers;

import lib.ConsoleIO;
import models.*;

import java.io.Console;
import java.util.ArrayList;

public class GameController {
    private static Map map;
    private static ArrayList<Player> players  = new ArrayList();

    public static void addPlayer(Player player){
        players.add(player);
    }

    public static void newMap(){
        map = new Map();
        map.randomMap();
    }

    public static void run(){
        mainMenu();
    }

    public static void mainMenu(){
        String[] options = {"Start a New Game", "Load a Game"};
        int selection = ConsoleIO.promptForMenuSelection(options, true);

        switch(selection){
            case 1:
                startGame();
                break;
            case 2:
                loadGame();
                break;
        }
    }

    public static void startGame() {
        players = new ArrayList<>();
        String[] gameTypes = {"1 Player", "2 Player"};
        int selection = ConsoleIO.promptForMenuSelection(gameTypes, false);
        switch(selection){
            case 1:
                makePlayer();
                makeAI();
                playGame();
                break;
            case 2:
                makePlayer();
                makePlayer();
                playGame();
        }
    }

    public static void makePlayer() {
        String name = ConsoleIO.promptForString("Enter a name for your character: ");
        String[] playerTypes = {"Archer", "Warrior", "Wizard"};
        Armor armor;
        int selectPlayerType = ConsoleIO.promptForMenuSelection(playerTypes, false);
        switch(selectPlayerType){
            case 1:
                addPlayer(createArcher(name));
                break;
            case 2:
                addPlayer(createWarrior(name));
                break;
            case 3:
                addPlayer(createWizard(name));
                break;
        }
    }

    public static Player createArcher(String name){
        Player archer;
        Armor armor;
        Weapon weapon;

        String[] armorTypes = {"Chainmail: type: mail, rating: 4", "Leather: type: padded, rating: 3"};
        String[] weaponTypes = {"Longbow: type: Pierce, rating: 8, ideal range: 5"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, false);
        int weaponSelection = ConsoleIO.promptForMenuSelection(weaponTypes, false);
        switch(armorSelection){
            case 1:
                armor = new Armor("Chain Mail", ArmorType.MAIL, 5);
                break;
            case 2:
                armor = new Armor("Gambeson", ArmorType.PADDED, 3);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + armorSelection);
        }

        switch(weaponSelection){
            case 1:
                weapon = new Weapon("Bow", WeaponType.PEIRCE, 8, 5);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weaponSelection);
        }

        archer = new Archer(name, 25, 7, 4, 12, 6, 15, weapon, armor, Icons.O);
        return archer;
    }

    public static Player createWarrior(String name){
        Player warrior;
        Armor armor;
        Weapon weapon;
        String[] armorTypes = {"Chainmail: type: mail, rating: 4", "Plate: type: steel, rating: 5"};
        String[] weaponTypes = {"Sword", "Spear"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, false);
        int weaponSelection = ConsoleIO.promptForMenuSelection(weaponTypes, false);
        switch(armorSelection){
            case 1:
                armor = new Armor("Chain Mail", ArmorType.MAIL, 5);
                break;
            case 2:
                armor = new Armor("Plate", ArmorType.PLATE, 7);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + armorSelection);
        }

        switch(weaponSelection){
            case 1:
                weapon = new Weapon("Sword", WeaponType.SLASH, 6, 1);
                break;
            case 2:
                weapon = new Weapon("Spear", WeaponType.PEIRCE, 4, 2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weaponSelection);
        }

        warrior = new Warrior(name, 20, 30, 25, 10, 15, 4, weapon, Icons.P, armor);
        return warrior;
    }

    public static Player createWizard(String name){
        Armor armor;
        Spell fireBallSpell = new Spell(5, 4, 4, SpellType.FIRE);
        Spell healingSpell = new Spell(5, 0, 0, SpellType.HEAL);
        Spell shieldSpell = new Spell(4, 6, 0, SpellType.SHIELD);
        Spell[] spells = {fireBallSpell, healingSpell, shieldSpell};
        String[] armorTypes = {"Gambeson: type: padded, rating: 3", "Cloak: type: padded, rating: 1"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, true);
        switch(armorSelection){
            case 1:
                armor = new Armor("Gambeson", ArmorType.PADDED, 3);
                break;
            case 2:
                armor = new Armor("Cloak", ArmorType.PADDED, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + armorSelection);
        }

        Player wizard = new Wizard(name, 40, 20, 15, 12, 25, 5, spells, Icons.T, armor);
        return wizard;
    }



}
