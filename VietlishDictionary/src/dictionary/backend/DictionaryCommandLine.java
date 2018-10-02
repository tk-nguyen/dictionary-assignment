package dictionary.backend;

import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement 
{
	
	//Nhập dữ liệu từ command line
	//Đây sẽ là hàm addNewWord()
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
	            String pronounce = input.nextLine();
	            String meaning = input.nextLine();
	            
	            dict.addWord(new Word(word, pronounce, meaning));
	        }
	        
        }
        catch (Exception ex)
        {
        	System.out.println("Lỗi đọc từ command line: " + ex);
        }
    }
	
    public void fixWord()
    {
            
    }
        
	//Hàm gọi các method của DictionaryManagement
	public void dictionaryAdvanced()
	{
		try
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Lựa chọn việc bạn muốn làm: ");
			System.out.println("1. Nhập database từ file (insertFromFile)");
			System.out.println("2. Hiện tất cả từ của từ điển (showAllWord)");
			System.out.println("3. Tìm một từ trong từ điển (dictionaryLookup)");
			int choice = scan.nextInt();
			
			switch(choice)
			{
				case (1): insertFromFile(); break;
				case (2): insertFromFile(); showAllWord(); break;
				case (3): insertFromFile(); dictionaryLookup(); break;
				default: break;
			}
		}
		catch (Exception ex)
		{
			System.out.println("Lỗi đọc từ command line: " + ex);
		}
	}
}
