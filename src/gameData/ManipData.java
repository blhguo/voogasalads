package gameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_engine.Level;

/*
 * authors: Brandon, Harry
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
	
	private void saveLevel(Level input, int levelnum) {
		System.out.println("Beginning of serialization");//println includes new line ya sily my bad
		try {
			xml = serializer.toXML(input);
			//writes each data object inside a unique data tag
	        fos.write(("<data"+Integer.toString(levelnum)+">").getBytes("UTF-8"));
	        byte[] bytes = xml.getBytes("UTF-8");
	        fos.write(bytes);
	        fos.write(("</data"+Integer.toString(levelnum)+">").getBytes("UTF-8"));
	        }
		catch (Exception e){
			System.out.println("Something broke"); //TODO
		}
	}
	
	public void saveData(List<Level> levels) {
		int counter = 0;
		try {
			//this writes only one file
			fos = new FileOutputStream("gameDataSave"+"someuniquefactor"+".xml");
	        try {
	        	//writes xml header and then the number of data objects inside
				fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
				fos.write(("<num>"+Integer.toString(levels.size())+"</num>").getBytes("UTF-8"));
				for(Level l: levels) {
					saveLevel(l, counter);
					counter++;
				}
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
	
	public ArrayList<Level> loadData(File load) {
		try {
			openFile(load);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO
		}
		return loadLevels();
	}
	
	private void openFile(File file) throws ParserConfigurationException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        String filePath = file.getAbsolutePath();
        String fileType = filePath.substring(filePath.length()-FILE_EXTENSION);
        //maybe a func to check that the file is an xml extension
        try {
        	dBuilder = dbFactory.newDocumentBuilder();
			Document doc;
			try {
				doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				int nums = Integer.parseInt(doc.getElementsByTagName("num").item(0).getTextContent());
				for (int i=0;i<nums;i++) {
					Level temp = (Level) deserializer.fromXML((Reader) doc.getElementsByTagName("<data"+Integer.toString(i)+">"));
					levellist.add(temp);
				}
			} catch (SAXException e) {
				return; //TODO
			}
        } catch (IOException e) {
        		return; //TODO
        }
	}
	
	private ArrayList<Level> loadLevels(){
		return levellist;
	}
	
}