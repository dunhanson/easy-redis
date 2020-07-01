package site.dunhanson.redis.entity;

import lombok.Data;

@Data
public class PoolConfig {
    /**最大连接数**/
    private Integer maxTotal;
    /**最大空闲连接数**/
    private Integer maxIdle;
    /**最少空闲连接数**/
    private Integer minIdle;
    /**当资源池用尽后，调用者是否要等待**/
    private Boolean blockWhenExhausted;
    /**当资源池连接用尽后，调用者的最大等待时间（单位为毫秒）**/
    private Integer maxWaitMillis;
    /**向资源池借用连接时是否做连接有效性检测（ping）。检测到的无效连接将会被移除**/
    private Boolean testOnBorrow;
    /**向资源池归还连接时是否做连接有效性检测（ping）。检测到无效连接将会被移除**/
    private Boolean testOnReturn;
    /**是否开启JMX监控**/
    private Boolean jmxEnabled;
    /**是否开启空闲资源检测**/
    private Boolean testWhileIdle;
    /**空闲资源的检测周期（单位为毫秒）**/
    private Integer timeBetweenEvictionRunsMillis;
    /**资源池中资源的最小空闲时间（单位为毫秒），达到此值后空闲资源将被移除*/
    private Integer minEvictableIdleTimeMillis;
    /**做空闲资源检测时，每次检测资源的个数**/
    private Integer numTestsPerEvictionRun;
}
