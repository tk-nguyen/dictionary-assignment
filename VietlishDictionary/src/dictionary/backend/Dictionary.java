package dictionary.backend;

import java.util.*;

public class Dictionary 
{
    //Mảng chứa tất cả các từ và nghĩa của từ điển
	private ArrayList<Word> database = new ArrayList<Word>();
    
    public void addWord(Word word)
    {
        database.add(word);
    }

    public ArrayList<Word> getDatabase() {
        return database;
    } 
}
