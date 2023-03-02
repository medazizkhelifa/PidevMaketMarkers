package tn.esprit.spring.serviceInterface;

import org.springframework.web.multipart.MultipartFile;

public interface IAddProductByExcel {

    public String saveFromExcel(MultipartFile excelFile);
}
