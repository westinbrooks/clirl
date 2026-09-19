public class Faction {
    private final String name;
    private final int id; // 0: Unknown, 1: Warrior, 2: Archer, 3: Wizard
    private final int baseHitPoints;
    private final Weapon baseWeapon;

    public Faction(int id) {
        this.id = id;

        if (id == 1) {
            this.name = "Warrior";
            this.baseHitPoints = 100;
            this.baseWeapon = Weapon.sword();
        }
        else if (id == 2) {
            this.name = "Archer";
            this.baseHitPoints = 80;
            this.baseWeapon = Weapon.bow();
        }
        else {
            throw new IllegalArgumentException("Invalid faction ID: " + id);
        }
    }

    public Weapon getBaseWeapon() {
        return baseWeapon;
    }

    public int getBaseHitPoints() {
        return baseHitPoints;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
