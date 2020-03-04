package models;

public class Warrior extends Player{
    private Weapon weapon;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Warrior(String name, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, Weapon weapon, Icons icon, Armor armor, boolean isHuman) {
        super(name, icon, isHuman);
        this.weapon = getWeapon();
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
