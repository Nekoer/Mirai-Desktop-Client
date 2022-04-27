package com.hcyacg.miraipc.load;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    private final static String PROPERTIES_NAME = "net.mamoe.mirai.console.plugin.jvm.JvmPlugin";
    private final static String MAIN_CLASS = "mainClass";

    /**
     * 加载jar文件
     *
     * @param jarFilePath jar文件路径
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void loadJar(String jarFilePath) throws IOException, ClassNotFoundException {
        getClassLoader(jarFilePath);
    }

    /**
     * 获得ClassLoader
     *
     * @param jarFilePath jar文件路径
     * @return
     * @throws MalformedURLException
     */
    private static void getClassLoader(String jarFilePath){
        try{
            File item = new File(jarFilePath);
            URL url = item.toURI().toURL();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, null);
            JarFile jarFile = new JarFile(jarFilePath);
            Enumeration<?> fileList = jarFile.entries();
            while (fileList.hasMoreElements()) {
                JarEntry entry = (JarEntry) fileList.nextElement();
                String name = entry.getName();
                if (name.contains(PROPERTIES_NAME)){
                    InputStream inputStream = jarFile.getInputStream(entry);
                    String mainClass = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    urlClassLoader.loadClass(mainClass);
                }else if(name.contains(".class")){
                    urlClassLoader.loadClass(name);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
