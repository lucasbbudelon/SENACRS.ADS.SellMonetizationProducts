/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApplication;

import Entities.Account;
import Service.AccountService;
import java.util.ArrayList;

/**
 *
 * @author lucas.budelon
 */
public class AccountConsoleApplication {
    
    private static final AccountService _service = new AccountService();
    
    public static void main(String[] args) {

        ConsoleUI.ShowMenuCRUD("Conta");

        int opMenu = 0;
        
        do {
            opMenu = ConsoleApplication.ConsoleUI.scanInt("Informe a opção de menu: ");
            
            ConsoleApplication.ConsoleUI.PrintWhiteSpace();
            
            switch (opMenu){
                case 1:{
                    Save();
                    break;
                }
                case 2:{
                    Save();
                    break;
                }
                case 3:{
                    Delete();
                    break;
                }
                case 4:{
                    List();
                    break;
                }
                
                case 5:{
                    Main.main(args);
                }
                
                default:{
                    System.err.println("Opção inválida!");
                }    
            }
            
            ConsoleApplication.ConsoleUI.ShowMenuCRUD("Conta");
            
        } while(opMenu != 5); 
    }
    
    private static void Save(){
        
        Account entiti = new Account();
        
        System.out.println("== Informe os dados da Conta ==");

        entiti.Number = ConsoleUI.scanString("Número: ");       
        
        _service.Save(entiti);
    }
      
    private static void Delete(){
        
        System.out.println("== Informe o número da conta ==");
        
        String number = ConsoleUI.scanString("Número: ");
        
       _service.Delete(number);
    }
    
    private static void List(){        
        ArrayList<Account> list = _service.GetAll();
        
        if (list.isEmpty()){
            System.out.println("Nenhuma conta cadastrada!");
        }
        else{
            list.forEach((c) -> {
                System.out.println(c.toString());
            });
        } 
    }
}
