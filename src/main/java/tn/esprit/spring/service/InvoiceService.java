package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.CurrencyDto;
import tn.esprit.spring.dto.InvoiceDto;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.Invoice;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.repository.CurrencyRepository;
import tn.esprit.spring.repository.IClientRepository;
import tn.esprit.spring.repository.IInvoiceRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.ProductRepository;
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
    IClientRepository clientRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    IHttpHelper httpHelper;

    @Autowired
    private Environment environment;

    @Override
    public void generateInvoice(Order order) {
        Order orderInDb = populateFields(order);//populateFields(order) est une methode qui va remplir les champs de orderInDb
        Invoice invoice = CalculateInvoicePrice(orderInDb);
        Client client = clientRepository.findById(orderInDb.getClient().getId()).get();
        //this.requestSendInvoice(invoice, client);
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
            float currencyRate = getCurrencyRate(orderLine.getProduct().getCurrency());//taux d'echange
            float productPrice = currencyRate * orderLine.getProduct().getPrix();
            float price = productPrice * orderLine.getQuantity();
            invoice.setSubtotal(invoice.getSubtotal() + price);
        });

        invoice.setTaxPercent(19);
        invoice.setTax(invoice.getSubtotal() * invoice.getTaxPercent()/100);
        invoice.setShipping(7.0);
        invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getShipping());
        invoice.setInvoiceDate(new Date());
        invoice.setCreatedAt(new Date());
        invoice.setUpdatedAt(new Date());
        invoice.setCurrency("DT");

        invoiceRepository.save(invoice);
        return invoice;
    }

    private void requestSendInvoice(Invoice invoice, Client client) {
        String invoiceServiceUrl = environment.getProperty("invoice_generator");
        InvoiceDto toSend = InvoiceDto.mapFrom(invoice, client);
        httpHelper.post(invoiceServiceUrl+"/send-invoice", toSend, null);
    }
}

