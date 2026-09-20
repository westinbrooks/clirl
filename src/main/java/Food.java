public class Food {
    private final String name;
    private final int saturation;

    Food(String name, int saturation) {
        this.name = name;
        this.saturation = saturation;
    }

    public static Food bread() {
        return new Food("Bread", 25);
    }

    public static Food pizza() {
        return new Food("Pizza", 50);
    }

    public String getName() {
        return name;
    }

    public int getSaturation() {
        return saturation;
    }
}
