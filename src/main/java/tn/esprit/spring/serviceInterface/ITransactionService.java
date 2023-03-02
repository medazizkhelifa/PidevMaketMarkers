package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.TransactionDTO;

public interface ITransactionService {
    void AddTransaction(TransactionDTO transaction) throws Exception;
    GiftPointDto getAvailableBalance(int client_id) throws Exception;
}
