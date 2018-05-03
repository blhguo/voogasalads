package main;

import authoring.AuthoringEnvironment;
import authoring.GUI_Heirarchy.GUIGridPaneSuper;
import authoring.controllers.Loader;
import authoring.loadingviews.AuthoringLoader;
import authoring.loadingviews.PlayerLoader;
import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
import authoring.right_components.EntityComponent.EntityPane;
import frontend_utilities.ButtonFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class SplashScreen extends GUIGridPaneSuper{

	private Stage myStage;
	private Loader myLoader;
	private EntityPane myEP;
	private LevelPane myLP;
	private StoryBoardPane mySB;
	//private AuthoringEnvironment ae;
	
	public SplashScreen(Stage stage){
		myStage = stage;
//		myEP = new EntityPane(stage);
//		myLP = new LevelPane(stage);
//		mySB = new StoryBoardPane();
//		myLoader = new Loader(myLP, mySB, myEP);
		//ae = new AuthoringEnvironment(myStage);
	}
	
	public Scene getScene(){
		GridPane gridpane = new GridPane();
		gridpane.setHgap(AuthRes.getInt("Padding"));
		gridpane.setVgap(AuthRes.getInt("Padding"));
		gridpane.setPadding(new Insets(AuthRes.getInt("Padding")));
		Scene myScene = initScene(gridpane);
		
		finishScene(gridpane);
		return myScene;
	}
	
	private VBox makeVBox(){
		VBox myVBox = new VBox(AuthRes.getInt("VBPadding"));		
		Button createButton = ButtonFactory.makeButton(e -> {
			AuthoringEnvironment ae = new AuthoringEnvironment(myStage);
			
			myStage.getScene().setRoot(ae.display());
			myStage.show();
		});
		Button loadButton = ButtonFactory.makeButton(e -> {
			AuthoringLoader chooseauthoring = new AuthoringLoader(myStage);
			myStage.getScene().setRoot(chooseauthoring.display());
			myStage.show();
		});
		
		Button playButton = ButtonFactory.makeButton(e -> {
			PlayerLoader chooseplayer = new PlayerLoader(myStage);
			myStage.getScene().setRoot(chooseplayer.display());
			myStage.show();
		});
				
		HBox createHB = ButtonFactory.makeHBox("Create New Game", "Authoring Environment", createButton);
		HBox loadHB = ButtonFactory.makeHBox("Load Game for Editing", "Authoring Environment", loadButton);
		HBox playHB = ButtonFactory.makeHBox("Load Game for Play", "Game Player", playButton);
		myVBox.getChildren().addAll(createHB, loadHB, playHB);
		return myVBox;
	}
	
	@Override
	public Pane finishScene(GridPane gridpane) {
//		initScene(gridpane);
		//System.out.println("Finishing splash screen");
		Text title = new Text(AuthRes.getString("SplashTitle"));
		title.getStyleClass().add("title");
		VBox vb = makeVBox();
		gridpane.getChildren().addAll(title, vb);
		double width = title.getParent().getLayoutBounds().getWidth();
//		double width = myStage.getScene().getRoot().getLayoutBounds().getWidth();
		int numCols = (int) width / AuthRes.getInt("Padding");
		//System.out.println("Height"+ width);
		double height = title.getParent().getLayoutBounds().getHeight();
//		double height = myStage.getHeight();
//		double height = myStage.getScene().getRoot().getLayoutBounds().getHeight();

		//System.out.println("Height" + height);
		int numRows = (int) height / AuthRes.getInt("Padding");
		GridPane.setConstraints(title, numCols / 20, numRows * 2 / 3);
		GridPane.setConstraints(vb, numCols / 3, numRows / 4);
		return gridpane;
	}

}
