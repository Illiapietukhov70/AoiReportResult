import java.util.ArrayList;

public class Feature {
    private String name; // Имя теста-проверки "Pin.Meniscus" для пина или "Reference" для корпуса чипа или резистора
    private String methodName; // Имя метода - чем мы проверяем, точнее описание инструмета проверки
    private ArrayList<Value> values; // Список с результатами тестов (экземпляры класса Value)
    private Status status
            ; // Окончательный результат проверки (сравнение Тест - Эталон) всех тестов = экземпляр класса Status

    public Feature(String name, String methodName, Status status) {
      this.name = name;
      this.methodName = methodName;
      this.status = status;
      this.values = new ArrayList<>();
  }

    public String getName() {
        return name;
    }

    public String getMethodName() {
        return methodName;
    }

    public Status getStatus() {
        return status;
    }
    public void addValue(Value value) {
        values.add(value);
    }
    public ArrayList<Value> getValues(){
        return values;
    }
}

