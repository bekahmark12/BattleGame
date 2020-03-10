package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Wizard extends Player implements Serializable {
    private ArrayList<Spell> spells = new ArrayList<>();

    public Wizard(String name, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, ArrayList<Spell> spells, Icons icon, Armor armor, boolean isHuman) {
        super(name, icon, isHuman);
        setSpells(spells);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setStamina(stamina);
        setStrength(strength);
        setAgility(agility);
        setWisdom(wisdom);
        setDexterity(dexterity);
        setArmor(armor);
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = getSpells();
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        if(maxHealth > 20){
            throw new IllegalStateException("Wizard's maxHealth cannot be more than 20");
        }
        this.maxHealth = maxHealth;
    }

    @Override
    public void setStamina(int stamina) {
        if(stamina > 6){
            throw new IllegalStateException("Wizard's stamina cannot be more than 6");
        }
        this.stamina = stamina;
    }

    @Override
    public void setStrength(int strength) {
        if(strength > 4){
            throw new IllegalStateException("Wizard's strength cannot be more than 4");
        }
        this.strength = strength;
    }

    @Override
    public void setAgility(int agility) {
        if(agility > 8){
            throw new IllegalStateException("Wizard's agility cannot be more than 8");
        }
        this.agility = agility;
    }

    @Override
    public void setWisdom(int wisdom) {
        if(wisdom > 10){
            throw new IllegalStateException("Wizard's wisdom cannot be more than 10");
        }
        this.wisdom = wisdom;
    }

    @Override
    public void setDexterity(int dexterity) {
        if(dexterity > 9){
            throw new IllegalStateException("Wizard's dexterity cannot be more than 9");
        }
        this.dexterity = dexterity;
    }

    @Override
    public void setArmor(Armor armor) {
        if(armor.armorType != ArmorType.PADDED){
            throw new IllegalStateException("Wizard's armorType MUST be PADDED");
        }
        this.armor = armor;
    }
}
