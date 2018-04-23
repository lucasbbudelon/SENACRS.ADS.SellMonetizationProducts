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
public class Product {

    public int Id;
    public String Code;
    public String Name;
    public double Price;

    public Product() {
    }

    public Product(String code, String name, double price) {
        this.Code = code;
        this.Name = name;
        this.Price = price;
    }
    
    @Override
    public String toString() {
        return "Código: " + Code + " | " +
               "Nome: " + Name + " | " +
               "Preço: R$ " + Price;
    }
}
