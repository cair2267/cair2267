package tasks.services.mock;/*@author: Andrei Chiritoiu*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

public class TaskServiceTestTaskMock {

    TasksService tasksService;
    private ArrayTaskList tasks;

    @Mock
    Task t01,t02,t03;

    @BeforeEach
    void setUp() {
        tasks = new ArrayTaskList();
        tasksService = new TasksService(tasks);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void size(){
        tasksService.addTask(t01);
        tasksService.addTask(t02);
        assert tasksService.size() == 2;

    }

    @Test
    void add_valid(){
        Mockito.when(t01.getTitle()).thenReturn("t01");
        tasksService.addTask(t01);
        assert tasksService.getAllTasks().get(0).getTitle().equals("t01");
    }

}
