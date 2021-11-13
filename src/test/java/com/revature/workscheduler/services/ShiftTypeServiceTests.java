package com.revature.workscheduler.services;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.ShiftType;
import com.revature.workscheduler.repositories.ShiftTypeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = WorkschedulerApplication.class)
public class ShiftTypeServiceTests
{
    @Autowired
    private ShiftTypeService service;

    @MockBean
    private ShiftTypeRepo repo;

    @Test
    void addShiftType()
    {
        ShiftType st = new ShiftType("name", 0, 0);
        int oldId = st.getShiftTypeID();
        Mockito.when(repo.save(st))
                .thenReturn(new ShiftType(1, "name", 0, 0));
        ShiftType newSt = service.add(st);
        Assertions.assertNotEquals(oldId, newSt.getShiftTypeID());
    }

    @Test
    void getShiftType()
    {
        ShiftType st = new ShiftType(0, "name", 0, 0);
        int oldID = service.getIDFor(st);
        Mockito.when(repo.findById(oldID))
                .thenReturn(Optional.of(st));
        ShiftType newSt = service.get(oldID);
        Assertions.assertEquals(oldID, newSt.getShiftTypeID());
    }

    @Test
    void getAllShiftTypes()
    {
        List<ShiftType> stList = new ArrayList<>();
        List<ShiftType> stList2 = service.getAll();
        Assertions.assertEquals(stList.getClass(), stList2.getClass());
    }

    @Test
    void updateShiftType()
    {
        ShiftType st = new ShiftType(0, "name", 0, 0);
        Mockito.when(repo.save(st))
                .thenReturn(st);
        Mockito.when(repo.findById(st.getShiftTypeID()))
                .thenReturn(Optional.of(st));
        ShiftType newSt = service.update(st);
        Assertions.assertEquals(newSt, st);
    }

    @Test
    void deleteShiftTypeById()
    {
        ShiftType st = new ShiftType(0,"name", 0,0);
        Mockito.when(repo.findById(st.getShiftTypeID()))
                .thenReturn(Optional.of(st));
        boolean deletedTrue = service.delete(st.getShiftTypeID());
        Mockito.doThrow(IllegalArgumentException.class)
            .when(repo).deleteById(null);
        boolean deletedFalse = service.delete(null);
        Assertions.assertTrue(deletedTrue);
        Assertions.assertFalse(deletedFalse);
    }
}