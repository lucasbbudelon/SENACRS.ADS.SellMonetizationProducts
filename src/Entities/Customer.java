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

    // <editor-fold defaultstate="collapsed" desc="properties">
    /**
     * Unique identifier
     */
    private int id;

    /**
     * Individual identifier
     */
    private String cpf;

    /**
     * Customer name
     */
    private String name;

    /**
     * Customer email
     */
    private String email;

    /**
     * Associated account
     */
    private Account account;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getter and setter">
    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String CPF) {
        this.cpf = CPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account Account) {
        this.account = Account;
    }

    // </editor-fold>
    
    public Customer() {
    }

    public Customer(String cpf, String name, String email, Account account) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.account = account;
    }

    public String GetAccountBalance() {
        return "Cliente: " + name + " | "
                + "CPF: " + cpf + " | "
                + "Conta: " + account.getNumber() + " | "
                + "Saldo: R$" + account.getBalance();
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + " | "
                + "Nome: " + name + " | "
                + "Email:" + email + " | "
                + account.toString();
    }

}
