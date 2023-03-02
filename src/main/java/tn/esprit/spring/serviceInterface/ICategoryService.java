package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);

    List<Category> getCategory();

    void deleteCategory(Long id);

    public Category updateCategory(Category category);


}
