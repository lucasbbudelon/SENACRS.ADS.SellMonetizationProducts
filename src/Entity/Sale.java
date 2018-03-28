/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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

    public Sale(String Code, LocalDateTime DateTime, Customer Customer, List<SaleItem> Items) {
        this.Code = Code;
        this.DateTime = DateTime;
        this.Customer = Customer;
        this.Items = Items;
    }
}
