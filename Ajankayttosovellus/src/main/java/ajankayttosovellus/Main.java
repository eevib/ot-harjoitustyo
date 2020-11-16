package ajankayttosovellus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Calender calender = new Calender("Kalenteri"); // Luodaan kalenteri.
        System.out.println("Mitä haluat tehdä?");
        int whatToDo = 0;
        System.out.println("Lopeta, painamalla 2 ");
        System.out.println("Lisää todo, painamalla 1");
        whatToDo = reader.nextInt();
        while (whatToDo != 2) {
            System.out.println("");
            if (whatToDo == 1) {
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
                whatToDo = reader.nextInt();
            } 

        }
        System.out.println(calender.toString());
        
    }

}
