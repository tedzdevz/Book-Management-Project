package oopproject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//Inheritance
public class Books extends Return{
    private String book_id;
    private String id;
    private String borrowed_book;
    private int []books = new int[10];
    ArrayList<String> borrowers_arr = new ArrayList<>();
    
    
    public  void MainDetails(){
        try{
        Scanner scan = new Scanner(System.in);
        Scanner read = new Scanner(new File("borrowers.txt"));
        boolean found_id = false;
        System.out.println("--------------------------------");
        System.out.print("Enter ID: ");
        String id1 = scan.nextLine();
        setBorrowersID(id1);
        super.setBorrowersID(id1);
        
        borrowers_arr.add(id1);
        while(read.hasNextLine()){
            String info = read.nextLine();
            String spl[] = info.split(",");
            if(id.equals(spl[0])){
                System.out.println("--------------------------------");
                System.out.println("Borrower ID: " + spl[0]);
                System.out.println("Borrower Name: " + spl[1]);
                System.out.println("Borrower Credit: " + spl[2]);
                System.out.println("--------------------------------");
                found_id = true;
                BookDetails();
            }
        }
        if(!found_id){
            System.err.println("Error!! User ID not found");
            MainDetails();
        }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void GatherAvail()throws FileNotFoundException, IOException{
        Scanner scan = new Scanner(System.in);
        Scanner read = new Scanner(new File("books.txt"));
        int x = 0;
        
        while(read.hasNextLine()){
            String info = read.nextLine().trim();
            String spl[] = info.split("_");

            if(spl == null || spl.length != 4){
                continue;
            }

            int sample = Integer.parseInt(spl[3]);
            int sample2 = sample;
            this.books[x] = sample2;
            x++;
        }
    }
    
    

    public void BookDetails()throws FileNotFoundException, IOException{
        Scanner scan = new Scanner(System.in);
        Scanner read = new Scanner(new File("books.txt"));
        int x = 0;
        System.out.println("List of Available Books: ");
        while(read.hasNextLine()){
            String info = read.nextLine().trim();
            String spl[] = info.split("_");
        
        
            if(spl == null || spl.length != 4){
                continue;
            }
            
            
            System.out.println(spl[0] + ": " + spl[1] + " worth of " + spl[2] + " credits and it has " + this.books[x] + " stock(s) left");
            x++;
        }
        Book();
    }
    
    public void Book() throws FileNotFoundException, IOException{
        Scanner scan = new Scanner(System.in);
        Scanner read = new Scanner(new File("books.txt"));
        boolean found = false;
        System.out.println("--------------------------------");
        System.out.println("Enter Book ID: ");
        setBookID(scan.nextLine());
        while(read.hasNextLine()){
            String book_info = read.nextLine();
            String book_sep[] = book_info.split("_");
           
            if(getBookID().equals(book_sep[0])){
                System.out.println("Book id found!");
                Borrowed();
                found = true;
            }
        }
        if(!found){
            System.err.println("Error!! Book id not found");
            Book();
        }
    }
    
    public void credits() throws FileNotFoundException, IOException{
        Scanner borrowID = new Scanner(new File("borrowers.txt"));
        Scanner bookID = new Scanner(new File("books.txt"));
        int borrower_credit, book_credit;
        while(borrowID.hasNextLine()){
                String borrow = borrowID.nextLine();
                String borrow_sep[] = borrow.split(",");
                if(getBorrowersID().equals(borrow_sep[0])){
                    while(bookID.hasNextLine()){
                        String book = bookID.nextLine();
                        String book_sep[] = book.split("_");
                        if(getBookID().equals(book_sep[0])){
                            borrower_credit = Integer.parseInt(borrow_sep[2]);
                            book_credit = Integer.parseInt(book_sep[2]);    
                            if(borrower_credit >= book_credit){
                                System.out.println("Borrowed Complete!");
                            }else{
                                System.err.println("Not Enough Credit!");
                                Main.main(null);
                                
                            }
                        }
                    }
                }
        }
    }
    
    public void BookAvailability() throws FileNotFoundException, IOException{
        int count = 0, bookcount = 0;
        ArrayList<String> logs_arr = new ArrayList<String>();
        ArrayList<String> books_arr = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        Scanner log = new Scanner(new File("log_file.txt"));
        Scanner book = new Scanner(new File("books.txt"));
        System.out.println("--------------------------------");
        System.out.println("Enter book ID: ");
        setBookID(input.next());
         
        while (book.hasNextLine()){
            String bookinfo = book.nextLine();
            String []id_info = bookinfo.split("_");
            if(getBookID().equals(id_info[0])){
                System.out.println("Book name: " + id_info[1]);
                count = Integer.parseInt(id_info[3]);
                bookcount = Integer.parseInt(id_info[3]);
                while(log.hasNextLine()){
                    String loginfo = log.nextLine();
                    String []logbook = loginfo.split(";");
                    if(getBookID().equals(logbook[1])){
                        if(logbook[2].equalsIgnoreCase("OUT")){
                            count -= 1;
                        }else if(logbook[2].equalsIgnoreCase("IN")){
                            count += 1;
                        }
                    }
                }
            }
        }
        if(count > 0){
            while(log.hasNextLine()){
                String logs = log.nextLine();
                String log_sep[] = logs.split(";");
                if(getBorrowersID().equals(log_sep[0])){
                    logs_arr.add(log_sep[2]);
                    books_arr.add(log_sep[1]);
                }
            }
            if(logs_arr.isEmpty()){
                credits();
                System.out.println("Book Borrowed");
            }else if(logs_arr.get(logs_arr.size() - 1).equals("OUT")){
                while(book.hasNextLine()){
                    String book_info = book.nextLine();
                    String book_sep[] = book_info.split("_");
                    if(books_arr.get(books_arr.size() - 1).equals(book_sep[0])){
                        System.out.println("Borrowed Book: " +book_sep[1]);
                    }
                }
            }else{
                System.out.println("Book Borrowed");
                credits();
            }
        }
    }
    
    public void Borrowed(){
        try{
        int borrower_credit, book_credit; 
        Scanner borrowed_book = new Scanner(System.in);
        System.out.println("Do you want to Borrow the Book? [1] Confirm [2] Decline");
        setBorrowedBook(borrowed_book.nextLine());
        
       
        if(getBorrowedBook().equalsIgnoreCase("1")){
           
            ArrayList<String> logstats = new ArrayList<String>();
            Scanner borrowID = new Scanner(new File("borrowers.txt"));
            Scanner bookID = new Scanner(new File("books.txt"));
            Scanner log = new Scanner(new File("log_file.txt"));
            boolean found = false;
            while(log.hasNextLine()){
                String stats = log.nextLine();
                String []status = stats.split(";");
                if(getBorrowersID().equals(status[0])){
                   logstats.add(status[2]);     
                }
                
            }
            if(logstats.isEmpty()){
                credits();
                FileWriter fw = new FileWriter("log_file.txt",true);
                fw.write(getBorrowersID() + ";" + getBookID() +
                                        ";" + "OUT");
                fw.write(System.getProperty("line.separator"));
                fw.close();   
                
            }else if(logstats.get(logstats.size()-1).equals("OUT")){
                System.err.println("Book is already borrowered\n"
                                 + "Return the Book you borrowed");
            }else{
                credits();
                FileWriter fw = new FileWriter("log_file.txt",true);
                fw.write(getBorrowersID() + ";" + getBookID() +
                                        ";" + "OUT");
                fw.write(System.getProperty("line.separator"));
                fw.close();
              
            }
        }else if(getBorrowedBook().equalsIgnoreCase("2")){
            System.err.println("Back to main menu");
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
    
    public void setBorrowedBook(String borrowed_book){
        this.borrowed_book = borrowed_book;
    }
    public String getBorrowedBook(){
        return this.borrowed_book;
    }
    public void setBorrowersID(String id){
        this.id = id;
    }
    public String getBorrowersID(){
        return this.id;
    }
    public void setBookID(String book_id){
        this.book_id = book_id;
    }
    public String getBookID(){
        return this.book_id;
    }
}

