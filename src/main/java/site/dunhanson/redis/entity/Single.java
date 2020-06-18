package site.dunhanson.redis.entity;

import lombok.Data;

/**
 * @author dunhanson
 * 2020-04-22
 * 单节点信息
 */
@Data
public class Single {
    /**地址**/
    private String host;
    /**端口**/
    private Integer port;
    /**密码**/
    private String password;
}
