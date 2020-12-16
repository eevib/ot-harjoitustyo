package ajankayttosovellus.ui;

//import ajankayttosovellus.dao.FileCalenderDao;
import ajankayttosovellus.dao.FileCalenderDao;
import ajankayttosovellus.dao.FileUserDao;
import ajankayttosovellus.dao.UserDao;
import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.CalenderService;
import ajankayttosovellus.domain.Todo;
import ajankayttosovellus.domain.User;
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
    private GridPane calenderPane;
    private HBox calenderWeekBox;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String userFile = properties.getProperty("userFile");
        String calenderFile = properties.getProperty("calenderFile");
        FileUserDao fileUserDao = new FileUserDao(userFile);
        FileCalenderDao calenderDao = new FileCalenderDao(calenderFile, fileUserDao);
        this.calenderService = new CalenderService(calenderDao, fileUserDao);
        this.calenderPane = new GridPane();
        this.calenderWeekBox = new HBox();
    }

    @Override
    public void start(Stage window) {
        this.window = window;

        this.todoBox = new VBox();
        this.scheduledTodosBox = new VBox();
        this.scheduleComponentsBox = new VBox();
        this.calenderWeekBox = new HBox();
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
        Button scheduleTodoButton = new Button("Schedule todo");
        Button showScheduledTodosButton = new Button("Show scheduled todos");
        Button showCalenderButton = new Button("Show calender");
        VBox components = new VBox();
        VBox scheduledTodos = new VBox();

        GridPane addTodoPane = new GridPane();
        addTodoPane.add(todoLabel, 0, 0);
        addTodoPane.add(todoName, 0, 1);
        addTodoPane.add(addTodoButton, 1, 1);
        addTodoPane.add(todoAddedLabel, 0, 2);
        addTodoPane.add(showTodosButton, 0, 3);
        addTodoPane.add(this.todoBox, 0, 4);
        addTodoPane.add(scheduleTodoButton, 0, 6);
        addTodoPane.add(this.scheduleComponentsBox, 0, 5);
        addTodoPane.add(showScheduledTodosButton, 0, 7);
        addTodoPane.add(scheduledTodos, 1, 6);
        addTodoPane.add(this.scheduledTodosBox, 0, 8);
        addTodoPane.add(showCalenderButton, 0, 10);
        //     addTodoPane.add(this.calenderWeekBox, 0, 11);
        addTodoPane.add(this.calenderPane, 0, 12);

        addTodoPane.setPrefSize(500, 500);
        addTodoPane.setAlignment(Pos.CENTER);
        addTodoPane.setVgap(10);
        addTodoPane.setHgap(10);
        addTodoPane.setPadding(new Insets(15, 15, 15, 15));
        this.addTodos = new Scene(addTodoPane);

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
        scheduleTodoButton.setOnAction(e -> {
            scheduleTodos();
            window.setScene(addTodos);
            window.show();
        });
        showCalenderButton.setOnAction(e -> {
            drawCalender();
            window.setScene(addTodos);
            window.show();
        });
    }

    public void setLoginScene() {
        Label welcome = new Label("Welcome");
        Label nameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        TextField username = new TextField();
        PasswordField passwordField = new PasswordField();
        Label textLabel = new Label("");
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        GridPane loginPane = new GridPane();
        loginPane.add(welcome, 0, 0);
        loginPane.add(nameLabel, 0, 1);
        loginPane.add(username, 1, 1);
        loginPane.add(passwordLabel, 0, 2);
        loginPane.add(passwordField, 1, 2);
        loginPane.add(loginButton, 1, 3);
        loginPane.add(registerButton, 1, 4);
        loginPane.add(textLabel, 1, 5);

        loginPane.setPrefSize(500, 500);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(15, 15, 15, 15));

        this.loginScene = new Scene(loginPane, 500, 500);
        loginButton.setOnAction(e -> {
            String userName = username.getText();
            String password = passwordField.getText();
            if (calenderService.login(userName, password)) {
                setAddTodoScene();
                window.setScene(addTodos);
                window.show();
            } else {
                textLabel.setText("No user found, please try again or register user first.");
                window.setScene(this.loginScene);
                window.show();
            }
        });
        registerButton.setOnAction(e -> {
            if (this.calenderService.createUser(username.getText(), passwordField.getText())) {
                calenderService.login(username.getText(), passwordField.getText());
                setAddTodoScene();
                window.setScene(addTodos);
                window.show();
            } else {
                textLabel.setText("This user already exists, please try again or try login.");
                window.setScene(this.loginScene);
                window.show();
            }
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
        Button logoutButton = new Button("Save and logout");

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
        this.scheduleComponentsBox.getChildren().addAll(this.todoBox, scheduleDayTimeLabel, todoIdLabel, todoId, dayLabel, day, timeLabel, time, addScheduleButton, scheduleTodoSucces, logoutButton);
        logoutButton.setOnAction(e -> {
            this.calenderService.logout();
            System.out.println("logout");
            start(this.window);
        });
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
        if (this.calenderService.getUnScheduledTodos() == null) {
            return;
        }
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

    private void drawCalender() {
        List<String> calender = calenderService.drawCalender();
        int i = 0;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 24; y++) {
                calenderPane.add(new Label(calender.get(i)), x, y);
                i++;
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
