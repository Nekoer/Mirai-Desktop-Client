package com.hcyacg.miraipc.utils;


import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/8/21 20:58
 */
public class StageManager {


        /**
         * ��������
         */
        private static final Map<String, Stage> stageMap = new ConcurrentHashMap<String, Stage>();

        /**
         * ����key���Scene
         * @param key
         * @param stage
         */
        public static void put(String key, Stage stage) {
            if (StringUtils.isEmpty(key)) {
                throw new RuntimeException("key��Ϊ��!");
            }
            if (Objects.isNull(stage)) {
                throw new RuntimeException("scene��Ϊ��!");
            }
            stageMap.put(key,stage);
        }

        /**
         * ����key��ȡScene
         * @param key
         * @return
         */
        public static Stage getStage(String key) {
            if (StringUtils.isEmpty(key)) {
                throw new RuntimeException("key��Ϊ��!");
            }
            return stageMap.get(key);
        }

}