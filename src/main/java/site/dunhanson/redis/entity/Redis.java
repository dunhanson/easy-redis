package site.dunhanson.redis.entity;

import lombok.Data;

/**
 * @author dunhanson
 * 2020-04-22
 * redis
 */
@Data
public class Redis {
    /**集群类型**/
    private String type;
    /**单节点**/
    private Single single;
    /**集群**/
    private Cluster cluster;
}
