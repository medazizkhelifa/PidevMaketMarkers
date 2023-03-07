package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.TransactionDTO;

public interface ITransactionService {
    void AddTransaction(TransactionDTO transaction) throws InvalidInputException;
    GiftPointDto getAvailableBalance(Long client_id) throws InvalidInputException;
}
