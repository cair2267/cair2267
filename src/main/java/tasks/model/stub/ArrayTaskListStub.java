package tasks.model.stub;/*@author: Andrei Chiritoiu*/

import tasks.model.Task;
import tasks.model.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayTaskListStub extends TaskList {

    private Task[] tasks = new Task[5];
    private SimpleDateFormat sdf;

    public ArrayTaskListStub() throws ParseException {
        sdf= Task.getDateFormat();
        Task t01 = new Task("t01", sdf.parse("2021-04-02 00:00"));
        Task t02 = new Task("t02", sdf.parse("2021-04-03 00:00"));
        Task t03 = new Task("t03", sdf.parse("2021-04-04 00:00"));
        tasks[0] = t01;
        tasks[1] = t02;
        tasks[2] = t03;
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public boolean remove(Task task) {
        return false;
    }

    @Override
    public int size() {
        return tasks.length;
    }

    @Override
    public Task getTask(int index) {
        return tasks[index];
    }

    @Override
    public List<Task> getAll() {
        return Arrays.asList(tasks);
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }
}
