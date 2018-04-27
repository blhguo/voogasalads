package gameData;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import game_engine.level.Level;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LoadData {
	private File f;
	private List<Level> levels;
	private static final int FILE_EXTENSION = 4;
	private XStream mySerializer;

	
	public LoadData(File load) {
		this.f = load;
		this.mySerializer = new XStream(new DomDriver());
		try {
			openFile(this.f);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openFile(File file) throws ParserConfigurationException{
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
					Level temp = (Level) mySerializer.fromXML((Reader) doc.getElementsByTagName("<data"+Integer.toString(i)+">"));
					levels.add(temp);
				}
			} catch (SAXException e) {
				return;
			}
        } catch (IOException e) {
        		return;
        }
	}
	
	public List<Level> loadLevels(){
		return levels;
	}
}
