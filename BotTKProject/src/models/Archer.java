package models;

public class Archer extends Player{
    private Weapon weapon;


    public Archer(String name, int maxHealth, int stamina, int strength, int agility, int wisdom, int dexterity, Weapon weapon, Armor armor, Icons icon) {
        super(name, icon);
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
        if(maxHealth > 25){
            throw new IllegalArgumentException("Archer's maxHealth cannot be more than 25");
        }
        this.maxHealth = maxHealth;
    }

    @Override
    public void setStamina(int stamina) {
        if(stamina > 7){
            throw new IllegalArgumentException("Archer's stamina cannot be more than 7");
        }
        this.stamina = stamina;
    }

    @Override
    public void setStrength(int strength) {
        if(strength > 4){
            throw new IllegalArgumentException("Archer's stamina cannot be more than 4");
        }
        this.strength = strength;
    }

    @Override
    public void setAgility(int agility) {
        if(agility > 12){
            throw new IllegalArgumentException("Archer's agility cannot be more than 12");
        }
        this.agility = agility;
    }

    @Override
    public void setWisdom(int wisdom) {
        if(wisdom > 6){
            throw new IllegalArgumentException("Archer's wisdom cannot be more than 6");
        }
        this.wisdom = wisdom;
    }

    @Override
    public void setDexterity(int dexterity) {
        if(dexterity > 15){
            throw new IllegalArgumentException("Archer's dexterity cannot be morethan 15");
        }
        this.dexterity = dexterity;
    }

    @Override
    public void setArmor(Armor armor) {
        if(armor.armorType == ArmorType.PLATE){
            throw new IllegalArgumentException("Archer's armor cannot be of type PLATE");
        }
        this.armor = armor;
    }
}
