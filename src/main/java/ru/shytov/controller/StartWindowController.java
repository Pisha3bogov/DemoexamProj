package ru.shytov.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.model.Admin;
import ru.shytov.service.AdminDaoImpl;

import java.io.IOException;
import java.util.Objects;

public class StartWindowController {
    private final AdminDaoImpl AdminDao;

    public PasswordField passwordField;

    public TextField loginField;

    public Label Error;

    public StartWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.AdminDao = new AdminDaoImpl(factory);
    }

    public void OpenMain(ActionEvent actionEvent) {

        try {
            Admin admin = new Admin(loginField.getText(), passwordField.getText());
            final boolean exit = AdminDao.findAll().stream().anyMatch(admin::equals);
        if(exit) {
            Button input = (Button) actionEvent.getSource();
            input.getScene().getWindow().hide();

            openSceneMain();
        }else {
            Error.setTextFill(Color.RED);
            Error.setText("Логин или пароль введены не верно");
            passwordField.clear();
        }
        }catch (NullPointerException e){
            Error.setTextFill(Color.RED);
            Error.setText("Введите пароль");
        }
    }

    public void Registration(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        openSceneRegistration();
    }

    public void Exit(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    private void openSceneMain() {
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/general_window.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setScene(new Scene(root));
        stage.setTitle("Authorization");
        stage.getIcons().add(new Image("/school_logo.png"));
        stage.show();

    }

    public void ResetPassword(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        openSceneResetPassword();
    }

    private void openSceneRegistration(){
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/registration.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Registration");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/school_logo.png"));
        stage.show();
    }

    private void openSceneResetPassword(){
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/resetPassword1.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setTitle("Воставление пароля");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/school_logo.png"));
        stage.show();
    }
}
