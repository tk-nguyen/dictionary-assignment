package vietlishdictionary;
import dictionary.backend.*;
public class VietlishDictionary {


    public static void main(String[] args) 
    {
        DictionaryManagement tuDien = new DictionaryManagement();
        tuDien.insertFromCommandLine();
        tuDien.showAllWord();
    }
    
}
