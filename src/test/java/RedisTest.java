import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Jedis;
import site.dunhanson.redis.utils.RedisUtils;
import site.dunhanson.redis.utils.RedissonUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class RedisTest {

    @Test
    public void start() {
        Jedis jedis = RedisUtils.get();
        jedis.set("test", "hello world");
        System.out.println(jedis.get("test"));
    }

    @Test
    public void redisson() {
        String[] addresses = {
                "redis://192.168.2.170:26377",
                "redis://192.168.2.170:26378",
                "redis://192.168.2.170:26379"
        };
        Config config = new Config();
        config.useSentinelServers()
              .setMasterName("mymaster")
              .addSentinelAddress(addresses)
              .setPassword("bxkc2016");
        RedissonClient client = Redisson.create(config);
        String key = "test";
        //设置锁
        RLock lock = client.getLock("key");
        lock.lock(1, TimeUnit.SECONDS);
        //
        //do something
        //
        //解锁
        lock.unlock();
    }

    @Test
    public void redissonYaml() {
        RedissonClient client = RedissonUtils.getRedisClient();
        String key = "test";
        //设置锁
        RLock lock = client.getLock(key);
        lock.lock(1, TimeUnit.SECONDS);
        //
        //do something
        //
        //解锁
        lock.unlock();
    }

}
