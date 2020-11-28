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
        addTodoButton.setOnAction(e -> {
            this.calenderService.createTodo(todoName.getText());
        });
        Button showTodosButton = new Button("Print unscheduled todos");
        showTodosButton.setOnAction(e -> {
            drawTodos();
        });
        Button scheduleTodo = new Button("Schedule todo");
        VBox components = new VBox();
        components.getChildren().addAll(todoLabel, todoName, addTodoButton, showTodosButton, this.todoBox, scheduleTodo);
        VBox scheduleComponents = new VBox();
        Label reserveDayTime = new Label("Which todo do you want to schedule? ");
        Label todoIdLabel = new Label("Give todo id: ");
        TextField todoId = new TextField();
        Label dayLabel = new Label("Give day: ");
        TextField day = new TextField();
        Label timeLabel = new Label("Give time: ");
        TextField time = new TextField();
        Button addScheduleButton = new Button("Schedule todo");
        Label scheduleTodoSucces = new Label();
        drawTodos();
        scheduleComponents.getChildren().addAll(reserveDayTime, this.todoBox, todoIdLabel, todoId, dayLabel, day, timeLabel, time, addScheduleButton);
        addScheduleButton.setOnAction(e -> {
            Todo todo = calenderService.getTodo(Integer.parseInt(todoId.getText()));
            String todoDay = day.getText();
            String todoTime = time.getText();
            if (this.calenderService.scheduleTodo(todoDay, todoTime, todo) == false) {
                scheduleTodoSucces.setText("The time is already taken or you gave the time in wrong format.");
            } else {
                scheduleTodoSucces.setText("The todo is now scheduled.");
            }
        });
        Scene scheduleTodos = new Scene(scheduleComponents);
        scheduleTodo.setOnAction(e -> {
            window.setScene(scheduleTodos);
        });
        Scene addTodos = new Scene(components);
        window.setScene(addTodos);
        window.show();
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
        Label label = new Label(todo.getTodoName() + " " + todo.getTodoId());
        box.getChildren().addAll(label);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
