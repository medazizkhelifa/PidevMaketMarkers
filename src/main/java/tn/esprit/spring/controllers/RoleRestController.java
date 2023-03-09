package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.service.RoleService;


import java.util.List;

@RestController
@RequestMapping("/Role")
public class RoleRestController {
    @Autowired
    RoleService roleService;



    @PostMapping("/add-Role")
    @ResponseBody
    public Role addRole(@RequestBody Role r)
    {
        return roleService.addRole(r);

    }
    @GetMapping
    public List<Role> getRole() {
        return roleService.getRole();
    }
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
    @PutMapping
    public Role updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }
}
