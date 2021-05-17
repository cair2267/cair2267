package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/*@author: Andrei Chiritoiu*/
class ArrayTaskListTest {

    ArrayTaskList tasks;
    Task t01;
    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        sdf= Task.getDateFormat();
        tasks = new ArrayTaskList();
        try {
            t01 = new Task("t01", sdf.parse("2021-04-02 00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tasks.add(t01);

    }


    @Test
    void size() {
        assert tasks.size() == 1;
    }

    @Test
    void getAll() {
        assert tasks.getAll().get(0).getTitle().equals("t01");
    }
}