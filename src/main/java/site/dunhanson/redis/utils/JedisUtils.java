package site.dunhanson.redis.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
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
    private static Jedis getSingle(Single single) {
        Jedis jedis = new Jedis(single.getHost(), single.getPort());
        String password = single.getPassword();
        if(StringUtils.isNoneBlank(password)) {
            jedis.auth(password);
        }
        return jedis;
    }

    /**
     * 哨兵
     * @param sentinel
     * @return Jedis
     */
    private static Jedis getSentinel(Sentinel sentinel) {
        String masterName = sentinel.getMasterName();
        Set<String> sentinels = sentinel.getHostAndPort();
        String password = sentinel.getPassword();
        Pool<Jedis> pool = poolMap.get("sentinel");
        if(pool == null) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            //复制属性,忽略空值
            CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true);
            BeanUtil.copyProperties(sentinel.getPoolConfig(), poolConfig, copyOptions);
            pool = new JedisSentinelPool(masterName, sentinels, poolConfig, password);
            poolMap.put("sentinel", pool);
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
        if(type.equals("single")) {
            return getSingle(redis.getSingle());
        }
        //集群
        Cluster cluster = redis.getCluster();
        type = cluster.getType();
        //哨兵
        if(type.equals("sentinel")) {
            return getSentinel(cluster.getSentinel());
        }
        return null;
    }
}
