package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Tax;
import tn.esprit.spring.entities.Unit;
import tn.esprit.spring.service.TaxService;
import tn.esprit.spring.service.UnitService;

import java.util.List;

@RestController
@RequestMapping("/Unit")
public class UnitRestController {

    @Autowired
    UnitService unitService;


    @PostMapping("/add-Unit")
    @ResponseBody
    public Unit addUnit(@RequestBody Unit u) {
        return unitService.addUnit(u);

    }

    @GetMapping
    public List<Unit> getUnit() {
        return unitService.getUnit();
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
    }

    @PutMapping
    public Unit updateUnit(@RequestBody Unit unit) {
        return unitService.updateUnit(unit);
    }


}