package com.hcyacg.miraipc.menu;


import com.hcyacg.miraipc.entity.Record;
import com.hcyacg.miraipc.utils.DialogUtil;
import com.hcyacg.miraipc.controller.RecordController;
import io.github.palexdev.materialfx.controls.MFXContextMenu;
import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import javafx.scene.Node;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


/**
 * @Author: Nekoer
 * @Desc: �����¼�б��Ҽ��˵�
 * @Date: 2021/8/26 11:19
 */
public class RecordMenu extends MFXContextMenu {

    public static RecordMenu INSTANCE = null;

    public RecordMenu(Node owner) {
        super(owner);
    }

    public static   MFXContextMenu recordMenu(Node owner) {
        MFXContextMenuItem copyMenuItem = new MFXContextMenuItem("����");

        copyMenuItem.setOnAction (e ->{
            ClipboardContent content = new ClipboardContent();
            Record record = RecordController.selectData;
            content.putString(record.getTime() + record.getState() + record.getFrom() + record.getContent());
            Clipboard.getSystemClipboard().setContent(content);
            DialogUtil.info(owner.getScene().getWindow(), "������ʾ","���Ƴɹ�");
        });




        return MFXContextMenu.Builder.build(owner).addItem(copyMenuItem).installAndGet();
    }
}