package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.service.CalculdistanceService;

@RestController
@RequestMapping("/calculdistance")
public class CalculdistanceRestController {

 @Autowired
  private CalculdistanceService calculdistanceService;

 @GetMapping("/apiCalc/{x1}/{y1}/{x2}/{y2}")
 public Double getCLalcDistance(@PathVariable Double x1,@PathVariable Double y1,@PathVariable Double x2,@PathVariable Double y2){
  return calculdistanceService.calcDistance(x1,y1,x2,y2);

 }



 }
