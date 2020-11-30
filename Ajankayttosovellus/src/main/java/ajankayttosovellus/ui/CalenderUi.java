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
    private Scene addTodos;
    private VBox todoBox;
    private VBox scheduleTodoBox;

    @Override
    public void start(Stage window) {
        this.calenderService = new CalenderService();
        this.todoBox = new VBox();
        this.scheduleTodoBox = new VBox();
        Label todoLabel = new Label("Todo");
        TextField todoName = new TextField();
        Button addTodoButton = new Button("Add todo");
        Label todoAddedLabel = new Label("");
        addTodoButton.setOnAction(e -> {
            this.calenderService.createTodo(todoName.getText());
            todoAddedLabel.setText("Todo was added");
        });
        Button showTodosButton = new Button("Print unscheduled todos");
        Button scheduleTodo = new Button("Schedule todo");
        Button showScheduledTodosButton = new Button("Show scheduled todos");

        VBox components = new VBox();
        VBox scheduledTodos = new VBox();
        drawTodos();
        components.getChildren().addAll(todoLabel, todoName, addTodoButton, todoAddedLabel, showTodosButton, this.todoBox, scheduleTodo, showScheduledTodosButton, scheduledTodos);
        this.addTodos = new Scene(components);
        window.setScene(addTodos);

        showTodosButton.setOnAction(e -> {
            drawTodos();
            window.setScene(addTodos);
            window.show();
        });
        showScheduledTodosButton.setOnAction(e -> {
            drawScheduledTodos();
            window.setScene(addTodos);
            window.show();
        });
        scheduleTodo.setOnAction(e -> {
            window.setScene(scheduleTodos());
        });
        window.setScene(addTodos);
        window.show();
    }

    public Scene scheduleTodos() {
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
        Button backButton = new Button("Back");

        addScheduleButton.setOnAction(e -> {
            Todo todo = calenderService.getTodo(Integer.parseInt(todoId.getText()));
            String todoDay = day.getText();
            String todoTime = time.getText();
            int id = Integer.parseInt(todoId.getText());
            if (id > calenderService.getCalenderSize() + 1 || id < 1) {
                scheduleTodoSucces.setText("Give a todo from the list. ");
            } else if (this.calenderService.scheduleTodo(todoDay, todoTime, todo) == false) {
                scheduleTodoSucces.setText("The time is already taken or you gave the information asked in wrong format. Try again.");
            } else {
                scheduleTodoSucces.setText("The todo is now scheduled.");
            }
        });
        drawTodos();
        scheduleComponents.getChildren().addAll(reserveDayTime, this.todoBox, todoIdLabel, todoId, dayLabel, day, timeLabel, time, addScheduleButton, backButton, scheduleTodoSucces);

        Scene scheduleTodosScene = new Scene(scheduleComponents);
        backButton.setOnAction(e -> {

        });
        return scheduleTodosScene;
    }

    public VBox drawScheduledTodos() {
        VBox scheduledTodosBox = new VBox();
        List<Todo> scheduledTodos = this.calenderService.getScheduledTodos();
        scheduledTodos.forEach(todo -> {
            scheduledTodosBox.getChildren().add(createTodoNode(todo));
        });
        return scheduledTodosBox;
    }

    public void drawTodos() {
        this.todoBox.getChildren().clear();
        List<Todo> unScheduledTodos = this.calenderService.getUnScheduledTodos();
        unScheduledTodos.forEach(todo -> {
            this.todoBox.getChildren().add(createTodoNode(todo));
        });
    }

    public Node createTodoNode(Todo todo) {
        HBox box = new HBox(10);
        Label label = new Label("Todo: " + todo.getTodoName() + " Id: " + todo.getTodoId());
        box.getChildren().addAll(label);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
