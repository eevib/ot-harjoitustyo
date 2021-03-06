package ajankayttosovellus.ui;

import ajankayttosovellus.dao.FileCalenderDao;
import ajankayttosovellus.dao.FileUserDao;
import ajankayttosovellus.domain.CalenderService;
import ajankayttosovellus.domain.Todo;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Stage window;
    private GridPane calenderPane;
    private GridPane scheduleTodoPane;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties"));
        String userFile = properties.getProperty("userFile");
        String calenderFile = properties.getProperty("calenderFile");
        FileUserDao fileUserDao = new FileUserDao(userFile);
        FileCalenderDao calenderDao = new FileCalenderDao(calenderFile);
        this.calenderService = new CalenderService(calenderDao, fileUserDao);
    }

    @Override
    public void start(Stage window) {
        this.window = window;
        this.todoBox = new VBox();
        this.scheduledTodosBox = new VBox();
        this.scheduleTodoPane = new GridPane();
        this.calenderPane = new GridPane();
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
        Button logoutButton = new Button("Save and logout");
        VBox scheduledTodos = new VBox();

        GridPane addTodoPane = new GridPane();
        addTodoPane.add(todoLabel, 0, 0);
        addTodoPane.add(todoName, 0, 1);
        addTodoPane.add(addTodoButton, 1, 1);
        addTodoPane.add(todoAddedLabel, 0, 2);
        addTodoPane.add(showTodosButton, 0, 3);
        addTodoPane.add(this.todoBox, 0, 4);
        addTodoPane.add(scheduleTodoButton, 0, 6);
        addTodoPane.add(showScheduledTodosButton, 0, 8);
        addTodoPane.add(scheduledTodos, 1, 6);
        addTodoPane.add(this.scheduleTodoPane, 0, 7);
        addTodoPane.add(this.scheduledTodosBox, 0, 8);
        addTodoPane.add(showCalenderButton, 0, 10);
        addTodoPane.add(logoutButton, 0, 11);

        addTodoPane.setPrefSize(800, 800);
        addTodoPane.setVgap(10);
        addTodoPane.setHgap(10);
        addTodoPane.setPadding(new Insets(15, 15, 15, 15));
        GridPane scenePane = new GridPane();
        scenePane.add(addTodoPane, 0, 0);
        scenePane.add(this.calenderPane, 1, 0);
        this.addTodos = new Scene(scenePane, 1500, 1500);

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
        logoutButton.setOnAction(e -> {
            this.calenderService.saveCalender();
            start(this.window);
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

        loginPane.setPrefSize(1000, 1000);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(15, 15, 15, 15));

        this.loginScene = new Scene(loginPane, 1000, 1000);
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
        Label scheduleTodoSuces2 = new Label();

        addScheduleButton.setOnAction(e -> {
            if (calenderService.getUnScheduledTodos() == null) {
                scheduleTodoSucces.setText("Start by adding a todo.");
            }
            Todo todo = calenderService.getTodo(Integer.parseInt(todoId.getText()));
            String todoDay = day.getText();
            String todoTime = time.getText();
            int id = Integer.parseInt(todoId.getText());
            Boolean todoIsInTheList = true;
            List<Todo> unscheduledTodos = calenderService.getScheduledTodos();
            for (int i = 0; i < unscheduledTodos.size(); i++) {
                if (unscheduledTodos.get(i).getTodoId().equals(id)) {
                    todoIsInTheList = true;
                    continue;
                }
            }
            if (todoIsInTheList == false) {
                scheduleTodoSucces.setText("Give a todo from the list. ");
            } else if (this.calenderService.scheduleTodo(todoDay, todoTime, todo) == false) {
                scheduleTodoSucces.setText("The time is already taken,");
                scheduleTodoSuces2.setText("or you gave the information in wrong format.");
            } else {
                scheduleTodoSucces.setText("The todo is now scheduled.");
            }
            this.calenderService.saveCalender();

        });
        drawTodos();
       
        scheduleTodoPane.add(scheduleDayTimeLabel, 0, 1);
        scheduleTodoPane.add(todoIdLabel, 0, 2);
        scheduleTodoPane.add(todoId, 1, 2);
        scheduleTodoPane.add(dayLabel, 0, 3);
        scheduleTodoPane.add(day, 1, 3);
        scheduleTodoPane.add(timeLabel, 0, 4);
        scheduleTodoPane.add(time, 1, 4);
        scheduleTodoPane.add(addScheduleButton, 0, 5);
        scheduleTodoPane.add(scheduleTodoSucces, 0, 6);
        scheduleTodoPane.add(scheduleTodoSuces2, 0, 7);
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

    private void drawCalender() {
        this.calenderPane.setVgap(5);
        this.calenderPane.setHgap(10);
        this.calenderPane.setPadding(new Insets(5, 5, 5, 5));
        List<String> calender = calenderService.drawCalender();
        // Alustetaan aika
        for (int j = 0; j < 24; j++) {
            this.calenderPane.add(new Label("" + j), 0, j + 1);
        }
        // Alustetaan otsikot
        this.calenderPane.add(new Label("Time"), 0, 0);
        this.calenderPane.add(new Label("Monday"), 1, 0);
        this.calenderPane.add(new Label("Tuesday"), 2, 0);
        this.calenderPane.add(new Label("Wednesday"), 3, 0);
        this.calenderPane.add(new Label("Thursday"), 4, 0);
        this.calenderPane.add(new Label("Friday"), 5, 0);
        this.calenderPane.add(new Label("Saturday"), 6, 0);
        this.calenderPane.add(new Label("Sunday"), 7, 0);

        //Piirretään kalenteri
        int i = 0;
        for (int x = 1; x < 8; x++) {
            for (int y = 1; y < 25; y++) {
                calenderPane.add(new Label(calender.get(i)), x, y);
                i++;
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
