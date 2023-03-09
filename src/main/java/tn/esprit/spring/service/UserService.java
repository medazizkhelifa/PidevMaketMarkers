package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IUserInterface;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
}

