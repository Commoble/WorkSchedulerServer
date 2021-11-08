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
        Role role = new Role(false, false, false);
        int oldId = role.getRoleID();
        Mockito.when(repo.save(role)).thenReturn(new Role(1,role.isCanCreateShifts(), role.isCanAssignShifts(), role.isCanApproveTimeOff()));
        Role addedRole = service.add(role);
        int newId= addedRole.getRoleID();
        Assertions.assertNotEquals(oldId, newId);
        Assertions.assertNotEquals(role.isCanCreateShifts(), addedRole.isCanCreateShifts());
        Assertions.assertNotEquals(role.isCanAssignShifts(), addedRole.isCanAssignShifts());
        Assertions.assertNotEquals(role.isCanApproveTimeOff(), addedRole.isCanApproveTimeOff());
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
        Role role = new Role(1,false, false, false);
        int oldId = role.getRoleID();
        Optional<Role> optionalRole = Optional.of(role);
        Mockito.when(repo.findById(oldId)).thenReturn(optionalRole);
        Role actRole = this.service.get(oldId);
        int actId= actRole.getRoleID();
        Assertions.assertNotEquals(oldId, actId);
        Assertions.assertNotEquals(role.isCanCreateShifts(), actRole.isCanCreateShifts());
        Assertions.assertNotEquals(role.isCanAssignShifts(), actRole.isCanAssignShifts());
        Assertions.assertNotEquals(role.isCanApproveTimeOff(), actRole.isCanApproveTimeOff());
    }
    @Test
    void updateRole()
    {
        Role role = new Role(1,false, false, false);
        int id = role.getRoleID();
        role.setCanApproveTimeOff(true);
        Mockito.when(repo.save(role)).thenReturn(role);
        Mockito.when(repo.findById(id)).thenReturn(Optional.of(role));
        Role updatedRole = this.service.update(role);
        Assertions.assertEquals(role.isCanApproveTimeOff(), updatedRole.isCanApproveTimeOff());
    }

    @Test
    void deleteEmployee()
    {
        int id = 1;
        Role role = new Role(false, false, false);
        Role mockEmployee = new Role(id, role.isCanCreateShifts(), role.isCanAssignShifts(), role.isCanApproveTimeOff());
        Optional<Role> optionalRole = Optional.of(role);
        Mockito.when(repo.findById(id)).thenReturn(optionalRole);
        Mockito.doNothing().when(repo).deleteById(id);

        boolean deleted = this.service.delete(id);
        Assertions.assertTrue(deleted);
    }
}
