package com.hcyacg.miraipc.utils;

import com.hcyacg.miraipc.Launcher;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;


/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/26 12:26
 */
public class MiraiSystemTray {
    private static MenuItem showItem= null;
    private static MenuItem exitItem = null;
    private static TrayIcon trayIcon = null;
    private static ActionListener showListener = null;
    private static ActionListener exitListener = null;
    private static MouseListener mouseListener = null;

    //右小角,最小化.
    //菜单项(打开)中文乱码的问题是编译器的锅,如果使用IDEA,需要在Run-Edit Configuration在LoginApplication中的VM Options中添加-Dfile.encoding=GBK
    //如果使用Eclipse,需要右键Run as-选择Run Configuration,在第二栏Arguments选项中的VM Options中添加-Dfile.encoding=GBK
    //打包成exe安装后打开不会乱码

    static {
        //执行stage.close()方法,窗口不直接退出
        Platform.setImplicitExit(false);
        //菜单项(打开)中文乱码的问题是编译器的锅,如果使用IDEA,需要在Run-Edit Configuration在LoginApplication中的VM Options中添加-Dfile.encoding=GBK
        //如果使用Eclipse,需要右键Run as-选择Run Configuration,在第二栏Arguments选项中的VM Options中添加-Dfile.encoding=GBK
        showItem = new MenuItem("打开");
        //菜单项(退出)
        exitItem = new MenuItem("退出");
        //此处不能选择ico格式的图片,要使用16*16的png格式的图片
//        System.out.println(Launcher.class.getResource("com/hcyacg/miraipc/image/mirai.png"));
//        System.out.println(Launcher.class.getResource("/com/hcyacg/miraipc/image/mirai.png"));
//        System.out.println(Launcher.class.getResource("mirai.png"));
//        System.out.println(Launcher.class.getClassLoader().getResource("mirai.png"));
        URL url = Launcher.class.getResource("/com/hcyacg/miraipc/image/all.png");
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        //系统托盘图标
        trayIcon = new TrayIcon(image);
        //初始化监听事件(空)
        showListener = e -> Platform.runLater(new Runnable() {
            @Override
            public void run() {

            }
        });

        exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        };
    }



    public MiraiSystemTray() {
        try {
            //检查系统是否支持托盘
            if (!SystemTray.isSupported()) {
                //系统托盘不支持
//                log.info(Thread.currentThread().stackTrace[1].className + ":系统托盘不支持")
            }
            //设置图标尺寸自动适应
            trayIcon.setImageAutoSize(true);
            //系统托盘
            SystemTray tray = SystemTray.getSystemTray();
            //弹出式菜单组件
            PopupMenu popup = new PopupMenu();
            popup.add(showItem);
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            //鼠标移到系统托盘,会显示提示文本
            trayIcon.setToolTip("Mirai");
            tray.add(trayIcon);

        } catch (Exception e) {
            MiraiFileUtil.error(e);
            //系统托盘添加失败
//            log.error(Thread.currentThread().stackTrace[1].className + ":系统添加失败", e)
        }
    }

    /**
     * 更改系统托盘所监听的Stage
     */
    public void listen(Stage stage) {
        //防止报空指针异常
        if (showListener == null || exitListener == null || mouseListener == null || showItem == null || exitItem == null || trayIcon == null) {
            return;
        }
        //移除原来的事件
        showItem.removeActionListener(showListener);
        exitItem.removeActionListener(exitListener);
        trayIcon.removeMouseListener(mouseListener);
        //行为事件: 点击"打开"按钮,显示窗口
        showListener = e -> Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showStage(stage);
            }
        });
        //行为事件: 点击"退出"按钮, 就退出系统
        exitListener = e -> {
            System.exit(0);
        };
        //鼠标行为事件: 单机显示stage
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //鼠标左键
                if (e.getButton() == MouseEvent.BUTTON1) {
                    showStage(stage);
                }
            }
        };
        //给菜单项添加事件
        showItem.addActionListener(showListener);
        exitItem.addActionListener(exitListener);
        //给系统托盘添加鼠标响应事件
        trayIcon.addMouseListener(mouseListener);
    }

    /**
     * 关闭窗口
     */
    public void hide(Stage stage) {
        Platform.runLater(() -> {
            if (SystemTray.isSupported()) {
                //stage.hide()与stage.close()等价
                stage.hide();
            } else {
                System.exit(0);
            }
        });
    }

    /**
     * 点击系统托盘,显示界面(并且显示在最前面,将最小化的状态设为false)
     */
    private void showStage(Stage stage) {
        Platform.runLater(() -> {
            if (stage.isIconified()) {
                stage.setIconified(false);
            }
            if (!stage.isShowing()) {
                stage.show();
            }else {
                hide(stage);
            }

            stage.toFront();
        });
    }
}