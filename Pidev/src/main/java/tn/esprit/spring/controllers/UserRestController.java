package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.UserService;

import java.util.List;
@RestController
@RequestMapping("/User")
public class UserRestController {

    @Autowired
    UserService userService;



    @PostMapping("/add-User")
    @ResponseBody
    public User addUser(@RequestBody User u)
    {
        return userService.addUser(u);

    }
    @GetMapping
    public List<User> getUser() {
        return userService.getUser();
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
