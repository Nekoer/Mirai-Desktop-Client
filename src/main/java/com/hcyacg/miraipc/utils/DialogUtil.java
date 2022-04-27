package com.hcyacg.miraipc.utils;



import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.DialogType;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/26 0:10
 */
public class DialogUtil {

    public static void info(Window window, String title, String content) {
        Platform.runLater(() -> {
            //DialogType.INFO, title, content


            MFXStageDialog dialog = MFXGenericDialogBuilder.build(MFXGenericDialogBuilder.build().setContentText(content).makeScrollable(true).get())
                    .toStageDialogBuilder()
                    .initOwner(window)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle(title)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();


            dialog.show();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.close();
                }
            }, 2000);
        });
    }

    public static void error(Window window, String title, String content) {
        Platform.runLater(() -> {
            MFXStageDialog dialog = MFXGenericDialogBuilder.build(MFXGenericDialogBuilder.build().setContentText(content).makeScrollable(true).get())
                    .toStageDialogBuilder()
                    .initOwner(window)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle(title)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.close();
                }
            }, 2000);
        });

    }

    public void generic(Window window, String title, String content) {
        Platform.runLater(() -> {
            MFXStageDialog dialog = MFXGenericDialogBuilder.build(MFXGenericDialogBuilder.build().setContentText(content).makeScrollable(true).get())
                    .toStageDialogBuilder()
                    .initOwner(window)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle(title)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.close();
                }
            }, 2000);
        });
    }

    public static void warning(Window window, String title, String content) {
        Platform.runLater(() -> {
            MFXStageDialog dialog = MFXGenericDialogBuilder.build(MFXGenericDialogBuilder.build().setContentText(content).makeScrollable(true).get())
                    .toStageDialogBuilder()
                    .initOwner(window)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle(title)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.close();
                }
            }, 2000);
        });
    }
}

