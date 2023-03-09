package tn.esprit.testReclamation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.testReclamation.Entities.Avis;
import tn.esprit.testReclamation.Repository.IAvis;

import java.util.List;

@Service
public class AvisService {
    @Autowired
    private IAvis avisRepository;

    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    public Avis getAvisById(int id) {
        return avisRepository.findById(id)
                .orElseThrow(null);
    }

    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    public Avis updateAvis(int id, Avis avisDetails) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(null);

        avis.setType(avisDetails.getType());
        avis.setDescription(avisDetails.getDescription());
        avis.setRating(avisDetails.getRating());

        return avisRepository.save(avis);
    }

    public void deleteAvis(int id) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(null);

        avisRepository.delete(avis);
    }
}

