package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/*@author: Andrei Chiritoiu*/class TaskTest {

    private Task task;
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
        task = new Task("seminar", start, end, 1);
        task.setActive(true);
    }

    @Test
    @Order(1)
    void getTitle() {
        assertEquals("seminar", task.getTitle(), "Task title name should be \'seminar\'");
    }

    @Test
    void isActive(){
        assertTrue(task.isActive());
    }
}