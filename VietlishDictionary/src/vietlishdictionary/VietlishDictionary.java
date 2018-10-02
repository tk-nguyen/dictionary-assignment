package vietlishdictionary;

import java.util.*;
import dictionary.backend.*;

public class VietlishDictionary {


    public static void main(String[] args) 
    {
        DictionaryCommandLine tuDien = new DictionaryCommandLine();
        
        tuDien.dictionaryAdvanced();
        
        /* Code để test dictionarySearch()
        ArrayList<Word> test = tuDien.dictionarySearch();
        for (int i = 0; i < test.size(); i++)
        	System.out.println(test.get(i).getWord_target());
       	*/
        
    }
    
}
