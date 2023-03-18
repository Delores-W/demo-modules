package com.delores.medusa.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author William
 * @date 4/29/21 9:22 AM
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenModel implements Serializable {

    private static final long serialVersionUID = 6904068278017178099L;

    private String accessToken;

    private Long accessExpire;

}
