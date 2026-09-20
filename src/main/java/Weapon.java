public class Weapon {
    private final String name;
    private final int baseDamage;
    private final int baseAccuracy;

    private Weapon(String name, int baseDamage, int baseAccuracy) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.baseAccuracy = baseAccuracy;
    }

    public static Weapon sword() {
        return new Weapon("Sword", 20, 100);
    }

    public static Weapon bow() {
        return new Weapon("Bow", 25, 80);
    }

    String getName() {
        return name;
    }

    int getBaseDamage() {
        return baseDamage;
    }

    int getBaseAccuracy() {
        return baseAccuracy;
    }
}
