package models;

public class Warrior extends Player{
    private Weapon weapon;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Warrior(String name, int health, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, Weapon weapon) {
        super(name, health, maxHealth, stamina, strength, agility, wisdom, dexterity);
        this.weapon = getWeapon();
    }
}
