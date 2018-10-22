package dictionary.backend;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class InfoWindowController 
{
	@FXML
	private TextArea infoScreen;
	
	public void initialize()
	{
		infoScreen.setText("Vietlish Dictionary\nAuthor: Thai Khac Nguyen, Nguyen Quang Minh");
	}
}
