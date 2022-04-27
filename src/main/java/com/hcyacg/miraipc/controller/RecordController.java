package com.hcyacg.miraipc.controller;


import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.entity.Record;
import com.hcyacg.miraipc.menu.RecordMenu;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.github.palexdev.materialfx.controls.MFXContextMenu;
import io.github.palexdev.materialfx.controls.MFXTableView;

import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;


/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/22 14:28
 */
public class RecordController implements Initializable {
    @FXML
    private MFXLegacyTableView<Record> table;

    @FXML
    private TableColumn<Record,String> from;

        @FXML
    private TableColumn<Record,String> time;

        @FXML
    private  TableColumn<Record,String> content ;

        @FXML
    private  TableColumn<Record,String> state;

    DecimalFormat decimalFormat = new DecimalFormat("0");

    public static ObservableList<Record> cellData = FXCollections.observableArrayList();
    public static Record selectData = null;


    private void load() {

        AppConstant.timerTask.purge();
        AppConstant.timerTask.schedule(new TimerTask() {
            @Override
            public void run() {
                table.getItems().addAll(cellData);
                table.refresh();
                cellData.clear();
            }
        },new Date(), 100);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setTableMenuButtonVisible(true);



        from.setCellValueFactory(new PropertyValueFactory<Record,String>("from"));
        time.setCellValueFactory(new PropertyValueFactory<Record,String>("time"));
        state.setCellValueFactory(new PropertyValueFactory<Record,String>("state"));
        content.setCellValueFactory(new PropertyValueFactory<Record,String>("content"));




        AppConstant.timerTask.cancel();
        AppConstant.timerTask = new Timer();
        cellData.addAll(AppConstant.recordData);
        table.getItems().addAll(cellData);
        table.refresh();
        table.scrollTo(AppConstant.recordData.size());
        cellData.clear();


        MFXContextMenu recordMenu = RecordMenu.recordMenu(table);

        table.setOnMousePressed(event -> {
            if (recordMenu.isShowing()){
                recordMenu.hide();
            }
        });

        table.setOnMouseClicked(it -> {

            if (it.getButton() == MouseButton.SECONDARY) {
                recordMenu.show(it.getPickResult().getIntersectedNode());
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener (
            (observable, oldValue, newValue) ->{
                    selectData = newValue;
        });
        load();
    }
}