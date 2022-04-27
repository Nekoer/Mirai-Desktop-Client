package com.hcyacg.miraipc.logger;

import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.controller.RecordController;
import com.hcyacg.miraipc.entity.Record;
import com.hcyacg.miraipc.utils.MiraiFileUtil;
import javafx.beans.property.SimpleStringProperty;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/9/3 14:44
 */
public class MiraiBotLogger implements MiraiLogger{
    private static final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    private final String currentTimeFormatted = dateFormat.format(new Date());


    @Nullable
    @Override
    public MiraiLogger getFollower() {
        return null;
    }

    @Override
    public void setFollower(@Nullable MiraiLogger miraiLogger) {

    }

    @Nullable
    @Override
    public String getIdentity() {
        return "MiraiPc";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void debug(@Nullable String s) {
        
//        System.out.println(s);
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("调试"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  调试   机器人   " + s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void debug(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("调试"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  调试   机器人   " + (null == s ? throwable.getLocalizedMessage() : s));
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void error(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("错误"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  错误   机器人   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void error(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("错误"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  错误   机器人   " + (null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void info(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("信息"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  信息   机器人   " +s);

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void info(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("信息"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  信息   机器人   " +(null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @NotNull
    @Override
    public <T extends MiraiLogger> T plus(@NotNull T t) {
        System.out.println(t);
        return null;
    }

    @Override
    public void verbose(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("详细"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  详细   机器人   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void verbose(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("详细"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  详细   机器人   " +(null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void warning(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("警告"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  警告   机器人   " +s);

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void warning(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("警告"),
                new SimpleStringProperty("机器人"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.botLog(currentTimeFormatted + "  警告   机器人   " +(null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }
}
