import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Javier Saorín Vidal

public class Exercise0134a {
    public static void main(String[] args) {     
        eliminarDuplicadosXML();
    }
    
    public static void eliminarDuplicadosXML() {
        
        try {
            File inputFile = new File("alumnos.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("persona");
            
            List<Persona> personas = new ArrayList<>();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    String actualId = eElement.getAttribute("codigo");
                    
                    boolean duplicado = false;
                    
                    for (Persona p : personas) {
                        if (p.getCodigo().equalsIgnoreCase(actualId)) {
                            duplicado = true;
                        }
                    }
                    
                    boolean conflicto = false;
                    
                    String nombre = "", apellidos = "";
                    
                    nombre = eElement.getElementsByTagName("nombre")
                            .item(0).getTextContent();
                    apellidos = eElement.getElementsByTagName("apellidos")
                            .item(0).getTextContent();
                    
                    for (Persona p : personas) {
                        if (p.getNombre().equalsIgnoreCase(nombre) ||
                                p.getApellidos().equalsIgnoreCase(apellidos)) {
                            conflicto = true;
                        }
                    }
                    
                    volcarXML(new Persona(actualId, nombre, apellidos)
                            , duplicado, conflicto);
                }
            }
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }
       
    }
    
    public static void volcarXML(Persona p, boolean duplicado, boolean conflicto) {
        try {
            PrintStream output = new PrintStream(new File("alumnos2.xml"));
            output.println(p.toXMLString(duplicado, conflicto));
            output.close();
            
            PrintStream conflictsOutput =
                    new PrintStream(new File("conflictos.xml"));
            output.println(p.toXMLString(duplicado, conflicto));
            conflictsOutput.close();
        } catch (IOException e) {
        }
    }
}

// -------------------------------------

class Persona {
    
    private String codigo;
    private String nombre;
    private String apellidos;
    
    public Persona(String codigo, String nombre, String apellidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String toXMLString(boolean duplicado, boolean conflicto) {
        if (duplicado) {
            return "<persona codigo=\"" + codigo + "\" duplicado=\"si\">"
                    + " <nombre>" + nombre +"</nombre>"
                    + "<apellidos>" + apellidos + "</apellidos>"
                    + "</persona>";
        }
        else if (conflicto)
        {
            return "<persona codigo=\"" + codigo + "\" duplicado=\"si\">"
                    + " <nombre>" + nombre +"</nombre>"
                    + "<apellidos>" + apellidos + "</apellidos>"
                    + "</persona>";
        }
        else {
            return "<persona codigo=\"" + codigo + "\">"
                    + " <nombre>" + nombre +"</nombre>"
                    + "<apellidos>" + apellidos + "</apellidos>"
                    + "</persona>";
        }
    }
}
