import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ObjectMain {
    protected Geometry geometry;
    protected Status status;
    ArrayList<Node> features;
    protected Node nodeMain;
    ArrayList<Node> fidMarksArray;
    ArrayList<Node> boards;

    public ObjectMain(Node nodeMain) {
        this.nodeMain = nodeMain;
        this.geometry = new Geometry(); //Создаем экземпляр класса Geometry - для записи данных Геометрии Объекта (Платы)
        this.status = new Status();// Новый статус для платы

        this.fidMarksArray = new ArrayList<>(); // Массив для 3-х объектов Fiducial.Mark
        this.boards = new ArrayList<>();// List для вставных плат входящих в в ObjectMain (если Плата состоит из плат)
        this.features = new ArrayList<>();

        NodeList startNode = this.nodeMain.getChildNodes();
        //System.out.println("Object complete " + startNode.getLength());

        for (int i = 0; i < startNode.getLength(); i++) {
            if (startNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                switch (startNode.item(i).getNodeName()) {
                    case "Geometry" -> setGeometry(startNode.item(i));// Записываем Геометрию
                    case "Status" -> setStatus(startNode.item(i));// Записываем Статус
                    case "Features" -> addFeature(startNode.item(i)); // Формируем list с Feature
                    case "Object" -> switchObject(startNode.item(i));// Формируем list fidMarksArray или list boards, в зависимости от имени Ноде
                    }
                }

            }

        }
    protected void setStatus (Node inputStatus) { // Передаем Ноде (startNode.item(i) с именем Status) для обработки Статуса
        NodeList nodeNext = inputStatus.getChildNodes(); // Получаем список детей входящего Нода
        for (int i = 0; i < nodeNext.getLength(); i++) {// Перебираем список
            if (nodeNext.item(i).getNodeType() == Node.ELEMENT_NODE) {
                switch (nodeNext.item(i).getNodeName()) {
                    case "Overall" -> setDictOverall(nodeNext.item(i));// Если имя под.ребенка Overall - пишем в Status словарь Overall
                    case "Inspection" -> setDictInspection(nodeNext.item(i));// то-же самое
                    case "Classification" -> setDictClassification(nodeNext.item(i));
                    case "Repair" -> setDictRepair(nodeNext.item(i));
                }

            }
        }
    }
    protected void setDictOverall (Node nodeDict) {// передаем под.ребенка с именем Overall для записи словаря Status.Overall
        NamedNodeMap attr = nodeDict.getAttributes();
        for (int j = 0; j < attr.getLength(); j++) {
            status.setOverall(attr.item(j).getNodeName(), Boolean.parseBoolean(attr.item(j).getNodeValue().toLowerCase()));
        }
    }

    protected void setDictInspection (Node nodeDict) {// то-же самое
        NamedNodeMap attr = nodeDict.getAttributes();
        for (int j = 0; j < attr.getLength(); j++) {
            status.setInspection(attr.item(j).getNodeName(), Boolean.parseBoolean(attr.item(j).getNodeValue().toLowerCase()));
        }
    }
    protected void setDictClassification (Node nodeDict) {
        NamedNodeMap attr = nodeDict.getAttributes();
        for (int j = 0; j < attr.getLength(); j++) {
            status.setClassification(attr.item(j).getNodeName(), Boolean.parseBoolean(attr.item(j).getNodeValue().toLowerCase()));
        }
    }
    protected void setDictRepair (Node nodeDict) {
        NamedNodeMap attr = nodeDict.getAttributes();
        for (int j = 0; j < attr.getLength(); j++) {
            status.setRepair(attr.item(j).getNodeName(), Boolean.parseBoolean(attr.item(j).getNodeValue().toLowerCase()));
        }
    }
    protected Status getStatus (){
        return this.status;
    }
    protected void setGeometry(Node inputGeometry){
        NodeList nodeNext = inputGeometry.getChildNodes(); // Получаем список детей входящего Нода
        for (int i = 0; i < nodeNext.getLength(); i++) {// Перебираем список
            if (nodeNext.item(i).getNodeType() == Node.ELEMENT_NODE) {
                switch (nodeNext.item(i).getNodeName()) {
                    case "Position" -> setPosition(nodeNext.item(i), this.geometry.getPosition());
                    case "PositionLocal" -> setPosition(nodeNext.item(i), this.geometry.getPositionLocal());
                    case "Rotation" -> setPosition(nodeNext.item(i), this.geometry.getRotation());
                    case "RotationLocal" -> setPosition(nodeNext.item(i), this.geometry.getRotationLocal());
                    case "Size" -> setPosition(nodeNext.item(i), this.geometry.getSize());
                    case "SizeLocal" -> setPosition(nodeNext.item(i), this.geometry.getSizeLocal());
                }

            }
        }

    }
    private void setPosition (Node nodeArray, int[] nameArray){
        NamedNodeMap attr = nodeArray.getAttributes();
        for (int j = 0; j < attr.getLength(); j++) {
            nameArray[j] = Integer.parseInt(attr.item(j).getNodeValue());
        }

    }
    public Geometry getGeometry() {
        return this.geometry;
    }

    protected void addFeature(Node inputFeatures) { // TODO в Main нужно добавить перебор всех Feature - и раскидать по классам Value and Feature с результатами
        NodeList nodeNext = inputFeatures.getChildNodes(); // Получаем список детей входящего Нода
        for (int i = 0; i < nodeNext.getLength(); i++) {// Перебираем список
            if (nodeNext.item(i).getNodeType() == Node.ELEMENT_NODE) {
                //System.out.println("Features Name is: " + nodeNext.item(i).getNodeName());
               if(nodeNext.item(i).getNodeName().equals("Feature")) {//Если Feature - добавляем в Лист features
                    features.add(nodeNext.item(i));
                }
            }

        }
//        System.out.println(features.size() + " Size Features List");
    }

    private void switchObject(Node inputObject) {
        NamedNodeMap attr = inputObject.getAttributes();// Получаем его атрибуты
        for (int j = 0; j < attr.getLength(); j++) {
            if (attr.item(j).getNodeName().equals("Name") && attr.item(j).getNodeValue().matches("T.")) {
//                System.out.println("***T***");
                fidMarksArray.add(inputObject);// Если имя Ноде Т1/Т2/Т3 - кладем в list fidMarksArray
            }
            if (attr.item(j).getNodeName().equals("Class") && attr.item(j).getNodeValue().equals("Board")) {
//                System.out.println("***Board***");
                boards.add(inputObject); // Если Board -  кладем в list boards
            }
        }
    }



}
