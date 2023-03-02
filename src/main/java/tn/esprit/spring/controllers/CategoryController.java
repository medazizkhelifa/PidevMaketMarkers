package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Category;
import tn.esprit.spring.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add-catogery")
    @ResponseBody
    public Category addCategory(@RequestBody Category c)
    {
        return categoryService.addCategory(c);

    }

    @GetMapping
    public List<Category> getCategory() {
        return categoryService.getCategory();
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
    @PutMapping
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

}
