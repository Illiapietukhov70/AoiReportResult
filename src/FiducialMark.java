import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class FiducialMark extends ObjectMain{

    public FiducialMark(Node nodeMain) {
        super(nodeMain);
        this.nodeMain = nodeMain;
        this.geometry = new Geometry();
        this.status = new Status();
        this.features = new ArrayList<>();

        NodeList startNode = this.nodeMain.getChildNodes();
        //System.out.println("Object complete " + startNode.getLength());

        for (int i = 0; i < startNode.getLength(); i++) {
            if (startNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                switch (startNode.item(i).getNodeName()) {
                    case "Geometry" -> setGeometry(startNode.item(i));// Записываем Геометрию
                    case "Status" -> setStatus(startNode.item(i));// Записываем Статус
                    case "Features" -> addFeature(startNode.item(i)); // Формируем list с Feature
                }
            }

        }
    }
}
