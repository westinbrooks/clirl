import java.util.Random;

public class Enemy {
    // Enemy Info
    private final String name;
    private final boolean isBoss;

    // Enemy Stats
    private int hitPoints;
    private double damage;
    private final double accuracy;

    Enemy(String name, boolean isBoss, int baseHitPoints, int baseDamage, int baseAccuracy) {
        this.name = name;
        this.isBoss = isBoss;
        this.hitPoints = baseHitPoints;
        this.damage = baseDamage;
        this.accuracy = baseAccuracy;
    }

    public static Enemy zombie() {
        return new Enemy("Zombie", false, 50, 10, 100);
    }

    public static Enemy skeleton() {
        return new Enemy("Skeleton", false, 40, 20, 80);
    }

    public static Enemy giant() {
        return new Enemy("Giant", true, 100, 15, 100);
    }

    public String enemyStats() {
        String stats = "Enemy: " + name;

        if (isBoss) stats += " (Boss)"; // Adds Boss title if appropriate
        stats += "\nHP: " + hitPoints + " | Accuracy: " + accuracy + "% | Damage: " + damage;

        return stats;
    }

    public static Enemy enemyEncounter(boolean isBossRound) throws InterruptedException {
        Enemy enemy = null; // Initializes enemy object

        // Normal enemy encounter
        if (!isBossRound) {
            // Assigns an enemy type based on the returned RNG value
            switch (new Random().nextInt(1, 3)) {
                case 1 -> enemy = zombie();
                case 2 -> enemy = skeleton();
                default -> throw new IllegalArgumentException("Invalid enemyEncounterRNG");
            }
        }

        // Boss encounter
        if (isBossRound) {
            switch (new Random().nextInt(1, 2)) { // Currently guarantees Giant encounter until new bosses are added
                case 1 -> enemy = giant();
                default -> throw new IllegalArgumentException("Invalid enemyEncounterRNG");
            }
        }

        Core.println("\n\n\nYou encountered a " + enemy.name + "!");

        return enemy;
    }

    public void enemyTurn(User user, Enemy enemy) throws InterruptedException { // May add further functionality, such as independent item usage, in the future
        enemyAttack(user, enemy);
    }

    public void enemyAttack(User user, Enemy enemy) throws InterruptedException {
        double damage = calculateDamage(enemy);
        Random accuracyCheck = new Random();

        if (accuracyCheck.nextDouble() * 100 < enemy.accuracy) User.userHurt(user, enemy, damage); // Triggers attack at random according to enemy accuracy
        else Core.println(enemy.name + " missed!\n"); // Triggers if enemy failed accuracy check
    }

    public static void enemyHurt(Enemy enemy, double damage) throws InterruptedException {
        enemy.hitPoints -= (int) (damage + 0.5);
        enemy.hitPoints = enemy.getHitPoints(); // Prevents negative hitPoints value

        // Checks if enemy is still alive and prints relevant response
        if (enemy.hitPoints == 0) Core.println("You dealt " + (int) damage + " damage!\nYou killed " + enemy.name + "!");
        else Core.println("You dealt " + (int) damage + " damage!\n" + enemy.name + " is now at " + enemy.hitPoints + " health.");
    }

    private double calculateDamage(Enemy enemy) { // Applies any valid buffs & debuffs to the User's damage value
        damage = enemy.damage; // Will adjust formula if / when I develop the buff & debuff system
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        if (hitPoints < 0) hitPoints = 0;
        return hitPoints;
    }
}
