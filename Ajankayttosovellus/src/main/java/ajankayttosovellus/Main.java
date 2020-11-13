
package ajankayttosovellus;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");
        String attGora = "diska";
        Todo todo = new Todo(attGora, 30);
        System.out.println(todo.toString());
    }
    
}
