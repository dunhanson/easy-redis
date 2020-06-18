package site.dunhanson.redis.utils;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dunhanson
 * 2020.03.20
 * YAML工具类
 */
public class YamlUtils {
    private static Map<String, Map<String, Object>> basicMap = new HashMap<>();

    /**
     * 加载YAML文件
     * @param path
     * @return Map<String, Object>
     */
    public static Map<String, Object> loadFile(String path) {
        Map<String, Object> map = basicMap.get(path);
        if(map == null) {
            Yaml yaml = new Yaml();
            try(InputStream inputStream = YamlUtils.class.getClassLoader().getResourceAsStream(path)) {
                basicMap.put(path, yaml.load(inputStream));
                map = basicMap.get(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取实体对象
     * @param clazz
     * @param path
     * @param keyArr
     * @param <T>
     * @return <T> T
     */
    public static <T> T getEntity(Class<T> clazz, String path, String...keyArr) {
        Map<String, Object> map = getMap(path, keyArr);
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(map), clazz);
    }

    /**
     * 获取Map对象
     * @param path
     * @param keyArr
     * @return Map<String, Object>
     */
    public static Map<String, Object> getMap(String path, String...keyArr) {
        Map<String, Object> map = YamlUtils.loadFile(path);
        for(int i = 0; i < keyArr.length; i++) {
            String key = keyArr[i];
            Object value = map.get(key);
            if(value instanceof Map) {
                map = (Map<String, Object>)value;
            }
        }
        return map;
    }

    /**
     * 获取Object
     * @param path
     * @param keyArr
     * @return Object
     */
    public static Object getValue(String path, String...keyArr) {
        Object value = null;
        Map<String, Object> map = YamlUtils.loadFile(path);
        for(int i = 0; i < keyArr.length; i++) {
            String key = keyArr[i];
            value = map.get(key);
            if(value instanceof Map) {
                map = (Map<String, Object>)value;
            }
        }
        return value;
    }

    /**
     * 获取String
     * @param path
     * @param keyArr
     * @return String
     */
    public static String getValueToString(String path, String...keyArr) {
        Object value = getValue(path, keyArr);
        return value == null ? null : (String)value;
    }

    /**
     * 获取List
     * @param path
     * @param keyArr
     * @return List<String>
     */
    public static List<String> getValueToList(String path, String...keyArr) {
        Object value = getValue(path, keyArr);
        return value == null ? null : (List<String>)value;
    }

    /**
     * 获取Integer
     * @param path
     * @param keyArr
     * @return Integer
     */
    public static Integer getValueToInteger(String path, String...keyArr) {
        Object value = getValue(path, keyArr);
        return value == null ? null : (Integer)value;
    }

    /**
     * 获取Double
     * @param path
     * @param keyArr
     * @return Double
     */
    public static Double getValueToDouble(String path, String...keyArr) {
        Object value = getValue(path, keyArr);
        return value == null ? null : (Double)value;
    }

    /**
     * 获取Long
     * @param path
     * @param keyArr
     * @return Long
     */
    public static Long getValueToLong(String path, String...keyArr) {
        Object value = getValue(path, keyArr);
        return value == null ? null : (Long)value;
    }

    /**
     * 获取Boolean
     * @param path
     * @param keyArr
     * @return Boolean
     */
    public static Boolean getValueToBoolean(String path, String...keyArr) {
        Object value = getValue(path, keyArr);
        return value == null ? null : (Boolean)value;
    }
}