package com.hcyacg.miraipc.constant;


import com.hcyacg.miraipc.Launcher;
import com.hcyacg.miraipc.entity.PluginInfo;
import com.hcyacg.miraipc.entity.Record;
import com.hcyacg.miraipc.entity.Setting;
import com.hcyacg.miraipc.utils.MiraiSystemTray;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import net.mamoe.mirai.Bot;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/25 18:07
 */
public class AppConstant {

    public static final String systemPath = System.getProperty("user.dir");
    public static final File fileDirectory = new File(systemPath + File.separator + "log");
    public static final File settingDirectory = new File(systemPath + File.separator + "config"+ File.separator +"setting");
    public static final File deviceInfoPath = new File(settingDirectory.getPath() + File.separator + "device.json");
    public static final File netLogFile = new File(fileDirectory.getPath() + File.separator +"net-"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + ".log");
    public static final File botLogFile = new File(fileDirectory.getPath() + File.separator +"bot-"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + ".log");
    public static final File errorLogFile = new File(fileDirectory.getPath() + File.separator +"error-"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()) + ".log");
    public static final File miraiSettingFile = new File(settingDirectory.getPath()+ File.separator +"mirai.yml");
    public static final File miraiSettingYaml = new File(Objects.requireNonNull(Launcher.class.getResource("/com/hcyacg/miraipc/mirai/mirai.yml")).getPath());


    public static ObservableList<Record> recordData = FXCollections.observableArrayList();

    public static ObservableList<PluginInfo> pluginData = FXCollections.observableArrayList();

    public static TableView<Record> table = new TableView<Record>();

    public static Timer timerTask = new Timer();
    public static Boolean recordBoolean = true;
    public static Integer scrollScreenX = 0;
    public static Integer scrollScreenY = 0;

    public static Long qq = 0L;
    public static String password = null;
    public static Yaml yaml = new Yaml();
    public static Setting setting;
    public static Bot bot;

    public static MiraiSystemTray systemTray = new MiraiSystemTray();
    public static Pane pane = new Pane();
    public static MFXProgressSpinner load = new MFXProgressSpinner();
    public static BorderPane borderPane = new BorderPane();

    public static Boolean isOnline = false;

//    public PluginViewController thread = new  PluginViewController();


}