package ru.shytov.controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.shytov.model.Product;

public class ProductItemController {
    
    public ImageView imageView;

    public Label idLbl;

    public Label titleLbl;

    public Label costLbl;

    public Label DescriptionLbl;

    public Label imagePathLbl;

    public Label isActiveLbl;

    public Label manufacLbl;

    public void setData(Product product) {
        Image image = new Image("/images/" + product.getMainImagePath());
        imageView.setImage(image);
        idLbl.setText(String.format("%d", product.getId()));
        titleLbl.setText(product.getTitle());
        costLbl.setText(String.format("%.2f", product.getCost()));
        DescriptionLbl.setText(product.getDescription());
        imagePathLbl.setText(product.getMainImagePath());
        isActiveLbl.setText(String.valueOf(product.getIsActive()));
        manufacLbl.setText(String.valueOf(product.getManufacturer().getName()));
    }
}
