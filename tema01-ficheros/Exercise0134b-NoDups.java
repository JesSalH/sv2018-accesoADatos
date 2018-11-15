import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Adrián Fernández Arnal
class Persona {
    int codigo;
    String nombre;
    String apellidos;
    
    public Persona(int codigo,String nombre,String apellidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
}

public class Exercise0134b {
    
    public static void eliminarDuplicados() {
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
        ArrayList<Persona> listaPersonasDuplicadas = new ArrayList<Persona>();
        ArrayList<Persona> listaPersonasConflicto = new ArrayList<Persona>();
        PrintWriter ficheroEscribir = null;
        PrintWriter ficheroEscribir2 = null;
        try {
            ficheroEscribir = new PrintWriter("alumnos2.xml");
            ficheroEscribir2 = new PrintWriter("conflictos.xml");
        } catch (FileNotFoundException e1) {
            System.out.println("Error, no se puede acceder al fichero "
                    + "alumnos2.xml o conflictos.xml");
        }
        try {
            File inputFile = new File("alumnos.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("persona");
            System.out.println();
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;        
                    
                    //Guardar campos en variables
                    int codigo = Integer.parseInt(
                            eElement.getAttribute("codigo"));
                    String nombre = eElement.getElementsByTagName("nombre")
                            .item(0).getTextContent();
                    
                    String apellidos= eElement.getElementsByTagName("apellidos")
                            .item(0).getTextContent();
                    
                    Persona p = new Persona(codigo,nombre,apellidos);
                    listaPersonas.add(p);
                }
            }
            int i=0;
            int j=0;
            boolean duplicado = false;
            while(i < listaPersonas.size()) {
                while(j < listaPersonas.size() && !duplicado) {
                    if(listaPersonas.get(i).codigo == 
                            listaPersonas.get(j).codigo) {
                        if( !listaPersonas.get(i).getApellidos()
                                .equals(listaPersonas.get(j).getApellidos()) 
                                || !listaPersonas.get(i).getNombre()
                                .equals(listaPersonas.get(j).getNombre())) {
                            listaPersonasConflicto.add(listaPersonas.get(i));
                        }
                        listaPersonasDuplicadas.add(listaPersonas.get(i));
                        listaPersonas.remove(i);
                        duplicado = true;
                    }
                    j++;
                }
                duplicado = false;
                i++;
            }
            for(Persona p : listaPersonasDuplicadas) {
                ficheroEscribir.println(
                        "<persona codigo=\""+p.getCodigo()+"\" "
                                + "duplicado=\"si\">");
                ficheroEscribir.println("<nombre>"+p.getNombre()+"</nombre>");
                ficheroEscribir.println(
                        "<apellidos>"+p.getApellidos()+"</apellidos>");
                ficheroEscribir.println("</persona>");
            }
            for(Persona p : listaPersonasConflicto) {
                ficheroEscribir2.println(
                        "<persona codigo=\""+p.getCodigo()+"\" "
                                + "conflicto=\"si\">");
                ficheroEscribir2.println("<nombre>"+p.getNombre()+"</nombre>");
                ficheroEscribir2.println(
                        "<apellidos>"+p.getApellidos()+"</apellidos>");
                ficheroEscribir2.println("</persona>");
            }
            for(Persona p : listaPersonas) {
                ficheroEscribir.println(
                        "<persona codigo=\""+p.getCodigo()+"\">");
                ficheroEscribir.println("<nombre>"+p.getNombre()+"</nombre>");
                ficheroEscribir.println(
                        "<apellidos>"+p.getApellidos()+"</apellidos>");
                ficheroEscribir.println("</persona>");
            }
            ficheroEscribir2.close();
            ficheroEscribir.close();
        } catch (Exception e) {
            System.out.println("Error al leer el fichero XML de alumnos");
        }
    }
    
    public static void main(String[] args) {
        eliminarDuplicados();
    }

}
