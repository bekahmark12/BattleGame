package controllers;

import lib.ConsoleIO;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private static Map map = new Map();
    private static ArrayList<Player> players  = new ArrayList();
    private static Game game = new Game();
    static boolean player1IsAlive = true;
    static boolean player2IsAlive = true;
    private static boolean isPlayer1Turn;
    public static AIPlayerGenerator AIGenerator = new AIPlayerGenerator();
    public static AIPathing AIController = new AIPathing();

    public static void addPlayer(Player player){
        players.add(player);
    }

    public static ArrayList<Player> getPlayers(){
        return players;
    }

    public static void newMap(){
        map = new Map();
        map.randomMap(players);
    }

    public static void run(){
        mainMenu();
    }

    public static void mainMenu(){
        String[] options = {"Start a New Game", "Load a Game"};
        int selection = -1;
        do {
            selection = ConsoleIO.promptForMenuSelection(options, true, "Exit app");
            switch (selection) {
                case 1:
                    startGame();
                    break;
                case 2:
                    loadGame();
                    playGame();
                    break;
            }
        }while (selection != 0);
    }

    public static void startGame() {
        players = new ArrayList<>();
        isPlayer1Turn = flipTheCoin();
        String[] gameTypes = {"1 Player", "2 Player"};
        int selection = ConsoleIO.promptForMenuSelection(gameTypes, false, null);
        switch(selection){
            case 1:
                makePlayer(Icons.A);
                makeAIPlayer(Icons.B);
                newMap();
                playGame();
                break;
            case 2:
                makePlayer(Icons.A);
                makePlayer(Icons.B);
                newMap();
                playGame();
        }

    }

    public static void playGame(){
        player1IsAlive = true;
        player2IsAlive = true;
        boolean hasQuit = false;

        do{
            if(isPlayer1Turn){
                Player p = getPlayers().get(0);
                String message = "\n\n\n" + p.getName() + ", it's your turn. You are " + p.getIcon() + "\n\n\n";
                ConsoleIO.printString(message);
                ConsoleIO.delay(3);
                hasQuit = takeHumanTurn(p.getRow(), p.getCol(), p);

            }
            else{
                Player p = getPlayers().get(1);
                if(p.isHuman){
                    String message = "\n\n\n" + p.getName() + ", it's your turn. You are " + p.getIcon() + "\n\n\n";
                    ConsoleIO.printString(message);
                    ConsoleIO.delay(3);
                    hasQuit = takeHumanTurn(p.getRow(), p.getCol(), p);
                }
                else{
                    takeAITurn(getPlayers().get(1), getPlayers().get(0));
                }

            }
            if(!hasQuit) {
                isPlayer1Turn = !isPlayer1Turn;
            }

        }while (player1IsAlive && player2IsAlive && !hasQuit);
            if(!player1IsAlive){
                ConsoleIO.printString("\n\n\n***" + players.get(0).getName() + " has been slain! All hail the True King, " + players.get(1).getName() + "***\n\n\n\n\n");
                ConsoleIO.delay(5);
            }
            else if(!player2IsAlive){
                ConsoleIO.printString("\n\n\n***" + players.get(1).getName() + " has been slain! All hail the True King, " + players.get(0).getName() + "***\n\n\n\n\n");
                ConsoleIO.delay(5);
            }

    }

    public static void takeAITurn(Player AIPlayer, Player opponent){
        AIController.AIMoveTowardsOpponent(map.getGrid(), AIPlayer);
        int distance = calculateDistance();
        int attackRange = 0;
        if(AIPlayer instanceof Warrior){
            attackRange = ((Warrior) AIPlayer).getWeapon().getIdealRange();
        } else if(AIPlayer instanceof Ranger){
            attackRange = ((Ranger) AIPlayer).getWeapon().getIdealRange();
        }
        if(distance <= attackRange){
            combat(AIPlayer, opponent);
        }
    }


    public static boolean takeHumanTurn(int row, int col, Player p){
        int currentStamina = p.getStamina();
        if(p.getClass().getSimpleName().equalsIgnoreCase("wizard")){
            ((Wizard)p).setShield(0);
        }
        int selection = -1;
        boolean turnEnded = false;
        do{
            map.printBoard();
            ConsoleIO.delay(2);
            ConsoleIO.printString(p.getName() + ", you have " + currentStamina + " stamina left");
            String[] playerOptions = evaluateOptions(row, col, p, currentStamina);
            selection = ConsoleIO.promptForMenuSelection(playerOptions, true, "Save and exit");
            if(selection == 0){
                saveGame();
                turnEnded = true;
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
                if(isPlayer1Turn){
                    turnEnded = combat(p, getPlayers().get(1));
                    if(turnEnded){
                        player2IsAlive = false;
                    }
                }
                else{
                    turnEnded = combat(p, getPlayers().get(0));
                    if(turnEnded){
                        player1IsAlive = false;
                    }
                }
                currentStamina -= 2;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("Cast Heal on self +5HP (5 stamina)")){
                p.setHealth(p.getHealth() + 5);
                if(p.getHealth() > p.getMaxHealth()){
                    p.setHealth(p.getMaxHealth());
                }
                currentStamina -= 5;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("Cast Shield on self(3 stamina)")){
                ((Wizard)p).setShield(3);
                currentStamina -= 3;
            }
            else if(playerOptions[selection - 1].equalsIgnoreCase("Attack enemy (2 stamina)")){
                if(isPlayer1Turn){
                    turnEnded = combat(p, getPlayers().get(1));
                    if(turnEnded){
                        player2IsAlive = false;
                    }
                }
                else{
                    turnEnded = combat(p, getPlayers().get(0));
                    if(turnEnded){
                        player1IsAlive = false;
                    }
                }
                currentStamina -= 2;
            }
        } while(currentStamina > 0 && !turnEnded);
        return turnEnded;
    }

    private static boolean combat(Player attacker, Player defender) {
        boolean hasDied = false;
        int hitChance = hitChance(attacker, defender);
        System.out.println(hitChance);
        Random rng = new Random();
        boolean hasHit = ((rng.nextInt(100) + 1) <= hitChance);
        System.out.println(hasHit);
        if (hasHit) {
            int damage = calculateDamage(attacker, defender);
            System.out.println(damage);
            ConsoleIO.delay(4);
            defender.setHealth(defender.getHealth() - damage);
            if (defender.getHealth() <= 0) {
                hasDied = true;
            }
        }
        return hasDied;
    }

    private static int calculateDamage(Player attacker, Player defender) {
        int damage = 0;
        Armor armor = defender.getArmor();
        if(attacker.getClass().getSimpleName().equalsIgnoreCase("wizard")){
            damage = ((Wizard)attacker).getSpells().get(0).getAffectRating();
            damage += (attacker.getWisdom() / 3);
            if(armor.armorType == ArmorType.PADDED) {
                damage = (int) (damage * 1.5f);
            }
            else if(armor.armorType == ArmorType.PLATE){
                damage = (int)(damage * .75f);
            }

        }
        else if(attacker.getClass().getSimpleName().equalsIgnoreCase("ranger")){
            Weapon weapon = ((Ranger)attacker).getWeapon();
            damage = weapon.damageRating;
            damage += (attacker.getDexterity() / 3);
            if(weapon.damageType == WeaponType.PEIRCE){
                if(armor.armorType == ArmorType.PADDED){
                    damage = (int)(damage * 1.5f);
                }
                else if(armor.armorType == ArmorType.MAIL){
                    damage = (int)(damage * .75f);
                }
            }
            else{
                if(armor.armorType == ArmorType.MAIL){
                    damage = (int)(damage * 1.5f);
                }
                else if(armor.armorType == ArmorType.PLATE){
                    damage = (int)(damage * .75f);
                }
            }
        }
        else{
            Weapon weapon = ((Warrior)attacker).getWeapon();
            damage = weapon.damageRating;
            damage += (attacker.getStrength() / 3);
            if(weapon.damageType == WeaponType.SLASH){
                if(armor.armorType == ArmorType.MAIL){
                    damage = (int)(damage * 1.5f);
                }
                else if(armor.armorType == ArmorType.PLATE){
                    damage = (int)(damage * .75f);
                }
            }
            else{
                if(armor.armorType == ArmorType.PLATE){
                    damage = (int)(damage * 1.5f);
                }
                else if(armor.armorType == ArmorType.PADDED){
                    damage = (int)(damage * .75f);
                }
            }
        }
        if(defender.getClass().getSimpleName().equalsIgnoreCase("wizard")){
            damage -= armor.armorRating - ((Wizard)defender).getShield();
        }
        else{
            damage-= armor.armorRating;
        }
        return damage;
    }

    private static int hitChance(Player attacker, Player defender) {
        int distance = calculateDistance();
        int chance = 100;
        if(attacker.getClass().getSimpleName().equalsIgnoreCase("wizard")){
            chance -= (distance * 10);
            chance -= (defender.getAgility() * 5);
            chance += (attacker.getDexterity() * 4);
        }
        else if(attacker.getClass().getSimpleName().equalsIgnoreCase("ranger")){
            chance -= (distance * 8);
            chance -= (defender.getAgility() * 7);
            chance += (attacker.getDexterity() * 3);
        }
        else{
            chance -= (defender.getAgility() * 5);
            chance += (attacker.getDexterity() * 4);
        }
        return chance;
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
            if(distance <= 3 && stamina >= 2){
                optionsList.add("Cast Fireball at enemy (2 stamina)");
            }
            if(p.getHealth() < p.getMaxHealth() && stamina >= 5){
                optionsList.add("Cast Heal on self +5HP (5 stamina)");
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

    public static void makePlayer(Icons icon) {
        String name = ConsoleIO.promptForString("Enter a name for your character: ");
        String[] playerTypes = {"Ranger", "Warrior", "Wizard"};
        int selectPlayerType = ConsoleIO.promptForMenuSelection(playerTypes, false, null);
        switch(selectPlayerType){
            case 1:
                addPlayer(createRanger(name, icon));
                break;
            case 2:
                addPlayer(createWarrior(name, icon));
                break;
            case 3:
                addPlayer(createWizard(name, icon));
                break;
        }
    }

    public static void makeAIPlayer(Icons icon){
        Player aiPlayer = AIPlayerGenerator.generateAIPlayer(icon);
        addPlayer(aiPlayer);
    }

    public static Player createRanger(String name, Icons icon){
        Player ranger;
        Armor armor;
        Weapon weapon;

        String[] armorTypes = {"Chainmail: type: mail, rating: 5", "Gambeson: type: padded, rating: 4"};
        String[] weaponTypes = {"Longbow: type: Pierce, rating: 8, range: 3", "Longsword: type : slash, rating: 9, range: 1"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, false, null);
        int weaponSelection = ConsoleIO.promptForMenuSelection(weaponTypes, false, null);
        switch(armorSelection){
            case 1:
                armor = new Armor("Chain Mail", ArmorType.MAIL, 5);
                break;
            case 2:
                armor = new Armor("Gambeson", ArmorType.PADDED, 4);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + armorSelection);
        }

        switch(weaponSelection){
            case 1:
                weapon = new Weapon("Longbow", WeaponType.PEIRCE, 8, 3);
                break;
            case 2:
                weapon = new Weapon( "Longsword", WeaponType.SLASH, 9, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weaponSelection);
        }
        ranger = new Ranger(name, 25, 7, 4, 12, 6, 15, weapon, armor, icon, true);
        return ranger;
    }

    public static Player createWarrior(String name, Icons icon){
        Player warrior;
        Armor armor;
        Weapon weapon;
        String[] armorTypes = {"Chainmail: type: mail, rating: 5", "Steel Plate: type: plate, rating: 6"};
        String[] weaponTypes = {"Sword: type: slash, rating: 7, range: 1", "Morning Star: type: blunt, rating: 6, range: 1"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, false, null);
        int weaponSelection = ConsoleIO.promptForMenuSelection(weaponTypes, false, null);
        switch(armorSelection){
            case 1:
                armor = new Armor("Chain Mail", ArmorType.MAIL, 5);
                break;
            case 2:
                armor = new Armor("Steel Plate", ArmorType.PLATE, 6);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + armorSelection);
        }

        switch(weaponSelection){
            case 1:
                weapon = new Weapon("Sword", WeaponType.SLASH, 7, 1);
                break;
            case 2:
                weapon = new Weapon("Morning Star", WeaponType.BLUNT, 6, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + weaponSelection);
        }

        warrior = new Warrior(name, 35, 5, 10, 6, 4, 6, weapon, icon, armor, true);
        return warrior;
    }

    public static Player createWizard(String name, Icons icon){
        Armor armor;
        ArrayList<Spell> spells = new ArrayList<>();
        Spell fireBall = new Spell(2, 3, 6, SpellType.FIRE);
        Spell heal = new Spell(5, 0, 5, SpellType.HEAL);
        Spell shield = new Spell(3, 0, 4, SpellType.SHIELD);
        spells.add(fireBall);
        spells.add(heal);
        spells.add(shield);
        String[] armorTypes = {"Gambeson: type: padded, rating: 4", "Cloak: type: padded, rating: 2"};
        ConsoleIO.printString("Please select your armor: ");
        int armorSelection = ConsoleIO.promptForMenuSelection(armorTypes, false, null);
        switch(armorSelection){
            case 1:
                armor = new Armor("Gambeson", ArmorType.PADDED, 4);
                break;
            case 2:
                armor = new Armor("Cloak", ArmorType.PADDED, 2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + armorSelection);
        }

        Player wizard = new Wizard(name, 20, 7, 4, 8, 10, 9, spells, icon, armor, true);
        return wizard;
    }

    public static void saveGame(){
        game.setSavedMap(map.getGrid());
        game.setSavedPlayers(players);
        game.setSavedIsPlayer1Turn(isPlayer1Turn);
        try{
            FileOutputStream fout = new FileOutputStream("Save.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(game);
            oos.close();
            fout.close();
            ConsoleIO.printString("Game Saved");
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
        Icons[][] icons = game.getSavedMap();
        map.setGrid(icons);
        players = game.getSavedPlayers();
        isPlayer1Turn = game.savedIsPlayer1Turn();
    }

}
