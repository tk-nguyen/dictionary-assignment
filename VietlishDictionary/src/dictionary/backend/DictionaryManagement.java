package dictionary.backend;

import java.util.*;
import java.io.*;

public class DictionaryManagement 
{
    private Dictionary dict = new Dictionary();
    private ArrayList<Word> dictionary = dict.getDatabase();
	
    public void insertFromCommandLine()
    {
        try 
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
        catch (Exception ex)
        {
        	System.out.println("Lỗi đọc từ command line: " + ex);
        }
    }
    /*
    Đọc dữ liệu từ file dictionaries.txt
    Target và meaning ngăn cách bởi tab
    Sử dụng String.split("\\s+") để tách String thành String[], bỏ tất cả khoảng trắng giữa từ và nghĩa
    */
    
    public void insertFromFile()
    {
        try
        {
            File file = new File("dictionaries.txt");
         
            Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
            
            while (scan.hasNext())
            {
                String w = scan.nextLine();
                String[] word = w.split("\\s+", 2);
                dict.addWord(new Word(word[0], word[1]));
            }            
            scan.close();
        } 
        catch (Exception ex)
        {
            System.out.println("Lỗi đọc file: " + ex);
        }
    }
    
    public void showAllWord()
    {   
        //Format lại output để in ra cho thẳng hàng
        //Gióng theo hàng dọc bên trái
        
        /* Output nhìn sẽ như thế này
         No.       English                         Vietnamese                      
         1         apple                           quả táo                         
	     2         pear                            quả lê   
	     3         notebook                        quyển vở
	     4		   ...							   ...                    
        */
    	dictionary.sort(null);
        System.out.format("%-10s%-32s%-32s%n", "No.", "English", "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++)
        {
            System.out.format("%-10d%-32s%-32s%n", i + 1, dictionary.get(i).getWord_target(), dictionary.get(i).getWord_explain());
        }
    }
    
    /* Hàm tra cứu các từ trong từ điển 
     * Đang debug dở, push tạm để làm việc khác
    public void dictionaryLookup()
    {
    	System.out.println("Viết từ mà bạn cần tra: ");
    	Scanner scan = new Scanner(System.in);
    	String lookup = scan.nextLine();
    	scan.close();
       	
    	
    	boolean exist = false;
    	
    	for (int i = 0; i < dictionary.size(); i++) 
    	{
    		if (dictionary.get(i).getWord_target().equals(lookup))
    		{
    			System.out.println(dictionary.get(i).getWord_explain());
    			exist = true;
    			break;
    		}
    	}
    	if (!exist)	System.out.println("Không tồn tại từ đó trong từ điển");
    }
    */
}
