package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Iterator;
public class Main extends Application {
    TextField usernameField;
    PasswordField passwordField;
    Button loginButton, signUpButton;
    Label username, password;
    ArrayList<Users> users;
    int index;
    public void start(Stage window) throws Exception {
        users = new ArrayList<Users>();
        Label header = new Label("   Welcome to the login page");
        header.setStyle("-fx-text-fill: #69359c");
        Insets insets = new Insets(20);
        Font font = new Font(20);
        header.setFont(font);
        usernameField = new TextField();
        usernameField.setStyle("#00FFFF");
        passwordField = new PasswordField();
        loginButton = new Button("Log in");
        signUpButton = new Button("Sign up");
        username = new Label("Username: ");
        username.setId("bold-label");
        password = new Label("Password: ");
        GridPane layout = new GridPane();
        BorderPane pane = new BorderPane();
        pane.setTop(header);
        pane.setMargin(header, insets);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(1.5);
        layout.setHgap(1.2);
        layout.add(username, 0, 0);
        layout.add(password, 0, 1);
        layout.add(usernameField, 1, 0);
        layout.add(passwordField, 1, 1);
        HBox buttons = new HBox();
        buttons.setSpacing(35);
        buttons.getChildren().addAll(loginButton, signUpButton);
        layout.add(buttons, 1, 2);
        pane.setCenter(layout);
        pane.setMargin(layout, insets);
        signUpButton.setOnAction(e -> signUp(usernameField, passwordField));
        loginButton.setOnAction(e -> logIn(usernameField, passwordField));
        window.setTitle("Login Page");
        Scene scene = new Scene(pane, 300, 275);
        scene.getStylesheets().add(getClass().getResource("Viper.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }
    public void signUp(TextField username, PasswordField password) {
        Users user = new Users();
       if(username.getText().isEmpty()||password.getText().isEmpty()){
            System.out.println("One (or more) fields are empty.");
        }
        else {
            user.setUsername(username.getText());
            user.setPassword(password.getText());
            users.add(user);
          }
        if(users.contains(user)){
            System.out.println("Signed up.");
        }
        else
            System.out.println("Error signing up");
    }
    public void logIn(TextField username, PasswordField password) {
        Users user = new Users();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        Iterator<Users> iterator = users.iterator();
        while (iterator.hasNext()) {
            Users iter = iterator.next();
            if (iter.getUsername().equals(username.getText())&&iter.getPassword().equals(password.getText())) {
                System.out.println("Logged in");
            }
            else
                System.out.println("You're not signed up, Enter your details and click on Sign Up to Sign up");
        }
        }
    public static void main(String[] args) {
        launch(args);
    }
}