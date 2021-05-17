package tasks.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/*@author: Andrei Chiritoiu*/class TasksServiceTest {


    TasksService tasksService;
    private ArrayTaskList tasks;
    private Date start, end;
    private SimpleDateFormat sdf;

    @Mock
    Task t;

    @BeforeEach
    void setUp() {
        sdf= Task.getDateFormat();
        try {
            start=sdf.parse("2020-02-30 12:00");
            end=sdf.parse("2020-02-03 10:00"); }
        catch (ParseException e)
        {
            fail(e.getMessage());
        }
        tasks = new ArrayTaskList();
        tasksService = new TasksService(tasks);
    }

    @Test
    @Order(1)
    @Tag("Valid")
    @Timeout(100)
    @DisplayName("ECP-Valid-Tests")
    void addTask() {
        tasksService.addTask("seminar",start,end,1);
        tasksService.addTask("laborator",start,end,15);
        assert tasks.size() == 2;

    }

    @Test
    @Order(2)
    @Disabled
    void disabledAddTask(){
        tasksService.addTask("1",start,end,1);
        tasksService.addTask("seminar",start,end,-1);
    }

    @Test
    @Order(3)
    @DisplayName("ECP-Non-Valid-Tests")
    void addTaskInavalid(){
        assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask("3",start,end,0);
    });
        assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(null,start,end,2);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, Integer.MAX_VALUE })
    @DisplayName("BVA-Valid-Tests-interval")
    @Tag("Valid")
    void intervalTest_valid(int interval){
        tasksService.addTask("a",start,end,interval);
    }

    @ParameterizedTest
    @ValueSource(strings = { "a","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" })
    @DisplayName("BVA-Valid-Tests-title")
    @Tag("Valid")
    void titleTest_valid(String title){
        tasksService.addTask(title,start,end,1);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, Integer.MAX_VALUE+1 })
    @DisplayName("BVA-NonValid-Tests-interval")
    void intervalTest_nonValid(int interval){
        assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask("3",start,end,interval);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "" })
    @DisplayName("BVA-NonValid-Tests-title")
    void titleTest_nonValid(String title){
        assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(title,start,end,2);
        });
    }

    @Test
    void add_valid(){
        Task t = new Task("laborator",start,end,15);
        tasksService.addTask(t);
        assert tasksService.getAllTasks().get(0).getTitle().equals("laborator");

    }

    @Test
    void size(){
        Task t = new Task("laborator",start,end,15);
        tasksService.addTask(t);
        assert tasksService.size() == 1;

    }
}