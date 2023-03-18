package com.delores.medusa.controller.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author William
 * @date 4/29/21 10:18 AM
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class AuthenticationRequest {

    private String username;
    private String password;
}
