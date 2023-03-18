package com.delores.medusa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author William
 * @date 4/30/21 2:09 PM
 * @description 承载自定义token信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto implements Serializable {

    private static final long serialVersionUID = -5060585447657560313L;

    private Long userId;

    private String userName;

    private Long timestamp;

    private String randomStr;

    private Long expireTime;
}
