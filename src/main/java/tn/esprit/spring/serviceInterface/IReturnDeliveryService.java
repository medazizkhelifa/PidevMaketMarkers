package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.ReturnDelivery;

import java.util.List;

public interface IReturnDeliveryService {
    ReturnDelivery addReturnDelivery(ReturnDelivery returnDelivery);

    List<ReturnDelivery> getReturnDelivery();

    void deleteReturnDelivery(Long id);

    ReturnDelivery updateReturnDelivery(ReturnDelivery returnDelivery);
}
