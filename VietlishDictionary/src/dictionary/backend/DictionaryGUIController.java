package dictionary.backend;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.event.*;

public class DictionaryGUIController extends DictionaryGUI
{
	@FXML
	private ListView<Word> wordResult;

	@FXML
	private TextField wordSearch;

	@FXML
	private TextArea wordDetails;

	@FXML
	private Button searchButton;

	@FXML
	private Button infoButton;

	@FXML
	private Button addButton;

	@FXML
	private Button deleteButton;

	// Danh sách các từ có trong database
	private ObservableList<Word> listOfWords = FXCollections.observableArrayList();
	public static ArrayList<Word> addByUser = new ArrayList<>();
	public static ArrayList<String> deletedWord = new ArrayList<>();
	
	// Khi chạy chương trình thì hàm này sẽ chạy
	public void initialize()
	{
		insertFromFile();
		loadedDatabase.sort(null);
		for (int i = 0; i < loadedDatabase.size(); i++)
			listOfWords.add(loadedDatabase.get(i));

		wordResult.setItems(listOfWords);
		
		handleInfoButton();
		handleSearchButton();
		showDetails();
		showSearchResult();
		handleAddButton();
		handleDeleteButton();
	}

	// Hiện phát âm và nghĩa của từ đang chọn
	// ở danh sách từ
	public void showDetails()
	{
		try
		{
			wordResult.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>()
			{
				@Override
				public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue)
				{
					if (newValue != null)
					{
						wordDetails.setText(newValue.getWord_target() + "\n" + newValue.getWord_pronounce() + "\n"
								+ newValue.getWord_explain());
					}
				}
			});
		} catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	// Thay đổi danh sách từ khi gõ vào phần text
	// để tìm từ gần giống
	public void showSearchResult()
	{
		try
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
		} catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	// Khi ấn enter sẽ chọn từ đầu tiên
	// trong danh sách từ tìm được
	@FXML
	public void handleEnterButton(KeyEvent key)
	{
		if (key.getCode() == KeyCode.ENTER && !listOfWords.isEmpty())
		{
			wordResult.getSelectionModel().clearAndSelect(0);
		}
	}

	// Cho nút search giống như ấn enter
	public void handleSearchButton()
	{
		searchButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				wordResult.getSelectionModel().clearAndSelect(0);
			}
		});
	}

	// Hàm cho nút about
	public void handleInfoButton()
	{
		infoButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
					Parent root = FXMLLoader.load(getClass().getResource("/vietlishdictionary/InfoWindow.fxml"));
					Stage stage = new Stage();
					stage.setTitle("About");
					stage.setScene(new Scene(root, 270, 60));
					stage.show();
				} catch (IOException ex)
				{
					System.out.println(ex);
				}
			}
		});
	}

	// Hàm cho nút thêm từ
	public void handleAddButton()
	{
		addButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				try
				{
                                        Stage s2 = (Stage) addButton.getScene().getWindow();                                    
					Parent root2 = FXMLLoader.load(getClass().getResource("/vietlishdictionary/AddWindow.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Thêm từ...");
					stage.setScene(new Scene(root2, 350, 220));
                                        s2.close();
					stage.show();
				} catch (IOException ex)
				{
					System.out.println(ex);
				}
			}
		});
	}

	// Hàm cho nút xóa từ
	public void handleDeleteButton()
	{
		deleteButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
                                        Stage s2 = (Stage) deleteButton.getScene().getWindow();
					Parent root3 = FXMLLoader.load(getClass().getResource("/vietlishdictionary/DeleteWindow.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Xóa từ...");
					stage.setScene(new Scene(root3, 400, 70));
					s2.close();			
					stage.show();
				} catch (IOException ex)
				{
					System.out.println(ex);
				}
			}

		});
	}

	// Hàm thêm từ có trong từ điển
	public void addWord(Word w)
	{
		Word newWord = new Word(w);
		listOfWords.add(newWord);
		wordResult.refresh();
	}

	// Hàm để xóa từ có sẵn trong từ điển
	public void deleteAddedWord()
	{
		for (int j = 0; j < deletedWord.size(); j++)
		{
			for (int i = 0; i < loadedDatabase.size(); i++)
			{
				if (loadedDatabase.get(i).getWord_target().equals(deletedWord.get(j)))
				{
					loadedDatabase.remove(i);
					i--;
				}
			}
		}

	}

	// Cập nhật dữ liệu gồm từ mới add hoặc xóa
	public void refreshListView()
	{
		loadedDatabase.addAll(addByUser);
		deleteAddedWord();
		loadedDatabase.sort(null);
		listOfWords = FXCollections.observableList(loadedDatabase);
		wordResult.setItems(listOfWords);
		wordResult.refresh();
	}
}
