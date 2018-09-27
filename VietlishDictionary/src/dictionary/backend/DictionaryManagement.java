package dictionary.backend;

import java.util.*;

public class DictionaryManagement 
{
    private Dictionary dict = new Dictionary();
    public void insertFromCommandLine()
    {
        Scanner input = new Scanner(System.in);
        int numberOfWord = input.nextInt();
        input.nextLine();
        for (int i = 0; i < numberOfWord; i++)
        {
            
            String word = input.nextLine();
            //input.nextLine();
            String meaning = input.nextLine();
            //input.nextLine();
            
            Word newWord = new Word(word, meaning);
            dict.addWord(newWord);
        }
        input.close();
    }
    
    public void showAllWord()
    {
        ArrayList<Word> dictionary = dict.getDatabase();
        System.out.format("%-10s%-32s%-32s%n", "No.", "English", "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++)
        {
            System.out.format("%-10d%-32s%-32s%n", i + 1, dictionary.get(i).getWord_target(), dictionary.get(i).getWord_explain());
        }
    }
}
