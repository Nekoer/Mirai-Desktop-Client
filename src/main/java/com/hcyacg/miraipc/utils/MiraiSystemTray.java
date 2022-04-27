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

    //��С��,��С��.
    //�˵���(��)��������������Ǳ������Ĺ�,���ʹ��IDEA,��Ҫ��Run-Edit Configuration��LoginApplication�е�VM Options�����-Dfile.encoding=GBK
    //���ʹ��Eclipse,��Ҫ�Ҽ�Run as-ѡ��Run Configuration,�ڵڶ���Argumentsѡ���е�VM Options�����-Dfile.encoding=GBK
    //�����exe��װ��򿪲�������

    static {
        //ִ��stage.close()����,���ڲ�ֱ���˳�
        Platform.setImplicitExit(false);
        //�˵���(��)��������������Ǳ������Ĺ�,���ʹ��IDEA,��Ҫ��Run-Edit Configuration��LoginApplication�е�VM Options�����-Dfile.encoding=GBK
        //���ʹ��Eclipse,��Ҫ�Ҽ�Run as-ѡ��Run Configuration,�ڵڶ���Argumentsѡ���е�VM Options�����-Dfile.encoding=GBK
        showItem = new MenuItem("��");
        //�˵���(�˳�)
        exitItem = new MenuItem("�˳�");
        //�˴�����ѡ��ico��ʽ��ͼƬ,Ҫʹ��16*16��png��ʽ��ͼƬ
//        System.out.println(Launcher.class.getResource("com/hcyacg/miraipc/image/mirai.png"));
//        System.out.println(Launcher.class.getResource("/com/hcyacg/miraipc/image/mirai.png"));
//        System.out.println(Launcher.class.getResource("mirai.png"));
//        System.out.println(Launcher.class.getClassLoader().getResource("mirai.png"));
        URL url = Launcher.class.getResource("/com/hcyacg/miraipc/image/all.png");
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        //ϵͳ����ͼ��
        trayIcon = new TrayIcon(image);
        //��ʼ�������¼�(��)
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
            //���ϵͳ�Ƿ�֧������
            if (!SystemTray.isSupported()) {
                //ϵͳ���̲�֧��
//                log.info(Thread.currentThread().stackTrace[1].className + ":ϵͳ���̲�֧��")
            }
            //����ͼ��ߴ��Զ���Ӧ
            trayIcon.setImageAutoSize(true);
            //ϵͳ����
            SystemTray tray = SystemTray.getSystemTray();
            //����ʽ�˵����
            PopupMenu popup = new PopupMenu();
            popup.add(showItem);
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            //����Ƶ�ϵͳ����,����ʾ��ʾ�ı�
            trayIcon.setToolTip("Mirai");
            tray.add(trayIcon);

        } catch (Exception e) {
            MiraiFileUtil.error(e);
            //ϵͳ�������ʧ��
//            log.error(Thread.currentThread().stackTrace[1].className + ":ϵͳ���ʧ��", e)
        }
    }

    /**
     * ����ϵͳ������������Stage
     */
    public void listen(Stage stage) {
        //��ֹ����ָ���쳣
        if (showListener == null || exitListener == null || mouseListener == null || showItem == null || exitItem == null || trayIcon == null) {
            return;
        }
        //�Ƴ�ԭ�����¼�
        showItem.removeActionListener(showListener);
        exitItem.removeActionListener(exitListener);
        trayIcon.removeMouseListener(mouseListener);
        //��Ϊ�¼�: ���"��"��ť,��ʾ����
        showListener = e -> Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showStage(stage);
            }
        });
        //��Ϊ�¼�: ���"�˳�"��ť, ���˳�ϵͳ
        exitListener = e -> {
            System.exit(0);
        };
        //�����Ϊ�¼�: ������ʾstage
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //������
                if (e.getButton() == MouseEvent.BUTTON1) {
                    showStage(stage);
                }
            }
        };
        //���˵�������¼�
        showItem.addActionListener(showListener);
        exitItem.addActionListener(exitListener);
        //��ϵͳ������������Ӧ�¼�
        trayIcon.addMouseListener(mouseListener);
    }

    /**
     * �رմ���
     */
    public void hide(Stage stage) {
        Platform.runLater(() -> {
            if (SystemTray.isSupported()) {
                //stage.hide()��stage.close()�ȼ�
                stage.hide();
            } else {
                System.exit(0);
            }
        });
    }

    /**
     * ���ϵͳ����,��ʾ����(������ʾ����ǰ��,����С����״̬��Ϊfalse)
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