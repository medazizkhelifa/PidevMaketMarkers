package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Supplier;
import tn.esprit.spring.entities.Tender;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.SupplierRepository;
import tn.esprit.spring.repository.TenderRepository;
import tn.esprit.spring.serviceInterface.ITender;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class TenderService implements ITender {
    private List<Supplier> suppliers;
    private Product product;
    private EmailService emailService;

    @Autowired
    public TenderService(EmailService emailService) {
        this.emailService = emailService;
    }
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TenderRepository tenderRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public void lancerAppelOffre(Long produitId, int quantiteRequise) {
        Product product = productRepository.findById(produitId)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé"));
        System.out.println(product.getNom());
        //System.out.println(product.toString());
        Tender tender = new Tender();
        tender.setProduct(product);
        tender.setQuantiteRequise(quantiteRequise);
        tender.setDateLimite(LocalDate.now().plusDays(7));
        System.out.println("11111111111111111111111111111111111111111");
        System.out.println(tender.getProduct().getNom());
        tenderRepository.save(tender);
        List<Product> products = productRepository.findAll();
        //List<Supplier> suppliers = fournisseurRepository.findByProduitDisponible(produitId);
        System.out.println("avant le boucle for");
        List<Supplier> suppliers = supplierRepository.findAll();
        System.out.println("222222222222222222222222222222");

        for (Supplier supplier : suppliers) {
            System.out.println("222222222222222222222222222222");
            String email = supplier.getEmail();
            System.out.println(email);
            String subject = "Appel d'offre pour le produit " + product.getNom();
            System.out.println(subject);
            String message = "Bonjour " + supplier.getFirstName() + ",\n\n" +
                    "Nous lançons un appel d'offre pour le produit " + product.getNom() +
                    " pour une quantité de " + quantiteRequise + " unités.\n\n" + ". Merci de nous envoyer votre meilleure offre.";

            System.out.println(message);
            emailService.sendEmail(email, subject, message);
            System.out.println("les appels d'offres envoyer avec succees");

        }

    }

}