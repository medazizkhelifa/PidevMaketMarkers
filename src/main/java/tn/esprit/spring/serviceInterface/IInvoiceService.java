package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Order;

public interface IInvoiceService {
    void generateInvoice(Order order);
}
