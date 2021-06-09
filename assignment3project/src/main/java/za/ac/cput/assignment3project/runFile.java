/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3project;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;







/**
 *
 * @author User
 */
public class runFile {
  
String  stakeholderOut = "stakeholder.ser";
ArrayList<Customer> customers = new ArrayList<>();
ArrayList<Supplier> suppliers = new ArrayList<>();
InputStream instream;
ObjectInputStream output;
FileWriter fileWriter;
BufferedWriter bufferedWritter;


public void readFile(){
     try{

        output = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
        
        
        System.out.println("**Open file***");

        while (true)
        {
        Object obj = output.readObject();
                if (obj instanceof Customer){
                customers.add((Customer) obj);
                }else 
                    suppliers.add((Supplier) obj );
        }
        
     }catch (EOFException ex){
         System.out.println(customers.size());
        System.out.println(suppliers.size());
            try {
                output.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("error opening stakeholder.ser file: " + e.getMessage());
        }
    }


// sorrt array in ascending order 
    public void sort(){
    customers.sort(Comparator.comparing(Stakeholder::getStHolderId));
    }
    
    public int determineAge(String dateOfBirth){
    
        String[] values = dateOfBirth.split(" ");
        int day = Integer.parseInt(values[0]);
        String month = values[1];
        int year = Integer.parseInt(values[2]);
        Calendar calMonth = Calendar.getInstance();
        Date date = null;
        
        try{
            date = new SimpleDateFormat("CHM").parse(month);
        }catch (ParseException e){
        System.out.println(e.getMessage());
        }
        if (date!=null)
            calMonth.setTime(date);
        System.out.println();
        int mth = calMonth.get(Calendar.MONTH) + 1;
        LocalDate dob = LocalDate.of(year,mth,day);
        LocalDate today = LocalDate.now();
        
        return (int) ChronoUnit.YEARS.between(dob, today);
    }

     
     public void formatDate(){
     
         HashMap<String, String > months = new HashMap<>();
         
        months.put("01","Jan");
        months.put("02","Feb");
        months.put("03","Mar");
        months.put("04","Apr");
        months.put("05","May");
        months.put("06","June");
        months.put("07","Jul");
        months.put("08","Aug");
        months.put("09","Sep");
        months.put("10","Oct");
        months.put("11","Nov");
        months.put("12","Dec");
        
        for ( int i = 0; i<customers.size() ; i++);
    int i = 0;
    System.out.println(customers.get(i));
        String dob = customers.get(i).getDateOfBirth();
        String month = dob.substring(5,7);
        String newMonth = months.get(month);
        customers.get(i).setDateOfBirth(dob.substring(8) + " " + newMonth + dob.substring(0,4));
        
        
        
     }
   
    
    
    public void printcustomers(){
try{
    try (FileWriter fileWriter = new FileWriter("customerOutFile.txt"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

        bufferedWriter.write("================== CUSTOMERS =============================\r\n");
        System.out.println(customers);
        System.out.println("================== CUSTOMERS =============================\r\n");
        String l3 = String.format("%-5s\t%-10s\t%-10s\t%-10s\t%-1s\r\n", "ID", "Name",
                "Surname", "Date of birth", "Age");
        bufferedWriter.write(l3);
        bufferedWriter.write("==========================================================\r\n");
        
        for (Customer cust : customers){
            int age = determineAge(cust.getDateOfBirth());
            System.out.println(customers);
            String str = String.format("%-5s\t%-10s\t%-10s\t%-14s\t%-1s\r\n",
                    cust.getStHolderId(), cust.getFirstName(), cust.getSurName(), cust.getDateOfBirth(), age);
            bufferedWriter.write(str);
            int i = 0;
            System.out.println(customers.get(i));
            System.out.println(suppliers.get(i));
        }
        int [] values = detRent();
        
        bufferedWriter.write("\r\nCustomers who can rent:\t\t"+ values[0] +"\n");
        bufferedWriter.write("Customers who cannot rent:\t"+ values[1]);
        
    }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
public int[] detRent(){
   int Rent = 0;
       int notRent = 0;

       for (Customer customer : customers){
           System.out.println(customers);
           if (customer.getCanRent())
               Rent++;
           else notRent++;
       }

       return new int[]{Rent, notRent};
    }

public void sortSuppliers(){
    suppliers.sort(Comparator.comparing(Supplier::getName));
    System.out.println(suppliers);
}

public void printSuppliers(){

        try{
            try (FileWriter fileWriter = new FileWriter("supplierOutFile.txt"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                
                bufferedWriter.write("================== SUPPLIERS ==========================\n");
                String heading = String.format("%-5s\t%-17s\t%-10s\t%-15s\r\n", "ID", "Name",
                        "Prod Type", "Description");
                bufferedWriter.write(heading);
                bufferedWriter.write("========================================================\n");
                
                for (Supplier sup1 : suppliers){
                    String line = String.format("%-5s\t%-17s\t%-10s\t%-15s\r\n",
                            sup1.getStHolderId(), sup1.getName(), sup1.getProductType(), sup1.getProductDescription());
                    bufferedWriter.write(line);
                    int i = 0;
                    System.out.println(suppliers.get(i));
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
}

}
}
