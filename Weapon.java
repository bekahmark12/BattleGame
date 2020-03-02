package models;

public class Weapon {
    private final String name;
    private final WeaponType damageType;
    private final int damageRating;
    private final int idealRange;

    public Weapon(String name, WeaponType damageType, int damageRating, int idealRange) {
        this.name = name;
        this.damageType = damageType;
        this.damageRating = damageRating;
        this.idealRange = idealRange;
    }
}
