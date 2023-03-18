package com.delores.security.security;

import com.delores.base.exception.TechnicalException;
import com.delores.base.utils.Constant;
import com.delores.base.utils.enums.StatusCode;
import com.google.common.base.Strings;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author William
 * @date 5/13/21 2:10 AM
 * @description
 */
@Component
public class TokenUtil {

    public String verifyToken(HttpServletRequest request) {
        final String token = request.getHeader(Constant.AUTH_HEADER_NAME);

//        if (!Strings.isNullOrEmpty(token)){
//            final TokenUser user = parseUserFromToken(token.replace("Bearer","").trim());
//            if (user != null) {
//                return  Optional.of(new UserAuthentication(user));
//            }
//        }
        if (!Strings.isNullOrEmpty(token)) {
            return parseUserFromToken(token);
        }
        return null;

    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(Constant.TOKEN_AUTH_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    // generate Token
    public static String generateJWT(final String subject, final Long expireMills) {

        // 定义生成秘钥的算法
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

        //定义生成签名的密钥
        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                // .setId(id)
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

    public static Claims parseJWT(final String accessToken) throws AuthenticationException {
        try {
            SecretKey key = generalKey();
            return Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("User credentials have expired");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseUserFromToken(String accessToken) {
        accessToken = accessToken.replace("Bearer","").trim();
        return parseJWT(accessToken).getSubject();
    }

//    public static String generateJWTWithoutExp(String id, String name) {
//        // 定义生成秘钥的算法
//        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
//
//        //定义生成签名的密钥
//        SecretKey key = generalKey();
//
//        JwtBuilder builder = Jwts.builder()
//                .setId(id)
//                .setSubject(name)
//                .setIssuer(Constant.TOKEN_ISSUER)
//                .setIssuedAt(DateTime.now().toDate())
//                // 签发时指定    加密算法    秘钥
//                .signWith(algorithm, key);
//
//        return builder.compact();
//    }
}
