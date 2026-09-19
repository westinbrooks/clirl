import java.util.Random;
import java.util.Scanner;

public class User {
    // Scanner
    static Scanner input = new Scanner(System.in);
    private static String userInput;

    // User Info
    private final String name;
    private final Faction faction;
    private final Weapon weapon;

    // User Stats
    private int hitPoints;
    private int hunger; // 0 - 100 %

    // User Inventory
    private int breadAmount;
    private int pizzaAmount;


    // Turn Counter
    private int turnCounter = 1;

    public User (String name, int factionID) {
        this.name = name;
        faction = new Faction(factionID);
        this.weapon = faction.getBaseWeapon();
        this.hitPoints = faction.getBaseHitPoints();
        this.hunger = 100;
        this.breadAmount = 5;
        this.pizzaAmount = 1;
    }

    public static void userTutorial() throws InterruptedException {
        boolean tutorialActive = true;

        Core.println("\nWelcome to the CLIRL Tutorial!");

        while (tutorialActive) {
            boolean validInput = false;

            while (!validInput){
                Core.println("\nPlease choose a topic below.\n[1: Turn Structure | 2: Enemies | 3: Factions | 4: Passive Effects | 5: Exit Tutorial]");
                userInput = input.nextLine().trim();

                // Activates corresponding module according to user input
                switch (userInput) {
                    case "1" -> {
                        Core.println("Every turn starts by showcasing basic info and your current stats." +
                                "\nYou begin by choosing an action ('Attack', 'Eat', or 'Inspect Enemy')");
                        Thread.sleep(500);
                        Core.println("After you complete your chosen action, the enemy will take its turn.");
                        Thread.sleep(500);
                        Core.println("Finally, the cycle repeats until you either die or defeat the enemy.");
                        Thread.sleep(500);
                        validInput = true;
                    }
                    case "2" -> {
                        Core.println("There are multiple types of enemies you will encounter, varying in stats and abilities.");
                        Thread.sleep(500);
                        Core.println("The type of enemy you encounter is random, but you will be informed the upcoming enemy at the start of an encounter.");
                        Thread.sleep(500);
                        Core.println("Upon defeating an enemy, you will have the option to continue to fight another enemy.");
                        Thread.sleep(500);
                        validInput = true;
                    }
                    case "3" -> {
                        Core.println("Before the start of the game, you will be prompted to choose a Faction." +
                                "\nCurrently, there are 2 Factions to choose from");
                        Thread.sleep(500);
                        Core.println("The Warriors, who specialize in consistent damage with perfect accuracy," +
                                "\nand the Archers, yielding high damage in exchange for lower health and the risk of missing.");
                        Thread.sleep(500);
                        validInput = true;
                    }
                    case "4" -> {
                        Core.println("Passive effects trigger at the start of your turn.");
                        Thread.sleep(500);
                        Core.println("Currently, there are two passive effects: hunger and health regeneration.");
                        Thread.sleep(500);
                        Core.println("You will lose 10% saturation from hunger every turn." +
                                "\nIf you fall below 80% saturation, you will no longer passively heal at the start of your turn.");
                        Thread.sleep(500);
                        Core.println("However, when you're at 80% or higher saturation, you will gain 10 health at the start of your turn automatically.");
                        Thread.sleep(500);
                        Core.println("Passive effects are what allow you to survive against tough enemies over multiple encounters, so make sure to keep track of them.");
                        Thread.sleep(500);
                        validInput = true;
                    }
                    case "5" -> {
                        System.out.println();
                        tutorialActive = false;
                        validInput = true;
                    }
                    default -> Core.println("'" + userInput + "' is not a valid option.\n");
                }
            }
        }
    }

