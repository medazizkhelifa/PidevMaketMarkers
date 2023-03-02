package tn.esprit.spring.service;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tn.esprit.spring.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setIdProduct(rs.getLong("idProduct"));
        product.setDescription(rs.getString("description"));
        product.setNom(rs.getString("nom"));
        product.setImage(rs.getString("image"));
        product.setPrix(rs.getDouble("prix"));
        return product;
    }
}
