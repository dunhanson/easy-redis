import org.junit.Test;
import redis.clients.jedis.Jedis;
import site.dunhanson.redis.utils.JedisUtils;
import site.dunhanson.utils.basic.ObjectUtils;
import java.util.HashMap;
import java.util.Map;

public class JedisTest {
    @Test
    public void start() {
        try (Jedis jedis = JedisUtils.get()){
            jedis.set("test", "hello world");
            System.out.println(jedis.get("test"));
        }
    }

    @Test
    public void byteSetTest() {
        try (Jedis jedis = JedisUtils.get()){
            // value
            Map<String, Object> map = new HashMap<>();
            map.put("a", "aaa");
            map.put("b", "bbb");
            map.put("c", "ccc");
            // set
            byte[] key = "test".getBytes();
            byte[] value = ObjectUtils.toByteArray(map);
            String result = jedis.set(key, value);
            System.out.println(result);
        }
    }

    @Test
    public void byteSetExTest() {
        try (Jedis jedis = JedisUtils.get()){
            // value
            Map<String, Object> map = new HashMap<>();
            map.put("1", "aaa");
            map.put("2", "bbb");
            map.put("3", "ccc");
            // set
            byte[] key = "test".getBytes();
            byte[] value = ObjectUtils.toByteArray(map);
            int time = 10;
            String result = jedis.setex(key, time, value);
            System.out.println(result);
        }
    }

    @Test
    public void byteSetNxTest() {
        try (Jedis jedis = JedisUtils.get()){
            // value
            Map<String, Object> map = new HashMap<>();
            map.put("1", "aaa");
            map.put("2", "bbb");
            map.put("3", "ccc");
            // set
            byte[] key = "test".getBytes();
            byte[] value = ObjectUtils.toByteArray(map);
            // nx
            Long result = jedis.setnx(key, value);
            System.out.println(result);
        }
    }

    @Test
    public void byteGetTest() {
        try (Jedis jedis = JedisUtils.get()){
            byte[] key = "test".getBytes();
            byte[] value = jedis.get(key);
            // get
            Map<String, Object> map = ObjectUtils.toEntity(value);
            System.out.println(map);
        }
    }
}
