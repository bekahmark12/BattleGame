package models;

import java.util.ArrayList;

public class Wizard extends Player{
    private ArrayList<Spell> spells;

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;


    }

    public Wizard(String name, int health, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, ArrayList<Spell> spells) {
        super(name, health, maxHealth, stamina, strength, agility, wisdom, dexterity);
        this.spells = spells;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "spells=" + spells +
                '}';
    }
}
