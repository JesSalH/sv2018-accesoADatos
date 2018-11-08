//Adrián Fernández Arnal
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class Vehiculo implements Serializable{
	int id;
	String marca;
	String modelo;
	
	public Vehiculo(int id, String marca, String modelo) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String toString() {
		return id+",\""+marca+"\",\""+modelo+"\"";
	}
}
class Marca{
	String nombre;
	String pais;
	String anyo;
	
	public Marca(String nombre,String pais,String anyo) {
		this.nombre = nombre;
		this.pais = pais;
		this.anyo = anyo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		return "\""+pais+"\",\""+anyo+"\"";
	}
}

public class Exercise0131 {
	
	public static ArrayList<Marca> leerXMLMarcas() {
		ArrayList<Marca> marcas = new ArrayList<>();
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
                    String nombre = eElement.getAttribute("nombre");
                    String pais = eElement.getAttribute("pais");
                    String anyo = eElement.getAttribute("anyo");
                    
                    marcas.add(new Marca(nombre,pais,anyo));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el fichero XML de marcas");
        }
		return marcas;
	}
	
	public static ArrayList<Vehiculo> leerXMLVehiculos() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
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
                    int id = Integer.parseInt(eElement.getAttribute("id"));
                    
                    String marca = eElement.getElementsByTagName("marca")
                    		.item(0).getTextContent();
                    
                    String modelo = eElement.getElementsByTagName("modelo")
                    		.item(0).getTextContent();
                    
                    vehiculos.add(new Vehiculo(id,marca,modelo));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el fichero XML de vehículos");
        }
		return vehiculos;
	}
	
	public static void serializarVehiculos(ArrayList<Vehiculo> listaVehiculos) {
		try {
			File fichero = new File("vehiculos.dat");
			 FileOutputStream ficheroSalida =
			 new FileOutputStream(fichero);
			 ObjectOutputStream ficheroObjetos =
			 new ObjectOutputStream(ficheroSalida);
			 
			 ficheroObjetos.writeObject(listaVehiculos);
			 
			 ficheroSalida.close();
			 ficheroObjetos.close();
			 
		}catch(IOException e) {
			System.out.println("Error serializando la lista de vehículos");
		}
	}
	
	public static void escribirCSV(ArrayList<Marca> listaMarcas,
			ArrayList<Vehiculo> listaVehiculos) {
		PrintWriter ficheroCSV = null;
		try {
			ficheroCSV = new PrintWriter("vehiculos.csv");
			ficheroCSV.println("id,marca,modelo,paisFundacion,anyoFundacion");
			int j = 0;
			boolean encontrado = false;
			for(int i=0;i<listaVehiculos.size();i++) {
	    		while(j<listaMarcas.size() && !encontrado) {
	    			
	    			if(listaVehiculos.get(i).getMarca().
	    					equals(listaMarcas.get(j).getNombre())) {
	    				ficheroCSV.println(
		    						listaVehiculos.get(i).toString()+","+
		    						listaMarcas.get(j).toString()
	    						);
	    				encontrado = true;
	    			}
	    			j++;
	    		}
	    		if(!encontrado) { 
	    			ficheroCSV.println(
						listaVehiculos.get(i).toString()+","+
						"\"Desconocido,\",\"Desconocido\""
					);
	    		}
	    		encontrado = false;
	    		j=0;
	    	}
		}catch(IOException e) {
			System.out.println("Error al abrir el fichero CSV");
		}
		finally {
			ficheroCSV.close();
		}
		
	}
	
    public static void main(String[] args){
    	ArrayList<Marca> listaMarcas = leerXMLMarcas();
    	ArrayList<Vehiculo> listaVehiculos = leerXMLVehiculos();

    	if(listaMarcas.size()>0 && listaVehiculos.size()>0)
    		escribirCSV(listaMarcas,listaVehiculos);
    	
    	if(listaVehiculos.size()>0)
    		serializarVehiculos(listaVehiculos);
    }
}