package ru.shytov.controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.shytov.model.Product;

public class TileController {

    public ImageView loadedImage;

    public Label bookTitleLbl;

    public Label costLbl;

    public Label isActiveLbl;

    private  MyListener myListener;

    private Product product;

    public void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    public void setData(Product product,MyListener myListener){
        this.product = product;
        this.myListener = myListener;
        Image image = new Image(String.valueOf(getClass().getResource("/images/" + product.getMainImagePath())));
        loadedImage.setImage(image);
        bookTitleLbl.setText(subTitle(product.getTitle()));
        costLbl.setText(String.format("%.2f руб.", product.getCost()));
        isActiveLbl.setText(productActive(product.getIsActive()));
    }

    private String subTitle(String title) {
        if(title.length() < 15){
            return title;
        }else return title.substring(0,15) + "...";
    }

    private String productActive(int isActive) {
        return isActive == 0? "не активен" : "";
    }
}
