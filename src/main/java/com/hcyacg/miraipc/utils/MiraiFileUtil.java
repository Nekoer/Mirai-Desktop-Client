package com.hcyacg.miraipc.utils;

import com.hcyacg.miraipc.constant.AppConstant;

import java.io.*;
import java.util.Arrays;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/9/3 14:00
 */
public class MiraiFileUtil {

    static {
        if (!AppConstant.errorLogFile.exists()){
            try {
                AppConstant.errorLogFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!AppConstant.botLogFile.exists()){
            try {
                AppConstant.botLogFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!AppConstant.netLogFile.exists()){
            try {
                AppConstant.netLogFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public static void error(Exception exception){

        try {
            FileWriter out =new FileWriter(AppConstant.errorLogFile,true);
            StackTraceElement[] stackTrace = exception.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace){
                out.append("\n");
                out.append(stackTraceElement.toString());
            }
            out.flush();
            out.close();
        }catch (Exception e){
            error(e);
        }
    }

    public static void botLog(String content){
        try {
            FileWriter out =new FileWriter(AppConstant.botLogFile,true);
            out.append("\n");
            out.append(content);
            out.flush();
            out.close();
        }catch (Exception e){
            error(e);
        }
    }

    public static void netLog(String content){
        try {
            FileWriter out =new FileWriter(AppConstant.netLogFile,true);
            out.append("\n");
            out.append(content);
            out.flush();
            out.close();
        }catch (Exception e){
            error(e);
        }
    }

}
