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

class PersonXML{
    private String code;
    private String name;
    private String surname;
    private boolean duplicated;
    private boolean conflict;
    
    
    public PersonXML(String code,String name,String surname) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        duplicated = conflict = false;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setConflict(boolean isConflict) {
        conflict = isConflict;
    }
    
    public void setDuplicated(boolean isDuplicated) {
        duplicated = isDuplicated;
    }
    
    public boolean isConflict() {
        return conflict;
    }
    
    public boolean isDuplicated() {
        return duplicated;
    }
}

public class Exercise0134c {
    public static ArrayList<PersonXML> getPersons(String fileName){
        ArrayList<PersonXML> ret = new ArrayList<>();
        
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("persona");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    String code = eElement.getAttribute("codigo");
                    String name = eElement.getElementsByTagName("nombre")
                            .item(0).getTextContent();
                    String surname = eElement.getElementsByTagName("apellidos")
                            .item(0).getTextContent();
                    
                    ret.add(new PersonXML(code,name,surname));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return ret;
    }
    
    public static void writeXMLPersons(ArrayList<PersonXML> persons,String fileName) {
        if(persons.size() == 0)
            return;
        
        try {
            PrintWriter writer = new PrintWriter(fileName);
            
            writer.println("<personas>");
            
            persons.forEach(person -> {
                if(person.isDuplicated()) {
                    writer.println("\t<persona codigo=\"" + person.getCode() 
                        + "\" duplicado=\"si\">");
                }
                else if(person.isConflict()) {
                    writer.println("\t<persona codigo=\"" + person.getCode() 
                        + "\" conflicto=\"si\">");
                }
                else {
                    writer.println("\t<persona codigo=\"" + person.getCode() 
                        + "\">");
                }
                
                writer.println("\t\t<nombre>"+ person.getName() + "</nombre>");
                writer.println("\t\t<apellidos>"+ person.getSurname() 
                    + "</apellidos>");
                
                writer.println("\t</persona>");
            });
            
            writer.println("</personas>");
            
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        ArrayList<PersonXML> allPersons = getPersons("alumnos.xml");
        allPersons.sort((p1,p2) -> p1.getCode().compareToIgnoreCase(p2.getCode()));
        
        ArrayList<PersonXML> conflictPersons = new ArrayList<>();
        
        for(int i=0;i < allPersons.size() - 1;i++) {
            PersonXML current = allPersons.get(i);
            PersonXML next = allPersons.get(i + 1);
            if(current.getCode().equals(next.getCode())) 
            {
                if(current.getName().equals(next.getName()) &&
                    current.getSurname().equals(next.getSurname())) 
                {
                    current.setDuplicated(true);
                    next.setDuplicated(true);
                }
                else {
                    current.setConflict(true);
                    next.setConflict(true);
                    conflictPersons.add(next);
                }
                allPersons.remove(next);
                i--;
            }
        }
        
        writeXMLPersons(allPersons,"alumnos2.xml");
        writeXMLPersons(conflictPersons,"conflictos.xml");
    }
}
