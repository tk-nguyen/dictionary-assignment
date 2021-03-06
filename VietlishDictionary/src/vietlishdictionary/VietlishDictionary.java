package vietlishdictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;


public class VietlishDictionary extends Application 
{
	//Phần dùng để khởi động chương trình
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("DictionaryGUI.fxml"));
			Scene scene = new Scene(root,650,450);
			primaryStage.setTitle("Vietlish Dictionary");
			primaryStage.setScene(scene);
			primaryStage.show();	
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)		
	{
		/*
		DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile();
        dm.showWords();
        */
		launch(args);
	}

}
