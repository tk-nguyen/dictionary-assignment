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
	            
	            addToDatabase.add(new Word(word, pronounce, meaning));
	        }
	        
        }
        catch (Exception ex)
        {
        	System.out.println("Lỗi đọc từ command line: " + ex);
        }
    }
	
    public void fixWord()
    {
        System.out.println("Nhập từ bạn muốn sửa: ");
        Scanner scan  = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println("Lựa chọn mục bạn muốn sửa: (Nhập số để chọn) ");
	System.out.println("1. Từ");
	System.out.println("2. Phiên âm");
	System.out.println("3. Nghĩa");
        System.out.println("0. Dừng");
        int choice = scan.nextInt();
        if (choice != 0)
        {
            System.out.println("Bạn muốn sửa thành: ");
            String fix = scan.nextLine();
            if (fix(input, choice, fix))
            {
                System.out.println("Từ đã được sửa!");
            }
        }
        
        
    }
    
    public void deleteWord()
    {
        System.out.println("Nhập từ bạn muốn xóa: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        boolean isDeleted = delete(input);
        if (isDeleted)
        {
            System.out.println("Xóa từ thành công!");
        } 
        else
        {
            System.out.println("Từ không tồn tại.");
        }
        
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
