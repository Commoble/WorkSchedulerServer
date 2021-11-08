package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Role;

import java.util.List;

public interface RoleService {
    public Role addRole(Role a);
    public Role getRole(int id);
    public List<Role> getAllRoles();
    public Role updateRole(Role change);
    public boolean deleteRole(int id);


}
