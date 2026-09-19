public class Core {
    private static int printSpeed = 25;

    public static void print(String string) throws InterruptedException { // Prints characters of a String according to defined time in milliseconds
        for (char character : string.toCharArray()) { // Prints each character one-by-one
            System.out.print(character);
            System.out.flush(); // Forces display of current character during execution
            Thread.sleep(printSpeed); // Pause according to defined time in milliseconds
        }
    }

    public static void println(String string) throws InterruptedException { // Prints characters of a String according to defined time in milliseconds
        for (char character : string.toCharArray()) { // Prints each character one-by-one
            System.out.print(character);
            System.out.flush(); // Forces display of current character during execution
            Thread.sleep(printSpeed); // Pause according to defined time in milliseconds
        }

        System.out.println();
    }

    public static void setPrintSpeed(int printSpeed) {
        Core.printSpeed = printSpeed;
    }
}
