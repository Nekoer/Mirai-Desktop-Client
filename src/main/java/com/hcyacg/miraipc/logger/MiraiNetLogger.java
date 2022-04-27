package com.hcyacg.miraipc.logger;

import com.alibaba.fastjson.JSONObject;
import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.controller.RecordController;
import com.hcyacg.miraipc.entity.Record;
import com.hcyacg.miraipc.utils.MiraiFileUtil;
import com.hcyacg.miraipc.utils.MiraiLogUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.*;
import net.mamoe.mirai.event.AbstractEvent;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotActiveEvent;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.MiraiLogger;
import net.mamoe.mirai.utils.MiraiLoggerPlatformBase;
import net.mamoe.mirai.utils.SimpleLogger;
import org.apache.commons.lang3.StringUtils;
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
 * @Date: 2021/8/29 20:26
 */
public class MiraiNetLogger implements MiraiLogger {
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
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  调试   系统   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void debug(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("调试"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  调试   系统   " +(null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void error(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("错误"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  错误   系统   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void error(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("错误"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  错误   系统   " +(null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void info(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("信息"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  信息   系统   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void info(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("信息"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  信息   系统   " +(null == s ? throwable.getLocalizedMessage() : s));

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
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  详细   系统   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void verbose(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("详细"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  详细   系统   " +(null == s ? throwable.getLocalizedMessage() : s));

        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void warning(@Nullable String s) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("警告"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  警告   系统   " +s);
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

    @Override
    public void warning(@Nullable String s, @Nullable Throwable throwable) {
        Record record = new Record(
                new SimpleStringProperty(currentTimeFormatted),
                new SimpleStringProperty("警告"),
                new SimpleStringProperty("系统"),
                new SimpleStringProperty(null == s ? Objects.requireNonNull(throwable).getLocalizedMessage() : s));
        MiraiFileUtil.netLog(currentTimeFormatted + "  警告   系统   " +(null == s ? throwable.getLocalizedMessage() : s));
        AppConstant.recordData.add(record);
        RecordController.cellData.add(record);
    }

//    static {
//        try{
//            MessageEvent();
//            BotActiveEvent();
//            AbstractEvent();
//        }catch (Exception e){
//            MiraiLogUtil.error("网络日志",e.getLocalizedMessage());
//        }
//    }


    public static void MessageEvent() throws Exception{
        GlobalEventChannel.INSTANCE.subscribeAlways(MessageEvent.class, e -> {
            AppConstant.recordData.add(
                    new Record(
                            new SimpleStringProperty(timeFormat.format(e.getTime() * 1000 )),
                            new SimpleStringProperty("信息"),
                            new SimpleStringProperty(e.getSubject().toString()),
                            new SimpleStringProperty(e.getMessage().toString())
                    )
            );
            RecordController.cellData.add(
                    new Record(
                            new SimpleStringProperty(timeFormat.format(e.getTime() * 1000)),
                            new SimpleStringProperty("信息"),
                            new SimpleStringProperty(e.getSubject().toString()),
                            new SimpleStringProperty(e.getMessage().toString())
                    )
            );
        });
    }

    public static void BotActiveEvent()  throws Exception{
        GlobalEventChannel.INSTANCE.subscribeAlways(BotActiveEvent.class, e -> {
            String event = e.toString();
            if (StringUtils.isBlank(event)) {
                return;
            }
            JSONObject obj = null;
            try {
                event = event.replaceFirst("\\(", "{");
                event = event.substring(event.indexOf("{"), event.lastIndexOf(")")) + "\"}";
                event = event.replace("target=", "\"target\":\"").replace(", message=", "\", \"message\":\"").replace(", exception=", "\", \"exception\":\"").replace(", receipt=", "\", \"receipt\":\"");
                obj = JSONObject.parseObject(event);
            } catch (Exception ex) {
                return;
            }
            String target = obj.getString("target");
            String message = obj.getString("message");

            Record record = new Record(
                    new SimpleStringProperty(timeFormat.format(new Date())),
                    new SimpleStringProperty("信息"),
                    new SimpleStringProperty(target),
                    new SimpleStringProperty(message)
            );
            AppConstant.recordData.add(record);
            RecordController.cellData.add(record);
        });
    }

    public static void AbstractEvent()  throws Exception{
        GlobalEventChannel.INSTANCE.subscribeAlways(AbstractEvent.class, e -> {
            String event = e.toString();
            if (StringUtils.isBlank(event)) {
                return;
            }
            event = event.replaceFirst("\\(", "{\"");
            JSONObject obj = null;
            try {
                event = event.substring(event.indexOf("{"), event.lastIndexOf(")")) + "\"}";
                event = event.substring(event.indexOf("{"), event.lastIndexOf(")")) + "\"}";
                event = event.replace("=", "\":\"").replace(", ", "\", \"");
                obj = JSONObject.parseObject(event);
            } catch (Exception ex) {
                return;
            }
//            System.out.println(event);


            Record record = null;
            String target = obj.getString("target");
            String message = obj.getString("message");
            String time = obj.getString("messageTime");
            String bot = obj.getString("bot");
            String authorId = obj.getString("authorId");
            String messageIds = obj.getString("messageIds");
            String messageInternalIds = obj.getString("messageInternalIds");
            String operator = obj.getString("operator");
            String author = obj.getString("author");
            String group = obj.getString("group");

            //群撤回
            if (e.toString().contains("GroupRecall")) {
                record = new Record(
                        new SimpleStringProperty(timeFormat.format(Long.parseLong(time + "000"))),
                        new SimpleStringProperty("群撤回"),
                        new SimpleStringProperty(group),
                        new SimpleStringProperty("撤回了一条信息")
                );
            }

            //好友发送
//            if (e.toString().contains("FriendMessagePostSendEvent")) {
//                record = new Record(
//                        new SimpleStringProperty(timeFormat.format(new Date())),
//                        new SimpleStringProperty("发送好友消息"),
//                        new SimpleStringProperty(target),
//                        new SimpleStringProperty(message)
//                );
//            }
            //好友撤回
            if (e.toString().contains("FriendRecall")) {
                record = new Record(
                        new SimpleStringProperty(timeFormat.format(Long.parseLong(time + "000"))),
                        new SimpleStringProperty("好友消息撤回"),
                        new SimpleStringProperty(operator),
                        new SimpleStringProperty("撤回了一条信息")
                );

            }

            if (null == record) {
                return;
            }

            AppConstant.recordData.add(record);
            RecordController.cellData.add(record);
        });
    }


}
