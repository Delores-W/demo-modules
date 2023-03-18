package com.delores.medusa.utils;

import com.delores.medusa.exception.TechnicalException;
import com.delores.medusa.model.Constant;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author William
 * @date 5/5/21 1:31 AM
 * @description
 */
public class JwtUtil {

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(Constant.TOKEN_AUTH_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    // generate Token
    public static String generateJWT(final String id, final String subject, final Long expireMills) {

        // 定义生成秘钥的算法
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

        //定义生成签名的密钥
        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer(Constant.TOKEN_ISSUER)
                .setIssuedAt(DateTime.now().toDate())
                // 签发时指定    加密算法    秘钥
                .signWith(algorithm, key);

        if (expireMills >= 0) {
            long realExpireTime = System.currentTimeMillis() + expireMills;
            builder.setExpiration(new Date(realExpireTime));
        }

        return builder.compact();
    }

    //验证解析token
    public static Claims validateJWT(final String accessToken) throws TechnicalException {
        Claims claims;
        try {
            claims = parseJWT(accessToken);
            // response.setData(claims);
        } catch (ExpiredJwtException e) {
            throw new TechnicalException(StatusCode.TokenValidateExpireToken);
        } catch (Exception e) {
            throw new TechnicalException(StatusCode.TokenValidateCheckFail);
        }
        return claims;
    }

    public static Claims parseJWT(final String accessToken) {
        SecretKey key = generalKey();
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(accessToken)
                .getBody();
    }

    public static String parseUserFromToken(final String accessToken) {
        return parseJWT(accessToken).getSubject();
    }

    public static String generateJWTWithoutExp(String id, String name) {
        // 定义生成秘钥的算法
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

        //定义生成签名的密钥
        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(name)
                .setIssuer(Constant.TOKEN_ISSUER)
                .setIssuedAt(DateTime.now().toDate())
                // 签发时指定    加密算法    秘钥
                .signWith(algorithm, key);

        return builder.compact();
    }
}
