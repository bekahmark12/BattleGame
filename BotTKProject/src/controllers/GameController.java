package controllers;

import lib.ConsoleIO;
import models.*;

public class GameController {
    public static void run(){

        String[] playerTypes = {"Archer", "Warrior", "Wizard"};
        Armor armor;
        

        int selectPlayerType = ConsoleIO.promptForMenuSelection(playerTypes, true);
        switch(selectPlayerType){
            case 1:
                String armorType[] = {"Mail", "Padded"};
                ConsoleIO.printString("Please select an armor type: ");
                int armorSelection = ConsoleIO.promptForMenuSelection(armorType, true);
                switch(armorSelection){
                    case 1:
                        armor = new Armor("Chainmail", ArmorType.MAIL, 4);

                }
                Player archer = new Archer();
                break;
            case 2:
                Player warrior = new Warrior();
                break;
            case 3:
                Player wizard = new Wizard();
                break;
        }

    }
}
