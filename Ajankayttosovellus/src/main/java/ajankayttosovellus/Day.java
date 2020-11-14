package ajankayttosovellus;

public class Day {

    TimeSlot[] day;
    String name;
    
    public Day(String whatDay) {
        this.day = new TimeSlot[24];
        this.name = whatDay;
        for(int i=0; i<24; i++) {
            this.day[i] = new TimeSlot();
        }
    }    

    public void putTodo(Todo todo) {
        if (this.day[todo.getTime()] == null) {
            this.day[todo.getTime()].setTodo(todo);
        }
    }
}
