# Easy Redis

简化redis操作

## 简单开始

```java
public class RedisTest {
    @Test
    public void start() {
        Jedis jedis = RedisUtils.get();
        jedis.set("test", "hello world");
        System.out.println(jedis.get("test"));
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
    <version>2020.0422.0624</version>
</dependency>
```

## 参考资料

[jedis](https://github.com/xetorthio/jedis)