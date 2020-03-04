package models;


import java.io.Serializable;

public abstract class Player implements Serializable {
    private String name;
    public final boolean isHuman;
    private int health;
    protected int maxHealth;
    protected int stamina;
    protected int strength;
    protected int agility;
    protected int wisdom;
    protected int dexterity;
    private Icons icon;
    protected Armor armor;

    public Player(String name, Icons icon, boolean isHuman) {
        setName(name);
        setIcon(icon);
        this.isHuman = isHuman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public abstract void setMaxHealth(int maxHealth);

    public int getStamina() {
        return stamina;
    }

    public abstract void setStamina(int stamina);

    public int getStrength() {
        return strength;
    }

    public abstract void setStrength(int strength);

    public int getAgility() {
        return agility;
    }

    public abstract void setAgility(int agility);

    public int getWisdom() {
        return wisdom;
    }

    public abstract void setWisdom(int wisdom);

    public int getDexterity() {
        return dexterity;
    }

    public abstract void setDexterity(int dexterity);

    public Icons getIcon() {
        return icon;
    }

    public void setIcon(Icons icon) {
        this.icon = icon;
    }

    public Armor getArmor(){
        return armor;
    }

    public abstract void setArmor(Armor armor);

}
