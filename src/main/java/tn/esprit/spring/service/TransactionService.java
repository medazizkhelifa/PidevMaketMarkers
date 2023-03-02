package tn.esprit.spring.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.TransactionDTO;
import tn.esprit.spring.entities.GiftPoint;
import tn.esprit.spring.entities.Invoice;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.Transaction;
import tn.esprit.spring.entities.TransactionStatus;
import tn.esprit.spring.repository.IGiftPointRepository;
import tn.esprit.spring.repository.IInvoiceRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.ITransactionRepository;
import tn.esprit.spring.serviceInterface.ITransactionService;

@Service
public class TransactionService  implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IInvoiceRepository invoiceRepository;
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IGiftPointRepository giftPointRepository;


    
    @Override
    public void AddTransaction(TransactionDTO transaction) throws Exception {

        if(transaction.getInvoice() != null && transaction.getInvoice().getId() <= 0){
            Invoice invoiceInDb = invoiceRepository.findById(transaction.getInvoice().getId()).get();
            if(invoiceInDb != null){


                if(invoiceInDb.getTransaction() != null){
                    throw new Exception("Invoice already paid");//verification du paiement de la facture
                }

                if(!verifyDiscountValidty(invoiceInDb.getOrder().getClient().getId(), transaction.getDiscount())){
                    throw new Exception("Discount is not valid");
                }

                Transaction transactionToSave = new Transaction();
                transactionToSave.setInvoice(invoiceInDb);
                transactionToSave.setTransactionDate(new Date());
                transactionToSave.setStatus(TransactionStatus.COMPLETED);
                transactionRepository.save(transactionToSave);
                invoiceInDb.setDiscount(transaction.getDiscount());
                invoiceRepository.save(invoiceInDb);
                this.addGiftPoint(transactionToSave.getId(), invoiceInDb);
                return;
            }
            throw new Exception("Invoice not found");
        }
        throw new Exception("Invoice is required");
    }

    private boolean verifyDiscountValidty(int clientId, double discount) throws Exception{
        GiftPointDto giftPointDto = this.getAvailableBalance(clientId);
        System.out.println("giftPointDto.getAvailableBalance() : " + giftPointDto.getAvailableBalance());
        return giftPointDto.getAvailableBalance() >= discount;
    }


    private void addGiftPoint(int transactionId, Invoice invoice) {
        Transaction transactionInDb = transactionRepository.getById(transactionId);
        transactionInDb.setInvoice(invoice);

        Order order = orderRepository.findByInvoice_Id(transactionInDb.getInvoice().getId());
        transactionInDb.getInvoice().setOrder(order);

        GiftPoint giftPoint = new GiftPoint();

        giftPoint.setTransaction(transactionInDb);
        giftPoint.setPoint((int)transactionInDb.getInvoice().getTotal());
        giftPoint.setClient(transactionInDb.getInvoice().getOrder().getClient());

        giftPointRepository.save(giftPoint);
        transactionInDb.setGiftPoint(giftPoint);
        transactionRepository.save(transactionInDb);
    }

    public GiftPointDto getAvailableBalance(int client_id) throws Exception
    {

        Map<String, Object> output = new HashMap<String,Object>();
        output = giftPointRepository.getAvailableBalance(client_id);
        for(Map.Entry<String, Object> entry : output.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        GiftPointDto giftPointDto = new GiftPointDto();
        if(output.get("available_balance") == null){
            giftPointDto.setAvailableBalance(0);
        }else{
            Double availabeBalance = (double)output.get("available_balance") / 3;
            giftPointDto.setAvailableBalance( availabeBalance);
        }

        if(output.get("total") == null){
            giftPointDto.setTotalBalance(0);
        }else{
            BigDecimal total = (BigDecimal)output.get("total");
            Double formattedTotal = (double)total.doubleValue() / 3;
            giftPointDto.setTotalBalance(formattedTotal);
        }
        if(output.get("client_id") != null){
            giftPointDto.setClientId((int)output.get("client_id"));
        }else{
            throw new Exception("Client not found");
        }

        return giftPointDto;


    }
    
}