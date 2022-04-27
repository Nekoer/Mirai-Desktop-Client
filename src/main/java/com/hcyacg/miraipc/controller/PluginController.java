package com.hcyacg.miraipc.controller;


//import io.github.palexdev.materialfx.controls.MFXProgressSpinner

import com.hcyacg.miraipc.Launcher;
import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.entity.PluginInfo;
import com.hcyacg.miraipc.utils.MiraiFileUtil;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/26 22:51
 */
public class PluginController implements Initializable {
    @FXML
    private GridPane gridPane = null;
    @FXML
    private Pane pane = null;
    @FXML
    private BorderPane borderPane = null;
    @FXML
    private BorderPane outBorderPane = null;
    @FXML
    private ScrollPane scrollPane = null;

    @FXML
    private MFXProgressSpinner load = null;

    private void draw() {
        Platform.runLater(() -> {
            gridPane.getColumnConstraints().clear();
            gridPane.getRowConstraints().clear();
            gridPane.setHgap(10.0);
            gridPane.setVgap(20.0);
        });
        Thread thread = new pluginDraw();
        thread.start();
        ;
    }

    class pluginDraw extends Thread {
        @Override
        public void run() {
            int rowNum = 3;
            int columnNum = ((AppConstant.pluginData.size() - (AppConstant.pluginData.size() % 3)) / rowNum) + 1;
            int num = 0;

            for (int i = 0; i < columnNum; i++) {
                gridPane.getColumnConstraints().add(new ColumnConstraints());
                for (int j = 0; j < rowNum; j++) {
                    if (num < AppConstant.pluginData.size()) {
                        PluginInfo data = AppConstant.pluginData.get(num);

                        BorderPane borderPane = new BorderPane();
                        DropShadow dropShadow = new DropShadow(); // 阴影向外
                        dropShadow.setRadius(5.0); // 颜色蔓延的距离
                        dropShadow.setOffsetX(0.0);// 水平方向，0则向左右两侧，正则向右，负则向左
                        dropShadow.setOffsetY(0.0); // 垂直方向，0则向上下两侧，正则向下，负则向上
                        dropShadow.setSpread(0.1); // 颜色变淡的程度
                        dropShadow.setColor(Color.gray(0.7));  // 设置颜色
                        borderPane.setEffect(dropShadow);


                        Label nameVersion = new Label(data.getName() + " - v" + data.getVersion() + " - " + data.getAuthor());
                        nameVersion.setPadding(new Insets(8.0, 0.0, 0.0, 0.0));

                        ImageView image = new ImageView();
                        image.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode(data.getCover()))));
                        image.setFitHeight(100.0);
                        image.setFitWidth(200.0);

                        AnchorPane left = new AnchorPane();
                        left.setPrefWidth(30.0);
                        left.setPrefHeight(30.0);
                        borderPane.setLeft(left);

                        AnchorPane top = new AnchorPane();
                        top.setPrefWidth(30.0);
                        top.setPrefHeight(30.0);
                        borderPane.setTop(top);

                        BorderPane inBorderPane = new BorderPane();
//                        image.style = "-fx-border-width: 3px;-fx-border-color: black;-fx-border-style: solid"
//                        label.style = "-fx-border-width: 3px;-fx-border-color: black;-fx-border-style: solid"

                        inBorderPane.setTop(image);
                        inBorderPane.setCenter(nameVersion);

                        Label desc = new Label(data.getDescription());
                        desc.setPadding(new Insets(0.0, 8.0, 8.0, 8.0));
                        desc.setWrapText(true);
//                        desc.style = "-fx-border-width: 3px;-fx-border-color: black;-fx-border-style: solid"
                        desc.setPrefWidth(image.getFitHeight());
                        desc.setPrefHeight(40.0);
                        inBorderPane.setBottom(desc);

                        BorderPane.setAlignment(inBorderPane.getBottom(), Pos.CENTER);
                        inBorderPane.setStyle("-fx-background-color : white");
                        borderPane.setCenter(inBorderPane);
//                        borderPane.style = "-fx-background-color : white"
//                        borderPane.bottom = label
//                        borderPane.style = "-fx-border-width: 3px;-fx-border-color: black;-fx-border-style: solid"

                        SplitPane splitPane = new SplitPane();
                        splitPane.setOrientation(Orientation.HORIZONTAL);
                        splitPane.setDividerPositions(0.5, 0.5);

                        SplitPane leftSplitPane = new SplitPane();
                        leftSplitPane.setOrientation(Orientation.HORIZONTAL);
                        leftSplitPane.setDividerPositions(0.5, 0.5);


                        Label wareHouse = new Label("仓库");
                        ImageView icon = new ImageView();
                        try {
                            icon.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(Launcher.class.getResource("/com/hcyacg/miraipc/image/GitHub.png")).openStream())));
                        } catch (IOException e) {
                            MiraiFileUtil.error(e);
                            e.printStackTrace();
                        }
                        icon.setFitWidth(18.0);
                        icon.setFitHeight(18.0);
                        wareHouse.setOnMouseClicked(e -> {
                            try {
                                Desktop.getDesktop().browse(new URI(data.getWarehouse()));
                            } catch (IOException | URISyntaxException ex) {
                                MiraiFileUtil.error(ex);
                                ex.printStackTrace();
                            }
                        });
                        leftSplitPane.getItems().addAll(icon, wareHouse);


                        SplitPane rightSplitPane = new SplitPane();
                        rightSplitPane.setOrientation(Orientation.HORIZONTAL);
                        rightSplitPane.setDividerPositions(0.5, 0.5, 0.5);
                        Label disable = new Label("禁用");
//                        disable.id = num.toString()
                        ImageView disableIcon = new ImageView();
                        try {
                            disableIcon.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(Launcher.class.getResource("/com/hcyacg/miraipc/image/del.png")).openStream())));
                        } catch (IOException e) {
                            MiraiFileUtil.error(e);
                            e.printStackTrace();
                        }

                        disableIcon.setFitWidth(18.0);
                        disableIcon.setFitHeight(18.0);
                        disable.setOnMouseClicked(e -> {
//                            AppConstant.miraiClassloader.removeClassLoader(data.getClassLoader());
                            AppConstant.miraiClassloader.disable(data.getClasspath(), data.getClassFileName(),data.getClassLoader(),data.getClassName());
                        });
                        rightSplitPane.getItems().addAll(new Label("               "), disableIcon, disable);

                        splitPane.getItems().addAll(leftSplitPane, rightSplitPane);

                        borderPane.setBottom(splitPane);
                        BorderPane.setMargin(borderPane.getBottom(), new Insets(0.0, 0.0, 0.0, 30.0));
                        int finalJ = j;
                        int finalI = i;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //                            GridPane.setHgrow(borderPane,Priority.ALWAYS);
//                            GridPane.setVgrow(borderPane, Priority.ALWAYS);
                                gridPane.add(borderPane, finalJ, finalI);
                            }
                        });
                        num++;
                    }
                }

            }

            Platform.runLater(() -> {
                outBorderPane.setCenter(gridPane);
                scrollPane.setContent(outBorderPane);
                borderPane.setVisible(false);
                load.setVisible(false);
            });
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.setVisible(true);
        load.setVisible(true);
        draw();
    }
}