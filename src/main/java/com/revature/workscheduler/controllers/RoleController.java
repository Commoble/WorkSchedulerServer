package com.revature.workscheduler.controllers;


import com.google.gson.Gson;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private static final Gson GSON = new Gson();

    @Autowired
    RoleService as;

//    @Secured("ROLE_MANAGER")
//    @GetMapping("/roles")
//    public String getAllRoles() {
//        System.out.println("Getting All Roles");
//        List<Role> roles = as.getAll();
//        return GSON.toJson(roles);
//    }
//
//    @Secured("ROLE_MANAGER")
//    @GetMapping("/roles/{id}")
//    public String getRole(@PathVariable("id") String id) {
//        Role role = as.get(Integer.parseInt(id));
//        return GSON.toJson(role);
//    }
////
////
////
//    @Secured("ROLE_MANAGER")
//    @PostMapping(value = "/roles", consumes = "application/json", produces = "application/json")
//    public String addRole(@RequestBody Role a) {
//        Role role = as.add(a);
//        return GSON.toJson(role);
//    }
//
//    @Secured("ROLE_MANAGER")
//    @PutMapping(value = "roles/{id}", consumes = "application/json", produces = "application/json")
//    public String updateRole(@PathVariable("id") String id, @RequestBody Role change) {
//        change.setRoleID(Integer.parseInt(id));
//        Role role = as.update(change);
//        return GSON.toJson(role);
//    }
//
//
//    @Secured("ROLE_MANAGER")
//    @DeleteMapping("roles/{id}")
//    public boolean deleteRole(@PathVariable("id") int id) {
//
//        System.out.println("Deleting Role");
//        return as.delete(id);
//    }
}
