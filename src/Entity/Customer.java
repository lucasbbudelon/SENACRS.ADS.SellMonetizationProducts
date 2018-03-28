/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author lucas.budelon
 */
public class Customer {

    public int Id;
    public String CPF;
    public String Name;
    public String Email;
    public Account Account;

    public Customer() {
    }

    public Customer(String CPF, String Name, String Email, Account Account) {
        this.CPF = CPF;
        this.Name = Name;
        this.Email = Email;
        this.Account = Account;
    }
}
