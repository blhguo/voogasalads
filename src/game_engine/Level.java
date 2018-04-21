package game_engine;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author benhubsch
 * 
 *         This class is simply a convenient data structure to store the Entity objects in a given
 *         level.
 */
public class Level {

	private List<Entity> myLevel = new ArrayList<>();
	private String myName;
	private String bgImage;
	private String musicPath;
	private double width;
	private double height;
	private boolean isInf;
	private String thumbPath;

	//TODO: scrolling direction implementation

	public void setThumb(String filepath) {
		this.thumbPath = filepath;
	}

	public String getThumb() {
		return this.thumbPath;
	}

	public void setName(String name) {
		this.myName = name;
	}

	public String getName() {
		return this.myName;
	}

	public void setBG(String filepath) {
		this.bgImage = filepath;
	}

	public String getBG() {
		return this.bgImage;
	}

	public void setMusic(String filepath) {
		this.musicPath = filepath;
	}

	public String getMusic() {
		return this.musicPath;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getWidth() {
		return this.width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getHeight() {
		return this.height;
	}

	public boolean isInfinite() {
		return isInf;
	}

	public void setInf(boolean boolin) {
		this.isInf = boolin;
	}

	public void addEntity(Entity e) {
		myLevel.add(e);
	}

	public List<Entity> getEntities() {
		return myLevel;
	}

	public void remove(Entity e) {
		myLevel.remove(e);
	}

}
