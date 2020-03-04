package models;

import java.util.ArrayList;

public class Wizard extends Player{
    private Spell[] spells;

    public Spell[] getSpells() {
        return spells;
    }

    public void setSpells(Spell[] spells) {
        this.spells = getSpells();
    }

    public Wizard(String name, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, Spell[] spells, Icons icon, Armor armor, boolean isHuman) {
        super(name, icon, isHuman);
        this.spells = getSpells();
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "spells=" + spells +
                '}';
    }

    @Override
    public void setMaxHealth(int maxHealth) {

    }

    @Override
    public void setStamina(int stamina) {

    }

    @Override
    public void setStrength(int strength) {

    }

    @Override
    public void setAgility(int agility) {

    }

    @Override
    public void setWisdom(int wisdom) {

    }

    @Override
    public void setDexterity(int dexterity) {

    }

    @Override
    public void setArmor(Armor armor) {

    }
}
