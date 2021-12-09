package ru.shytov.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.model.Admin;
import ru.shytov.service.AdminDaoImpl;

import java.io.IOException;
import java.util.Objects;

public class ResetPassword2Controller {

    private final AdminDaoImpl AdminDao;

    public Label error;

    public PasswordField passwordField;

    public PasswordField confirmation;

    public ResetPassword2Controller() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.AdminDao = new AdminDaoImpl(factory);
    }


    public void back(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        openSceneResPas1();
    }

    public void exit(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    private void openSceneResPas1() {
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/resetPassword1.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setTitle("Восстановление пароля");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void openSceneStart() {
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StartWindow.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void reset(ActionEvent actionEvent) {

        ResetPassword1Controller res = Context.getInstance().getResPas();

        Admin admin = res.getAdmin();

        if(passwordField.getText().equals(confirmation.getText())){

            admin.setPassword(passwordField.getText());

            AdminDao.update(admin);

            final Button source = (Button) actionEvent.getSource();
            source.getScene().getWindow().hide();

            openSceneStart();
        } else {
            error.setTextFill(Color.RED);
            error.setText("Пароли не совпадают");
        }
    }
}