    public void userTurn () throws InterruptedException {
        boolean enemyAlive = true;

        Enemy enemy = Enemy.enemyEncounter();
        userConfirm(); // Pause game until user confirmation to stabilize printing speed

        while (enemyAlive) {
            boolean validInput = false;

            Core.println(name + "'s Turn (#" + turnCounter + ")"); // Prints turn number

            // Triggers passive effects if applicable
            if (turnCounter > 1) { // Only triggers after first turn
                userPassiveHeal();
                userPassiveHunger();
            }

            // Requests user action choice and prevents illegal option
            while (!validInput) {
                Core.println(userStats()); // Prints user stats after eligible passive effects are applied

                // Prompts User to choose an action and stores the response
                Core.println("\n[1]: Attack | [2]: Eat | [3]: Inspect " + enemy.getName() + "   ");
                userInput = input.nextLine().trim();

                switch (userInput) {
                    case "1" -> {
                        userAttack(enemy);
                        validInput = true;
                    }
                    case "2" -> {
                        if (breadAmount > 0 || pizzaAmount > 0) {
                            userEat();
                            validInput = true;
                        } else Core.println("You don't have any food! Please select another option.");
                    }
                    case "3" -> {
                        userInspect(enemy);
                        validInput = true;
                    }
                    default -> Core.println("'" + userInput + "' is not a valid option.\n");
                }
            }

            if (enemy.getHitPoints() == 0) enemyAlive = false; // Checks if enemy is still alive

            if (enemyAlive) {
                Thread.sleep(500);
                enemy.enemyTurn(this, enemy); // Executes enemy action
            }

            if (getHitPoints() == 0) userDeath(); // Checks if user is still alive

            turnCounter++;
        }
    }

    public boolean userContinue () throws InterruptedException {
        boolean validInput = false;
        while (!validInput) {
            Core.println("Would you like to continue? [Y/N]: ");
            userInput = input.nextLine().trim();
            if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N")) validInput = true;
            else Core.println("'" + userInput + "' is not a valid option.\n");
        }
        return userInput.equalsIgnoreCase("Y");
    }

    private void userAttack (Enemy enemy) throws InterruptedException {
        double damage = calculateDamage();
        Random accuracyCheck = new Random();

        if (accuracyCheck.nextDouble() * 100 < calculateAccuracy()) Enemy.enemyHurt(enemy, damage); // Triggers attack at random according to user accuracy
        else Core.println("You missed!"); // Triggers if user failed accuracy check
    }

    public static void userHurt (User user, Enemy enemy, double damage) throws InterruptedException {
        user.hitPoints -= (int) (damage + 0.5);
        user.hitPoints = user.getHitPoints(); // Prevents negative hitPoints value

        Core.println("\n" + enemy.getName() + " dealt " + (int) damage + " damage!\n" + "You're now at " + user.hitPoints + " health.\n");

        Thread.sleep(500);
    }

