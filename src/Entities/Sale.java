/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Sales record
 *
 * @author lucas.budelon
 */
public class Sale {

    // <editor-fold defaultstate="collapsed" desc="properties">
    /**
     * Unique identifier
     */
    private int id;

    /**
     * Sale identifier
     */
    private String code;

    /**
     * date of sale
     */
    private LocalDate date;

    /**
     *
     */
    private Customer customer;

    /**
     *
     */
    public ArrayList<SaleItem> items;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getter and setter">
    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String Code) {
        this.code = Code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate Date) {
        this.date = Date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer Customer) {
        this.customer = Customer;
    }

    public ArrayList<SaleItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SaleItem> Items) {
        this.items = Items;
    }
    
    public double getTotal() {
        double totalSale = 0;
        totalSale = items.stream()
                .map((item) -> item.getProduct().getPrice() * item.getQuantity())
                .reduce(totalSale, (accumulator, _item) -> accumulator + _item);
        return totalSale;
    }
    
    public String getItemsResume() {
        String items = "";
        for (SaleItem item : this.items) {
            items += item.getProduct().getName() + " (" + item.getQuantity() + ")\n";
        }
        return items;
    }

// </editor-fold>
    
    public Sale() {
        this.customer = new Customer();
        this.items = new ArrayList<>();
    }

    public Sale(String code, LocalDate dateTime, Customer customer, ArrayList<SaleItem> items) {
        this.code = code;
        this.date = dateTime;
        this.customer = customer;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Código: " + code + " | "
                + "Data: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
                + "Cliente: " + customer.getName() + " | "
                + "Itens: " + getItemsResume() + " | "
                + "Total: R$" + getTotal();
    }

    

    
}
