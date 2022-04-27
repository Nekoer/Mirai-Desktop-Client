package com.hcyacg.miraipc.entity;

import javafx.beans.property.SimpleStringProperty;


import java.io.Serializable;
import java.net.URLClassLoader;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/24 22:34
 */
public class PluginInfo implements Serializable{

    private static final long serialVersionUID = -4982256316290036604L;
    private Integer id;
    private SimpleStringProperty enable;
    private SimpleStringProperty name;
    private SimpleStringProperty version;
    private SimpleStringProperty author;
    private String cover;
    private SimpleStringProperty warehouse;
    private SimpleStringProperty description;
    private String classpath;
    private String classFileName ;
    private ClassLoader classLoader;
    private Object className;

    public PluginInfo() {
    }

    public PluginInfo(Integer id, SimpleStringProperty enable, SimpleStringProperty name, SimpleStringProperty version, SimpleStringProperty author, String cover, SimpleStringProperty warehouse, SimpleStringProperty description, String classpath, String classFileName, ClassLoader classLoader, Object className) {
        this.id = id;
        this.enable = enable;
        this.name = name;
        this.version = version;
        this.author = author;
        this.cover = cover;
        this.warehouse = warehouse;
        this.description = description;
        this.classpath = classpath;
        this.classFileName = classFileName;
        this.classLoader = classLoader;
        this.className = className;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnable() {
        return enable.get();
    }

    public SimpleStringProperty enableProperty() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable.set(enable);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getVersion() {
        return version.get();
    }

    public SimpleStringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWarehouse() {
        return warehouse.get();
    }

    public SimpleStringProperty warehouseProperty() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse.set(warehouse);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getClassFileName() {
        return classFileName;
    }

    public void setClassFileName(String classFileName) {
        this.classFileName = classFileName;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Object getClassName() {
        return className;
    }

    public void setClassName(Object className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "id=" + id +
                ", enable=" + enable +
                ", name=" + name +
                ", version=" + version +
                ", author=" + author +
                ", cover='" + cover + '\'' +
                ", warehouse=" + warehouse +
                ", description=" + description +
                ", classpath='" + classpath + '\'' +
                ", classFileName='" + classFileName + '\'' +
                ", classLoader=" + classLoader +
                ", className=" + className +
                '}';
    }
}
