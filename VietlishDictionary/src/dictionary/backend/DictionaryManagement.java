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
            String meaning = input.nextLine();
            
            dict.addWord(new Word(word, meaning));
        }
        input.close();
    }
    
    public void showAllWord()
    {
        ArrayList<Word> dictionary = dict.getDatabase();
        
        //Format lại output để in ra cho thẳng hàng
        //Gióng theo hàng dọc bên trái
        
        /* Output nhìn sẽ như thế này
         No.       English                         Vietnamese                      
		 1         apple                           quả táo                         
	     2         pear                            quả lê   
	     3		   notebook						   quyển vở
	     4		   ...							   ...                    
        */
      
        System.out.format("%-10s%-32s%-32s%n", "No.", "English", "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++)
        {
            System.out.format("%-10d%-32s%-32s%n", i + 1, dictionary.get(i).getWord_target(), dictionary.get(i).getWord_explain());
        }
    }
}
