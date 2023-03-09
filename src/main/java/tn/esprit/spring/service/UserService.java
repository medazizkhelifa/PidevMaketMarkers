package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IUserInterface;

import javax.persistence.*;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserInterface {

    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }
    @Override
    public List<User> getUser(){
        return userRepository.findAll();
    }
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    @Override
    public User updateUser(User user){return userRepository.save(user);
    }
    //@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }

//    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user != null) {
//            user.setResetPasswordToken(token);
//            userRepository.save(user);
//        } else {
//            throw new UsernameNotFoundException("Could not find any user with the email " + email);
//        }
//    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByEmail(String email) {
        String hql = "FROM User u WHERE u.email = :email";
        Query<User> query = (Query<User>) entityManager.createQuery(hql, User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        } else if (users.size() == 1) {
            return users.get(0);
        } else {
            throw new RuntimeException("Multiple users found with the same email address");
        }
    }






}

