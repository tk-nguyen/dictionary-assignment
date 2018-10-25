package dictionary.backend;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DeleteWindowController {
    
    private String w_target;
    
    @FXML
    private Button Delete;
    
    @FXML
    private TextField Word;
    
    @FXML
    void deleteWord(ActionEvent event) {
        if (Word.getText().isEmpty())
        {
            try {
                Stage stage = (Stage) Delete.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("/vietlishdictionary/DictionaryGUI.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Vietlish Dictionary");
                primaryStage.setScene(scene);
                primaryStage.show();
                stage.close();
            } catch (IOException ex) {
                Logger.getLogger(DeleteWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
        try {
            Stage stage = (Stage) Delete.getScene().getWindow();
            w_target = Word.getText();
            
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vietlishdictionary/DictionaryGUI.fxml"));     

            Parent root = (Parent)fxmlLoader.load();          
            DictionaryGUIController controller = fxmlLoader.<DictionaryGUIController>getController();
            
            Scene scene = new Scene(root); 
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);    
            primaryStage.setTitle("Vietlish Dictionary");
            
            primaryStage.show();
            controller.deletedWord.add(w_target);
            controller.refreshListView();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(DeleteWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
}
