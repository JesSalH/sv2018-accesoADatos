//Jose Antonio Navarro Marco
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Exercise0129 {
    public static void main(String[] args){
        leerVehiculos();
        leerMarcas();        
    }
    private static void leerVehiculos() {
        try {
            File inputFile = new File("vehiculos.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("vehiculo");
            System.out.println();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println(eElement.getAttribute("id") 
                        + "-" + eElement.getElementsByTagName("marca")
                        .item(0).getTextContent() + "-" + eElement
                        .getElementsByTagName("modelo")
                        .item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void leerMarcas() {
        try {
            File inputFile = new File("marcas.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("marca");
            System.out.println();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println(eElement.getAttribute("nombre") 
                        + "-" + eElement.getAttribute("pais") + "-" 
                            + eElement.getAttribute("anyo") );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
