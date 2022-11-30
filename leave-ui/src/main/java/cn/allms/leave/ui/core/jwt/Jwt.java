package cn.allms.leave.ui.core.jwt;

import com.alibaba.fastjson.JSONObject;

import com.codingapi.springboot.framework.exception.LocaleMessageException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Jwt {

    private final Key key;
    private final int jwtTime;
    private final int jwtRestTime;

    public Jwt(String secretKey, int jwtTime, int jwtRestTime) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.jwtTime = jwtTime;
        this.jwtRestTime = jwtRestTime;
    }

    public Token create(long userId,String username){
        Token token = new Token(userId,username,jwtTime,jwtRestTime);
        String jwt = Jwts.builder().setSubject(token.toJson()).signWith(key).compact();
        token.setToken(jwt);
        return token;
    }

    public Token parser(String sign){
        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(sign);
            if (jws != null) {
                String subject = jws.getBody().getSubject();
                return JSONObject.parseObject(subject, Token.class);
            }
            throw new LocaleMessageException("token.expired");
        }catch (Exception exp){
            throw new LocaleMessageException("token.expired",exp);
        }
    }


}