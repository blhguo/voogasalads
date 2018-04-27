package gameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {
	
	private static void saveSingleEntry(String input, String key, int counter, boolean meta, FileOutputStream writer) {
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
			System.out.println("ERROR");
		} 
	}
	
	public static void main (String[] args) {
		HashMap<String, String> metaMap = new HashMap<>();
		metaMap.put("test", "dick");
		metaMap.put("yols", "output");
		File file = new File("games/" + "test/");
		if(!file.exists()) {
			file.mkdirs();
		}
		file = new File("games/test/metadata.xml");
		try {
			FileOutputStream writer = new FileOutputStream(file);
			try {
				writer.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
				writer.write(("<stuff>").getBytes("UTF-8"));
				int counter = 0;
				StringBuilder key = new StringBuilder();
				StringBuilder val = new StringBuilder();
				for (String k: metaMap.keySet()) {
					key.append(k+",");
					val.append(metaMap.get(k)+",");
					counter++;
				}
				
				saveSingleEntry(key.substring(0, key.length()-1), val.substring(0, val.length()-1), 0, true, writer);

				writer.write("</stuff>".getBytes("UTF-8"));
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
		}
		
		//opening file
		HashMap<String, String> loader = new HashMap<String, String>();
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
					loader.put(keyArr[i], valArr[i]);
				}
				System.out.println(loader);
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
		
	}
}
