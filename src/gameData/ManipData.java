package gameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

import game_engine.Level;

/**
 * 
 * @author Brandon Guo and Harry Wang
 * 
 * This class handles all the data writing and reading. 
 *
 */

public class ManipData {
	private static final int FILE_EXTENSION = 4;
	private XStream serializer;
	private XStream deserializer;
	private String xml;
	private FileOutputStream fos;
	private ArrayList<Level> levellist;

	//constructor
	public ManipData() {
		//given a list of levels
		this.serializer = new XStream(new DomDriver());
		this.deserializer = new XStream(new DomDriver());
		this.xml = "";
		this.fos = null;
		this.levellist = new ArrayList<Level>();
	}
	/**
 * 
 * @param Level to save, level num for file writing
 * 
 * This method is a private method that serialized and saved a single level under a unique tag.
 *
 */

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
		/**
 * 
 * @param List of levels to save
 * 
 * This method is a public method that takes in a list of levels to save, and makes repeated calls to saveLevel to save each one.
 *
 */
	public void saveData(List<Level> levels) {
		int counter = 0;
		try {
			//this writes only one file
			fos = new FileOutputStream("savedata/gameDataSave"+"someuniquefactor"+".xml");
	        try {
	        	//writes xml header and then the number of data objects inside
				fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
				String nums = Integer.toString(levels.size());
				fos.write(("<higher "+"info='"+nums+"'>").getBytes("UTF-8"));
				for(Level l: levels) {
					saveLevel(l, counter);
					counter++;
				} 
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
	        if(fos != null){
	            try{
	                fos.close();
	            }catch (IOException e) {
	                e.printStackTrace(); //TODO
	            }
	        }
	    }
	}

 
 			/**
 * 
 * @param File to load
 *  
 * @return Array list of instantiated level objects
 * 
 * This method is a public method that takes in a list of levels to load from a file, and makes repeated calls to openFile to load each one.
 *
 */
	public ArrayList<Level> loadData(File load) {
		try {
			openFile(load);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO
		}
		return loadLevels();
	}
		/**
 * 
 * @param File to load
 * 
 * 
 * This method is a private method that deserializes and loads a single level under a unique tag with a call to a helper class nodeToString
 *
 */
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
				int nums = Integer.parseInt(eElement.getAttribute("info"));
				for(int i=0;i<nums;i++) {

					String s = nodeToString(eElement.getElementsByTagName("data"+Integer.toString(i)).item(0).getFirstChild());
					System.out.println(s);
					Level lilGuy = (Level) deserializer.fromXML(s);
					System.out.println(lilGuy);
					levellist.add(lilGuy);
				}
				System.out.println(levellist);
				
			} catch (SAXException e) {
				System.out.println("here1");
				return; //TODO
			}
        } catch (IOException e) {
			System.out.println("here2");

        		return; //TODO
        }
	}
	
			/**
 * 
 * @param node to convert
 * 
 * @return string representing the node, to be deserialized
 * 
 * This method is a private method that converts a node that we need deserialized (read from file) into a string for ease of parsing later
 *
 */
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
	
			/**
 * 
 * @return list of levels that have been instantiated
 * 
 * Returns the stored "buffer" of the list of instantiated levels
 *
 */
	
	private ArrayList<Level> loadLevels(){
		return levellist;
	}
	
}