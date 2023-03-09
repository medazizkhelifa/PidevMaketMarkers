package tn.esprit.testReclamation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.testReclamation.Entities.Reclamation;
import tn.esprit.testReclamation.Repository.IReclamation;

import java.util.List;

@Service
public class ReclamationService {
    @Autowired
    private IReclamation reclamationRepository;

    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    public Reclamation getReclamationById(int id) {
        return reclamationRepository.findById(id)
                .orElseThrow(null);
    }

    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public Reclamation updateReclamation(int id, Reclamation reclamationDetails) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(null);

        reclamation.setId_produit(reclamationDetails.getId_produit());
        reclamation.setDate_rec(reclamationDetails.getDate_rec());
        reclamation.setGarantie(reclamationDetails.getGarantie());
        reclamation.setReference(reclamationDetails.getReference());
        reclamation.setIncomplete_liv(reclamationDetails.getIncomplete_liv());
        reclamation.setPanne(reclamationDetails.getPanne());

        return reclamationRepository.save(reclamation);
    }

    public void deleteReclamation(int id) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(null);

        reclamationRepository.delete(reclamation);
    }
}
