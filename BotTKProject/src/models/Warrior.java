package models;

import java.io.Serializable;

public class Warrior extends Player implements Serializable {
    private Weapon weapon;

    public Warrior(String name, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, Weapon weapon, Icons icon, Armor armor, boolean isHuman) {
        super(name, icon, isHuman);
        setWeapon(weapon);
        setMaxHealth(maxHealth);
        setStamina(stamina);
        setStrength(strength);
        setAgility(agility);
        setWisdom(wisdom);
        setDexterity(dexterity);
        setArmor(armor);
        setHealth(maxHealth);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    @Override
    public void setMaxHealth(int maxHealth) {
        if(maxHealth > 35){
            throw new IllegalStateException("Warrior's maxHealth cannot be more than ");
        }
        this.maxHealth = maxHealth;
    }

    @Override
    public void setStamina(int stamina) {
        if(stamina > 5){
            throw new IllegalArgumentException("Warrior's stamina cannot be more than 5");
        }
        this.stamina = stamina;
    }

    @Override
    public void setStrength(int strength) {
        if(strength > 10){
            throw new IllegalArgumentException("Warrior's strength cannot be more than 10");
        }
        this.strength = strength;
    }

    @Override
    public void setAgility(int agility) {
        if(agility > 6){
            throw new IllegalArgumentException("Warrior's agility cannot be more than 6");
        }
        this.agility = agility;
    }

    @Override
    public void setWisdom(int wisdom) {
        if(wisdom > 4){
            throw new IllegalArgumentException("Warrior's wisdom cannot be more than 4");
        }
        this.wisdom = wisdom;
    }

    @Override
    public void setDexterity(int dexterity) {
        if(dexterity > 6){
            throw new IllegalArgumentException("Warrior's dexterity cannot be more than 6");
        }
        this.dexterity = dexterity;
    }

    @Override
    public void setArmor(Armor armor) {
        if(armor.armorType == ArmorType.PADDED){
            throw new IllegalArgumentException("Warrior's armor cannot be of type PADDED");
        }
        this.armor = armor;
    }
}
