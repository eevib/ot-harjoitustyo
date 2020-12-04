package ajankayttosovellus.ui;

import ajankayttosovellus.dao.FileCalenderDao;
import ajankayttosovellus.dao.FileUserDao;
import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.CalenderService;
import ajankayttosovellus.domain.Todo;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalenderUi extends Application {

    private CalenderService calenderService;
    private Scene addTodos;
    private Scene loginScene;
    private VBox todoBox;
    private VBox scheduledTodosBox;
    private VBox scheduleComponentsBox;
    private Stage window;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String userFile = properties.getProperty("userFile");
        String calenderFile = properties.getProperty("calenderFile");
        FileUserDao userDao = new FileUserDao(userFile);
        FileCalenderDao calenderDao = new FileCalenderDao(calenderFile, userDao);
        this.calenderService = new CalenderService(calenderDao, userDao);
    }

    @Override
    public void start(Stage window) {
        this.window = window;

        this.todoBox = new VBox();
        this.scheduledTodosBox = new VBox();
        this.scheduleComponentsBox = new VBox();

        setLoginScene();
        window.setScene(loginScene);
        window.show();

    }

    public void setAddTodoScene() {
        Label todoLabel = new Label("Todo");
        TextField todoName = new TextField();
        Button addTodoButton = new Button("Add todo");
        Label todoAddedLabel = new Label("");
        addTodoButton.setOnAction(e -> {
            this.calenderService.createTodo(todoName.getText());
            todoAddedLabel.setText("Todo was added, add another one");
        });
        Button showTodosButton = new Button("Show unscheduled todos");
        Button scheduleTodo = new Button("Schedule todo");
        Button showScheduledTodosButton = new Button("Show scheduled todos");

        VBox components = new VBox();
        VBox scheduledTodos = new VBox();
        drawTodos();
        components.getChildren().addAll(todoLabel, todoName, addTodoButton, todoAddedLabel, showTodosButton,
                this.todoBox, scheduleTodo, this.scheduleComponentsBox, showScheduledTodosButton, scheduledTodos, this.scheduledTodosBox);

        this.addTodos = new Scene(components, 500, 575);

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
            scheduleTodos();
            window.setScene(addTodos);
            window.show();
        });
    }

    public void setLoginScene() {
        Label welcome = new Label("Welcome");
        Label nameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        Button loginButton = new Button("Login");
        Button register = new Button("Register");

        GridPane loginPane = new GridPane();
        loginPane.add(welcome, 0, 0);
        loginPane.add(nameLabel, 0, 1);
        loginPane.add(username, 1, 1);
        loginPane.add(passwordLabel, 0, 2);
        loginPane.add(password, 1, 2);
        loginPane.add(loginButton, 1, 3);
        loginPane.add(register, 1, 4);

        loginPane.setPrefSize(500, 500);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(15, 15, 15, 15));

        this.loginScene = new Scene(loginPane, 500, 500);
        loginButton.setOnAction(e -> {
            String userName = username.getText();
            if (calenderService.login(userName, password.getText())) {
                setAddTodoScene();
                window.setScene(addTodos);
            } else {
                
            }
            setAddTodoScene();
            window.setScene(addTodos);
        });
    }

    public void scheduleTodos() {
        Label scheduleDayTimeLabel = new Label("Which todo do you want to schedule? ");
        Label todoIdLabel = new Label("Give todo id: ");
        TextField todoId = new TextField();
        Label dayLabel = new Label("Give day (Mon, Tue, Wed, Thu, Fri, Sat, Sun): ");
        TextField day = new TextField();
        Label timeLabel = new Label("Give time (0-23): ");
        TextField time = new TextField();
        Button addScheduleButton = new Button("Add timing");
        Label scheduleTodoSucces = new Label();

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
        this.scheduleComponentsBox.getChildren().addAll(this.todoBox, scheduleDayTimeLabel, todoIdLabel, todoId, dayLabel, day, timeLabel, time, addScheduleButton, scheduleTodoSucces);

    }

    public void drawScheduledTodos() {
        this.scheduledTodosBox.getChildren().clear();
        if (this.calenderService.getScheduledTodos().isEmpty()) {
            Label noTodosAdded = new Label("You haven't timed any todos.");
            this.scheduledTodosBox.getChildren().add(noTodosAdded);
            return;
        }
        List<Todo> scheduledTodos = this.calenderService.getScheduledTodos();
        scheduledTodos.forEach(todo -> {
            scheduledTodosBox.getChildren().add(createTodoNode(todo));
        });
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

    private void reserveSpot() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
