package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Unit;
import tn.esprit.spring.repository.UnitRepository;
import tn.esprit.spring.serviceInterface.IUnitService;

import java.util.List;

@Service
@Slf4j
public class UnitService implements IUnitService {

    @Autowired
    UnitRepository unitRepository;
    @Override
    public Unit addUnit(Unit unit) {

        return unitRepository.save(unit);
    }
    @Override
    public List<Unit> getUnit(){
        return unitRepository.findAll();
    }

    @Override
    public void deleteUnit(Long id){
        unitRepository.deleteById(id);
    }
    @Override
    public Unit updateUnit(Unit unit){
        return unitRepository.save(unit);
    }
}
