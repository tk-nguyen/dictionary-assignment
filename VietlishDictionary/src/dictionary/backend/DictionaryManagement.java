package dictionary.backend;

import java.util.*;
import java.io.*;

public class DictionaryManagement 
{
    protected Dictionary dict = new Dictionary();
    protected ArrayList<Word> loadedDatabase = dict.getDatabase();
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
