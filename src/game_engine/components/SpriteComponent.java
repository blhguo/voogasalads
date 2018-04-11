package game_engine.components;

import java.util.List;

import game_engine.Component;

public class SpriteComponent implements Component{

    private String myFileName;
    private boolean isVisible;
    private double myHeight;
    private double myWidth;
    private double myAngle;

    public SpriteComponent(List<String> args) {
        myFileName = args.get(0);
        isVisible = Boolean.parseBoolean(args.get(1));
        myWidth = Double.parseDouble(args.get(2));
        myHeight = Double.parseDouble(args.get(3));
        myAngle = Double.parseDouble(args.get(4));
    }
    public String getFileName() {
        return myFileName;
    }

    public void setFileName(String fileName) {
        myFileName = fileName;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible() {
        isVisible = true;
    }

    public void setInvisible() {
        isVisible = false;
    }

    public double getHeight() {
        return myHeight;
    }

    public void setHeight(int height) {
        myHeight = height;
    }

    public double getWidth() {
        return myWidth;
    }

    public void setWidth(int width) {
        myWidth = width;
    }

    public double getAngle() {
        return 0;
        //return myAngle;
    }

    public void setAngle(double angle) {
        //myAngle = angle;
    }
    public String getName(){
        return "Sprite";
    }
    @Override
    public String getValues() {
        return "Path,s," + getFileName() + ";Visible,b," + isVisible +
                ";Width,d," + getWidth() + ";Height,d," + getHeight() +
                ";Angle,d," + getAngle();
    }

}
