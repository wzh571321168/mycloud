package com.wang.gateway.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.wang.gateway.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <p>
 * jwt token工具类
 * </p>
 *
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 *
 * @author
 */
@Component
@Slf4j
public class JwtTokenUtil {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key从token中
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public void parseToken(String token) throws JwtException {

        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }


    /**
     *生成token(通过用户名和签名时候用的随机数)
     * @param appUser
     * @return
     */
  /*  public String generateToken(User appUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), getRandomKey(10));
        claims.put("userName", appUser.getUserName());
        claims.put("id", appUser.getId());
        claims.put("mobile", appUser.getMobile());
        return doGenerateToken(claims, appUser.getUserName());
    }*/

    /**
     * 生成token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + jwtProperties.getExpiration() * 1000);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()).compact();
    }

    /**
     * 获取混淆MD5签名用的随机字符串
     */
    /*public String getRandomKey(int length) {
        return ToolUtil.getRandomString(length);
    }

    *//**
     * 根据Token获取用户编号 name
     *//*
    public String getUserName(String token)  {
        DecodedJWT verifier = JWT.decode(token);
        Claim account = verifier.getClaim("name");
        return account.asString();
    }

    *//**
     * 根据Token获取用户编号 name
     *//*
    public String getAccount(String token)  {
        DecodedJWT verifier = JWT.decode(token);
        Claim account = verifier.getClaim("account");
        return account.asString();
    }

    *//**
     * 根据Token获取渠道编号 id
     *//*
    public Integer getUserId(String token)  {
        DecodedJWT verifier = JWT.decode(token);
        Claim userId = verifier.getClaim("id");
        return userId.asInt();
    }

    *//**
     * 根据Token获取手机号 phone
     *//*
    public String getMobile(String token) {
        DecodedJWT verifier = JWT.decode(token);
        Claim mobile = verifier.getClaim("phone");
        return mobile.asString();
    }

    *//**
     * 根据request获取userName
     *//*
    public String getUesrNameFromHeader(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        DecodedJWT verifier = JWT.decode(token);
        Claim userId = verifier.getClaim("name");
        return userId.asString();
    }

    *//**
     * 根据request获取账号
     *//*
    public String getAccountFromHeader(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        DecodedJWT verifier = JWT.decode(token);
        Claim userId = verifier.getClaim("account");
        return userId.asString();
    }

    *//**
     * 根据request获取userId
     *//*
    public Integer getUserIdFromHeader(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        DecodedJWT verifier = JWT.decode(token);
        Claim userId = verifier.getClaim("id");
        return userId.asInt();
    }

    *//**
     * 根据request获取mobile
     *//*
    public String getMobileFromHeader(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        DecodedJWT verifier = JWT.decode(token);
        Claim mobile = verifier.getClaim("phone");
        return mobile.asString();
    }

    *//**
     * 根据request获取enterprise_id
     *//*
    public Integer getEnterpriseIdFromHeader(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        log.info("JwtTokenUtil:getEnterpriseIdFromHeader:token:" + token);
        DecodedJWT verifier = JWT.decode(token);
        Claim enterpriseId = verifier.getClaim("enterprise_id");
        return enterpriseId.asInt();
    }

    *//**
     * 根据request获取role_id
     *//*
    public String getRoleIdFromHeader(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        DecodedJWT verifier = JWT.decode(token);
        Claim roleId = verifier.getClaim("role_id");
        return roleId.asString();
    }





    public void checkRole(HttpServletRequest request,String checkRole){
        //获取用户角色信息
        String token = request.getHeader(jwtProperties.getHeader());
        DecodedJWT verifier = JWT.decode(token);
        Claim roleId = verifier.getClaim("role_id");
        //判断权限是否正确
        if(!checkRole.equals(roleId.asString())){
            throw TybootErrorCodesException.REJECT_REQUEST(err->err.build());
        }
    }*/
}