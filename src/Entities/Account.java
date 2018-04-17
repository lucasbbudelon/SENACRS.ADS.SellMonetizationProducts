/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author lucasbbudelon
 */
public class Account {

    public int Id;
    public String Number;
    public double Balance;

    public Account() {
    }

    public Account(String Number, double Balance) {
        this.Number = Number;
        this.Balance = Balance;
    }
}
