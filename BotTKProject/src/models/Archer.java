package models;

public class Archer extends Player{
    private Weapon weapon;


    public Archer(String name, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, Weapon weapon, Icons icon) {
        super(name, icon);
        setWeapon(weapon);
        setMaxHealth(maxHealth);
        setStamina(stamina);
        setStrength(strength);
        setAgility(agility);
        setWisdom(wisdom);
        setDexterity(dexterity);

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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
