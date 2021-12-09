package ru.shytov.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hibernate.JDBCException;
import org.hibernate.OptimisticLockException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import ru.shytov.model.Admin;
import ru.shytov.service.AdminDaoImpl;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {

    private final AdminDaoImpl adminDao;

    public PasswordField passwordField;

    public TextField loginField;

    public Label Error;

    public PasswordField confirmation;

    public RegistrationController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.adminDao = new AdminDaoImpl(factory);
    }

    public void registration(ActionEvent actionEvent) {

        if(passwordField.getText().equals(confirmation.getText())) {

            try {
                Admin admin = new Admin(loginField.getText(), passwordField.getText());
                adminDao.save(admin);
                final Button source = (Button) actionEvent.getSource();
                source.getScene().getWindow().hide();

                openScene();
            } catch (NullPointerException e) {
                Error.setTextFill(Color.RED);
                Error.setText("Введите пароль");
            } catch (JDBCException e) {
                Error.setTextFill(Color.RED);
                Error.setText("Такой пользователь уже существует");
            }
        }else {
            Error.setTextFill(Color.RED);
            Error.setText("Пароли не совпадают");
        }
    }

    public void back(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        openScene();
    }

    public void exit(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    private void openScene() {
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/startWindow.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Authorization");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
