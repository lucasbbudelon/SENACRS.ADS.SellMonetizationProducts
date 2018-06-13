/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * Account registration
 * @author lucasbbudelon
 */
public class Account {

    /**
     * Unique identifier
     */
    public int Id;
    
    /**
     * Account Identifier
     */
    public String Number;
    
    /**
     * Current balance
     */
    public double Balance;

    public Account() {
    }

    public Account(String number) {
        this.Number = number;
        this.Balance = 0;
    }
    
    public Account(String number, double balance) {
        this.Number = number;
        this.Balance = balance;
    }
    
    @Override
    public String toString() {
        return "Conta: " + Number + " | " +
               "Saldo: R$ " + Balance;
    }
}
