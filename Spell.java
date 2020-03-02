package models;

public class Spell {
    private final int cost;
    private final int idealRange;
    private final int affectRating;
    private final SpellType type;

    public Spell(int cost, int idealRange, int affectRating, SpellType type) {
        this.cost = cost;
        this.idealRange = idealRange;
        this.affectRating = affectRating;
        this.type = type;
    }
}
