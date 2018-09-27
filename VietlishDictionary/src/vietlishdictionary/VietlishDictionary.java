package vietlishdictionary;
import dictionary.backend.*;
public class VietlishDictionary {


    public static void main(String[] args) 
    {
        DictionaryManagement DN = new DictionaryManagement();
        DN.insertFromCommandLine();
        DN.showAllWord();
    }
    
}
