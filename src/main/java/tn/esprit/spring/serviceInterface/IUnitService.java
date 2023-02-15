package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Unit;

import java.util.List;

public interface IUnitService {
    Unit addUnit(Unit unit);

    List<Unit> getUnit();

    void deleteUnit(Long id);

    Unit updateUnit(Unit unit);
}
