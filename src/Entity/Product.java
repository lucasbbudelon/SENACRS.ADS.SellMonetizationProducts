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
public class Product {

    public int Id;
    public int Code;
    public String Name;
    public double Price;

    public Product() {
    }

    public Product(int Code, String Name, double Price) {
        this.Code = Code;
        this.Name = Name;
        this.Price = Price;
    }
}
