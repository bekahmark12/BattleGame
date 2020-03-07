package controllers;

import lib.ConsoleIO;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private static Map map = new Map();
    private static ArrayList<Player> players  = new ArrayList();
    private static Game game;
    private static boolean isPlayer1Turn;

    public static void addPlayer(Player player){
        players.add(player);
    }

    public static void newMap(){
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
                playGame();
                break;
        }
    }

    public static void startGame() {
        players = new ArrayList<>();
        newMap();
        isPlayer1Turn = flipTheCoin();
        String[] gameTypes = {"1 Player", "2 Player"};
        int selection = ConsoleIO.promptForMenuSelection(gameTypes, false);
        switch(selection){
            case 1:
                makePlayer();
//                makeAI();
                playGame();
                break;
            case 2:
                makePlayer();
                makePlayer();
                playGame();
        }

    }

    public static void playGame(){

    }

    public static boolean flipTheCoin(){
        Random rng = new Random();
        boolean result = false;
        int side = rng.nextInt(2);
        if(side == 0){
            result = true;
        }
        return result;
    }

    public static void makePlayer() {
        String name = ConsoleIO.promptForString("Enter a name for your character: ");
        String[] playerTypes = {"Ranger", "Warrior", "Wizard"};
        int selectPlayerType = ConsoleIO.promptForMenuSelection(playerTypes, false);
        switch(selectPlayerType){
            case 1:
                addPlayer(createRanger(name));
                break;
            case 2:
                addPlayer(createWarrior(name));
                break;
            case 3:
                addPlayer(createWizard(name));
                break;
        }
    }

    public static Player createRanger(String name){
        Player ranger;
        Armor armor;
        Weapon weapon;

        String[] armorTypes = {"Chainmail: type: mail, rating: 4", "Gambeson: type: padded, rating: 3"};
        String[] weaponTypes = {"Longbow: type: Pierce, rating: 6, ideal range: 4", "Longsword: type : slash, rating: 5, ideal range: 1"};
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
                weapon = new Weapon("Longbow", WeaponType.PEIRCE, 6, 4);
                break;
            case 2:
                weapon = new Weapon( "Longsword", WeaponType.SLASH, 5, 1);
            default:
                throw new IllegalStateException("Unexpected value: " + weaponSelection);
        }
        ranger = new Ranger(name, 25, 7, 4, 12, 6, 15, weapon, armor, Icons.O, true);
        return ranger;
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
                armor = new Armor("Steel", ArmorType.PLATE, 7);
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

        warrior = new Warrior(name, 35, 5, 10, 6, 4, 6, weapon, Icons.P, armor, true);
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

        Player wizard = new Wizard(name, 40, 20, 15, 12, 25, 5, spells, Icons.T, armor, true);
        return wizard;
    }

    public void takeHumanTurn(Icons[][] map, int row, int col, Player p){
        //int playerPosition = map[row][col];
        int maxMoves = p.getDexterity();
        String[] playerOptions = {"Move Up", "Move Down", "Move Right", "Move Left", "End Turn"};
        int move = 0;
        do{
            int userChoice = ConsoleIO.promptForMenuSelection(playerOptions, true);
            switch(userChoice){
                case 1:
                    if(map.checkValidSpace(row - 1, col)) {
                        map.setIcon(map, row - 1, col);
                        move ++;
                    } else {
                        System.out.println("That space is not a valid move.");
                    }
                    break;
                case 2:
                    if(map.checkValidSpace(row + 1, col)) {
                        map.setIcon(map, row + 1, col);
                        move++;
                    } else {
                        System.out.println("That space is not a valid move.");
                    }
                    break;
                case 3:
                    if(map.checkValidSpace(row, col - 1)) {
                        map.setIcon(map, row, col - 1);
                        move++;
                    } else {
                        System.out.println("That space is not a valid move.");
                    }
                    break;
                case 4:
                    if(map.checkValidSpace(row, col +1)) {
                        map.setIcon(map, row, col + 1);
                        move++;
                    } else{
                        System.out.println("That space is not a valid move.");
                    }
                    break;
            }
        } while(move <= maxMoves);
        //somehow pass in the current instance of the board/map to call methods on?
        //get the players dexterity to set movement limit
        //player can move in any direction as long as that space is an _
        //Player moves by being prompted for up, down, left, or right
        //or figure out how to bind arrow key strokes to up down left right
        //Player turn ends when they run out of moves or select end turn
        //Make sure board dynamically updates itself after every change instead of reprinting entire console

    }

    public static void saveGame(){
        game.setSavedMap(map);
        game.setSavedPlayers(players);
        try{
            FileOutputStream fout = new FileOutputStream("Save.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(game);
            oos.close();
            fout.close();
            ConsoleIO.printString("Success");
        }catch (IOException ioe){}

    }

    public static void loadGame(){
        try {
            FileInputStream fileIn = new FileInputStream("Save.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            fileIn.close();
            in.close();
        }catch (IOException | ClassNotFoundException e){}
    }


}
