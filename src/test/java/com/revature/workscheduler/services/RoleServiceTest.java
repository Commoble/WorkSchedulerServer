package com.revature.workscheduler.services;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.models.Role;
import com.revature.workscheduler.repositories.RoleRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes= WorkschedulerApplication.class)
public class RoleServiceTest {
    @Autowired
    private RoleService service;

    @MockBean
    private RoleRepo repo;




    @Test
    void addRole()
    {
        Role role = new Role("Test Role", false);
        int oldId = role.getRoleID();
        Mockito.when(repo.save(role)).thenReturn(new Role(1,role.getName(), role.isManager()));
        Role addedRole = service.add(role);
        int newId= addedRole.getRoleID();
        Assertions.assertNotEquals(oldId, newId);
        Assertions.assertEquals(role.getName(), addedRole.getName());
        Assertions.assertEquals(role.isManager(), addedRole.isManager());
    }



    @Test
    void getAllRoles()
    {
      List<Role> Rlist = new ArrayList<>();
      Rlist = service.getAll();
      Assertions.assertNotNull(Rlist);
    }


    @Test
    void getRole()
    {
        Role role = new Role(1,"Test Role", false);
        int oldId = role.getRoleID();
        Optional<Role> optionalRole = Optional.of(role);
        Mockito.when(repo.findById(oldId)).thenReturn(optionalRole);
        Role actRole = this.service.get(oldId);
        int actId= actRole.getRoleID();
        Assertions.assertEquals(oldId, actId);
        Assertions.assertEquals(role.getName(), actRole.getName());
        Assertions.assertEquals(role.isManager(), actRole.isManager());
    }
    @Test
    void updateRole()
    {
        Role role = new Role(1,"Test Role", false);
        int id = role.getRoleID();
        role.setIsManager(true);
        Mockito.when(repo.save(role)).thenReturn(role);
        Mockito.when(repo.findById(id)).thenReturn(Optional.of(role));
        Role updatedRole = this.service.update(role);
        Assertions.assertEquals(role.isManager(), updatedRole.isManager());
    }

    @Test
    void deleteRole()
    {
        int id = 1;
        Role role = new Role(id, "Test Role", false);
        Optional<Role> optionalRole = Optional.of(role);
        Mockito.when(repo.findById(id)).thenReturn(optionalRole);
        Mockito.doNothing().when(repo).deleteById(id);

        boolean deleted = this.service.delete(id);
        Assertions.assertTrue(deleted);
    }
}
