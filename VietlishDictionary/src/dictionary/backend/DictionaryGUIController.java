package dictionary.backend;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class DictionaryGUIController extends DictionaryGUI
{
	@FXML
	private ListView<Word> wordResult; 
	
	@FXML
	private TextField wordSearch;
	
	@FXML 
	private TextArea wordDetails;
	
	//TODO Viết hàm cho nút để tìm từ 
	@FXML
	private Button searchButton;
	
	//Danh sách các từ có trong database
	private ObservableList<Word> listOfWords = FXCollections.observableArrayList();
	
	//Khi chạy chương trình thì hàm này sẽ chạy
	//hiện tại đang dùng file dictionaries cũ
	public void initialize()
	{
		insertFromFile();
		loadedDatabase.sort(null);
		for (int i = 0; i < loadedDatabase.size(); i++)
			listOfWords.add(loadedDatabase.get(i));
	
		wordResult.setItems(listOfWords);
		
		
		showDetails();
		showSearchResult();
	}
	
	//Hiện phát âm và nghĩa của từ đang chọn 
	//ở danh sách từ 
	public void showDetails()
	{
		wordResult.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>()
		{
			@Override
			public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue)
			{
				wordDetails.clear();
				wordDetails.appendText(newValue.getWord_pronounce() + "\n" + newValue.getWord_explain());
			}
		});	
	}
	
	//Thay đổi danh sách từ khi gõ vào phần text
	//để tìm từ gần giống
	public void showSearchResult()
	{
		wordSearch.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				ArrayList<Word> searchResult = dictionarySearch(wordSearch.getText());
				listOfWords = FXCollections.observableList(searchResult);
				wordResult.setItems(listOfWords);
			}
		});
	}
}


