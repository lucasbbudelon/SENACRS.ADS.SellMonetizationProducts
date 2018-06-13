/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * Customer registration
 *
 * @author lucas.budelon
 */
public class Customer {

    /**
     * Unique identifier
     */
    public int Id;

    /**
     * Individual identifier
     */
    public String CPF;

    /**
     * Customer name
     */
    public String Name;

    /**
     * Customer Email
     */
    public String Email;

    /**
     * Associated account
     */
    public Account Account;

    public Customer() {
    }

    public Customer(String cpf, String name, String email, Account account) {
        this.CPF = cpf;
        this.Name = name;
        this.Email = email;
        this.Account = account;
    }

    public String GetAccountBalance() {
        return "Cliente: " + Name + " | "
                + "CPF: " + CPF + " | "
                + "Conta: " + Account.Number + " | "
                + "Saldo: R$" + Account.Balance;
    }

    @Override
    public String toString() {
        return "CPF: " + CPF + " | "
                + "Nome: " + Name + " | "
                + "Email:" + Email + " | "
                + Account.toString();
    }

}
