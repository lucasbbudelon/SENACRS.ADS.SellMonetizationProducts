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

    /**
     * Unique identifier
     */
    public int Id;

    /**
     * Sale identifier
     */
    public String Code;

    /**
     * Date of sale
     */
    public LocalDate Date;

    /**
     *
     */
    public Customer Customer;

    /**
     *
     */
    public ArrayList<SaleItem> Items;

    public Sale() {
        this.Customer = new Customer();
    }

    public Sale(String code, LocalDate dateTime, Customer customer, ArrayList<SaleItem> items) {
        this.Code = code;
        this.Date = dateTime;
        this.Customer = customer;
        this.Items = items;
    }

    @Override
    public String toString() {
        return "Código: " + Code + " | "
                + "Data: " + Date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
                + "Cliente: " + Customer.Name + " | "
                + "Itens: " + GetItems() + " | "
                + "Total: R$" + GetTotal();
    }

    public String GetItems() {
        String items = "";
        for (SaleItem item : Items) {
            items += item.Product.Name + "(" + item.Quantity + "), ";
        }
        return items;
    }

    public double GetTotal() {
        double totalSale = 0;
        totalSale = Items.stream()
                .map((item) -> item.Product.Price * item.Quantity)
                .reduce(totalSale, (accumulator, _item) -> accumulator + _item);
        return totalSale;
    }
}
