package dictionary.backend;

import java.util.ArrayList;

public class DictionaryGUI extends DictionaryManagement
{
    //Hàm search với input từ bên ngoài, dùng cho GUI
    public ArrayList<Word> dictionarySearch(String input)
    {
    	ArrayList<Word> matchedWord = new ArrayList<Word>();
	    
    	for (int i = 0; i < loadedDatabase.size(); i++)
	    	if (loadedDatabase.get(i).getWord_target().indexOf(input) == 0)
	    			matchedWord.add(loadedDatabase.get(i));
	  
    	return matchedWord;
    }
    
    //TODO Viết lại insertFromFile cho database dạng mới
}
