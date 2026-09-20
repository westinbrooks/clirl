import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Utilities
        Scanner input = new Scanner(System.in);

        // Internals
        boolean gameActive = true;
        boolean validInput = false;

        // User Info
        User user = null;
        String userInput;
        String userName = "";

        // Introduction
        Core.println("Welcome to CLIRL!\n");

        // Request and store user's name
        while (!validInput) {
            Core.println("What is your name?:");
            userInput = input.next();
            if (!userInput.isEmpty()) { // Only runs if the input is NOT empty
                userName = userInput;
                validInput = true;
            }
            else {
                Core.println("'" + userInput + "' is not a valid name!");
            }
        } validInput = false;

        // Request and store user's desired print speed
        while (!validInput) {
            Core.println("How fast would you like text to appear? [1: Instant | 2: Fast | 3: Default | 4: Slow]");
            userInput = input.next();
            switch (userInput) {
                case "1" -> {
                    Core.setPrintSpeed(0);
                    Core.setSleepSpeed(0);
                    validInput = true;
                }
                case "2" -> {
                    Core.setPrintSpeed(10);
                    Core.setSleepSpeed(200);
                    validInput = true;
                }
                case "3" -> validInput = true; // printSpeed: 25ms | sleepSpeed: 500ms
                case "4" -> {
                    Core.setPrintSpeed(50);
                    Core.setSleepSpeed(1000);
                    validInput = true;
                }
                default -> Core.println("'" + userInput + "' is not a valid input!");
            }
        } validInput = false;

        // Initiates Tutorial upon request
        while (!validInput) {
            Core.println("Would you like a tutorial on how to play? [Y/N]");
            userInput = input.next();
            if (userInput.equalsIgnoreCase("Y")) {
                // Tutorial
                User.userTutorial();
                validInput = true; // Exit Loop
            }
            else if (userInput.equalsIgnoreCase("N")) {
                // Skip Tutorial
                validInput = true; // Exit Loop
            }
            else { // Reprompt user
                Core.println("'" + userInput + "' is not a valid input!");
            }
        } validInput = false;

        // Request and store user's faction
        while (!validInput) {
            Core.print("What is your faction? [1: Warrior, 2: Archer]: ");
            userInput = input.next();
            if (userInput.equals("1")) {
                user = new User(userName, Faction.warrior());
                validInput = true;
            }
            else if (userInput.equals("2")) {
                user = new User(userName, Faction.archer());
                validInput = true;
            }
            else Core.println("'" + userInput + "' is not a valid input!");
        }

        // Game Loop
        while (gameActive) {
            user.userTurn(); // Initiates round with user's turn
            if (!user.userContinue()) gameActive = false; // Verifies game status
        }

        // Game Exit
        System.exit(0);
    }
}