package com.hcyacg.miraipc.entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;



import java.io.Serializable;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/22 13:47
 */
public class Record extends RecursiveTreeObject<Record> implements Serializable {

    private static final long serialVersionUID = 1717686440790960001L;
    private SimpleStringProperty time;
    private SimpleStringProperty  state;
    private SimpleStringProperty  from;
    private SimpleStringProperty  content;

    public Record() {
    }

    public Record(SimpleStringProperty time, SimpleStringProperty state, SimpleStringProperty from, SimpleStringProperty content) {
        this.time = time;
        this.state = state;
        this.from = from;
        this.content = content;
    }

    public String getTime() {
        return time.get();
    }


    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getFrom() {
        return from.get();
    }

    public SimpleStringProperty fromProperty() {
        return from;
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    @Override
    public String toString() {
        return "Record{" +
                "time=" + time +
                ", state=" + state +
                ", from=" + from +
                ", content=" + content +
                '}';
    }
}