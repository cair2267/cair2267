package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

/*@author: Andrei Chiritoiu*/class TasksOperationsTest {

    ObservableList<Task> tasksList;
    ArrayTaskList tasksArray;
    private Date start, end, start1, end1, start2, end2;
    Task t01,t02,t03,t04,t05;

    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        sdf= Task.getDateFormat();
        tasksArray = new ArrayTaskList();
        try {
            start1=sdf.parse("2021-04-01 8:00");
            start2=sdf.parse("2021-04-03 8:00");
            end1=sdf.parse("2021-04-05 8:00");
            end2=sdf.parse("2021-04-10 00:00");
            t01 = new Task("t01", sdf.parse("2021-04-02 00:00"));
            t02 = new Task("t02", sdf.parse("2021-04-03 00:00"));
            t03 = new Task("t03", sdf.parse("2021-04-04 00:00"));
            t04 = new Task("t04", sdf.parse("2021-04-10 00:00"));
            t05 = new Task("t05", sdf.parse("2021-04-29 00:00"));
            t01.setActive(true);
            t02.setActive(true);
            t03.setActive(true);
            t04.setActive(true);
            t05.setActive(true);
        }
        catch (ParseException e)
        {
            fail(e.getMessage());
        }


    }


    @Test
    @Order(1)
    @DisplayName("TC01-null list")
    void incoming() {
        tasksList = FXCollections.observableArrayList(tasksArray.getAll());
        TasksOperations tOps = new TasksOperations(tasksList);
        List<Task> incomingList = StreamSupport.stream(tOps.incoming(start1,end1).spliterator(), false).collect(Collectors.toList());
        assert incomingList.size() == 0;
    }

    @Test
    @Order(2)
    @DisplayName("TC02-one task before start")
    void incoming2(){
        tasksArray.add(t01);
        tasksList = FXCollections.observableArrayList(tasksArray.getAll());
        TasksOperations tOps = new TasksOperations(tasksList);
        List<Task> incomingList = StreamSupport.stream(tOps.incoming(start2,end1).spliterator(), false).collect(Collectors.toList());
        assert incomingList.size() == 0;
    }

    @Test
    @Order(3)
    @DisplayName("TC03-tasks between start end")
    void incoming3(){
        tasksArray.add(t01);
        tasksArray.add(t02);
        tasksList = FXCollections.observableArrayList(tasksArray.getAll());
        TasksOperations tOps = new TasksOperations(tasksList);
        List<Task> incomingList = StreamSupport.stream(tOps.incoming(start1,end1).spliterator(), false).collect(Collectors.toList());
        assert incomingList.contains(t01);
        assert incomingList.contains(t02);

        //incomingList.forEach(System.out::println);
    }
    @Test
    @DisplayName("TC04-one before and one after end")
    void incoming4(){
        tasksArray.add(t04);
        tasksArray.add(t05);
        tasksList = FXCollections.observableArrayList(tasksArray.getAll());
        TasksOperations tOps = new TasksOperations(tasksList);
        List<Task> incomingList = StreamSupport.stream(tOps.incoming(start1,end2).spliterator(), false).collect(Collectors.toList());
        assert incomingList.contains(t04);
        assertFalse(incomingList.contains(t05));

    }
    @Test
    @DisplayName("TC05- all tasks test; before and equal")
    void incoming5(){
        tasksArray.add(t02);
        tasksArray.add(t01);
        tasksArray.add(t03);
        tasksArray.add(t04);
        tasksArray.add(t05);
        tasksList = FXCollections.observableArrayList(tasksArray.getAll());
        TasksOperations tOps = new TasksOperations(tasksList);
        List<Task> incomingList = StreamSupport.stream(tOps.incoming(start2,end2).spliterator(), false).collect(Collectors.toList());

        //assert incomingList.contains(t02);
        assert incomingList.contains(t03);
        assert incomingList.contains(t04);
        assertFalse(incomingList.contains(t01));
        assertFalse(incomingList.contains(t05));

    }
}