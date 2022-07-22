package oopproject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Return morph;
        Books obj = new Books();
        while(true){
            
            Scanner input = new Scanner(System.in);
            System.out.println("Nyte Library Services");
            System.out.print("[1] Borrow [2] Return\nInput: ");
            String choice = input.next();
            
            //Polymorphism
            if (choice.equals("1")){
                morph = new Books();
                morph.B_Details();
            }else if(choice.equals("2")){
                morph = new Return();
                morph.B_Details();
            }
        }
    }
}
