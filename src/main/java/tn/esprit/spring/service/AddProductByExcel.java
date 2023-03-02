package tn.esprit.spring.service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.serviceInterface.IAddProductByExcel;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddProductByExcel  implements IAddProductByExcel {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public String saveFromExcel(MultipartFile excelFile) {//pour recuperer les files
        try {
            Product product = new Product();
            List<Product> products = new ArrayList<>();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelFile.getInputStream());
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            for(int i=1; i<xssfSheet.getPhysicalNumberOfRows();i++){
                XSSFRow row = xssfSheet.getRow(i);
                product= new Product();
               for(int j=0; j<row.getPhysicalNumberOfCells();j++){


                   switch (j) {
                       case 1:
                           product.setDescription( row.getCell(j).getStringCellValue() );
                            break;
                       case 2:
                           product.setNom(row.getCell(j).getStringCellValue());
                           break;
                       case 3:
                           product.setImage(row.getCell(j).getStringCellValue());
                           break;
                       case 4:
                           product.setPrix(Double.valueOf(row.getCell(j).getStringCellValue()));
                           break;
                   }


                }
                products.add(product);
                System.out.println("add++++++++++++++++++++++++ ");

            }
            productRepository.saveAll(products);
            return "List Product added";
        }catch (Exception e){
            System.out.println("error "+e.getMessage());
            return null;
        }

    }
}
