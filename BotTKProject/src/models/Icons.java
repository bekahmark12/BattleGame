package models;

public enum Icons {
    P(0),
    E(5),
    T(3),
    O(1),
    _(0);

    public final int cost;

    private Icons(int cost) {
        this.cost = cost;
    }
}
