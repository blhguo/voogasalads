package gameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_engine.Engine;
import game_engine.level.Level;

/*
 * authors: Brandon, Harry
 */
public class ManipData {
	private static final int FILE_EXTENSION = 4;
	private XStream serializer;
	private XStream deserializer;
	private String xml;
	private FileOutputStream fos;
	private FileOutputStream fos1;
	private ArrayList<Level> levellist;
	private Engine output;

	//constructor
	public ManipData() {
		//given a list of levels
		this.serializer = new XStream(new DomDriver());
		this.deserializer = new XStream(new DomDriver());
		this.xml = "";
		this.fos = null;
		this.fos1 = null;
		this.levellist = new ArrayList<Level>();
	}
	
	private void saveLevel(Level input, int levelnum) {
		System.out.println("Beginning of serialization");//println includes new line ya sily my bad
		try {
			xml = serializer.toXML(input);
			//writes each data object inside a unique data tag
	        fos.write(("<data"+Integer.toString(levelnum)+">").getBytes("UTF-8"));
	        fos.write(xml.getBytes("UTF-8"));
	        fos.write(("</data"+Integer.toString(levelnum)+">").getBytes("UTF-8"));
	        }
		catch (Exception e){
			System.out.println("Something broke"); //TODO
		}
	}
	
	private void saveEngine(Engine engine, String gameName) {
		System.out.println("Beginning of serialization-engine");//println includes new line ya sily my bad
		try {
			xml = serializer.toXML(engine);
			fos.write("<data>".getBytes("UTF-8"));
			fos.write(xml.getBytes("UTF-8"));
			fos.write("</data>".getBytes("UTF-8"));
		}
		catch (Exception e) {
			System.out.println("u dun goofed"); //TODO
		}

	}
	
	public void saveData(Engine engine, String gameName, Map<String, String> metaMap) {
		int counter = 0;
		try {
			//this writes only one file
			File file = new File("savedata/gameLevels"+".xml");
			if (!file.exists()) {
			     try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			fos = new FileOutputStream(file);
	        try {
	        	//writes xml header and then the number of data objects inside
				fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
				fos.write(("<higher>").getBytes("UTF-8"));
				saveEngine(engine, gameName);
				fos.write("</higher>".getBytes("UTF-8"));

	        } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//saveMeta(metaMap, gameName);
	        //if(fos != null){
	            try{
	                fos.close();
	            //    fos1.close();
	            }catch (IOException e) {
	                e.printStackTrace(); //TODO
	            }
	        }
	    //}
	}
	
	private void saveMeta(Map<String, String> metaMap, String gameName) {
		File file = new File("savedata/metaData"+".xml");
		if (!file.exists()) {
		     try {
				file.createNewFile();
				fos1 = new FileOutputStream(file);
				fos1.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
				fos1.write(("<stuff>").getBytes("UTF-8"));
				int counter = 0;
				for (String k : metaMap.keySet()) {
					saveOneMeta(k, metaMap.get(k), counter);
					counter++;
				}
				fos.write("</stuff>".getBytes("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	}
	
	private void saveOneMeta(String key, String value, int counter) {
		try {
	        fos1.write(("<key"+Integer.toString(counter)+">").getBytes("UTF-8"));
			fos1.write(key.getBytes("UTF-8"));
	        fos1.write(("</key"+Integer.toString(counter)+">").getBytes("UTF-8"));
	        fos1.write(("<value"+Integer.toString(counter)+">").getBytes("UTF-8"));
			fos1.write(value.getBytes("UTF-8"));
	        fos1.write(("</value"+Integer.toString(counter)+">").getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Node getEntry(Document doc, String id, String key, String value) {
		Element entry = doc.createElement("Data");
		entry.setAttribute("id", id);
        entry.appendChild(getEntryElements(doc, entry, "key", key));
        entry.appendChild(getEntryElements(doc, entry, "value", value));
        return entry;
    }
 
    // utility method to create text node
    private Node getEntryElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
	
	
	
	
	public Engine loadData(File file, String gamename) {
		try {
			//File load = new File(filePath);
			openFile(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO
		}
		return loadEngine();
	}
	
	public Map<String, String> openMeta(File file) {
		Map<String, String> metaMap;
		metaMap = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        String filePath = file.getAbsolutePath();
        String fileType = filePath.substring(filePath.length()-FILE_EXTENSION);
        if (!fileType.equals(".xml")) {
        	System.out.println("You dun goofed");
        };
        
        try {
        	dBuilder = dbFactory.newDocumentBuilder();
        	Document doc;
        	try {
        		doc = dBuilder.parse(file);
        		doc.getDocumentElement().normalize();
        		NodeList nList = doc.getElementsByTagName("stuff");
        		for (int i = 0; i < nList.getLength(); i = i + 2) {
        			Node nNode = nList.item(i);
        			Node nNode1 = nList.item(i+1);
        			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        				Element eElement = (Element) nNode;
        				Element eElement1 = (Element) nNode1;
        				metaMap.put(eElement.getNodeValue(), eElement.getNodeValue());
        			}
        		}}
        		finally {
        	        
        	    }
        		
	}
        finally {
        	return metaMap;
        }
	}
	
	private void openFile(File file) throws ParserConfigurationException{
		System.out.println(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        String filePath = file.getAbsolutePath();
        String fileType = filePath.substring(filePath.length()-FILE_EXTENSION);
        if (!fileType.equals(".xml")) {
        	System.out.println("You dun goofed");
        };
        //maybe a func to check that the file is an xml extension
        try {
        	dBuilder = dbFactory.newDocumentBuilder();
			Document doc;
			try {
				doc = dBuilder.parse(file);

				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("higher");
				Node nNode = nList.item(0);
				Element eElement = (Element) nNode;			
					String s = nodeToString(eElement.getElementsByTagName("data").item(0).getFirstChild());
					System.out.println(s);
					Engine lilGuy = (Engine) deserializer.fromXML(s);
					System.out.println(lilGuy);
					output = lilGuy;
				
				System.out.println(output);
				
			} catch (SAXException e) {
				System.out.println("here1");
				return; //TODO
			}
        } catch (IOException e) {
			System.out.println("here2");

        		return; //TODO
        }
	}
	
	private String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.out.println("exception");
		}
		return sw.toString();
	}
	
	private ArrayList<Level> loadLevels(){
		return levellist;
	}
	
	private Engine loadEngine() {
		return output;
	}
}