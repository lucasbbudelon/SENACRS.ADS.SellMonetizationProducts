/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * Sale Items
 *
 * @author lucas.budelon
 */
public class SaleItem {

    // <editor-fold defaultstate="collapsed" desc="properties">
    /**
     * Product sold
     */
    private Product Product;

    /**
     * Quantity of items
     */
    private int Quantity;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getter and setter">
    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product Product) {
        this.Product = Product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    // </editor-fold>
    
    public SaleItem() {
    }

    public SaleItem(Product product, int quantity) {
        this.Product = product;
        this.Quantity = quantity;
    }

    @Override
    public String toString() {
        return "Produto: " + Product.toString() + " | "
                + "Quantidade: " + Quantity;
    }
}
