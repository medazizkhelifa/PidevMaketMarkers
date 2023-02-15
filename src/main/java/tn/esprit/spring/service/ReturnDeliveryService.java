package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.ReturnDelivery;
import tn.esprit.spring.repository.ReturnDeliveryRepository;
import tn.esprit.spring.serviceInterface.IReturnDeliveryService;

import java.util.List;

@Service
@Slf4j
public class ReturnDeliveryService implements IReturnDeliveryService {

    @Autowired
    ReturnDeliveryRepository returnDeliveryRepository;
    @Override
    public ReturnDelivery addReturnDelivery(ReturnDelivery returnDelivery) {

        return returnDeliveryRepository.save(returnDelivery);
    }
    @Override
    public List<ReturnDelivery> getReturnDelivery(){
        return returnDeliveryRepository.findAll();
    }

    @Override
    public void deleteReturnDelivery(Long id){
        returnDeliveryRepository.deleteById(id);
    }
    @Override
    public ReturnDelivery updateReturnDelivery(ReturnDelivery returnDelivery){
        return returnDeliveryRepository.save(returnDelivery);
    }
}
