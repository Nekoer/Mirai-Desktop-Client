package com.hcyacg.miraipc;

import com.hcyacg.miraipc.utils.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MiraiStart extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader login = new FXMLLoader(Launcher.class.getResource("Login.fxml"));
        StageManager.put("login", stage);

        Scene scene = new Scene(login.load());

        scene.setFill(Paint.valueOf("#ffffff00"));
        stage.setScene(scene);
        stage.setTitle("MiraiµÇÂ¼´°¿Ú");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);

        stage.getIcons().add(new Image(Objects.requireNonNull(Objects.requireNonNull(Launcher.class.getResource("/com/hcyacg/miraipc/image/all.png")).openStream())));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
