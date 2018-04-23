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
public class SaleItem {

    public Product Product;
    public int Quantity;

    public SaleItem() {
    }

    public SaleItem(Product product, int quantity) {
        this.Product = product;
        this.Quantity = quantity;
    }
}
