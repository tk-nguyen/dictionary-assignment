package dictionary.backend;

import java.util.*;
import java.io.*;

public class DictionaryManagement 
{
    protected Dictionary dict = new Dictionary();
    private ArrayList<Word> dictionary = dict.getDatabase();
    private File file = new File("dictionaries.txt");
    /*
     * Đọc dữ liệu từ file dictionaries.txt
     * Target và meaning ngăn cách bởi tab
     * Sử dụng String.split("\\s+") để tách String thành String[], bỏ tất cả khoảng trắng giữa từ và nghĩa
     */
    
    public void insertFromFile()
    {
        try
        {         
            Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
            
            while (scan.hasNext())
            {
                String w = scan.nextLine();
                String[] word = w.split("\\s+", 3);
                dict.addWord(new Word(word[0], word[1], word[2]));
            }            
            scan.close();
        } 
        catch (Exception ex)
        {
            System.out.println("Lỗi đọc file: " + ex);
        }
    }
    
    
    //Hàm dùng để thêm từ vào dictionaries.txt
    public void writeToFile()
    {
        try 
        {
            FileWriter fw = new FileWriter(file, true);
            
            for(int i = 0; i < dictionary.size(); i++)
            {
                Word w = dictionary.get(i);
                fw.write(w.getWord_target()    + "\t"
                        +w.getWord_pronounce() + "\t"
                        +w.getWord_explain()   + "\n");
            }
            fw.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Loi ghi file: " + ex);
        }
    }
    
    public void showAllWord()
    {   
        //Format lại output để in ra cho thẳng hàng
        //Gióng theo hàng dọc bên trái
        
        /* Output nhìn sẽ như thế này
         *	
         * No.       English                         Pronunciation                   Vietnamese                      
         * 1         animal                          /ˈanɪm(ə)l/                     động vật                        
         * 2         apple                           /ˈap(ə)l/                       quả táo                         
         * 3         bear                            /bɛː/                           con gấu                         
         * 4         dog                             /dɒɡ/                           con chó       
         * .		 ...							 ...							   ...               
         */
    	dictionary.sort(null);
        System.out.format("%-10s%-32s%-32s%-32s%n", "No.", "English", "Pronunciation", "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++)
        {
            System.out.format("%-10d%-32s%-32s%-32s%n", i + 1, dictionary.get(i).getWord_target(),dictionary.get(i).getWord_pronounce(), dictionary.get(i).getWord_explain());
        }
    }
    
    public void dictionaryLookup()
    {
    	try
    	{
    		System.out.print("Viết từ mà bạn cần tra: ");
    		
    		Scanner scan = new Scanner(System.in);
	    	String lookup = scan.nextLine();     
	    	lookup.toLowerCase();
	    	
	    	//Bỏ tất cả kí hiệu đặc biệt trong từ mà người dùng nhập vào
	    	StringBuilder corrected = new StringBuilder();
	    	for (int i = 0; i < lookup.length(); i++)
	    		if (Character.isAlphabetic(lookup.charAt(i)) ) corrected.append(lookup.charAt(i));
	    	lookup = corrected.toString();
	    	
	    	boolean exist = false;
	    	
	    	for (int i = 0; i < dictionary.size(); i++) 
	    	{
	    		if (dictionary.get(i).getWord_target().equals(lookup))
	    		{
	    			System.out.println(dictionary.get(i).getWord_pronounce());
	    			System.out.println(dictionary.get(i).getWord_explain());
	    			exist = true;
	    			break;
	    		}
	    	}
	    	if (!exist)	System.out.println("Không tồn tại từ đó trong từ điển");
    	}
    	catch (Exception ex)
    	{
    		System.out.println("Lỗi đọc command line: " + ex);
    	}
    }
    
}
