//package game_engine.components;
//
//import javafx.util.Pair;
//
//import java.util.List;
//
///**
// * @author: Jeremy Chen
// * Component containing offset and
// */
//public class RectHitboxComponent implements HitboxComponent {
//
//    private double myWidth;
//    private double myHeight;
//    private double myXOffset;
//    private double myYOffset;
//
//    public RectHitboxComponent(List<String> args){
//        myWidth = Double.parseDouble(args.get(0));
//        myHeight = Double.parseDouble(args.get(1));
//        myXOffset = Double.parseDouble(args.get(2));
//        myYOffset = Double.parseDouble(args.get(3));
//    }
//
//    @Override
//    public double getXOffset() {
//        return myXOffset;
//    }
//
//    @Override
//    public double getYOffset() {
//        return myYOffset;
//    }
//
//    @Override
//    public double getWidth() {
//        return myWidth;
//    }
//
//    @Override
//    public double getHeight() {
//        return myHeight;
//    }
//}
