package tn.esprit.spring.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Invoice;
import tn.esprit.spring.entities.Order;

@Setter
@Getter
@ToString
public class InvoiceDto {
    public Date invoiceDate;
    public Order order;
    public String currency;
    public double tax;
    public double subtotal;
    public double taxPercent;
    public double shipping;
    public double total;
    public Date createdAt;
    public Date updatedAt;
    @JsonProperty("Email")
    public String email;
    public String firstName;
    public String lastName;
    @JsonProperty("Address")
    public String address;

    public InvoiceDto() {}

    public static InvoiceDto mapFrom(Invoice invoiceInput, Client client) {
        InvoiceDto invoice = new InvoiceDto();
        invoice.setEmail(client.getEmail());
        invoice.setFirstName(client.getFirstName());
        invoice.setLastName(client.getLastName());
        invoice.setAddress(client.getAdresse());
        invoice.setOrder(invoiceInput.getOrder());
        invoice.setCurrency("DT");
        invoice.setInvoiceDate(invoiceInput.getInvoiceDate());
        invoice.setTax(invoiceInput.getTax());
        invoice.setSubtotal(invoiceInput.getSubtotal());
        invoice.setTaxPercent(invoiceInput.getTaxPercent());
        invoice.setShipping(invoiceInput.getShipping());
        invoice.setTotal(invoiceInput.getTotal());
        invoice.setCreatedAt(invoiceInput.getCreatedAt());
        invoice.setUpdatedAt(invoiceInput.getUpdatedAt());

        // if(order.getOrderLines().get(0).getProduct().getCurrency() == null){
        // invoice.setCurrency("DT");
        // }
        return invoice;
    }
}
