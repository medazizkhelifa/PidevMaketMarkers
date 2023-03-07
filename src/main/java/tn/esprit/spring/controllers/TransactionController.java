package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.ApiResponse;
import tn.esprit.spring.dto.GiftPointDto;
import tn.esprit.spring.dto.TransactionDTO;
import tn.esprit.spring.serviceInterface.ITransactionService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/pay-invoice")
    @ResponseBody
    public ApiResponse createTransaction(@RequestBody TransactionDTO transactionInput) {
        ApiResponse apiResponse = new ApiResponse();
        try
        {
            transactionService.AddTransaction(transactionInput);
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Transaction created successfully");
        }
        catch(InvalidInputException ex)
        {
            apiResponse.setStatus("ERROR");
            apiResponse.setMessage("Error while creating transaction");
            apiResponse.setError(ex.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/discount-balance/{id}")
    @ResponseBody
    public ApiResponse getAvailableBalance(@PathVariable("id") Long id) {
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Balance found");
            GiftPointDto giftPointDto = transactionService.getAvailableBalance(id);
            apiResponse.setData(giftPointDto);
        }catch(InvalidInputException ex){
            apiResponse.setStatus("ERROR");
            apiResponse.setMessage("Error while getting balance");
            apiResponse.setError(ex.getMessage());
        }
        return apiResponse;
    }
}
