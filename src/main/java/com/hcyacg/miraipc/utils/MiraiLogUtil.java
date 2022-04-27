package com.hcyacg.miraipc.utils;

import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.entity.Record;
import javafx.beans.property.SimpleStringProperty;
import net.mamoe.mirai.utils.MiraiLogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/28 18:25
 */
public class MiraiLogUtil {
    private static final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    private static final MiraiLogger.Companion logger = MiraiLogger.Companion;

    public static void info(String identity,String content){

        logger.create(identity).info(content);
        addLogger(identity,content,"ÐÅÏ¢");
    }

    public static void error(String identity,String content){
        logger.create(identity).error(content);
        addLogger(identity,content,"´íÎó");
    }

    public static void warning(String identity,String content){
        logger.create(identity).warning(content);
        addLogger(identity,content,"¾¯¸æ");
    }
    public static void verbose(String identity,String content){
        logger.create(identity).verbose(content);
        addLogger(identity,content,"ÏêÏ¸");
    }


    private static void addLogger(String identity,String content,String state){
        AppConstant.recordData.add(
                new Record(
                new SimpleStringProperty(timeFormat.format(new Date())),
                new SimpleStringProperty(state),
                new SimpleStringProperty(identity),
                new SimpleStringProperty(content)
                ));
    }
}
