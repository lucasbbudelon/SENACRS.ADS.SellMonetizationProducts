/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lucas.budelon
 */
public class Customer {

    public int Id;
    public String CPF;
    public String Name;
    public String Email;
    public int AccountId;
    public Account Account;

    public Customer() {
    }

    public Customer(String cpf, String name, String email, Account account) {
        this.CPF = cpf;
        this.Name = name;
        this.Email = email;
        this.Account = account;
    }

    @Override
    public String toString() {
        return "CPF: " + CPF + " | " +
                "Nome: " + Name + " | " +
                "Email:" + Email+ " | " +
                Account.toString();
    }
    
    
}
