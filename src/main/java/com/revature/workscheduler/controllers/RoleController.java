package com.revature.workscheduler.controllers;


import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService as;

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        System.out.println("Getting All Roles");
        return as.getAll();
    }

    @GetMapping("/roles/{id}")
    public Role getRole(@PathVariable("id") String id) {
        return as.get(Integer.parseInt(id));
    }
//
//
//
    @PostMapping(value = "/roles", consumes = "application/json", produces = "application/json")
    public Role addRole(@RequestBody Role a) {
        return as.add(a);
    }

    @PutMapping(value = "roles/{id}", consumes = "application/json", produces = "application/json")
    public Role updateRole(@PathVariable("id") String id, @RequestBody Role change) {
        change.setRoleID(Integer.parseInt(id));
        return as.update(change);
    }


    @DeleteMapping("roles/{id}")
    public boolean deleteRole(@PathVariable("id") int id) {

        System.out.println("Deleting Role");
        return as.delete(id);
    }
}
