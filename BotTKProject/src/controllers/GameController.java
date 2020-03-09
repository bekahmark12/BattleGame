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

    public static ArrayList<Player> getPlayers(){
        return players;
    }

    public static void newMap(){
        map.randomMap(players);
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
        isPlayer1Turn = flipTheCoin();
        String[] gameTypes = {"1 Player", "2 Player"};
        int selection = ConsoleIO.promptForMenuSelection(gameTypes, false);
        switch(selection){
            case 1:
                makePlayer();
//                makeAI();
                newMap();
                playGame();
                break;
            case 2:
                makePlayer();
                makePlayer();
                newMap();
                playGame();
        }

    }

    public static void playGame(){
        boolean player1IsAlive = true;
        boolean player2IsAlive = true;

        do{
            if(isPlayer1Turn){
                Player p = getPlayers().get(0);
                takeHumanTurn(p.getRow(), p.getCol(), p);
                isPlayer1Turn = !isPlayer1Turn;
            }
            else{
                Player p = getPlayers().get(1);
                if(p.isHuman){
                    takeHumanTurn(p.getRow(), p.getCol(), p);
                }
                else{
//                    takeAITurn(p.getRow(), p.getCol(), p);
                }
                isPlayer1Turn = !isPlayer1Turn;
            }

        }while (player1IsAlive && player2IsAlive);


    }

    public static void takeHumanTurn(int row, int col, Player p){
        int currentStamina = p.getStamina();
        do{
            map.printBoard();
            ConsoleIO.printString(p.getName() + ", you have " + currentStamina + " stamina left");
            String[] playerOptions = evaluateOptions(row, col, p, currentStamina);
            int selection = ConsoleIO.promptForMenuSelection(playerOptions, true);
            if(selection == 0){
                saveGame();
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("move up")){
                map.setIcon(p.getRow(), p.getCol(), Icons._);
                p.setRow(p.getRow() - 1);
                row--;
                map.setIcon(p.getRow(), p.getCol(), p.getIcon());
                currentStamina--;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("move down")){
                map.setIcon(p.getRow(), p.getCol(), Icons._);
                p.setRow(p.getRow() + 1);
                row++;
                map.setIcon(p.getRow(), p.getCol(), p.getIcon());
                currentStamina--;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("move left")){
                map.setIcon(p.getRow(), p.getCol(), Icons._);
                p.setCol(p.getCol() - 1);
                col--;
                map.setIcon(p.getRow(), p.getCol(), p.getIcon());
                currentStamina--;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("move right")){
                map.setIcon(p.getRow(), p.getCol(), Icons._);
                p.setCol(p.getCol() + 1);
                col++;
                map.setIcon(p.getRow(), p.getCol(), p.getIcon());
                currentStamina--;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("Cast Fireball at enemy (2 stamina)")){

                currentStamina -= 2;
            }
        } while(currentStamina > 0);
        //somehow pass in the current instance of the board/map to call methods on?
        //get the players dexterity to set movement limit
        //player can move in any direction as long as that space is an _
        //Player moves by being prompted for up, down, left, or right
        //or figure out how to bind arrow key strokes to up down left right
        //Player turn ends when they run out of moves or select end turn
        //Make sure board dynamically updates itself after every change instead of reprinting entire console

    }

    private static String[] evaluateOptions(int row, int col, Player p, int stamina) {
        ArrayList<String> optionsList = new ArrayList<>();
        if(map.checkValidSpace(row - 1, col)) {
            optionsList.add("Move up");
        }
        if(map.checkValidSpace(row + 1, col)) {
            optionsList.add("Move down");
        }
        if(map.checkValidSpace(row, col - 1)) {
            optionsList.add("Move left");
        }
        if(map.checkValidSpace(row, col + 1)) {
            optionsList.add("Move right");
        }
        int distance = calculateDistance();
        if(p.getClass().getSimpleName().equalsIgnoreCase("wizard")){
            if(distance >= 3 && stamina >= 2){
                optionsList.add("Cast Fireball at enemy (2 stamina)");
            }
            if(p.getHealth() < p.getMaxHealth() && stamina >= 5){
                optionsList.add("Cast Heal on self (5 stamina)");
            }
            if(stamina >= 3) {
                optionsList.add("Cast Shield on self(3 stamina)");
            }
        }
        else if(p.getClass().getSimpleName().equalsIgnoreCase("ranger")){
            int range = ((Ranger) p).getWeapon().idealRange;
            if(range >= distance && stamina >= 2){
                optionsList.add("Attack enemy (2 stamina)");
            }
        }
        else{
            int range = ((Warrior) p).getWeapon().idealRange;
            if(range >= distance && stamina >= 2){
                optionsList.add("Attack enemy (2 stamina)");
            }
        }
        String[] options = new String[optionsList.size()];
        for(int i = 0; i < optionsList.size(); i++){
            options[i] = optionsList.get(i);
        }
        return options;
        }

    private static int calculateDistance() {
        Player one = players.get(0);
        Player two = players.get(1);
        int x = one.getCol() - two.getCol();
        int y = one.getRow() - two.getRow();
        int distance = (int) Math.sqrt((x * x) + (y * y));
        return distance;
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
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weaponSelection);
        }
        ranger = new Ranger(name, 25, 7, 4, 12, 6, 15, weapon, armor, Icons.P, true);
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
        ArrayList<Spell> spells = new ArrayList<>();
        Spell fireBall = new Spell(2, 3, 4, SpellType.FIRE);
        Spell heal = new Spell(5, 0, 5, SpellType.HEAL);
        Spell shield = new Spell(3, 0, 4, SpellType.SHIELD);
        spells.add(fireBall);
        spells.add(heal);
        spells.add(shield);
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

        Player wizard = new Wizard(name, 20, 6, 4, 8, 10, 9, spells, Icons.P, armor, true);
        return wizard;
    }


    public static void saveGame(){
        game.setSavedMap(map);
        game.setSavedPlayers(players);
        game.setSavedIsPlayer1Turn(isPlayer1Turn);
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
        map = game.getSavedMap();
        players = game.getSavedPlayers();
        isPlayer1Turn = game.savedIsPlayer1Turn();
    }


}
