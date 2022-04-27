package com.hcyacg.miraipc.controller;


import com.hcyacg.miraipc.Launcher;
import com.hcyacg.miraipc.utils.MiraiFileUtil;
import com.hcyacg.miraipc.utils.StageManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/25 16:52
 */
public class ListController implements Initializable{
    @FXML
    public Button quit = null;
    @FXML
    public Pane leftPane;
    @FXML
    public BorderPane topBorderPane;
    //    @FXML
//    private Pane root = null;
    @FXML
    private Pane pane  = null;

    @FXML
    private BorderPane borderPane = null;
    @FXML
    private JFXButton record = null;
    @FXML
    private JFXButton plugin = null;

    @FXML
    private VBox cardPane = null;
    private Double xOffset = 0.0;
    private Double yOffset = 0.0;
    private static Stage stage = null;
    private static Stage getStage() {
        return StageManager.getStage("list");
    }

    private void loadFXML(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            borderPane.setCenter(loader.load());
        } catch (IOException e) {
            MiraiFileUtil.error(e);
            e.printStackTrace();
        }
    }
    static  {
        stage = getStage();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(1.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setSpread(0.1);
        dropShadow.setColor(Color.gray(0.7));
        pane.setEffect(dropShadow);


        Background paneBackground = new Background(new BackgroundFill(Paint.valueOf("#ffffff00"), new CornerRadii(10.0), new Insets(0.0)));
        pane.setBackground(paneBackground);
//        borderPane.setBackground(bg);
        Background cardPaneBackground = new Background(new BackgroundFill(Paint.valueOf("#8a00ff"), new CornerRadii(0.0,0.0,0.0,10.0,false), new Insets(0.0)));
//        borderPane.setBackground(cardPaneBackground);
        cardPane.setBackground(cardPaneBackground);
        cardPane.setStyle("-fx-border-color: #ffffff00;-fx-border-width: 0px;-fx-border-style: solid");

        Background leftPaneBackground = new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), new CornerRadii(10.0,0.0,0.0,0.0,false), new Insets(0.0)));
        leftPane.setBackground(leftPaneBackground);
        leftPane.setStyle("-fx-border-color: #ffffff00;-fx-border-width: 0px;-fx-border-style: solid");


        record.setOnMouseClicked(e -> {
            FXMLLoader record = new FXMLLoader(Launcher.class.getResource("/com/hcyacg/miraipc/Record.fxml"));
            loadFXML(record.getLocation());
        });

        plugin.setOnMouseClicked(e->{
            loadFXML(Launcher.class.getResource("/com/hcyacg/miraipc/Plugin.fxml"));
        });

        cardPane.setOnMousePressed(event ->{
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });


        cardPane.setOnMouseDragged (event ->{
            stage.setX(event.getScreenX()- xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        topBorderPane.setOnMousePressed(event ->{
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });


        topBorderPane.setOnMouseDragged (event ->{
            stage.setX(event.getScreenX()- xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        stage.close();
    }
}