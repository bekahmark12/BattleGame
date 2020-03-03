package models;

public abstract class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int stamina;
    private int strength;
    private int agility;
    private int wisdom;
    private int dexterity;
    private Icons icon;

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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public Icons getIcon() {
        return icon;
    }

    public void setIcon(Icons icon) {
        this.icon = icon;
    }

    public Player(String name, int health, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.stamina = stamina;
        this.strength = strength;
        this.agility = agility;
        this.wisdom = wisdom;
        this.dexterity = dexterity;
        this.icon = icon;
    }
}
