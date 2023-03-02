package tn.esprit.spring.service;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;
import tn.esprit.spring.entities.Product;

//Crée un objet ItemProcessor pour convertir les lignes du fichier Excel en objets Product :
public class ProductProcessor implements ItemProcessor<Row, Product> {

    @Override
    public Product process(final Row row) throws Exception {
        final Product entity = new Product();
        entity.setIdProduct((long) row.getCell(0).getNumericCellValue());
        entity.setDescription(row.getCell(1).getStringCellValue());
        entity.setNom(row.getCell(2).getStringCellValue());
        entity.setPrix(row.getCell(3).getNumericCellValue());

        final String imagePath = row.getCell(4).getStringCellValue();
        final String imageName = imagePath.substring(imagePath.lastIndexOf('/') + 1);
        entity.setImage(imageName);

        return entity;
    }
}
