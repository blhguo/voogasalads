package gameData;

import java.util.Map;

import game_engine.Engine;

public class FirebaseObject {
	private Engine engine;
	private String gameName;
	private String saveFileName;
	Map<String, String> metaMap;
	Map<String, String> configMap;
	
	public FirebaseObject(Engine eng, String gn, String fn, Map<String, String> meta, Map<String, String>conf) {
		this.engine = eng;
		this.gameName = gn;
		this.saveFileName = fn;
		this.metaMap = meta;
		this.configMap = conf;
	}
	
	public FirebaseObject(){
		
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public String getSaveFileName() {
		return saveFileName;
	}
	
	public Map<String, String> getMetaMap() {
		return metaMap;
	}
	
	public Map<String, String> getConfigMap() {
		return configMap;
	}
	

}
