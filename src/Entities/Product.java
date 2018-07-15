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
    private int id;

    /**
     * Product Identifier
     */
    private String code;

    /**
     * Product name
     */
    private String name;

    /**
     * Product value
     */
    private double price;

    public void setId(int Id) {
        this.id = Id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Product() {
    }

    public Product(int id, String code, String name, double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Código: " + code + " | "
                + "Nome: " + name + " | "
                + "Preço: R$ " + price;
    }
}
