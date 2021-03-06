package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Classroom;

public class Main extends Application{
	
	private ClassroomGUI classroomGUI;
	private Classroom classroom;
	//private 
	
	public Main(){
		classroom = new Classroom();
		classroomGUI = new ClassroomGUI(classroom);
	}
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Parent root = FXMLLoader.load(getClass().getResource("main_pane.fxml"));
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("main_pane.fxml"));
		fxmlloader.setController(classroomGUI);
		Parent root	= fxmlloader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Classroom");
		primaryStage.show();
		
	}
	
	

}
