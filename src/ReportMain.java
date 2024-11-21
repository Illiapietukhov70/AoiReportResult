import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ReportMain {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {


        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();


        //Создается объект Document — он является представлением всей информации внутри XML
        Document document = builder.parse("S300216_b_p3_AOI_101908_03092024.xml");

        //Создается узел root путем получения первого дочернего узла. Это будет тег <VvExtDataExportXml>
        Node root = document.getFirstChild();
        System.out.println("Root: " + root.getNodeName());

        ArrayList<Node> nodeArray = new ArrayList<>();
        String testProgNumber = "";
        String testProgName = "";
        String inspectionStart = "";
        String inspectionEnd = "";
        String analysisStart = "";
        String analysisEnd = "";
        String cycleTime = "";
        String inspectionAborted = "";

        Node objectMain = null;


        NodeList nodeFirst = root.getChildNodes();
        for (int i = 0; i < nodeFirst.getLength(); i++) {
            if (nodeFirst.item(i).getNodeType() == Node.ELEMENT_NODE) {
                nodeArray.add(nodeFirst.item(i));
                NamedNodeMap attr = nodeFirst.item(i).getAttributes();
                for (int j = 0; j < attr.getLength(); j++) {
                    if (attr.item(j).getNodeName().equals("Barcode")) {
                        testProgNumber = attr.item(j).getNodeValue();
                    }
                    if (attr.item(j).getNodeName().equals("Name")) {
                        testProgName = attr.item(j).getNodeValue();
                    }
                }
            }
        }
        System.out.printf("Test program Name: %s, Result ID test : %s\n", testProgName, testProgNumber);

        System.out.println("==============================NodeFirst===========================");

        for (int h = 0; h < nodeArray.size(); h++) {
            NodeList nodeNext = nodeArray.get(h).getChildNodes(); // Получаем список детей входящего Нода
            for (int i = 0; i < nodeNext.getLength(); i++) {// Перебираем список
                if (nodeNext.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    if (nodeNext.item(i).getNodeName().equals("Object")) {
                        objectMain = nodeNext.item(i);// Объект (Нод для дальнейшей работы)
                    }
                    NamedNodeMap attr = nodeNext.item(i).getAttributes(); // Получаем его атрибуты
                    for (int j = 0; j < attr.getLength(); j++) {
                        switch (attr.item(j).getNodeName()) {
                            case "InspectionStart" -> inspectionStart = attr.item(j).getNodeValue();
                            case "InspectionEnd" -> inspectionEnd = attr.item(j).getNodeValue();
                            case "AnalysisStart" -> analysisStart = attr.item(j).getNodeValue();
                            case "AnalysisEnd" -> analysisEnd = attr.item(j).getNodeValue();
                            case "CycleTime" -> cycleTime = attr.item(j).getNodeValue();
                            case "InspectionAborted" -> inspectionAborted = attr.item(j).getNodeValue();
                        }
                    }
                }
            }
        }

        System.out.println("InspectionStart: " + inspectionStart);
        System.out.println("InspectionEnd: " + inspectionEnd);
        System.out.println("AnalysisStart: " + analysisStart);
        System.out.println("AnalysisEnd: " + analysisEnd);
        System.out.println("CycleTime: " + cycleTime + " second");
        System.out.println("InspectionAborted: " + inspectionAborted);


        ObjectMain rep1 = new ObjectMain(objectMain); // Формирую 1 объект
        System.out.println("=====================RESULT TEST BOARD======================");
        System.out.println("Result TEST Problem?: " + rep1.getStatus().getOverall("IsFailed"));
        System.out.println("Need Repair: " + rep1.getStatus().getRepair("NeedsRepair"));
        System.out.println("Real Size Test Board: ");
        System.out.println("X = " + rep1.getGeometry().getSizeLocal()[0]);
        System.out.println("Y = " + rep1.getGeometry().getSizeLocal()[1]);
        System.out.println("Z = " + rep1.getGeometry().getSizeLocal()[2]);

        System.out.println("+++++++++Test Array with Fid Marks+++++++++++++++++++++");
        FiducialMark mark1 = new FiducialMark(rep1.fidMarksArray.get(0));
        System.out.println("Size Fid Mark T1: ");
        System.out.println("X = " + mark1.getGeometry().getSizeLocal()[0]);
        System.out.println("Y = " + mark1.getGeometry().getSizeLocal()[1]);
        System.out.println("Z = " + mark1.getGeometry().getSizeLocal()[2]);
        Board board1 = new Board(rep1.boards.get(0));
        System.out.println("Size Board1: ");
        System.out.println("X = " + board1.getGeometry().getSizeLocal()[0]);
        System.out.println("Y = " + board1.getGeometry().getSizeLocal()[1]);
        System.out.println("Z = " + board1.getGeometry().getSizeLocal()[2]);
        System.out.println("+++++++++++++++++++Test Array Comp++++++++++++++++++++++++++");
        System.out.println(board1.comps.size());
        for(Node comp: board1.comps){
            NamedNodeMap attr = comp.getAttributes();// Получаем его атрибуты
            for (int j = 0; j < attr.getLength(); j++) {
                if (attr.item(j).getNodeName().equals("Name")) {
                    System.out.printf("%s, ", attr.item(j).getNodeValue());
                }
            }
        }
        System.out.println("+++++++++++++++++Test Array Pin++++++++++++++++++++++++++");
        Comp comp1 = new Comp(board1.comps.get(50));
        System.out.println(comp1.pins.size());
        for(Node pin: comp1.pins){
            NamedNodeMap attr = pin.getAttributes();// Получаем его атрибуты
            for (int j = 0; j < attr.getLength(); j++) {
                if (attr.item(j).getNodeName().equals("Name")) {
                    System.out.printf("%s, ", attr.item(j).getNodeValue());
                }
            }

        }
        System.out.println("========================Test Status Pin=================");
        Pin pin1 = new Pin(comp1.pins.get(0));
        System.out.printf("Full Test of Pin is: %b\n", pin1.getStatus().getOverall("IsFailed"));








        }
    }