    private void userEat () throws InterruptedException {
        boolean hasBread = breadAmount > 0;
        boolean hasPizza = pizzaAmount > 0;
        boolean validInput = false;

        // Build String containing the User's current food inventory based on its contents
        StringBuilder foodInventoryBuilder = new StringBuilder("You currently have "); // Initialize String

        // Builds String according to food inventory contents
        if (hasBread) foodInventoryBuilder.append(breadAmount).append(" Bread");
        if (hasBread && hasPizza) foodInventoryBuilder.append(" and ");
        if (hasPizza)  foodInventoryBuilder.append(pizzaAmount).append(" pizza");

        foodInventoryBuilder.append(".");

        String foodInventory = foodInventoryBuilder.toString(); // Converts to printable String
        Core.println(foodInventory); // Prints built String

        // Build String containing the User's current food choices based on their food inventory's contents
        StringBuilder foodPromptBuilder = new StringBuilder("What would you like to eat? "); // Initialize String

        // Builds String according to food inventory contents
        if (hasBread) foodPromptBuilder.append("[1]: Bread ");
        if (hasBread && hasPizza) foodPromptBuilder.append("| [2]: Pizza");
        if (hasPizza && !hasBread) foodPromptBuilder.append("[1]: Pizza");

        foodInventoryBuilder.append("   ");

        String foodPrompt = foodPromptBuilder.toString(); // Converts to printable String

        // Consumes foodstuff based on the User's input and current food inventory contents
        while (!validInput) {
            Core.println(foodPrompt); // Prints built String
            userInput = input.nextLine().trim();

            // Executes if User has Bread and Pizza
            if (hasBread && hasPizza) {
                switch (userInput) {
                    case "1" -> {
                        Food bread = Food.bread(); // Initialize bread object to pull data from
                        breadAmount--;
                        hunger += bread.getSaturation();
                        if (hunger > 100) hunger = 100; // Ensures hunger doesn't exceed 100%
                        Core.println("You ate " + bread.getName() + " and are now " + hunger + "% full.");
                        validInput = true;
                    }
                    case "2" -> {
                        Food pizza = Food.pizza(); // Initialize pizza object to pull data from
                        pizzaAmount--;
                        hunger += pizza.getSaturation();
                        if  (hunger > 100) hunger = 100; // Ensures hunger doesn't exceed 100%
                        Core.println("You ate " + pizza.getName() + " and are now " + hunger + "% full.");
                        validInput = true;
                    }
                    default -> Core.println("'" + userInput + "' is not a valid option.\n");
                }
            }

            // Executes if User only has Bread
            else if (hasBread) {
                if (userInput.equals("1")) {
                    Food bread = Food.bread(); // Initialize bread object to pull data from
                    breadAmount--;
                    hunger += bread.getSaturation();
                    if (hunger > 100) hunger = 100; // Ensures hunger doesn't exceed 100%
                    Core.println("You ate " + bread.getName() + "and are now " + hunger + "% full.");
                    validInput = true;
                } else Core.println("'" + userInput + "' is not a valid option.\n");
            }

            // Executes if User only has Pizza
            else if (hasPizza) {
                if (userInput.equals("1")) {
                    Food pizza = Food.pizza(); // Initialize pizza object to pull data from
                    pizzaAmount--;
                    hunger += pizza.getSaturation();
                    if  (hunger > 100) hunger = 100; // Ensures hunger doesn't exceed 100%
                    Core.println("You ate " + pizza.getName() + " and are now " + hunger + "% full.");
                    validInput = true;
                } else Core.println("'" + userInput + "' is not a valid option.\n");
            }
        }
    }

    private void userPassiveHeal () throws InterruptedException {
        int healAmount = 10; // May be adjusted for balancing purposes
        if (hunger >= 80) {
            hitPoints += healAmount;
            Core.println("You healed " + healAmount + " health for being full, and now have " + hitPoints + " health!"); // May remove this since it's implied after the tutorial
        }
        else Core.println("You are hungry! Eat some food to passively heal.");
    }

    private void userPassiveHunger () {
        int hungerAmount = 10; // May be adjusted for balancing purposes
        if (hunger > 0) {
            hunger = Math.max(0, hunger - hungerAmount); // Avoid notifying user since this is implied and would only clutter terminal
        }
    }

    private void userInspect (Enemy enemy) throws InterruptedException {
        Core.println(enemy.enemyStats());
    }

    private void userDeath () throws InterruptedException {
        Core.println("\n\n\nYou died! Press any key to exit.");
        userInput = input.nextLine().trim();
        System.exit(0);
    }

    private void userConfirm () throws InterruptedException {
        Core.println("\nPress 'Enter' to continue.");
        userInput = input.nextLine().trim();
    }

    public String userStats () {
        return "Faction: " + faction.getName() + " | Weapon: " + weapon.getName()
                + "\nHP: " + getHitPoints() + " | Hunger: " + getHunger() + "% | Damage: " + calculateDamage() + " | Accuracy: " + calculateAccuracy() + "%";
    }

    private double calculateDamage () { // Applies any valid buffs & debuffs to the User's damage value
        // Will adjust formula if / when I develop the buff & debuff system
        return weapon.getBaseDamage();
    }

    private double calculateAccuracy () { // Applies any valid buffs & debuffs to the User's accuracy value
        // Will adjust formula if / when I develop the buff & debuff system
        return weapon.getBaseAccuracy();
    }

    public String getName () {
        return name;
    }

    public int getHitPoints () {
        if (hitPoints < 0) hitPoints = 0;
        return hitPoints;
    }

    public int getHunger () {
        return hunger;
    }
}
