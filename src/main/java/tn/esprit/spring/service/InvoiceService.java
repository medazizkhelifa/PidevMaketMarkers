package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.CurrencyDto;
import tn.esprit.spring.dto.InvoiceDto;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.Invoice;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CurrencyRepository;
import tn.esprit.spring.repository.IInvoiceRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IHttpHelper;
import tn.esprit.spring.serviceInterface.IInvoiceService;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    IInvoiceRepository invoiceRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    IHttpHelper httpHelper;

    @Autowired
    private Environment environment;

    @Override
    public Invoice generateInvoice(Order order) {
        Order orderInDb = populateFields(order);//populateFields(order) est une methode qui va remplir les champs de orderInDb
        Invoice invoice = CalculateInvoicePrice(orderInDb);
        User client = userRepository.findById(orderInDb.getClient().getId()).get();
        this.requestSendInvoice(invoice, client);
        return invoice;
    }

    private Order populateFields(Order order) {//elle vas parcurire la liste des orderLines et va remplir les champs de orderInDb
        order.getOrderLines().forEach(orderLine -> {
            orderLine.setProduct(productRepository.findById(orderLine.getProduct().getCode()).get());
        });
        return orderRepository.getById(order.getId());
    }

    private float getCurrencyRate(Currency currency) {//calcule le taux de change
        if(currency.getCode().equals("TND")) return 1;
        String currencyServiceUrl = environment.getProperty("currency_service");
        CurrencyDto currencyDto = httpHelper.get(currencyServiceUrl+"/fetch-multi?from="+ currency.getCode() +"&to=tnd&api_key=demo", CurrencyDto.class);
        return currencyDto.getResults().get("TND");
    }

    private Invoice CalculateInvoicePrice(Order order) {
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setSubtotal(0);

        order.getOrderLines().forEach(orderLine -> {//parcourir la liste des orderLines
            double currencyRate = getCurrencyRate(orderLine.getProduct().getCurrency());//taux d'echange
            double productPrice = currencyRate * orderLine.getProduct().getPrix();
            double price = round(productPrice * orderLine.getQuantity());
            invoice.setSubtotal(invoice.getSubtotal() + price);
        });

        invoice.setTaxPercent(9);
        double taxPrice = invoice.getSubtotal() * invoice.getTaxPercent()/100;
        invoice.setTax(round(taxPrice));
        invoice.setShipping(7.0);
        double totalPrice = invoice.getSubtotal() + invoice.getTax() + invoice.getShipping();
        invoice.setTotal(round(totalPrice));
        invoice.setInvoiceDate(new Date());
        invoice.setCreatedAt(new Date());
        invoice.setUpdatedAt(new Date());
        invoice.setCurrency("DT");

        invoiceRepository.save(invoice);
        return invoice;
    }

    private void requestSendInvoice(Invoice invoice, User client) {
        String invoiceServiceUrl = environment.getProperty("invoice_generator");
        invoice.getOrder().setInvoice(null);
        InvoiceDto toSend = InvoiceDto.mapFrom(invoice, client);
        httpHelper.post(invoiceServiceUrl+"/send-invoice", toSend, null);
    }

    private double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

