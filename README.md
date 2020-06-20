# Easy Redis

simplify redis operations

## Maven

```xml
<dependency>
  <groupId>site.dunhanson.redis</groupId>
  <artifactId>easy-redis</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Config

redis.yaml

```yaml
redis:
  type: single
  single:
    host: localhost
    port: 6379
    password:
  cluster:
    type: sentinel
    sentinel:
      masterName: mymaster
      password: bxkc2016
      hostAndPort:
        - 192.168.2.170:26377
        - 192.168.2.170:26378
        - 192.168.2.170:26379
```

## Start

```java
public class JedisTest {
    @Test
    public void jedis() {
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
        byte[] value = new Gson().toJson(map).getBytes();
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
        byte[] value = new Gson().toJson(map).getBytes();
        int time = 10;
        Jedis jedis = JedisUtils.get();
        String result = jedis.setex(key, time, value);
        System.out.println(result);
    }

    @Test
    public void byteGetTest() {
        byte[] key = "test".getBytes();
        byte[] value = JedisUtils.get().get(key);
        // get
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> map = new Gson().fromJson(new String(value), type);
        System.out.println(map);
    }
}
```

## references

https://github.com/xetorthio/jedis
