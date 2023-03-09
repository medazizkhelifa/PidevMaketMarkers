package tn.esprit.testReclamation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.testReclamation.Entities.Reclamation;
import tn.esprit.testReclamation.Service.ReclamationService;

import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @GetMapping("")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/{id}")
    public Reclamation getReclamationById(@PathVariable int id) {
        return reclamationService.getReclamationById(id);
    }

    @PostMapping("")
    public Reclamation createReclamation( @RequestBody Reclamation reclamation) {
        return reclamationService.createReclamation(reclamation);
    }

    @PutMapping("/{id}")
    public Reclamation updateReclamation(@PathVariable int id,  @RequestBody Reclamation reclamationDetails) {
        return reclamationService.updateReclamation(id, reclamationDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReclamation(@PathVariable int id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.ok().build();
    }
}

