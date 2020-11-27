package ajankayttosovellus.ui;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.CalenderService;
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

    private CalenderService calenderService;
    private Scene calenderScene;
    private VBox todoBox;

    @Override
    public void start(Stage window) {
        this.calenderService = new CalenderService();
        this.todoBox = new VBox();
        Label todoLabel = new Label("Todo");
        TextField todoName = new TextField();

        Button addTodoButton = new Button("Add todo");
        Label dayLabel = new Label("Give day: ");
        TextField day = new TextField();
        Label timeLabel = new Label("Give time: ");
        TextField time = new TextField();
        addTodoButton.setOnAction(e -> {
            calenderService.createTodo(todoName.getText());
        });
        Button showTodosButton = new Button("Print unscheduled todos");
        showTodosButton.setOnAction(e -> {
            drawTodos();
        });
        Button scheduleTodo = new Button("Schedule todo");
        
    //    scheduleTodo.setOnAction(e -> {
//            calenderService.scheduleTodo(dayLabel.getText(), timeLabel.getText(), todo);
//        });
        VBox components = new VBox();
        components.getChildren().addAll(todoLabel, todoName, dayLabel, day, timeLabel, time, addTodoButton, showTodosButton, this.todoBox);
        Scene addTodos = new Scene(components);
        window.setScene(addTodos);
        window.show();
    }

    public void scheduleTodo() {

    }

    public void drawTodos() {
        todoBox.getChildren().clear();
        List<Todo> unScheduledTodos = calenderService.getUnScheduledTodos();
        unScheduledTodos.forEach(todo -> {
            todoBox.getChildren().add(createTodoNode(todo));
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
