package vietlishdictionary;
import dictionary.backend.*;
public class VietlishDictionary {


    public static void main(String[] args) 
    {
        DictionaryCommandLine tuDien = new DictionaryCommandLine();
        
        tuDien.insertFromCommandLine();
        tuDien.writeToFile();
        tuDien.dictionaryAdvanced();
    }
    
}
