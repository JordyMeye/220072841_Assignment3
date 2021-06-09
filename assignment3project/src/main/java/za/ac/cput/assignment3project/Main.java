/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3project;

/**
 *
 * @author 220072841
 */
public class Main {
    public static void main(String[] args) {
        runFile object = new runFile();
        object.readFile();
        object.sort();
        object.formatDate();
        
        object.sortSuppliers();
        object.printSuppliers();
        object.detRent();
      
        
        
    }
   
}
