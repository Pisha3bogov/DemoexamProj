package ru.shytov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/startWindow.fxml")));
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/school_logo.png"));
        stage.show();
    }
}

