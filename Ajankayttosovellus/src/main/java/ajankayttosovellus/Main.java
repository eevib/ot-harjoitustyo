package ajankayttosovellus;

import ajankayttosovellus.ui.CalenderUi;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        CalenderUi calenderUi = new CalenderUi(reader);
        calenderUi.start();
    }

}
