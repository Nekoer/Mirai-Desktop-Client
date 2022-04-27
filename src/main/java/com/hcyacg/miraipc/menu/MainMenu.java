package com.hcyacg.miraipc.menu;


import com.hcyacg.miraipc.Launcher;
import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.logger.MiraiBotLogger;
import com.hcyacg.miraipc.logger.MiraiNetLogger;
import com.hcyacg.miraipc.utils.*;
import com.hcyacg.miraipc.utils.StageManager;
import io.github.palexdev.materialfx.controls.MFXContextMenu;
import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.*;
import java.util.Objects;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/22 21:04
 */
public class MainMenu extends MFXContextMenu {


    private static final String systemPath = System.getProperty("user.dir");

    public MainMenu(Node owner) {
        super(owner);
    }


    public static MFXContextMenu mainMenu(Node owner) {

        MFXContextMenuItem listMenuItem = new MFXContextMenuItem("������");
        MFXContextMenuItem restartMenuItem = new MFXContextMenuItem("����");
        MFXContextMenuItem exitMenuItem = new MFXContextMenuItem("�˳�");
        MFXContextMenuItem onlineMenuItem = new MFXContextMenuItem("����");
        MFXContextMenuItem offlineMenuItem = new MFXContextMenuItem("����");
        try {

            /**
             * ����
             */
            restartMenuItem.setOnAction(e -> {
                try {
                    Runtime.getRuntime().exec(systemPath + File.separator+ "miraipc.exe");
                    System.exit(0);

                } catch (IOException ex) {
                    ex.printStackTrace();
                    DialogUtil.error(owner.getScene().getWindow(), "��������","ʧ��");
                }
            });


            /**
             * �˳�����
             */
            exitMenuItem.setOnAction(e -> {
                Platform.exit();
                System.exit(0);
            });


            /**
             * �б�
             */
            listMenuItem.setOnAction(e -> {
                FXMLLoader list = new FXMLLoader(Launcher.class.getResource("/com/hcyacg/miraipc/List.fxml"));
                var listStage = StageManager.getStage("list");
                if (null != listStage) {
                    listStage.show();
                } else {
                    listStage = new Stage();
                    StageManager.put("list", listStage);
                    listStage.setTitle("��ҳ��");
                    try {
                        listStage.getIcons().add(new Image(Objects.requireNonNull(Objects.requireNonNull(Launcher.class.getResource("/com/hcyacg/miraipc/image/all.png")).openStream())));
                    } catch (IOException ex) {
                        MiraiFileUtil.error(ex);
                        ex.printStackTrace();
                    }
                    try {
                        Scene scene = new Scene(list.load());
                        scene.setFill(Paint.valueOf("#ffffff00"));
                        listStage.setScene(scene);
                    } catch (IOException ex) {
                        MiraiFileUtil.error(ex);
                        ex.printStackTrace();
                    }
                    listStage.initStyle(StageStyle.TRANSPARENT);
                    listStage.setResizable(false);
                    listStage.show();
                }
            });

            onlineMenuItem.setOnAction(exe -> {
                if (!AppConstant.bot.isOnline()){
                    AppConstant.bot = BotFactory.INSTANCE.newBot(AppConstant.qq, AppConstant.password, e -> {
                        if (!AppConstant.settingDirectory.exists()) {
                            AppConstant.settingDirectory.mkdirs();
                        }
                        if (!AppConstant.deviceInfoPath.exists()) {
                            try {
                                AppConstant.deviceInfoPath.createNewFile();
                            } catch (IOException ex) {
                                MiraiFileUtil.error(ex);
                                ex.printStackTrace();
                            }
                            e.fileBasedDeviceInfo(AppConstant.deviceInfoPath.getPath());

                        } else {
                            BufferedReader reader = null;
                            StringBuilder sbf = new StringBuilder();
                            try {
                                reader = new BufferedReader(new FileReader(AppConstant.deviceInfoPath));
                            } catch (FileNotFoundException ex) {
                                MiraiFileUtil.error(ex);
                                ex.printStackTrace();
                            }
                            String tempStr = "";
                            while (true) {
                                try {
                                    assert reader != null;
                                    if ((tempStr = reader.readLine()) == null) break;
                                } catch (IOException ex) {
                                    MiraiFileUtil.error(ex);
                                    ex.printStackTrace();
                                }
                                sbf.append(tempStr);
                            }
                            try {
                                reader.close();
                            } catch (IOException ex) {
                                MiraiFileUtil.error(ex);
                                ex.printStackTrace();
                            }
                            e.loadDeviceInfoJson(sbf.toString());
                        }

                        switch (AppConstant.setting.getAccount().getConfiguration().getProtocol()) {
                            case "ANDROID_PHONE" : e.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);break;
                            case "ANDROID_WATCH" : e.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);break;
                            case "ANDROID_PAD" : e.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PAD);break;
                            default : e.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
                        }

                        e.redirectBotLogToFile(AppConstant.botLogFile);
                        e.redirectNetworkLogToFile(AppConstant.netLogFile);

                        e.setBotLoggerSupplier(bot1 -> new MiraiBotLogger());
                        e.setNetworkLoggerSupplier(bot1 -> new MiraiNetLogger());
                    });
                    AppConstant.bot.login();
                }else {
                    DialogUtil.warning(AppConstant.pane.getScene().getWindow(),"������״̬","�������Ѿ�������,��Ҫ����������������");
                }
            });

            offlineMenuItem.setOnAction(e -> {
                AppConstant.bot.close();
            });
        } catch (Exception e) {
            e.printStackTrace();
            MiraiFileUtil.error(e);
        }


//        items.add(logMenuItem)
//        items.add(pluginMenuItem)


        return MFXContextMenu.Builder.build(owner)
                .addItem(listMenuItem)
                .addLineSeparator()
                .addItem(onlineMenuItem)
                .addItem(offlineMenuItem)
                .addLineSeparator()
                .addItem(restartMenuItem)
                .addItem(exitMenuItem).installAndGet();
    }
}