package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.User;

import java.util.List;

public interface IUserInterface {

    User addUser(User user);


    List<User> getUser();

    void deleteUser(Long id);

    User updateUser(User user);

    User getUserByEmail(String email);
}
