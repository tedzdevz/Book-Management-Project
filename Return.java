package oopproject;

import java.io.*;
import java.util.*;

public class Return implements Templete{
    private String book_id;
    private String id;
    private String returned_book;
    Scanner input = new Scanner(System.in);
    
    public void Returned(ArrayList<String> book_arr) {
    	try{
    	    Scanner borrowed_book = new Scanner(System.in);
    	    System.out.println("Do you want to Return the Book? [Yes or No]");
            setReturnedBook(borrowed_book.nextLine()); //return
    	       
    	    if(getReturnedBook().equalsIgnoreCase("yes")){
    	        Scanner book_info = new Scanner(new File("book_record.txt"));
                while(book_info.hasNextLine()){
  	            String books = book_info.nextLine();
    	            String books_sep[] = books.split("_");
    	            if(getBookID().equals(books_sep[0])){
                        System.out.println("You have Return the Book! ");
    	                    FileWriter fw = new FileWriter("log_file.txt",true);
    	                    fw.write(getBorrowersID() + ";" + book_arr.get(book_arr.size() - 1) +
    	                                 ";" + "IN" );
                            fw.write(System.getProperty("line.separator"));
    	                    fw.close();
    	                    Main.main(null);
    	            }
    	        }
    	            
    	    }else if(getReturnedBook().equalsIgnoreCase("no")){
                System.err.println("Return to Main Menu");
    	        System.exit(0);
    	    }else{
    	        System.err.println("Error!! Incorrect input value");
    	    }                  
    	}catch(IOException e){
    	    e.printStackTrace();
    	}	    
    }
    
    public void checkBooksID(){
        try{
        System.out.println("--------------------------------");
        System.out.println("Enter Book ID: ");
        setBookID(input.nextLine());
        Scanner logs = new Scanner(new File("log_file.txt"));
        boolean found_id = false;
        while(logs.hasNextLine()){
            String info = logs.nextLine();
            String logs_sep[] = info.split(";");
            if(getBookID().equals(logs_sep[1])){
                System.out.println("Book ID Found!!");
                found_id = true;
            }
        }
        if(!found_id){
            System.err.println("Error!! Book not found");
            checkBooksID();
        }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
    public void B_Details(){
        try{
        ArrayList<String> log_arr = new ArrayList<String>();
        ArrayList<String> book_arr = new ArrayList<String>();
        Scanner borrower = new Scanner(new File("borrowers.txt"));
        Scanner book = new Scanner(new File("books.txt"));
        Scanner logs = new Scanner(new File("log_file.txt"));
        
        System.out.println("--------------------------------");
        System.out.print("Enter ID: ");
        setBorrowersID(input.nextLine());
        checkBorrowersID();
        
        boolean found = false;
        
        checkBooksID();
        
        while(borrower.hasNextLine()){  
            String borrower_info = borrower.nextLine();
            String borrower_sep[] = borrower_info.split(",");
            
            if(getBorrowersID().equals(borrower_sep[0])){
                
                while (logs.hasNextLine()){
                    String log = logs.nextLine();
                    String log_sep[] = log.split(";");
                    if(getBorrowersID().equals(log_sep[0])){
                        log_arr.add(log_sep[2]);
                        book_arr.add(log_sep[1]);
                    }
                }
                if(log_arr.isEmpty()){
                    System.err.println("No Borrowed Book");
                    
                }else if(log_arr.get(log_arr.size() - 1).equalsIgnoreCase("OUT")){
                        while(book.hasNextLine()){
                            String books = book.nextLine();
                            String books_sep[] = books.split("_");
                            if(book_arr.get(book_arr.size() - 1).equals(books_sep[0])){
                                System.out.println("Latest Book Borrowed: " + books_sep[1]);
                            }
                        }
                        Returned(book_arr);
                    }
                found = true;
            }
        }
        if(!found){
            System.err.println("Error!! User ID not found");
        }
    
        }catch(IOException e){
        e.printStackTrace();
    }
    }
    
    public void showRecord() {
        try{
        Scanner logs = new Scanner(new File("log_file.txt"));
        while(logs.hasNextLine()){
            String info = logs.nextLine();
            String logs_sep[] = info.split(";");
            System.out.println("Borrower ID: " +logs_sep[0]);
            System.out.println("Book ID: " +logs_sep[1]);
            System.out.println("Status: " +logs_sep[2]);
            
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    
    public  void checkBorrowersID(){
        try{
        Scanner borrow = new Scanner(new File("borrowers.txt"));
        boolean found_id = false;
        while(borrow.hasNextLine()){
            String info = borrow.nextLine();
            String spl[] = info.split(",");
            if(getBorrowersID().equals(spl[0])){
                System.out.println("Book ID Found!!");
                found_id = true;
            }
        }
        if(!found_id){
            System.err.println("Error!! Book not found");
            B_Details();
        }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
        
    public void setReturnedBook(String returned_book){
        this.returned_book = returned_book;
    }
    public String getReturnedBook(){
        return this.returned_book;
    }

    public void setBookID(String book_id) {
        this.book_id = book_id;
    }

    public String getBookID() {
        return this.book_id;
    }

    public void setBorrowersID(String id) {
        this.id = id;
    }

    public String getBorrowersID() {
       return this.id;
       
    }
    
    
    
}
    
