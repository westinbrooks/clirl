public record Faction(String name, int baseHitPoints, Weapon baseWeapon) {

    public static Faction warrior() {
        return new Faction("Warrior", 100, Weapon.sword());
    }

    public static Faction archer() {
        return new Faction("Archer", 80, Weapon.bow());
    }
}
