package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo ar;


    @Override
    public RoleRepo getRepo() {
        return ar;
    }

    @Override
    public Integer getIDFor(Role value) {
        return value.getRoleID();
    }
}
