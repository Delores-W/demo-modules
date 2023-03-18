package com.delores.medusa.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author William
 * @date 4/29/21 9:24 AM
 * @description
 */
@Data
public class AuthToken implements Serializable {
    private static final long serialVersionUID = 5419046852706072990L;

    private Long id;

    private Long userId;

    private String accessToken;

    private Long accessExpire;

    private Long tokenTimestamp;

    private Byte isActive=1;

    private Date createTime;

    private Date updateTime;

}
