package site.dunhanson.redis.utils;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;

/**
 * @author dunhanson
 * 2020-06-17
 * 对象工具类
 */
public class ObjectUtils {
    /**
     * 对象转二进制
     * @param object
     * @return byte[]
     * @throws IOException
     */
    public static byte[] toByteArray(Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制转对象
     * @param bytes
     * @return Object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object toObject(byte[] bytes) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制转对象
     * @param bytes
     * @param type
     * @param <T>
     * @return <T> T
     */
    public static <T> T toEntity(byte[] bytes, Type type) {
        Object object = toObject(bytes);
        Gson gson = GsonUtils.gson;
        String json = gson.toJson(object);
        return gson.fromJson(json, type);
    }

    /**
     * 二进制转对象
     * @param bytes
     * @param clazz
     * @param <T>
     * @return <T> T
     */
    public static <T> T toEntity(byte[] bytes, Class<T> clazz) {
        Object object = toObject(bytes);
        Gson gson = GsonUtils.gson;
        String json = gson.toJson(object);
        return gson.fromJson(json, clazz);
    }
}
