package site.dunhanson.redis.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.util.Pool;
import site.dunhanson.redis.entity.Cluster;
import site.dunhanson.redis.entity.Redis;
import site.dunhanson.redis.entity.Sentinel;
import site.dunhanson.redis.entity.Single;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dunhanson
 * @date 2020.03.20
 * @description redis工具类
 */
public class RedisUtils {
    /**Gson对象**/
    private static final Gson gson = new Gson();
    /**yaml配置信息**/
    private static final Map<String, Object> yamlMap = YamlUtils.getMap("redis.yaml", "redis");
    /**Redis配置信息**/
    private static Redis redis = gson.fromJson(gson.toJson(yamlMap), Redis.class);
    /**库池MAP**/
    private static Map<String, Pool<Jedis>> poolMap = new ConcurrentHashMap<>();

    /**
     * 单节点
     * @param single
     * @return
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
     * @return
     */
    private static Jedis getSentinel(Sentinel sentinel) {
        String masterName = sentinel.getMasterName();
        Set<String> sentinels = sentinel.getHostAndPort();
        String password = sentinel.getPassword();
        Pool<Jedis> pool = poolMap.get("sentinel");
        if(pool == null) {
            pool = new JedisSentinelPool(masterName, sentinels, password);
            poolMap.put("sentinel", pool);
        }
        return pool.getResource();
    }

    /**
     * 获取
     * @return
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
