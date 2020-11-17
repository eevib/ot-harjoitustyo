package ajankayttosovellus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Calender calender = new Calender("kaleteri"); // Luodaan kalenteri.
        System.out.println("Mitä haluat tehdä?");
        String whatToDo = "";
        System.out.println("Lisää todo painamalla 1, lopeta, painamalla 2 ");
        whatToDo = reader.next();
        while (true) {
            if (whatToDo.equals("2")) {
                break;
            } else if (whatToDo.equals("1")) {
                System.out.println("Kirjoita todon nimi: ");
                String todoName = reader.next();
                System.out.println("Mihin aikaan todo ajastetaan? ");
                System.out.println("Anna päivä (ma, ti, ke, to, pe, la tai su): ");
                String day = reader.next();
                System.out.println("Anna aika (kokonaisluku 0-23): ");
                int time = reader.nextInt();
                Todo todo = new Todo(todoName);
                calender.addTodo(todo);
                System.out.println("Lisää seuraava todo, painamalla 1, lopeta painamalla 2");
                whatToDo = reader.next();
            } else {
                System.out.println("En ymmärrä, kirjoita uusi numero.");
                whatToDo = reader.next();
            }

        }
        System.out.println(calender.toString());

    }

}
