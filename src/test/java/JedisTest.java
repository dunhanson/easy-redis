import org.junit.Test;
import redis.clients.jedis.Jedis;
import site.dunhanson.redis.utils.JedisUtils;

public class JedisTest {
    @Test
    public void redis() {
        Jedis jedis = JedisUtils.get();
        jedis.set("test", "hello world");
        System.out.println(jedis.get("test"));
    }
}
