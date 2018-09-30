package dictionary.backend;

import java.util.*;
import java.io.*;

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
    /*
    Đọc dữ liệu từ file dictionaries.txt
    Target và meaning ngăn cách bởi tab
    Sử dụng String.split("\t") để tách String thành String[], bỏ dấu tab
    */
    
    public void insertFromFile()
    {
        try
        {
            File file = new File("dictionaries.txt");
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader(file)));
            
            while (scan.hasNext())
            {
                String w = scan.nextLine();
                String[] word;
                word = w.split("\t");
                String target = word[0];
                String meaning = word[1];
                dict.addWord(new Word(target, meaning));
            }            
            scan.close();            
        } catch (Exception ex)
        {
            System.out.println("Loi doc file: " + ex);
        }
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
	     3         notebook                        quyển vở
	     4		   ...							   ...                    
        */
      
        System.out.format("%-10s%-32s%-32s%n", "No.", "English", "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++)
        {
            System.out.format("%-10d%-32s%-32s%n", i + 1, dictionary.get(i).getWord_target(), dictionary.get(i).getWord_explain());
        }
    }
}
