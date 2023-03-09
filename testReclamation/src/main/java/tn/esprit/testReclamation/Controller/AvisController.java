package tn.esprit.testReclamation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.testReclamation.Entities.Avis;
import tn.esprit.testReclamation.Service.AvisService;

import java.util.List;


@RestController
@RequestMapping("/api/avis")
public class AvisController {
    @Autowired
    private AvisService avisService;

    @GetMapping("")
    public List<Avis> getAllAvis() {
        return avisService.getAllAvis();
    }

    @GetMapping("/{id}")
    public Avis getAvisById(@PathVariable int id) {
        return avisService.getAvisById(id);
    }

    @PostMapping("")
    public Avis createAvis( @RequestBody Avis avis) {
        return avisService.createAvis(avis);
    }

    @PutMapping("/{id}")
    public Avis updateAvis(@PathVariable int id,  @RequestBody Avis avisDetails) {
        return avisService.updateAvis(id, avisDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAvis(@PathVariable int id) {
        avisService.deleteAvis(id);
        return ResponseEntity.ok().build();
    }
}

