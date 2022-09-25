package edu.wit.hbasemanager.common.jwt;


import io.jsonwebtoken.*;

import java.util.Date;

public class JwtHelper {

    //过期时间
    private static long tokenExpiration = 24*60*60*1000;
    //签名秘钥
    private static String tokenSignKey = "123456";

    //根据参数生成token
    public static String createToken(Long userId, String email,String role) {
        String token = Jwts.builder()
                .setSubject("hbase-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("email", email)
                .claim("role",role)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //根据token字符串得到用户id
    public static Long getUserId(String token) {
        if("".equals(token)||token==null) return null;

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    //根据token字符串得到邮箱
    public static String getEmail(String token) {
        if("".equals(token)||token==null) return "";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("email");
    }

    /**
     *  根据token获取权限
     * @param token
     * @return
     */
    public static String getRole(String token) {
        if("".equals(token)||token==null) return "";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("role");
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken(12L, "cc3389","admin");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getEmail(token));
    }
}

