public class Weapon {
    private String name;
    private int type; // 0: Unknown, 1: Melee, 2: Ranged, 3: Magic
    private int baseDamage; // >= 0 flat
    private int baseAccuracy; // 0 - 100 %

    private Weapon(String name, int type, int baseDamage, int baseAccuracy) {
        this.name = name;
        this.type = type;
        this.baseDamage = baseDamage;
        this.baseAccuracy = baseAccuracy;
    }

    public static Weapon sword() {
        return new Weapon("Sword", 1, 20, 100);
    }

    public static Weapon bow() {
        return new Weapon("Bow", 2, 25, 80);
    }

    String getName () {
        return name;
    }

    int getType () {
        return type;
    }

    int getBaseDamage () {
        return baseDamage;
    }

    int getBaseAccuracy () {
        return baseAccuracy;
    }
}
