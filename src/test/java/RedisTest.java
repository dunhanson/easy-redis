import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;
import site.dunhanson.redis.utils.RedisUtils;
import site.dunhanson.redis.utils.RedissonUtils;
import java.util.concurrent.TimeUnit;

public class RedisTest {
    @Test
    public void redis() {
        Jedis jedis = RedisUtils.get();
        jedis.set("test", "hello world");
        System.out.println(jedis.get("test"));
    }

    @Test
    public void redisson() {
        RedissonClient client = RedissonUtils.getRedisClient();
        RLock lock = client.getLock("test");
        lock.lock(1, TimeUnit.SECONDS);
        //do something
        lock.unlock();
    }
}
