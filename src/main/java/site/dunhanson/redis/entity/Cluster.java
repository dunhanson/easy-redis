package site.dunhanson.redis.entity;

import lombok.Data;

/**
 * @author dunhanson
 * 2020-04-22
 * 集群信息
 */
@Data
public class Cluster {
    /**集群类型**/
    private String type;
    /**哨兵信息**/
    private Sentinel sentinel;
}
