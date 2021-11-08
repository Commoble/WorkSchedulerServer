package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo ar;


    @Override
    public Role addRole(Role a) {
        return ar.save(a);
    }

    @Override
    public Role getRole(int id) {
        return ar.findById(id).get();
    }

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) ar.findAll();
    }

    @Override
    public Role updateRole(Role change) {
        return ar.save(change);
    }

    @Override
    public boolean deleteRole(int id) {
        try{
            ar.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
