package site.dunhanson.redis.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.util.Pool;
import site.dunhanson.redis.entity.*;
import site.dunhanson.utils.basic.YamlUtils;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dunhanson
 * 2020.03.20
 * redis工具类
 */
public class JedisUtils {
    /**配置文件路径**/
    private static String configPath = "redis.yaml";
    /**Redis配置信息**/
    private static Redis redis = YamlUtils.load(configPath, Redis.class,"redis");
    /**库池MAP**/
    private static Map<String, Pool<Jedis>> poolMap = new ConcurrentHashMap<>();
    private static String SINGLE = "single";
    private static String SENTINEL = "sentinel";

    /**
     * 设置configPath
     * @param configPath
     */
    public static void setConfigPath(String configPath) {
        JedisUtils.configPath = configPath;
    }

    /**
     * 单节点
     * @param single
     * @return Jedis
     */
    private static Jedis getSingle(Single single, PoolConfig poolConfig) {
        Pool<Jedis> pool = poolMap.get(SINGLE);
        if(pool == null) {
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            String host = single.getHost();
            int port = single.getPort();
            int timeout = single.getTimeout();
            String password = single.getPassword();
            // 复制属性,忽略空值
            CopyOptions options = CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true);
            BeanUtil.copyProperties(poolConfig, genericObjectPoolConfig, options);
            // new pool
            pool = new JedisPool(genericObjectPoolConfig, host, port, timeout, password);
            poolMap.put(SINGLE, pool);
        }
        return pool.getResource();
    }

    /**
     * 哨兵
     * @param sentinel
     * @return Jedis
     */
    private static Jedis getSentinel(Sentinel sentinel, PoolConfig poolConfig) {
        String masterName = sentinel.getMasterName();
        Set<String> sentinels = sentinel.getHostAndPort();
        String password = sentinel.getPassword();
        Pool<Jedis> pool = poolMap.get(SENTINEL);
        if(pool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            // 复制属性,忽略空值
            CopyOptions options = CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true);
            BeanUtil.copyProperties(poolConfig, jedisPoolConfig, options);
            // new pool
            pool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig, password);
            poolMap.put(SENTINEL, pool);
        }
        return pool.getResource();
    }

    /**
     * 获取Jedis
     * @return Jedis
     */
    public static Jedis get() {
        //类型（单节点&集群）
        String type = redis.getType();
        //单节点
        if(type.equals(SINGLE)) {
            return getSingle(redis.getSingle(), redis.getPoolConfig());
        }
        //集群
        Cluster cluster = redis.getCluster();
        type = cluster.getType();
        //哨兵
        if(type.equals(SENTINEL)) {
            return getSentinel(cluster.getSentinel(), redis.getPoolConfig());
        }
        return null;
    }
}
