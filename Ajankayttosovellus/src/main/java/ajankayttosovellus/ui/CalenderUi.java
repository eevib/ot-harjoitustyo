package ajankayttosovellus.ui;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.Todo;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CalenderUi {

    private Scanner reader;
    private Map<String, String> whatToDo;
    private Calender calender;

    public CalenderUi(Scanner reader) {
        this.calender = new Calender("Kalenteri"); // Luodaan kalenteri.
        this.reader = new Scanner(System.in);
        whatToDo = new TreeMap<>();
        this.whatToDo.put("1", "1 lopeta");
        this.whatToDo.put("2", "2 lisää todo");
        this.whatToDo.put("3", "3 tulosta todoot");
        this.whatToDo.put("4", "4 tulosta ajastetut todoot");

    }

    public void start() {
        while (true) {
            printCommands();
            String command = reader.next();
            if (!this.whatToDo.keySet().contains(command)) {
                System.out.println("En ymmärrä, kirjoita komento uudestaan.");
            }
            if (command.equals("1")) {
                break;
            } else if (command.equals("2")) {
                addTodo();
            } else if (command.equals("3")) {
                printTodos();
            } else if (command.equals("4")) {
                printScheduledTodos();
            }
        }
    }

    public void printCommands() {
        System.out.println("Mitä haluat tehdä?");
        this.whatToDo.entrySet().stream().forEach(e -> {
            System.out.println(e.getValue() + " ");
        });
        System.out.println("Komento: ");
    }

    public void addTodo() {
        System.out.println("Kirjoita todon nimi: ");
        String todoName = reader.next();
        Todo todo = new Todo(todoName);
        this.calender.addTodoToList(todo);
        System.out.println("Haluatko ajastaa todoon? k = kyllä, e = ei");
        String scheduleTodo = reader.next();
        if (scheduleTodo.equals("k")) {
            scheduleTodo(todo);
        }
    }

    private void scheduleTodo(Todo todo) {
        System.out.println("Mihin aikaan todo ajastetaan? ");
        System.out.println("Anna päivä numerona (ma = 0, ti = 1, ke = 2, to = 3, pe = 4, la = 5 tai su = 6): ");
        String day = reader.next();
        System.out.println("Anna aika (kokonaisluku 0-23): ");
        String time = reader.next();
        while (calender.scheduleTodo(day, time, todo) == false) {
            System.out.println("Valitse toinen aika, tämä ei ole vapaa, tai annoit ajan väärässä muodossa.");
            System.out.println("Anna päivä numerona (ma=0, ti=1, ke=2, to=3, pe=4, la=5 tai su=6): ");
            day = reader.next();
            System.out.println("Anna aika (kokonaisluku 0-23): ");
            time = reader.next();
        }
        calender.scheduleTodo(day, time, todo);
    }

    private void printTodos() {
        calender.printTodos();
    }

    private void printScheduledTodos() {
        calender.printScheduledTodos();
    }
}
