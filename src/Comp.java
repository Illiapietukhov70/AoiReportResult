import java.util.ArrayList;

public class Comp {
    // Class="Comp" Name="R36" Type="R0805" Group="Resistor" PartNumber="10139"
    private String className, name, type, group;
    private int partNumber;
    private Geometry geometry;
    private Status status;
    private ArrayList<Feature> features;
    private ArrayList<Pin> pins;

    public Comp(String className, String name, String type, String group, int partNumber, Geometry geometry, Status status) {
        this.className = className;
        this.name = name;
        this.type = type;
        this.group = group;
        this.partNumber = partNumber;
        this.geometry = geometry;
        this.status = status;
        ArrayList<Feature> features = new ArrayList<>();
        ArrayList<Pin> pins;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public ArrayList<Pin> getPins() {
        return pins;
    }

    public void addPins(Pin pin) {
        pins.add(pin);
    }
}