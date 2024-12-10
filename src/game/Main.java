package game;

import game.Model;
import javafx.application.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.MainMenu;
import game.Model;
import game.menu.MapSelectionMenu;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;


public class Main extends Application {
	public static Stage mainStage;
	public void start(Stage primaryStage) {
		
//		Model model = new Model();
//		Pane root=new Pane();
//		Scene scene=new Scene(root, 360,400);
//		primaryStage.setTitle("Main");
//		primaryStage.setScene(scene);
//		primaryStage.centerOnScreen();
//		primaryStage.setOnCloseRequest(event ->{
//			System.out.println("Exit application");
//		});
//		Canvas canvas= new Canvas(360,400);
//		GraphicsContext gc=canvas.getGraphicsContext2D();
//		
//		root.getChildren().add(canvas);
//		for(Node child: root.getChildren()) {
//			child.setVisible(true);
//		}
//		model.startGameLoop(scene, gc);
//		primaryStage.setResizable(false);
//		primaryStage.show();
		mainStage = primaryStage;

        // Start with map selection
//        PageChanger.changeToMapSelection(primaryStage);
		PageChanger.changeToMainMenu(primaryStage);
		
        primaryStage.show();
	}
	
	public static Stage getMainStage() {
		return mainStage;
	}
	 public static void setMainStage(Stage mainStage) {
	        Main.mainStage = mainStage;
	    }
		
	
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
