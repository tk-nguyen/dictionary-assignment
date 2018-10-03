package dictionary.backend;

import java.util.*;
import java.io.*;

public class DictionaryManagement 
{
    protected Dictionary dict = new Dictionary();
    private ArrayList<Word> loadedDatabase = dict.getDatabase();
    protected ArrayList<Word> addToDatabase = new ArrayList<Word>();
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
            
            for(int i = 0; i < addToDatabase.size(); i++)
            {
                Word w = addToDatabase.get(i);
                fw.write(w.getWord_target()    + "\t"
                        +w.getWord_pronounce() + "\t"
                        +w.getWord_explain()   + "\n");
            }
            fw.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Lỗi ghi file: " + ex);
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
    	loadedDatabase.sort(null);
        System.out.format("%-10s%-32s%-32s%-32s%n", "No.", "English", "Pronunciation", "Vietnamese");
        for (int i = 0; i < loadedDatabase.size(); i++)
        {
            System.out.format("%-10d", i + 1);
            loadedDatabase.get(i).printWord();
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
	    	
	    	for (int i = 0; i < loadedDatabase.size(); i++) 
	    	{
	    		if (loadedDatabase.get(i).getWord_target().equals(lookup))
	    		{
	    			System.out.println(loadedDatabase.get(i).getWord_pronounce());
	    			System.out.println(loadedDatabase.get(i).getWord_explain());
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
    
    //Hàm tìm kiếm tất cả các từ bắt đầu bằng input
    //có sẵn trong database
    public ArrayList<Word> dictionarySearch()
    {
    	loadedDatabase.sort(null);
    	ArrayList<Word> matchedWord = new ArrayList<Word>();
    	try
    	{
	    	System.out.print("Nhập từ bạn muốn tìm trong database: ");
	    	Scanner scan = new Scanner(System.in);
	    	String input = scan.nextLine();
	    	
	    	for (int i = 0; i < loadedDatabase.size(); i++)
	    		if (loadedDatabase.get(i).getWord_target().indexOf(input) == 0)
	    			matchedWord.add(loadedDatabase.get(i));
	    	
    	}
    	catch (Exception ex)
    	{
    		System.out.println("Lỗi đọc từ command line: " + ex);
    	}
    	return matchedWord;
    }
    
    //Hàm để xóa từ có trong database được load
    public boolean delete(String s)
    {
        boolean isDeleted = false;
        boolean isInLoadedDB = false;
        for (int i = 0; i < loadedDatabase.size(); i++)
        {
            if (s.equals(loadedDatabase.get(i).getWord_target()))
            {
                loadedDatabase.remove(i);
                isDeleted = true;
                isInLoadedDB = true;
                break;
            }
        }
        
        //Nếu không có trong database được load
        //thì tìm trong danh sách từ được thêm vào database
        if (!isInLoadedDB)
        {
            for (int i = 0; i < addToDatabase.size(); i++)
            {
                if (s.equals(addToDatabase.get(i).getWord_target()))
                {
                    addToDatabase.remove(i);
                    isDeleted = true;
                    break;
                }
            }
        }
        return isDeleted;
    }
    
    //Hàm sửa từ có trong database được load
    public boolean fix(String s, int n, String fix)
    {
        boolean isFix = false;
        boolean isInLoadedDB = false;
        for (int i = 0; i < loadedDatabase.size(); i++)
        {
            if (s.equals(loadedDatabase.get(i).getWord_target()))
            {
                switch (n)
                {
                    case 1: loadedDatabase.get(i).setWord_target(fix); break;
                    case 2: loadedDatabase.get(i).setWord_pronounce(fix); break;
                    case 3: loadedDatabase.get(i).setWord_explain(fix); break;
                }
                isFix = true;
                isInLoadedDB = true;
                break;
            }
        }
        
        
        //Nếu không thấy trong database được load
        //thì tìm từ đó trong danh sách được add vào database
        if (!isInLoadedDB)
        {
            for (int i = 0; i < addToDatabase.size(); i++)
            {
                if (s.equals(addToDatabase.get(i).getWord_target()))
                {
                    switch (n)
                    {
                        case 1: addToDatabase.get(i).setWord_target(fix); break;
                        case 2: addToDatabase.get(i).setWord_pronounce(fix); break;
                        case 3: addToDatabase.get(i).setWord_explain(fix); break;
                    }
                    isFix = true;
                    break;
                }
            }
        }
        return isFix;
    }
}
