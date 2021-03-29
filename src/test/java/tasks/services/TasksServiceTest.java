package tasks.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    void exceptionTriggered(){
        assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask("3",start,end,0);
    });
        assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(null,start,end,2);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, Integer.MAX_VALUE })
    void intervalTest(int interval){
        tasksService.addTask("a",start,end,interval);
    }

    @ParameterizedTest
    @ValueSource(strings = { "a","aaa" })
    void titleTest(String title){
        tasksService.addTask(title,start,end,1);
    }
}