package ru.shytov.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.model.Admin;
import ru.shytov.service.AdminDaoImpl;

import java.io.IOException;
import java.util.Objects;

public class ResetPassword1Controller {

    private final AdminDaoImpl adminDao;

    public Label error;

    public TextField loginVos;

    private Admin admin;

    public ResetPassword1Controller() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.adminDao = new AdminDaoImpl(factory);
    }

    public void back(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        openSceneStart();
    }

    public void exit(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }


    private void openSceneStart() {
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

    private void openSceneResetPas2() {
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/resetPassword2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Восстановление пароля");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void further(ActionEvent actionEvent) {

        admin = adminDao.searchByLogin(loginVos.getText());

        if(admin != null){
            final Button source = (Button) actionEvent.getSource();
            source.getScene().getWindow().hide();

            openSceneResetPas2();
        }else {
            error.setTextFill(Color.RED);
            error.setText("Такого логина не существует");
        }

        Context.getInstance().setResPas(this);
    }

    public Admin getAdmin() {
        return admin;
    }
}
