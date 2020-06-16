import org.junit.Test;
import redis.clients.jedis.Jedis;
import site.dunhanson.redis.utils.RedisUtils;

public class RedisTest {
    @Test
    public void redis() {
        Jedis jedis = RedisUtils.get();
        jedis.set("test", "hello world");
        System.out.println(jedis.get("test"));
    }
}
