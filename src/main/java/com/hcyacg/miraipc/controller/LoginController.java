package com.hcyacg.miraipc.controller;


//import io.github.palexdev.materialfx.controls.*


import cn.hutool.core.io.FileUtil;
import com.hcyacg.miraipc.Launcher;
import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.entity.Password;
import com.hcyacg.miraipc.entity.Setting;
import com.hcyacg.miraipc.logger.MiraiBotLogger;
import com.hcyacg.miraipc.logger.MiraiNetLogger;
import com.hcyacg.miraipc.utils.*;
import com.hcyacg.miraipc.load.JavaPluginLoad;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class LoginController implements Initializable {
    @FXML
    private Pane pane = new Pane();

    @FXML
    private Pane root = null;

    @FXML
    private TextField username;

    @FXML
    private MFXButton loginButton = null;

    @FXML
    private PasswordField password;


    @FXML
    private Text logo = null;

    @FXML
    private MFXProgressSpinner load = new MFXProgressSpinner();

    @FXML
    private BorderPane borderPane = new BorderPane();
    Double xOffset = 229.6;
    Double yOffset = 187.2;
    private static Stage stage = null;
    DecimalFormat decimalFormat = new DecimalFormat("0");
    private static final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    private static Boolean isLoginFun = false;


    private static Stage getStage() {
        return StageManager.getStage("login");
    }


    @FXML
    public void loginFun(ActionEvent actionEvent) {
        AppConstant.load.setVisible(true);
        AppConstant.borderPane.setVisible(true);

        if (!isLoginFun) {
            updateYaml();
            Thread thread = new login();
            thread.start();
        }
    }

    public void login() {
        isLoginFun = true;
        if (StringUtils.isBlank(AppConstant.setting.getAccount().getAccount())) {
            DialogUtil.warning(AppConstant.pane.getScene().getWindow(), "登录提示", "账号不能为空");
            return;
        }

        if (StringUtils.isBlank(AppConstant.setting.getAccount().getPassword().getValue())) {
            DialogUtil.warning(AppConstant.pane.getScene().getWindow(), "登录提示", "密码不能为空");
            return;
        }
        AppConstant.qq = Long.valueOf(AppConstant.setting.getAccount().getAccount());
        AppConstant.password = AppConstant.setting.getAccount().getPassword().getValue();



        try {

            MiraiLogUtil.verbose("QQ登录", "账号登录中");
            AppConstant.bot = BotFactory.INSTANCE.newBot(AppConstant.qq, AppConstant.password,e -> {
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
//                e.redirectBotLogToDirectory(AppConstant.fileDirectory);
//                e.redirectNetworkLogToDirectory(AppConstant.fileDirectory);
//                e.redirectBotLogToFile(AppConstant.botLogFile);
//                e.redirectNetworkLogToFile(AppConstant.netLogFile);

                e.setBotLoggerSupplier(bot1 -> new MiraiBotLogger());
                e.setNetworkLoggerSupplier(bot1 -> new MiraiNetLogger());
            });
            new JavaPluginLoad();
            AppConstant.bot.login();
            MiraiLogUtil.verbose("QQ登录", "账号登录成功");

            afterLogin();
            isLoginFun = false;
            AppConstant.load.setVisible(false);
            AppConstant.borderPane.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            MiraiFileUtil.error(e);
            DialogUtil.error(AppConstant.pane.getScene().getWindow(), "错误提示", e.getMessage());
            isLoginFun = false;
            AppConstant.load.setVisible(false);
            AppConstant.borderPane.setVisible(false);
        }

    }

    private static void afterLogin() {
        Platform.runLater(() -> {
            Stage stage = StageManager.getStage("login");
            stage.close();

//        stage.initStyle(StageStyle.UNDECORATED)
            FXMLLoader main = new FXMLLoader(Launcher.class.getResource("/com/hcyacg/miraipc/Main.fxml"));
            stage = new Stage();
            StageManager.put("main", stage);


            Scene scene = null;
            try {
                scene = new Scene(main.load());
            } catch (IOException e) {
                MiraiFileUtil.error(e);
                e.printStackTrace();
            }
            assert scene != null;
            scene.setFill(Paint.valueOf("#ffffff00"));

            stage.setScene(scene);
            stage.setTitle("机器人");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            try {
                stage.getIcons().add(new Image(Objects.requireNonNull(Objects.requireNonNull(Launcher.class.getResource("/com/hcyacg/miraipc/image/all.png")).openStream())));
            } catch (IOException e) {
                MiraiFileUtil.error(e);
                e.printStackTrace();
            }
            stage.show();
        });
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        stage.close();
        System.exit(0);
    }

    @FXML
    public void mini(ActionEvent actionEvent) {
        AppConstant.systemTray.hide(stage);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        AppConstant.pane = pane;
        AppConstant.load = load;
        AppConstant.borderPane = borderPane;


        AppConstant.systemTray.listen(stage);
        Background bg = new Background(new BackgroundFill(null, null, new Insets(0.0)));
        root.setBackground(bg);

        DropShadow dropShadow = new DropShadow(); // 阴影向外
        dropShadow.setRadius(1.0);// 颜色蔓延的距离
        dropShadow.setOffsetX(0.0); // 水平方向，0则向左右两侧，正则向右，负则向左
        dropShadow.setOffsetY(0.0);// 垂直方向，0则向上下两侧，正则向下，负则向上
        dropShadow.setSpread(0.1);  // 颜色变淡的程度
        dropShadow.setColor(Color.gray(0.7));  // 设置颜色
        AppConstant.pane.setEffect(dropShadow);


        AppConstant.pane.setOnMousePressed(event -> {
            Platform.runLater(() -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
        });

        AppConstant.pane.setOnMouseDragged(event -> {
            Platform.runLater(() -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
        });

        AppConstant.load.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
        Reflection ref = new Reflection();
        ref.setBottomOpacity(0.2);
        ref.setFraction(12.0);
        ref.setTopOffset(10.0);
        ref.setTopOpacity(0.2);
        AppConstant.load.setEffect(ref);


        username.setOnKeyPressed(it -> {
            if (it.getCode() == KeyCode.ENTER) {


                updateYaml();

                AppConstant.load.setVisible(true);
                AppConstant.borderPane.setVisible(true);
                if (!isLoginFun) {
                    Thread thread = new login();
                    thread.start();
                }
            }
        });
        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                updateYaml();
            }
        });
        password.setOnKeyPressed(it -> {
            if (it.getCode() == KeyCode.ENTER) {

                updateYaml();
                AppConstant.load.setVisible(true);
                AppConstant.borderPane.setVisible(true);
                if (!isLoginFun) {
                    Thread thread = new login();
                    thread.start();
                }
            }
        });
        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                updateYaml();
            }
        });
        init();

        stage = getStage();

        if (StringUtils.isNotBlank(AppConstant.setting.getAccount().getAccount()) && StringUtils.isNotBlank(AppConstant.setting.getAccount().getPassword().getValue())){
            username.setText(AppConstant.setting.getAccount().getAccount());
            password.setText(AppConstant.setting.getAccount().getPassword().getValue());
        }

    }

    private void init() {
        MiraiLogUtil.verbose("程序", "软件初始化中,请稍后……");
        if (!AppConstant.fileDirectory.exists()) {
            AppConstant.fileDirectory.mkdirs();
        }
        if (!AppConstant.settingDirectory.exists()) {
            AppConstant.settingDirectory.mkdirs();
        }
        if (!AppConstant.netLogFile.exists()) {
            try {
                AppConstant.netLogFile.createNewFile();
            } catch (IOException e) {
                MiraiFileUtil.error(e);
                e.printStackTrace();
            }
        }
        if (!AppConstant.botLogFile.exists()) {
            try {
                AppConstant.botLogFile.createNewFile();
            } catch (IOException e) {
                MiraiFileUtil.error(e);
                e.printStackTrace();
            }
        }
        if (!AppConstant.miraiSettingFile.exists()) {
            try {
                AppConstant.miraiSettingFile.createNewFile();
                InputStream in = Launcher.class.getClassLoader().getResourceAsStream("com/hcyacg/miraipc/mirai/mirai.yml");
                //输出流
                OutputStream out = new FileOutputStream(AppConstant.miraiSettingFile,true);
                byte[] buffer = new byte[1024];
                while (true) {
                    int byteRead = 0;
                    if (in != null) {
                        byteRead = Objects.requireNonNull(in).read(buffer);
                    }
                    if (byteRead == -1)
                        break;
                    out.write(buffer, 0, byteRead);
                }
                in.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                MiraiFileUtil.error(ex);
                DialogUtil.error(AppConstant.pane.getScene().getWindow(),"异常",ex.getLocalizedMessage());
            }
        }
        try {
            byte[] bytes = new byte[0];
            bytes = new byte[AppConstant.miraiSettingFile.toURI().toURL().openStream().available()];
            AppConstant.miraiSettingFile.toURI().toURL().openStream().read(bytes);
            String str = new String(bytes);
            str = str.replaceFirst("\\|","");
            AppConstant.setting = AppConstant.yaml.loadAs(str,Setting.class);
        } catch (IOException e) {
            MiraiFileUtil.error(e);
            e.printStackTrace();
        }
    }

    private void updateYaml() {
        try {
            AppConstant.setting.getAccount().setAccount(this.username.getText());
            Password password = AppConstant.setting.getAccount().getPassword();
            password.setValue(this.password.getText());
            AppConstant.setting.getAccount().setPassword(password);

            AppConstant.yaml.dump(AppConstant.yaml.dumpAs(AppConstant.setting, Tag.MAP, DumperOptions.FlowStyle.BLOCK), new FileWriter(AppConstant.miraiSettingFile));
        } catch (IOException e) {
            MiraiFileUtil.error(e);
            e.printStackTrace();
        }
    }

    static class login extends Thread {
        @Override
        public void run() {
            new LoginController().login();
        }
    }
}

