package ru.shytov.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class MainController {

    public void Exit(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    public void Back(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        openScene();
    }

    private void openScene(){
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/startWindow.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setTitle("Authorization");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
