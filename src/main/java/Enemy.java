import java.util.Random;

public class Enemy {
    private final String name;
    private final int baseHitPoints;
    private int hitPoints;
    private final int baseDamage;
    private double damage;
    private final int baseAccuracy;
    private double accuracy;

    Enemy(String name, int baseHitPoints, int baseDamage, int baseAccuracy) {
        this.name = name;
        this.baseHitPoints = baseHitPoints;
        this.hitPoints = baseHitPoints;
        this.baseDamage = baseDamage;
        this.damage = baseDamage;
        this.baseAccuracy = baseAccuracy;
        this.accuracy = baseAccuracy;
    }

    public static Enemy zombie() {
        return new Enemy("Zombie", 100, 10, 100);
    }

    public static Enemy skeleton() {
        return new Enemy("Skeleton", 80, 15, 80);
    }

    public String enemyStats () {
        return "Enemy: " + name
                + "\nHP: " + hitPoints + " | Accuracy: " + accuracy + "% | Damage: " + damage;
    }

    public static Enemy enemyEncounter () throws InterruptedException {
        Enemy enemy;
        // Returns 1 to n, where n represents the number of enemy types
        int enemyEncounterRNG = new Random().nextInt(1, 3); // Generates a random integer from origin to bound-1

        // Assigns an enemy type to object corresponding to the RNG value
        if (enemyEncounterRNG == 1) {
            enemy = zombie();
        }
        else if (enemyEncounterRNG == 2) {
            enemy = skeleton();
        }
        else {
            throw new IllegalArgumentException("Invalid enemyEncounterRNG: " + enemyEncounterRNG);
        }

        Core.println("\n\n\nYou encountered a " + enemy.name + "!");

        return enemy;
    }

    public void enemyTurn (User user, Enemy enemy) throws InterruptedException { // May add further functionality, such as independent item usage, in the future
        enemyAttack(user, enemy);
    }

    public void enemyAttack(User user, Enemy enemy) throws InterruptedException {
        double damage = calculateDamage(enemy);
        Random accuracyCheck = new Random();

        if (accuracyCheck.nextDouble() * 100 < enemy.accuracy) User.userHurt(user, enemy, damage); // Triggers attack at random according to enemy accuracy
        else Core.println(enemy.name + " missed!"); // Triggers if enemy failed accuracy check
    }

    public static void enemyHurt (Enemy enemy, double damage) throws InterruptedException {
        enemy.hitPoints -= (int) (damage + 0.5);
        enemy.hitPoints = enemy.getHitPoints(); // Prevents negative hitPoints value
        Core.println("You dealt " + (int) damage + " damage!\n" + enemy.name + " is now at " + enemy.hitPoints + " health.");
    }

    private double calculateDamage (Enemy enemy) { // Applies any valid buffs & debuffs to the User's damage value
        damage = enemy.damage; // Will adjust formula if / when I develop the buff & debuff system
        return damage;
    }

    public String getName () {
        return name;
    }

    public int getHitPoints () {
        if (hitPoints < 0) hitPoints = 0;
        return hitPoints;
    }

    public int getBaseHitPoints () {
        return baseHitPoints;
    }

    public int getBaseDamage () {
        return baseDamage;
    }

    public int getBaseAccuracy () {
        return baseAccuracy;
    }
}
