
package ajankayttosovellus;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");
        String attGora = "diska";
        Todo todo = new Todo(attGora, 14);
        System.out.println(todo.toString());
        Calender calender = new Calender(1);
        System.out.println(calender.toString());
        calender.getSunday().putTodo(todo);
    }
    
}
