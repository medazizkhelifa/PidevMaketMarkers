package tn.esprit.spring.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.TransactionDTO;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;
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
    @Autowired
    private IClientRepository clientRepository;


    
    @Override
    public void AddTransaction(TransactionDTO transaction) throws InvalidInputException {//transaction est un objet de type TransactionDTO
        //"TransactionDTO" qui contient les détails de la transaction, tels que la facture associée, la date de transaction et la remise accordée.

        if(transaction.getInvoice() != null){//invoice exisiste
            Invoice invoiceInDb = invoiceRepository.findById(transaction.getInvoice().getId()).get();
            if(invoiceInDb != null){


                if(invoiceInDb.getTransaction() != null){
                    throw new InvalidInputException("Invoice already paid");//verification du paiement de la facture
                }

                if(!verifyDiscountValidty(invoiceInDb.getOrder().getClient().getId(), transaction.getDiscount())){
                    throw new InvalidInputException("Discount is not valid");
                }

                Transaction transactionToSave = new Transaction();
                transactionToSave.setInvoice(invoiceInDb);//invoiceInDb est une facture qui existe dans la base de données
                transactionToSave.setTransactionDate(new Date());
                transactionToSave.setStatus(TransactionStatus.COMPLETED);//finish transaction
                transactionRepository.save(transactionToSave);
                invoiceInDb.setDiscount(transaction.getDiscount());//remise accordée
                invoiceRepository.save(invoiceInDb);//sauvegarder la facture dans la base de données
                this.addGiftPoint(transactionToSave.getId(), invoiceInDb);//ajouter des points de cadeau
                return;
            }
            throw new InvalidInputException("Invoice not found");
        }
        throw new InvalidInputException("Invoice is required");
    }

    private boolean verifyDiscountValidty(int clientId, double discount) throws InvalidInputException{//verification de la validité de la remise
        GiftPointDto giftPointDto = this.getAvailableBalance(clientId);
        System.out.println("giftPointDto.getAvailableBalance() : " + giftPointDto.getAvailableBalance());
        return giftPointDto.getAvailableBalance() >= discount;
    }


    private void addGiftPoint(int transactionId, Invoice invoice) {
        Transaction transactionInDb = transactionRepository.getById(transactionId);//get transaction by id
        transactionInDb.setInvoice(invoice);

        Order order = orderRepository.findByInvoice_Id(transactionInDb.getInvoice().getId());//get order by invoice id
        transactionInDb.getInvoice().setOrder(order);

        GiftPoint giftPoint = new GiftPoint();

        giftPoint.setTransaction(transactionInDb);
        giftPoint.setPoint((int)transactionInDb.getInvoice().getTotal());//get total of invoice
        giftPoint.setClient(transactionInDb.getInvoice().getOrder().getClient());

        giftPointRepository.save(giftPoint);//save gift point in database
        transactionInDb.setGiftPoint(giftPoint);//sauvegarder le point de cadeau dans transaction (la relation one to one)
        transactionRepository.save(transactionInDb);
    }

    public GiftPointDto getAvailableBalance(int client_id) throws InvalidInputException
    {
        if(client_id <= 0){
            throw new InvalidInputException("Client id is required");
        }
        Optional<Client> client = clientRepository.findById(client_id);
        if(!client.isPresent()){
            throw new InvalidInputException("Client not found");
        }


//utilisée pour stocker les données renvoyées par la requête dans la base de données
        Map<String, Object> output = new HashMap<String,Object>();//map pour stocker les données
        //get available balance récupérer les détails des points cadeaux disponibles pour le client à partir de la base de données.
        output = giftPointRepository.getAvailableBalance(client_id);//requet dans gift point repository(retourner les point de cadeau)
        //available_balance (query repository)
        GiftPointDto giftPointDto = new GiftPointDto();
        if(output.get("available_balance") == null){//si il n'existe pas retourner 0
            giftPointDto.setAvailableBalance(0);
        }else{
            Double availabeBalance = (double)output.get("available_balance") / 3;//calculer le point de cadeau
            giftPointDto.setAvailableBalance( availabeBalance);
        }

        if(output.get("total") == null){//pour exprimer que total est changer
            giftPointDto.setTotalBalance(0);
        }else{
            BigDecimal total = (BigDecimal)output.get("total");
            Double formattedTotal = (double)total.doubleValue() / 3;//calculer le point de cadeau
            giftPointDto.setTotalBalance(formattedTotal);
        }

        giftPointDto.setClientId(client_id);

        return giftPointDto;
    }
    
}