/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * Product Registration
 *
 * @author lucas.budelon
 */
public class Product {

    /**
     * Unique identifier
     */
    public int Id;

    /**
     * Product Identifier
     */
    public String Code;

    /**
     * Product Name
     */
    public String Name;

    /**
     * Product value
     */
    public double Price;

    public Product() {
    }

    public Product(int Id, String code, String name, double price) {
        this.Id = Id;
        this.Code = code;
        this.Name = name;
        this.Price = price;
    }

    public Product(String code, String name, double price) {
        this.Code = code;
        this.Name = name;
        this.Price = price;
    }

    @Override
    public String toString() {
        return "Código: " + Code + " | "
                + "Nome: " + Name + " | "
                + "Preço: R$ " + Price;
    }
}
