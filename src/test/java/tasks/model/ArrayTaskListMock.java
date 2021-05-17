package tasks.model;/*@author: Andrei Chiritoiu*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ArrayTaskListMock {

    @Mock
    ArrayTaskList tasks;

    @Mock
    Task t01,t02;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void get() {
        Mockito.when(tasks.getTask(1)).thenReturn(t01);
        Task t = tasks.getTask(1);
        assert t == t01;
    }

    @Test
    void add() {
        Mockito.doNothing().when(tasks).add(t01);
        tasks.add(t01);
        Mockito.verify(tasks,Mockito.times(1)).add(t01);
    }

}
