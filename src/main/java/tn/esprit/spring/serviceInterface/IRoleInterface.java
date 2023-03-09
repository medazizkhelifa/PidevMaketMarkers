package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Role;

import java.util.List;

public interface IRoleInterface {
    Role addRole(Role role);


    List<Role> getRole();

    void deleteRole(Long id);

    Role updateRole(Role role);
}
