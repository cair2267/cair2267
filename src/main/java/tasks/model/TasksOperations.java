package tasks.model;

import javafx.collections.ObservableList;

import java.util.*;

public class TasksOperations {

    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList){
        tasks=new ArrayList<>();
        tasks.addAll(tasksList);
    }

    public Iterable<Task> incoming(Date start, Date end){
        System.out.println(start);
        System.out.println(end);
        ArrayList<Task> incomingTasks = new ArrayList<>(); //1
        for (Task t : tasks) { //2
            Date nextTime = t.nextTimeAfter(start); //3
                if(nextTime!=null){//4
                    if (nextTime.before(end)){//5
                        incomingTasks.add(t);//6
                    }

                    if(nextTime.equals(end)){//7
                        incomingTasks.add(t);//8
                    }
                    System.out.println(t.getTitle());
                }
            }
            /* if (nextTime != null && (nextTime.before(end) || nextTime.equals(end))) { //4
                incomingTasks.add(t);} //5 */
        return incomingTasks; //9
    } //10
    public SortedMap<Date, Set<Task>> calendar( Date start, Date end){
        Iterable<Task> incomingTasks = incoming(start, end);
        TreeMap<Date, Set<Task>> calendar = new TreeMap<>();

        for (Task t : incomingTasks){
            Date nextTimeAfter = t.nextTimeAfter(start);
            while (nextTimeAfter!= null && (nextTimeAfter.before(end) || nextTimeAfter.equals(end))){
                if (calendar.containsKey(nextTimeAfter)){
                    calendar.get(nextTimeAfter).add(t);
                }
                else {
                    HashSet<Task> oneDateTasks = new HashSet<>();
                    oneDateTasks.add(t);
                    calendar.put(nextTimeAfter,oneDateTasks);
                }
                nextTimeAfter = t.nextTimeAfter(nextTimeAfter);
            }
        }
        return calendar;
    }
}
