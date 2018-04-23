/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author lucas.budelon
 */
public class Sale {

    public int Id;
    public String Code;
    public LocalDateTime DateTime;
    public Customer Customer;
    public List<SaleItem> Items;

    public Sale() {
    }

    public Sale(String code, LocalDateTime dateTime, Customer customer, List<SaleItem> items) {
        this.Code = code;
        this.DateTime = dateTime;
        this.Customer = customer;
        this.Items = items;
    }
}
