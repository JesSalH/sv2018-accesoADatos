
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Victor Tebar

class Friend implements Serializable{
	protected String name;
	protected short age;
	protected String mail;
	protected String comment;
	
	public Friend(String name,short age,String mail,String comment) {
		this.name = name;
		this.age = age;
		this.mail = mail;
		this.comment = comment;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(short age) {
		this.age = age;
	}
	
	public short getAge() {
		return age;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	
	@Override
	public String toString() {
		return "Friend - Name: " + name + " , Age: " + age + 
				" , E-mail: " + mail + " , Comment: " + comment;
	}
}

public class Exercise0128 {
	static Scanner scanner = new Scanner(System.in);
	
	public static boolean tryParseInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		}
		catch(Exception e) {}
		
		return false;
	}
	
	public static void addFriend(ArrayList<Friend> friends) {
		System.out.println("Friend " + (friends.size() + 1) + ".");
		
		String name = "";
		do {
			System.out.println("Enter the name: ");
			name = scanner.nextLine();
			
			if(name.equals(""))
				System.out.println("The name cannot be empty.");
		}while(name.equals(""));
		
		int age = 0;
		
		do {
			System.out.println("Enter the age: ");
			String strAge = scanner.nextLine();
			
			if(tryParseInt(strAge)) {
				age = Integer.parseInt(strAge);
				if(age < 1 || age > 105)
					System.out.println("The age must be between 1 and 105.");
			}
			else {
				System.out.println("The age must be a number.");
				age = 0;
			}
		}while(age < 1 || age > 105);
		
		System.out.println("Enter the e-mail: ");
		String mail = scanner.nextLine();
		
		System.out.println("Enter the comment: ");
		String comment = scanner.nextLine();
		
		friends.add(new Friend(name,(short)age,mail,comment));
		
		System.out.println("Friend added successfully.");
	}
	
	public static void showAllNames(ArrayList<Friend> friends) {
		friends.forEach((friend) -> {
			System.out.println("Name : " + friend.getName());
		});
	}
	
	public static void searchFriend(ArrayList<Friend> friends) {
		System.out.println("Enter the key words: ");
		String keyWords = scanner.nextLine().toLowerCase();
		
		List<Friend> friendsFound = friends.stream()
				.filter((f) -> f.getName().toLowerCase().contains(keyWords)
						|| f.getMail().toLowerCase().contains(keyWords)
						|| f.getComment().toLowerCase().contains(keyWords))
				.collect(Collectors.toList());
		
		if(friendsFound.size() == 0)
			System.out.println("Friends not found.");
		else
			friendsFound.forEach(System.out::println);
	}
	
	public static void exportToXML(ArrayList<Friend> friends) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("friends");
			doc.appendChild(rootElement);

			
			friends.forEach((friend) -> {
				Element elementFriend = doc.createElement("friend");
				rootElement.appendChild(elementFriend);
				
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(friend.getName()));
				elementFriend.appendChild(name);

				Element lastname = doc.createElement("age");
				lastname.appendChild(doc.createTextNode(friend.getAge()+""));
				elementFriend.appendChild(lastname);

				Element mail = doc.createElement("mail");
				mail.appendChild(doc.createTextNode(friend.getMail()));
				elementFriend.appendChild(mail);

				Element comment = doc.createElement("comment");
				comment.appendChild(doc.createTextNode(friend.getComment()));
				elementFriend.appendChild(comment);
			});
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("friends.xml"));

			transformer.transform(source, result);

		  } catch (ParserConfigurationException e) {
			  System.err.println(e.getMessage());
		  } catch (TransformerException e) {
			  System.err.println(e.getMessage());
		  }
	}
	
	public static ArrayList<Friend> importFromXML() {		
		ArrayList<Friend> ret = new ArrayList<Friend>();
		
		if(!(new File("friends.xml").exists())) {
			return ret;
		}
		
		try {
            File inputFile = new File("friends.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("friend");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Friend friend = new
                    		Friend(eElement.getElementsByTagName("name")
                                    .item(0).getTextContent(),
                                    Short.parseShort(
                                    		eElement.getElementsByTagName("age")
                                            .item(0).getTextContent()),
                                    eElement.getElementsByTagName("mail")
                                    .item(0).getTextContent(),
                                    eElement.getElementsByTagName("comment")
                                    .item(0).getTextContent());
                    ret.add(friend);
                }
            }
        } catch (Exception e) {
        	System.err.println(e.getMessage());
        }
		
		return ret;
	}
	
	public static void printMenu() {
		System.out.println("1.Add friend.");
		System.out.println("2.Show friend names.");
		System.out.println("3.Search.");
		System.out.println("0.Exit.");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String option = "";
		
		ArrayList<Friend> friends = importFromXML();
		
		do {
			printMenu();
			option = sc.nextLine();
			
			switch(option) {
				case "1":
					addFriend(friends);
					friends.sort((f1,f2) -> f1.getName().compareTo(f2.getName()));
					exportToXML(friends);
					break;
				case "2":
					showAllNames(friends);
					break;
				case "3":
					searchFriend(friends);
					break;
				case "0":
					System.out.println("Bye!");
					break;
				default:
					System.out.println("Unknown option.");
					break;
			}
		}while(!option.equals("0"));
		
		scanner.close();
	}
}

