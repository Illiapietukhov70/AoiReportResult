import java.util.HashMap;

public class Status {
    private HashMap<String, Boolean> overall; // Словарь РЕЗУЛЬТАТОВ булевых данных на основе теста
    private HashMap<String, Boolean> inspection;
    private HashMap<String, Boolean> classification;
    private HashMap<String, Boolean> repair;

    public Status() {
        this.overall = new HashMap<>() ;
        this.inspection = new HashMap<>();
        this.classification = new HashMap<>();
        this.repair = new HashMap<>();
    }

    public boolean getOverall(String key) {
        return overall.get(key);
    }

    public void setOverall(String key, Boolean value) {
        overall.put(key, value);
    }

    public boolean getInspection(String key) {
        return inspection.get(key);
    }

    public void setInspection(String key, Boolean value) {
        inspection.put(key, value);
    }

    public boolean getClassification(String key) {
        return classification.get(key);
    }

    public void setClassification(String key, Boolean value) {
        classification.put(key, value);
    }

    public boolean getRepair(String key) {
        return repair.get(key);
    }

    public void setRepair(String key, Boolean value) {
        repair.put(key, value);
    }
}
