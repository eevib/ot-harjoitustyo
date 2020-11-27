package ajankayttosovellus.ui;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.Todo;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalenderUi extends Application {

    private Scene calenderScene;
    private Calender calender;
    private VBox todos;

    @Override
    public void start(Stage window) {
        this.calender = new Calender("Kalenteri"); // Luodaan kalenteri.

        Label todoLabel = new Label("Todo");
        TextField todoName = new TextField();

        Button addTodoButton = new Button("Add todo");
        addTodoButton.setOnAction(e -> {
            Todo todo = new Todo(todoName.getText(), calender.getTodoIdCalc());
            calender.addTodoToList(todo);
        });
        Button showTodosButton = new Button("Print unscheduled todos");
        showTodosButton.setOnAction(e -> {
            drawTodos();
                });
        VBox components = new VBox();
        components.getChildren().addAll(todoLabel, todoName, addTodoButton, showTodosButton);
        Scene addTodos = new Scene(components);
        window.setScene(addTodos);
        window.show();
    }

    public void drawTodos() {
        todos.getChildren().clear();
        List<Todo> unScheduledTodos = calender.getUnScheduledTodos();
        unScheduledTodos.forEach(todo -> {
            todos.getChildren().add(createTodoNode(todo));
        });
    }

    public Node createTodoNode(Todo todo) {
        HBox box = new HBox(10);
        Label label = new Label(todo.getTodoName());
        box.getChildren().addAll(label);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
