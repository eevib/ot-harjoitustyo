
package ajankayttosovellus.domain;

import java.util.List;

public class CalenderService {
    private Calender calender;
    
    public CalenderService() {
        this.calender = new Calender("Calender");
    }
    public void createTodo(String name) {
        calender.addTodoToList(new Todo(name, calender.todoIdCalc));
    }
    public List getUnScheduledTodos() {
        return this.calender.getUnScheduledTodos();
    }
    public boolean scheduleTodo(String day, String time, Todo todo) {
        return true;
    }
}
