package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.Invoice;

public interface IInvoiceService {
    Invoice generateInvoice(Order order);
}
