package com.hcyacg.miraipc.load;


import com.hcyacg.miraipc.Launcher;
import com.hcyacg.miraipc.constant.AppConstant;
import com.hcyacg.miraipc.entity.PluginInfo;
import com.hcyacg.miraipc.utils.MiraiFileUtil;
import com.hcyacg.miraipc.utils.MiraiLogUtil;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * @Author: Nekoer
 * @Desc: 插件加载器
 * @Date: 2021/8/24 13:04
 */
public class JavaPluginLoad {
    private static final String systemPath = System.getProperty("user.dir");
    private static final File pluginsDirectory =
            new File(systemPath + File.separator + "plugins");

    static {



        if (!pluginsDirectory.exists()) {
            pluginsDirectory.mkdirs();
        }
        MiraiLogUtil.verbose("插件", "正在加载插件中,请稍后……");

        File[] tempList = pluginsDirectory.listFiles();


        int num = 0;
//        this.loadPlugin();
        if (null != tempList && tempList.length > 0) {
            for (File item : tempList) {
                try{
//                    ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
//                    moduleClassLoader.loadJar(item.toURI().toURL());
                    PluginLoader.loadJar(item.getPath());
                }catch (IOException | ClassNotFoundException e){
                    e.printStackTrace();
                }
//                String packageName = item.getName().replace(".jar","");
//
//                if (item.isFile()) {
//                    try {
//                        JarFile jarFile = new JarFile(item);
////                        URL url = item.toURI().toURL();
//                        URL[] urls = new URL[]{item.toURI().toURL()};
//
//                        URLClassLoader classLoader = new URLClassLoader(urls);
//
//                        Enumeration<?> fileList = jarFile.entries();
//
//                        while (fileList.hasMoreElements()) {
//                            JarEntry entry = (JarEntry) fileList.nextElement();
//                            String name = entry.getName();
////                            System.out.println(packageName);
////                            System.out.println(name.replace("/", "."));
//
//                            if (name.endsWith(".class") && name.replace("/", ".").contains(packageName)) {
//                                String fileName = name.replace("/", ".");
//                                int n = 6;
//                                String ultimaName = fileName.substring(0, name.length() - n);
//                                System.out.println(ultimaName);
//                                Class<?> cls = null;
//                                try {
//                                    cls = classLoader.loadClass(ultimaName);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    MiraiFileUtil.error(e);
//                                    continue;
//                                }
////                                    System.out.println(cls);
////                            cls.getDeclaredField("isEnable").isAccessible = true
//                                Field[] fields = cls.getFields();
//                                for (Field field : fields) {
//                                    field.setAccessible(true);
//                                }
//
//
////                                Boolean isLive = false;
////
////                                for (PluginInfo i : AppConstant.pluginData) {
////                                    if (i.getClassFileName().contains(cls.getName())) {
////                                        isLive = true;
////                                        break;
////                                    }
////                                }
////                                if (!isLive) {
////                                    Method[] methods = cls.getMethods();
////                                    for (Method method : methods) {
////                                        method.setAccessible(true);
//////                                        System.out.println(method.getName());
////                                        if (method.getName().contains("onDisable") || method.getName().contains("onEnable") || method.getName().contains("getPluginData")) {
////
////                                            Constructor<?> declaredConstructor = cls.getDeclaredConstructor();
////                                            declaredConstructor.setAccessible(true);
////
////                                            Object obj = null;
////                                            try {
//////                                                System.out.println(declaredConstructor);
////                                                obj = declaredConstructor.newInstance();
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                                MiraiFileUtil.error(e);
////                                                continue;
////                                            }
////                                            if (method.getName().contains("getPluginData")) {
////
////
//////                                                PluginData pluginData = (PluginData) method.invoke(obj);
//////                                                byte[] bytes = IOUtils.toByteArray(null == pluginData.getCover()? Launcher.class.getResource("/com/hcyacg/miraipc/image/template.png").openStream(): pluginData.getCover());
//////
//////                                                PluginInfo pluginInfo = new PluginInfo(
//////                                                        num,
//////                                                        new SimpleStringProperty(pluginData.isEnable.toString()),
//////                                                        new SimpleStringProperty(pluginData.name),
//////                                                        new SimpleStringProperty(pluginData.version),
//////                                                        new SimpleStringProperty(pluginData.author),
//////                                                        Base64.getEncoder()
//////                                                                .encodeToString(bytes),
//////                                                        new SimpleStringProperty(pluginData.warehouse),
//////                                                        new SimpleStringProperty(pluginData.description),
//////                                                        pluginData.getClasspath(),
//////                                                        cls.getName(),
//////                                                        pluginData.classLoader,
//////                                                        null
//////                                                );
//////                                                MiraiLogUtil.verbose("插件", "插件" + pluginInfo.getName() + "-v" + pluginInfo.getVersion() + "已加载");
//////                                                AppConstant.pluginData.add(pluginInfo);
//////                                                num += 1;
//////                                                System.out.println(pluginInfo);
//////                                                    System.out.println(classLoader);
//////                                                    System.out.println(cls);
////
////                                            }else {
////                                                method.invoke(obj);
////                                            }
//////                                            method = null;
////
////                                        }
////
////
////                                    }
////                                    methods = null;
////                                }
//
////                                obj = null;
//                                cls = null;
//                            }
//                            entry.clone();
//                        }
//                        jarFile.close();
//                        classLoader.close();
//                    } catch (Exception e) {
//                        MiraiFileUtil.error(e);
//                        e.printStackTrace();
//                        MiraiLogUtil.error("插件", Arrays.toString(e.getStackTrace()));
//
//                    } finally {
//                        System.gc();
//                    }
//
//                }
            }
        }

        MiraiLogUtil.verbose("插件", "插件加载完毕,共加载" + AppConstant.pluginData.size() + "个");
    }
}