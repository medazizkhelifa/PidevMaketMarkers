package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import tn.esprit.spring.serviceInterface.ICalculdistanceService;
@Service
public class CalculdistanceService implements ICalculdistanceService {
    @Override
    public Double calcDistance(Double x1, Double y1, Double x2, Double y2) {
        double R = 6371.0;
        double dLat = Math.toRadians(x2 - x1);
        double dLon = Math.toRadians(y2 - y1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(x1))
                * Math.cos(Math.toRadians(x2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }
}
