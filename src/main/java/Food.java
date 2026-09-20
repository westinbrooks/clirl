public class Food {
    private final String name;
    private final int instantHealth;
    private final int saturation;

    Food(String name, int instantHealth, int saturation) {
        this.name = name;
        this.instantHealth = instantHealth;
        this.saturation = saturation;
    }

    public static Food bread() {
        return new Food("Bread", 10, 25);
    }

    public static Food pizza() {
        return new Food("Pizza", 20, 50);
    }

    public String getName() {
        return name;
    }

    public int getInstantHealth() {
        return instantHealth;
    }

    public int getSaturation() {
        return saturation;
    }
}
