package models;

public class Armor {
    public final String name;
    public final ArmorType armorType;
    public final int armorRating;

    public Armor(String name, ArmorType armorType, int armorRating) {
        this.name = name;
        this.armorType = armorType;
        this.armorRating = armorRating;
    }
}
