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
        this.reader = new Scanner(System.in);
        whatToDo = new TreeMap<>();
        this.whatToDo.put("1", "1 lopeta");
        this.whatToDo.put("2", "2 lisää todo");
        this.whatToDo.put("3", "3 tulosta todoot");

    }

    public void start() {
        this.calender = new Calender("kaleteri"); // Luodaan kalenteri.
        printCommands();
        System.out.println("");
        while (true) {
            System.out.println("Anna komento:");
            String command = reader.next();
            if (!this.whatToDo.keySet().contains(command)) {
                System.out.println("En ymmärrä, kirjoita komento uudestaan.");
            }
            if (command.equals("1")) {
                break;
            } else if (command.equals("2")) {
                addTodo();
            }
        }
        System.out.println(this.calender.todosToString());
        System.out.println(this.calender.toString());
    }

    public void printCommands() {
        System.out.println("Mitä haluat tehdä?");
        this.whatToDo.entrySet().stream().forEach(e -> {
            System.out.println(e.getValue() + " ");
        });
    }

    public void addTodo() {
        System.out.println("Kirjoita todon nimi: ");
        String todoName = reader.next();
        Todo todo = new Todo(todoName);
        this.calender.addTodoToList(todo);
        System.out.println("Haluatko ajastaa todoon? K = kyllä, E = ei");
        String scheduleTodo = reader.next();
        if (scheduleTodo.equals("k")) {
            scheduleTodo(todo);
        }
    }

    private void scheduleTodo(Todo todo) {
        System.out.println("Mihin aikaan todo ajastetaan? ");
        System.out.println("Anna päivä (ma, ti, ke, to, pe, la tai su): ");
        String day = reader.next();
        System.out.println("Anna aika (kokonaisluku 0-23): ");
        int time = reader.nextInt();
        while (this.calender.scheduleTodo(day, time, todo) == false) {
            System.out.println("Valitse toinen aika, tämä ei ole vapaa.");
            System.out.println("Anna päivä (ma, ti, ke, to, pe, la tai su): ");
            day = reader.next();
            System.out.println("Anna aika (kokonaisluku 0-23): ");
            time = reader.nextInt();
        }
        calender.scheduleTodo(day, time, todo);
    }
}
