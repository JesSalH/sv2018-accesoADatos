import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Dom1 {
    public static void main(String[] args){

        try {
            File inputFile = new File("asignaturas.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Elemento base : " 
                + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("asignatura");
            System.out.println();
            
            System.out.println("Recorriendo asignaturas..."); 
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Codigo: " 
                        + eElement.getAttribute("id"));
                    System.out.println("Nombre: " 
                        + eElement.getElementsByTagName("nombre")
                        .item(0).getTextContent());
                    System.out.println("Ciclo: " 
                        + eElement.getElementsByTagName("cicloFormativo")
                        .item(0).getTextContent());
                    System.out.println("Curso: " 
                        + eElement.getElementsByTagName("curso")
                        .item(0).getTextContent());
                    System.out.println("Profesor: " 
                        + eElement.getElementsByTagName("profesor")
                        .item(0).getTextContent());
                    System.out.println(); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
