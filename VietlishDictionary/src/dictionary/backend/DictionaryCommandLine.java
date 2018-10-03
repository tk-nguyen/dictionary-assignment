package dictionary.backend;

import java.util.ArrayList;
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
	
	
	//Hàm dùng để sửa từ có trong database
    public void fixWord()
    {
        try
        {
	    	String cont = "y";
        	do 
	    	{	
	        	System.out.print("Nhập từ bạn muốn sửa: ");
		        Scanner scan  = new Scanner(System.in);
		        String input = scan.nextLine();
		        System.out.println("Lựa chọn mục bạn muốn sửa: (Nhập số để chọn) ");
		        System.out.println("1. Từ");
		        System.out.println("2. Phiên âm");
		        System.out.println("3. Nghĩa");
		        System.out.println("0. Dừng");
		        int choice = scan.nextInt();
		        scan.nextLine();
		        if (choice != 0)
		        {
		            System.out.print("Bạn muốn sửa thành: ");
		            String fix = scan.nextLine();
		            if (fix(input, choice, fix))
		            {
		                System.out.println("Từ đã được sửa!");
		            }
		            else
		            {
		            	System.out.println("Từ không tồn tại.");
		            }
		            System.out.print("Bạn có muốn sửa tiếp không? (y/n): ");
		            cont = scan.nextLine().toLowerCase();
		        }
		        
	    	} while (cont.equals("y"));
        }
        catch (Exception ex)
        {
        	System.out.println("Lỗi đọc từ command line: " + ex); 
        }
        
    }
    
    //Hàm để xóa từ có trong database
    public void deleteWord()
    {
        try 
        {
	    	String cont = "y";
        	do
        	{
	        	System.out.print("Nhập từ bạn muốn xóa: ");
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
		        System.out.print("Bạn có muốn sửa tiếp không? (y/n): ");
	            cont = scan.nextLine().toLowerCase();
        	} while (cont.equals("y"));
        }
        catch (Exception ex)
        {
        	System.out.println("Lỗi đọc từ command line: " + ex);
        }
        
    }
    
    //Tìm một từ có sẵn trong database
    public void dictionaryLookup()
    {
    	try
    	{
    		System.out.print("Viết từ mà bạn cần tra: ");
    		
    		Scanner scan = new Scanner(System.in);
	    	String lookup = scan.nextLine();     
	    	lookup.toLowerCase();
	    	
	    	boolean exist = false;
	    	
	    	//Bỏ tất cả kí hiệu đặc biệt trong từ mà người dùng nhập vào
	    	StringBuilder corrected = new StringBuilder();
	    	for (int i = 0; i < lookup.length(); i++)
	    		if (Character.isAlphabetic(lookup.charAt(i)) ) corrected.append(lookup.charAt(i));
	    	lookup = corrected.toString();
	    	
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
    
        
	//Hàm để thực hiện các thao tác với từ điển
	public void dictionaryAdvanced()
	{
		try
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("Lựa chọn việc bạn muốn làm (nhập số để chọn): ");
			System.out.println("1. Hiện tất cả từ của từ điển (showAllWord)");
			System.out.println("2. Tìm một từ trong từ điển (dictionaryLookup)");
			System.out.println("3. Tìm những từ bắt đầu bằng input trong từ điển (dictionarySearch)");
			System.out.println("4. Xóa một từ trong từ điển (deleteWord)");
			System.out.println("5. Sửa từ có trong từ điển (fixWord)");
			int choice = scan.nextInt();
			
			switch(choice)
			{
				case (1): insertFromFile(); showAllWord(); break;
				case (2): insertFromFile(); dictionaryLookup(); break;
				case (3): 
				{
					insertFromFile();
	
					ArrayList<Word> searchResult = dictionarySearch();
			        for (int i = 0; i < searchResult.size(); i++)
			        	searchResult.get(i).printWord();
			        
			        break;

				}
				case (4): insertFromFile(); deleteWord(); break;
				case (5): insertFromFile(); fixWord(); break;
				default: break;
			}
			scan.close();
		}
		catch (Exception ex)
		{
			System.out.println("Lỗi đọc từ command line: " + ex);
		}
	}
}
