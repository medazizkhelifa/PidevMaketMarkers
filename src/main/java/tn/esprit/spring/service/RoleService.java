package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.serviceInterface.IRoleInterface;

import java.util.List;

@Service
@Slf4j
public class RoleService implements IRoleInterface {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRole() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);

    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
}
