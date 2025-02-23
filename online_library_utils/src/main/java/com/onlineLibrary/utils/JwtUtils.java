package com.onlineLibrary.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 生成和解析共用的密钥
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 生成JWT令牌的方法，接受一个Map作为参数
    public static String generateJwt(Map<String, Object> claimsMap) {
        // 设置JWT的过期时间为2小时
        Date expiration = new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000); // 2小时

        // 创建并返回JWT令牌
        return Jwts.builder()
                .setClaims(claimsMap)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 解析JWT令牌的方法
    public static Jws<Claims> parseJwt(String token) {
        try {
            // 解析JWT并返回claims
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            // 如果JWT验证失败或过期，可以处理异常
            System.out.println("~无效的JWT令牌或令牌已过期~");
            return null;
        }
    }
}
