package ru.shytov.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.dao.Dao;
import ru.shytov.model.Product;
import ru.shytov.service.ProductDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GenerelWindowController implements Initializable {

    public ScrollPane scrollPane;

    public TilePane tilePane;

    public BorderPane borderPane;

    private final ObservableList<Product> products = FXCollections.observableArrayList();

    private MyListener myListener;

    FlowPane flowPane;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rubberWindow();
        initDate();
        if(!products.isEmpty()){
            Stage stage = new Stage();
            stage.setTitle("ProductInfo");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/productitem.fxml"));

            AnchorPane anchorPane = loader.load();

            stage.setScene(new Scene(anchorPane));
            stage.getIcons().add(new Image("/school_logo.png"));
            stage.show();

            ProductItemController productItemController = loader.getController();

            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    productItemController.setData(product);
                }
            };
        }

        for(Product product : products){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
            flowPane = loader.load();

            TileController tileController = loader.getController();
            tileController.setData(product,myListener);

            tilePane.getChildren().add(flowPane);
        }
    }

    public void initDate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Product,Integer> productDao = new ProductDaoImpl(factory);
        products.addAll(productDao.findAll());
    }

    private void rubberWindow() {
        scrollPane.widthProperty().addListener((obj, oldValue, newValue) -> {
            tilePane.setPrefWidth(newValue.doubleValue());
        });
    }
}
