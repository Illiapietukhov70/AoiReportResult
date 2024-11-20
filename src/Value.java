public class Value {
    private String name;
    private String value;
    private String unit;
    private String threshold; // Не используется в некоторых тестах = описывает порог срабатывания Эталона

    public Value(String name, String value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.threshold = "Not Used"; // так как String - должны понимать, что в данном тесте это не используется

    }


    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
    public String getUnit() {
        return unit;
    }
    public String getThreshold() {
        return threshold;
    }
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
}
