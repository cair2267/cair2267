package tasks.services.mock;/*@author: Andrei Chiritoiu*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskServiceTestMock {

    @InjectMocks
    private TasksService service;

    @Mock
    private ArrayTaskList tasks;

    @Mock
    private Task t01,t02,t03;

    private SimpleDateFormat sdf = Task.getDateFormat();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_size(){
        Mockito.when(tasks.size()).thenReturn(2);
        assert 2 == service.size();
    }

    @Test
    void test_add_valid() throws ParseException {
       // Task t01 = new Task("t01", sdf.parse("2021-04-02 00:00"));
        //Task t02 = new Task("t02", sdf.parse("2021-04-03 00:00"));
        //Task t03 = new Task("t03", sdf.parse("2021-04-04 00:00"));

        Mockito.doNothing().when(tasks).add(t01);
        Mockito.doNothing().when(tasks).add(t02);
        service.addTask(t01);
        service.addTask(t02);
        Mockito.verify(tasks, Mockito.times(1)).add(t01);
        Mockito.verify(tasks, Mockito.times(1)).add(t02);
        Mockito.verify(tasks, Mockito.never()).add(t03);
       // assert service.size() == 2;
    }
}
