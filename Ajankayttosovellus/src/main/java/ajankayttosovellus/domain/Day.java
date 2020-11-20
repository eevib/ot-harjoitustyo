package ajankayttosovellus.domain;

public class Day {

    TimeSlot[] day;
    String name;
    int timeSLotsPerDay;

    public Day(String name) {
        this.timeSLotsPerDay = 24;
        this.day = new TimeSlot[this.timeSLotsPerDay];
        this.name = name;
        for (int i = 0; i < this.timeSLotsPerDay; i++) {
            TimeSlot timeslot = new TimeSlot(i);
            this.day[i] = timeslot;
        }
    }

    public void putTodo(Todo todo) {
        int time = todo.getTime();
        if (this.day[time] == null) {
            this.day[time].setTodo(todo);
        }
    }

    public TimeSlot getTimeslot(int time) {
        return this.day[time];
    }

    public Integer freeSlots() {
        int freeSlots = 0;
        for (int i = 0; i < this.timeSLotsPerDay; i++) {
            TimeSlot timeslot = this.day[i];
            if (timeslot.isFree) {
                freeSlots++;
            }
        }
        return freeSlots;
    }

    @Override
    public String toString() {
        return "Day{" + "day=" + day + ", name=" + name + '}';
    }

}
