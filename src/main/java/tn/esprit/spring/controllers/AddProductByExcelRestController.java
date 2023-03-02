package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.service.AddProductByExcel;

@RestController
@RequestMapping("/excel")
public class AddProductByExcelRestController {

    @Autowired
    private AddProductByExcel addProductByExcel;

    @PostMapping("/add")
    public String AddProductByExcel(@RequestParam("excelFile") MultipartFile multipartFile){
        return addProductByExcel.saveFromExcel(multipartFile);
    }
}
