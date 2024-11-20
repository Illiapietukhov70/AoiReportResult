import java.util.ArrayList;
import java.util.Arrays;

public class Pin {
    private String name;
    private String type;
    private String group;
    private int partNumber;
    private Geometry geometry;
    private Status status;
    private ArrayList<Feature> features;


    public Pin(String name, String type, String group, int partNumber, Geometry geometry, Status status) {
        this.name = name;
        this.type = type;
        this.group = group;
        this.partNumber = partNumber;
        this.geometry = geometry;
        this.status = status;
        ArrayList<Feature> features = new ArrayList<>();
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

    public Geometry getGeometry() {
        return geometry;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void addFeature (Feature feature) {
        features.add(feature);
    }
}


