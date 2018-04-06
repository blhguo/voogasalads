package gameData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_engine.Level;

public class ManipData {
	//constructor
	public ManipData() {
		
	}
	
	private void SaveLevel(Level input, int levelnum) {
		String xml = null;
		XStream serializer = new XStream(new DomDriver());
		System.out.println("Beginning of serialization \n");
		FileOutputStream fos = null;
		try {
			xml = serializer.toXML(input);
			fos = new FileOutputStream("dataLevel" + levelnum + ".xml");
	        fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
	        byte[] bytes = xml.getBytes("UTF-8");
	        fos.write(bytes);
	        }
		catch (Exception e){
			System.out.println("Something broke");
		}
		finally{
	        if(fos != null){
	            try{
	                fos.close();
	            }catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public void SaveData(List<Level> levels) {
		int counter = 0;
		for(Level l: levels) {
			SaveLevel(l, counter);
		}
	}
	
}
