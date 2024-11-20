import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Board extends ObjectMain{
    ArrayList<Node> comps = new ArrayList<>();
    public Board(Node nodeMain) {
        super(nodeMain);
        this.comps = new ArrayList<>();
        NodeList startNode = this.nodeMain.getChildNodes();
        //System.out.println("Object complete " + startNode.getLength());

        for (int i = 0; i < startNode.getLength(); i++) {
            if (startNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                switch (startNode.item(i).getNodeName()) {
                    case "Geometry" -> setGeometry(startNode.item(i));// Записываем Геометрию
                    case "Status" -> setStatus(startNode.item(i));// Записываем Статус
                    case "Object" -> addComp(startNode.item(i)); // Формируем list с Feature
                }
            }

        }


    }
    private void addComp(Node nodeComp) {
        comps.add(nodeComp);

    }

}
