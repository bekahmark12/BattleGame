package models;

import java.io.Serializable;

public class Weapon implements Serializable {
    public final String name;
    public final WeaponType damageType;
    public final int damageRating;

    public int getIdealRange() {
        return idealRange;
    }

    public final int idealRange;

    public Weapon(String name, WeaponType damageType, int damageRating, int idealRange) {
        this.name = name;
        this.damageType = damageType;
        this.damageRating = damageRating;
        this.idealRange = idealRange;
    }


}
