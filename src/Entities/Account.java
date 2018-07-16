/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * Account registration
 *
 * @author lucasbbudelon
 */
public class Account {

    // <editor-fold defaultstate="collapsed" desc="properties">
    /**
     * Unique identifier
     */
    private int id;

    /**
     * Account Identifier
     */
    private String number;

    /**
     * Current balance
     */
    private double balance;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getter and setter">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    // </editor-fold>

    public Account() {
    }

    public Account(String number) {
        this.number = number;
        this.balance = 0;
    }

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Conta: " + number + " | "
                + "Saldo: R$ " + balance;
    }
}
