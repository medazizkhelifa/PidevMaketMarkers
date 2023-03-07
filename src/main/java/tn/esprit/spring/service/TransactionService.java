package tn.esprit.spring.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.NotificationDto;
import tn.esprit.spring.dto.TransactionDTO;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;
import tn.esprit.spring.serviceInterface.IInvoiceService;
import tn.esprit.spring.serviceInterface.INotificationService;
import tn.esprit.spring.serviceInterface.ITransactionService;

@Service
public class TransactionService  implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IGiftPointRepository giftPointRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private INotificationService notificationService;
    
    @Override
    public void AddTransaction(TransactionDTO transaction) throws InvalidInputException {//transaction est un objet de type TransactionDTO
        //"TransactionDTO" qui contient les détails de la transaction, tels que la facture associée, la date de transaction et la remise accordée.

        if(transaction.getOrderId() >= 0){//order exisiste
            if(!orderRepository.existsById(transaction.getOrderId())){
                throw new InvalidInputException("Order not found");
            }

            Order orderInDb = orderRepository.getById(transaction.getOrderId());
            if(orderInDb.getInvoice() != null){
                throw new InvalidInputException("Order already paid");
            }

            if(orderInDb.getStatus() != OrderStatus.APPROVED){
                throw new InvalidInputException("Order is not approved");
            }

            if(transaction.getDiscount() > 0){ // verifier si le client a appliquer une remise
                //verifier si la remise est valide
                if(!verifyDiscountValidty(orderInDb.getClient().getId(), transaction.getDiscount()))
                {
                    throw new InvalidInputException("Discount is not valid");
                }
            }

            Invoice invoiceInDb = invoiceService.generateInvoice(orderInDb);

            Transaction transactionToSave = new Transaction();
            transactionToSave.setInvoice(invoiceInDb);//invoiceInDb est une facture qui existe dans la base de données
            transactionToSave.setTransactionDate(new Date());
            transactionToSave.setStatus(TransactionStatus.COMPLETED);//finish transaction
            transactionRepository.save(transactionToSave);
            invoiceInDb.setDiscount(transaction.getDiscount());//remise accordée
            invoiceRepository.save(invoiceInDb);//sauvegarder la facture dans la base de données
            this.addGiftPoint(transactionToSave.getId(), invoiceInDb);//ajouter des points de cadeau
            //notifier admin
            NotificationDto notificationDto = new NotificationDto(2L, "", String.valueOf(orderInDb.getId()),"Order paid", "An order has been paid successfully");
            this.notificationService.sendNotificationToUser(notificationDto);
            return;
        }
        throw new InvalidInputException("Order is required");
    }

    private boolean verifyDiscountValidty(Long clientId, double discount) throws InvalidInputException{//verification de la validité de la remise
        GiftPointDto giftPointDto = this.getAvailableBalance(clientId);
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

    public GiftPointDto getAvailableBalance(Long client_id) throws InvalidInputException
    {
        if(client_id <= 0){
            throw new InvalidInputException("Client id is required");
        }
        Optional<User> client = userRepository.findById(client_id);
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