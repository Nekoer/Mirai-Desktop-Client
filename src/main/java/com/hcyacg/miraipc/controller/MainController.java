package com.hcyacg.miraipc.controller;


import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.menu.MainMenu;
import com.hcyacg.miraipc.utils.StageManager;
import io.github.palexdev.materialfx.controls.MFXContextMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import net.mamoe.mirai.Bot;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/22 16:06
 */
public class MainController implements Initializable {
    @FXML
    private Pane pane = null;

    @FXML
    private ImageView avatar = null;

    @FXML
    private Circle circle= null;

    @FXML
    private Label botName = null;

    @FXML
    private Label botState = null;

    private static Stage stage = null;
    private Double xOffset = 0.0;
    private Double yOffset = 0.0;
    private final Timer timer = new Timer();

    private static Stage getStage() {
        return StageManager.getStage("main");
    }

    static  {
        stage = getStage();
    }

    private void botInfo() {
        timer.purge();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                botName.setText(AppConstant.bot.getNick());
                if (AppConstant.bot.isOnline()) {
                    AppConstant.isOnline = true;
                    Platform.runLater(() -> botState.setText("Online"));

                } else {
                    AppConstant.isOnline = false;
                    Platform.runLater(() -> botState.setText("Offline"));
                }
            }
        }, new Date(), 100);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppConstant.pane = pane;
        AppConstant.systemTray.listen(stage);

        DropShadow dropShadow = new DropShadow(); // 阴影向外
        dropShadow.setRadius(5.0); // 颜色蔓延的距离
        dropShadow.setOffsetX(0.0);// 水平方向，0则向左右两侧，正则向右，负则向左
        dropShadow.setOffsetY(0.0); // 垂直方向，0则向上下两侧，正则向下，负则向上
        dropShadow.setSpread(0.1); // 颜色变淡的程度
        dropShadow.setColor(Color.gray(0.7));  // 设置颜色
        pane.setEffect(dropShadow);


        Background bg = new Background(new BackgroundFill(null, new CornerRadii(30.0), new Insets(0.0)));
        pane.setBackground(bg);

        MFXContextMenu mainMenu = MainMenu.mainMenu(pane);

        pane.setOnMousePressed(event ->{
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();

            if (mainMenu.isShowing()) {
                mainMenu.hide();
            }
        });


        pane.setOnMouseDragged (event ->{
            stage.setX(event.getScreenX()- xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        pane.setOnMouseClicked (it ->{
            if (it.getButton() == MouseButton.SECONDARY) {
                mainMenu.show(it.getPickResult().getIntersectedNode());
            }
        });
        botInfo();
        Image image = new Image(AppConstant.bot.getAvatarUrl());
        avatar.setImage(image);
        avatar.setClip(circle);
    }
}