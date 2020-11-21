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
        this.whatToDo.put("x", "x lopeta");
        this.whatToDo.put("1", "1 lisää todo");
        this.whatToDo.put("2", "2 tulosta ajastamattomat todoot");
        this.whatToDo.put("3", "3 tulosta ajastetut todoot");
        this.whatToDo.put("4", "4 merkitse aika varatuksi");
        this.whatToDo.put("5", "5 ajasta todo listasta");

    }

    public void start() {
        while (true) {
            printCommands();
            String command = reader.next();
            if (!this.whatToDo.keySet().contains(command)) {
                System.out.println("En ymmärrä, kirjoita komento uudestaan.");
            }
            if (command.equals("x")) {
                break;
            } else if (command.equals("1")) {
                addTodo();
            } else if (command.equals("2")) {
                printUnScheduledTodos();
            } else if (command.equals("3")) {
                printScheduledTodos();
            } else if (command.equals("4")) {
                reserveTimeSpot();
            } else if (command.equals("5")) {
                scheduleTodoFromList();
            }
        }
    }
    
    public void printCommands() {
        System.out.println("");
        System.out.println("Mitä haluat tehdä?");
        this.whatToDo.entrySet().stream().forEach(e -> {
            System.out.println(e.getValue() + " ");
        });
        System.out.print("Komento: ");
    }

    public void addTodo() {
        System.out.println("Kirjoita todon nimi: ");
        String todoName = reader.next();
        int todoId = calender.getTodoIdCalc();
        Todo todo = new Todo(todoName, todoId);
        this.calender.addTodoToList(todo);
        System.out.println("Haluatko ajastaa todoon? k = kyllä, e = ei");
        String scheduleTodo = reader.next();
        while (!scheduleTodo.equals("e") && !scheduleTodo.equals("k")) {
            System.out.println("Tunnistamaton merkki. Haluatko ajastaa todoon? k = kyllä, e = ei");
            scheduleTodo = reader.next();
        }
        if (scheduleTodo.equals("k")) {
            scheduleTodo(todo);
        }
    }

    private void scheduleTodo(Todo todo) {
        System.out.println("Mihin aikaan todo ajastetaan? ");
        String day = getDay();
        String time = getTime();
        while (calender.scheduleTodo(day, time, todo) == false) {
            System.out.println("Valitse toinen aika, tämä ei ole vapaa, tai annoit ajan väärässä muodossa.");
            day = getDay();
            time = getTime();
        }
        calender.scheduleTodo(day, time, todo);
    }

    private void printUnScheduledTodos() {
        calender.printUnScheduledTodos();
    }

    private void printScheduledTodos() {
        calender.printScheduledTodos();
    }

    private void reserveTimeSpot() {
        System.out.println("Minkä ajan haluat varata?");
        String day = getDay();
        String time = getTime();
        while (calender.reserveTimeSlot(day, time) == false) {
            System.out.println("Valitse toinen aika, tämä on jo varattu, tai annoit ajan väärässä muodossa.");
            day = getDay();
            time = getTime();
        }
        calender.reserveTimeSlot(day, time);
    }

    private void scheduleTodoFromList() {
        System.out.println("Ajastamattomat todot: ");
        printUnScheduledTodos();
        System.out.println("Minkä todon haluat ajastaa?");
        System.out.println("Anna todon id: ");
        String todoId = reader.next();
        int id = Integer.parseInt(todoId);
        Todo todo = calender.getUnScheduledTodo(id);
        if(todo == null) {
            System.out.println("Todota ei löytynyt listasta. ");
            return;
        }
        scheduleTodo(calender.getUnScheduledTodo(id));
    }

    private String getDay() {
        System.out.println("Anna päivä numerona (ma = 0, ti = 1, ke = 2, to = 3, pe = 4, la = 5 tai su = 6): ");
        String day = reader.next();
        return day;
    }

    private String getTime() {
        System.out.println("Anna aika kokonaislukuna 0-23: ");
        String time = reader.next();
        return time;
    }
}
