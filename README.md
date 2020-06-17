# Easy Redis

简化redis操作

## 简单开始

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

## 配置文件

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

## Maven

依赖配置

``仅限公司内部项目配置生效``

```xml
<dependency>
    <groupId>site.dunhanson.redis</groupId>
    <artifactId>easy-redis</artifactId>
    <version>2020.0608.2256</version>
</dependency>
```

依赖关联

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.2.0</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.30</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.30</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.9</version>
</dependency>
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.26</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.6</version>
</dependency>
```

## 参考资料

https://github.com/xetorthio/jedis
