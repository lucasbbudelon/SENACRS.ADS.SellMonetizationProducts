/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * Sale Items
 * @author lucas.budelon
 */
public class SaleItem {

    /**
     * Product sold
     */
    public Product Product;
    
    /**
     * Quantity of items
     */
    public int Quantity;

    public SaleItem() {
    }

    public SaleItem(Product product, int quantity) {
        this.Product = product;
        this.Quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Produto: " + Product.toString() + " | " +
               "Quantidade: " + Quantity ;
    }
}