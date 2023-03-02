package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.TransactionDTO;
import tn.esprit.spring.entities.Transaction;
import tn.esprit.spring.serviceInterface.ITransactionService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/pay-invoice")
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionDTO transactionInput) {
        try{
            transactionService.AddTransaction(transactionInput);
            return ResponseEntity.ok().body("Transaction created successfully");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/discount-balance/{id}")
    public ResponseEntity<Object> getAvailableBalance(@PathVariable("id") int id) {
        try{
            GiftPointDto giftPointDto = transactionService.getAvailableBalance(id);
            return  ResponseEntity.ok().body(giftPointDto);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body("Error while getting available balance: Client not found");
        }
    }
}
