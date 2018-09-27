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
    }
    
    public void showAllWord()
    {
        ArrayList<Word> dictionary = dict.getDatabase();
        System.out.println("No." + "\t" + "English" + "\t\t" + "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++)
        {
            System.out.println(i+1 + "\t" + dictionary.get(i).getWord_target() + "\t\t" + dictionary.get(i).getWord_explain());
        }
    }
}
