package models;

import java.io.Serializable;

public class Spell implements Serializable {
    private final int cost;
    private final int idealRange;
    private final int affectRating;
    private final SpellType type;

    public int getCost() {
        return cost;
    }

    public int getIdealRange() {
        return idealRange;
    }

    public int getAffectRating() {
        return affectRating;
    }

    public SpellType getType() {
        return type;
    }

    public Spell(int cost, int idealRange, int affectRating, SpellType type) {
        this.cost = getCost();
        this.idealRange = getIdealRange();
        this.affectRating = getAffectRating();
        this.type = getType();
    }
}
