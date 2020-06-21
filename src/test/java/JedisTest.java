import org.junit.Test;
import redis.clients.jedis.Jedis;
import site.dunhanson.redis.utils.JedisUtils;
import site.dunhanson.utils.basic.ObjectUtils;
import java.util.HashMap;
import java.util.Map;

public class JedisTest {
    @Test
    public void start() {
        Jedis jedis = JedisUtils.get();
        jedis.set("test", "hello world");
        System.out.println(jedis.get("test"));
    }

    @Test
    public void byteSetTest() {
        // value
        Map<String, Object> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ccc");
        // set
        byte[] key = "test".getBytes();
        byte[] value = ObjectUtils.toByteArray(map);
        String result = JedisUtils.get().set(key, value);
        System.out.println(result);
    }

    @Test
    public void byteSetExTest() {
        // value
        Map<String, Object> map = new HashMap<>();
        map.put("1", "aaa");
        map.put("2", "bbb");
        map.put("3", "ccc");
        // set
        byte[] key = "test".getBytes();
        byte[] value = ObjectUtils.toByteArray(map);
        int time = 10;
        Jedis jedis = JedisUtils.get();
        String result = jedis.setex(key, time, value);
        System.out.println(result);
    }

    @Test
    public void byteSetNxTest() {
        // value
        Map<String, Object> map = new HashMap<>();
        map.put("1", "aaa");
        map.put("2", "bbb");
        map.put("3", "ccc");
        // set
        byte[] key = "test".getBytes();
        byte[] value = ObjectUtils.toByteArray(map);
        Jedis jedis = JedisUtils.get();
        // nx
        Long result = jedis.setnx(key, value);
        System.out.println(result);
    }

    @Test
    public void byteGetTest() {
        byte[] key = "test".getBytes();
        byte[] value = JedisUtils.get().get(key);
        // get
        Map<String, Object> map = ObjectUtils.toEntity(value);
        System.out.println(map);
    }
}
