package com.delores.medusa.service;

import com.delores.medusa.controller.request.AuthenticationRequest;
import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.LogicalException;
import com.delores.medusa.exception.TechnicalException;
import com.delores.medusa.model.AuthTokenModel;
import com.delores.medusa.model.response.BaseResponse;
import io.jsonwebtoken.Claims;

/**
 * @author William
 * @date 4/29/21 12:33 PM
 * @description
 */
public interface AuthService {

    AuthTokenModel authAndCreateTokenDB(AuthenticationRequest authenticationRequest) throws BaseMedusaException;

    BaseResponse<String> validateTokenDB(String accessToken) throws BaseMedusaException;

    AuthTokenModel authAndCreateTokenRedis(AuthenticationRequest authenticationRequest) throws BaseMedusaException;

    BaseResponse<String> validateTokenRedis(String accessToken) throws BaseMedusaException;

    AuthTokenModel authAndCreateTokenJwt(AuthenticationRequest authenticationRequest) throws BaseMedusaException;

    BaseResponse<String> validateTokenJwt(String accessToken) throws BaseMedusaException;

    AuthTokenModel authAndCreateTokenJwtRedis(AuthenticationRequest authenticationRequest) throws BaseMedusaException;

    BaseResponse<String> validateTokenJwtRedis(String accessToken) throws BaseMedusaException;
}
