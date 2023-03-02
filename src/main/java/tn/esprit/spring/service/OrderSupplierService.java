package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;
import tn.esprit.spring.serviceInterface.IOrderSupplierService;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderSupplierService implements IOrderSupplierService {
    @Autowired
    OrderSupplierRepository orderSupplierRepository;

    @Autowired
    OrderLineSupplierRepository orderLineSupplierRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private EmailService emailService;


    @Override
    public void passerCommande(int supplierId, Map<Long, Integer> produitsQuantites) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new IllegalArgumentException("Fournisseur introuvable"));
        OrderSupplier order = new OrderSupplier();
        order.setSupplier(supplier);
        order = orderSupplierRepository.save(order);
        for (Map.Entry<Long, Integer> entry : produitsQuantites.entrySet()) {
            Product product = productRepository.findById(entry.getKey()).orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));
            OrderLineSupplier ligne = new OrderLineSupplier();
            ligne.setOrderSupplier(order);
            ligne.setProduct(product);
            ligne.setQuantite(entry.getValue());
            orderLineSupplierRepository.save(ligne);

        String email = supplier.getEmail();
        String subject = "Commande Fournisseur  pour le produit " + product.getNom();
        String message = "Bonjour " + supplier.getFirstName() + ",\n\n" +
                "Nous lançons un commande pour le produit " + product.getNom() +
                " pour une quantité de " + entry.getValue() + " unités.\n\n" + ". Merci de nous envoyer votre meilleure de confirmation.";

        emailService.sendEmail(email, subject, message);
        }

    }

    @Override
    public boolean deleteOrderSupplier(int id) {
        if(orderSupplierRepository.existsById(id)){
            orderSupplierRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public OrderSupplier getOrderSupplier(int id) {
        if(orderSupplierRepository.existsById(id)){
            return orderSupplierRepository.findById(id).get();
        }
        return null;
    }
    @Override
    public List<OrderSupplier> getAllOrdersSuppliers(){
        return orderSupplierRepository.findAll();
    }
}
