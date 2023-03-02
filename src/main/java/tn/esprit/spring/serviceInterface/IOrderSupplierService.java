package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.OrderSupplier;

import java.util.List;
import java.util.Map;

public interface IOrderSupplierService {
    public void passerCommande(int fournisseurId, Map<Long, Integer> produitsQuantites);
    boolean deleteOrderSupplier(int id);
    OrderSupplier getOrderSupplier(int id);
    List<OrderSupplier> getAllOrdersSuppliers();
}
