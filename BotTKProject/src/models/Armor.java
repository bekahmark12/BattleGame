package models;

import models.enums.ArmorType;

import java.io.Serializable;

public class Armor implements Serializable {
    public final String name;
    public final ArmorType armorType;
    public final int armorRating;

    public Armor(String name, ArmorType armorType, int armorRating) {
        this.name = name;
        this.armorType = armorType;
        this.armorRating = armorRating;
    }
}
