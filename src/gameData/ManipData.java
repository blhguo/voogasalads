package gameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

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
import resources.keys.AuthRes;

/*
 * authors: Brandon, Harry
 */
public class ManipData {
	private static final int FILE_EXTENSION = 4;
	private XStream serializer;
	private XStream deserializer;
	public static final ResourceBundle CONFIGKEYS = ResourceBundle.getBundle("gameData/configMap");

	//constructor
	public ManipData() {
		//given a list of levels
		this.serializer = new XStream(new DomDriver());
		this.deserializer = new XStream(new DomDriver());

	}

	/*
	 * This method will be the main method that the backend (Authoring) will call to save data
	 * This method saves a "clean" game, not a game state. the other method signature represents the "game version", like a save file or a checkpoint to be reloaded
	 */
	public void saveData(Engine engine, String gameFolderName, String saveFileName, Map<String, String> metaMap, Map<String, String> ConfigMap) {
		saveData(engine, gameFolderName, saveFileName, false);

		saveConfig(gameFolderName, ConfigMap);
		
		saveMeta(gameFolderName, metaMap);
	}
	/*
	 * This method will be the main method that the frontend (player) will call to save data
	 * This method saves a "dirty" game, aka a game state or a game save file. 
	 */
	public void saveData(Engine engine, String gameName, String saveFileName, boolean isPlayer) {
		File file = new File("games/"+gameName);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		file = new File("games/" + gameName + "/" + saveFileName +".xml");
		if (isPlayer) {
			file = new File("games/"+gameName+"/gameSaves");
			if(!file.exists()) {
				file.mkdirs();
			}
		file = new File("games/"+gameName+"/gameSaves/" + saveFileName + ".xml");
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (file.exists()) {
			try {
				FileOutputStream writer = new FileOutputStream(file);
				//String saveData = serializer.toXML(engine);
				writer.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
				writer.write(("<higher>").getBytes("UTF-8"));
				saveEngine(engine, writer);
				//saveSingleEntry(saveData, "", 0, false, writer);
				writer.write(("</higher>").getBytes("UTF-8"));

			} catch (IOException e) {
				//System.out.println("error creating file");
			}
		}
	}
	/*
	 * This method will be the main method that the frontend (player) will call to save data
	 * This method saves a "dirty" game, aka a game state or a game save file. 
	 */
	public Engine loadData(String filePath) {
		try {
			File load = new File(filePath);
			return openFile(load);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO
		}
		return null;
		}
	
	public Map<String, String> openMeta(String filePath) {
		
		File file = new File(filePath);
		
		Map<String, String> metaMap = new HashMap<>();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc;
			try {
				doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("stuff");
				Node nNode = nList.item(0);
				Element eElement = (Element) nNode;
				String keys = eElement.getElementsByTagName("key0").item(0).getTextContent();
				String vals = eElement.getElementsByTagName("value0").item(0).getTextContent();
				String[] keyArr = keys.split(",");
				String[] valArr = vals.split(",");
				for (int i = 0; i < keyArr.length; i++) {
					metaMap.put(keyArr[i], valArr[i]);
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return metaMap;
	}
	
	public Map<String, String> openConfig(String filePath) {
		Map<String, String> configMap = new HashMap<>();
		
		ResourceBundle configkeys = ResourceBundle.getBundle(filePath);
		System.out.println(configkeys);
		for (String s : configkeys.keySet()) {
			//String buff = configkeys.getString(AuthRes.getStringKeys(s));
			configMap.put(s, configkeys.getString(s));
		}
		
		return configMap;
	}
	
	
	/* This method will be used to write a single entry into XML
	 * the type will disinguish between whether the written XML is MetaData or Objects
	 * no longer would need fos or xml
	 * 
	 */ 
	private void saveSingleEntry(String input, String key, int counter, boolean meta, FileOutputStream writer) {
		try {
			//if it is metadata, we need the key to map the values
			if(meta) {
				writer.write(("<key"+Integer.toString(counter)+">").getBytes("UTF-8"));
				writer.write(key.getBytes("UTF-8"));
				writer.write(("</key"+Integer.toString(counter)+">").getBytes("UTF-8"));
			}
			//if metadata or not, we still need to write the answer into XML
			writer.write(("<value"+Integer.toString(counter)+">").getBytes("UTF-8"));
			writer.write(input.getBytes("UTF-8"));
			writer.write(("</value"+Integer.toString(counter)+">").getBytes("UTF-8"));
		} catch (Exception e) {
			//System.out.println("ERROR");
		} 
	}

	public void saveConfig(String configLoc, Map<String, String> configMap) {
		
		File file = new File("games/"+configLoc);
		if(!file.exists()) {
			file.mkdirs();
		}
		try {
			Properties param = new Properties();
			
			for (int i = 0; i < configMap.size(); i ++) {
				param.setProperty(AuthRes.getStringKeys("key" + i), configMap.get(AuthRes.getStringKeys("key" + i)));
			}
			
			file = new File("games/" + configLoc + "/config.properties");
			if (!file.exists()) {
				try {file.createNewFile();}
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			FileOutputStream fos = new FileOutputStream(file);
			param.store(fos, "Configuration file");
			fos.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	private void saveEngine(Engine engine, FileOutputStream fos) {
		//System.out.println("Beginning of serialization-engine");//println includes new line ya sily my bad
		try {
			String xml = serializer.toXML(engine);
			fos.write("<data>".getBytes("UTF-8"));
			fos.write(xml.getBytes("UTF-8"));
			fos.write("</data>".getBytes("UTF-8"));
		}
		catch (Exception e) {
			//System.out.println("u dun goofed"); //TODO
		}

	}

	private void saveMeta(String gameName, Map<String, String> metaMap) {
		File file = new File("games/" + gameName + "/metaData.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream writer = new FileOutputStream(file);
			writer.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
			writer.write("<stuff>".getBytes("UTF-8"));
			int counter = 0;
			for (String k: metaMap.keySet()) {
				saveSingleEntry(metaMap.get(k), k, counter, true, writer);
				counter++;
			}
			writer.write("</stuff>".getBytes("UTF-8"));
		} catch (IOException e) {
			//System.out.println("error creating file");
		}
	}

	private Engine openFile(File file) throws ParserConfigurationException{
		//System.out.println(file);
		Engine lilGuy = new Engine();
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
				//System.out.println(s);
				lilGuy = (Engine) deserializer.fromXML(s);
				//System.out.println(lilGuy);
				return lilGuy;
				
				//System.out.println(output);

			} catch (SAXException e) {
				//System.out.println("here1");
				return lilGuy; //TODO
			}
		} catch (IOException e) {
			//System.out.println("here2");

			return lilGuy; //TODO
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
			//System.out.println("exception");
		}
		return sw.toString();
	}

}
