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
 * @Desc: 网络记录列表右键菜单
 * @Date: 2021/8/26 11:19
 */
public class RecordMenu extends MFXContextMenu {

    public static RecordMenu INSTANCE = null;

    public RecordMenu(Node owner) {
        super(owner);
    }

    public static   MFXContextMenu recordMenu(Node owner) {
        MFXContextMenuItem copyMenuItem = new MFXContextMenuItem("复制");

        copyMenuItem.setOnAction (e ->{
            ClipboardContent content = new ClipboardContent();
            Record record = RecordController.selectData;
            content.putString(record.getTime() + record.getState() + record.getFrom() + record.getContent());
            Clipboard.getSystemClipboard().setContent(content);
            DialogUtil.info(owner.getScene().getWindow(), "操作提示","复制成功");
        });




        return MFXContextMenu.Builder.build(owner).addItem(copyMenuItem).installAndGet();
    }
}