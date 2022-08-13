package com.saktaWdi.MyWebApp.utils;

import com.saktaWdi.MyWebApp.model.entity.Admin;
import com.saktaWdi.MyWebApp.model.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JWTUtil {
    /**
     * 过期时间：三天
     */
    private  static final long EXPIRE=6000*60*24*3;
    /**
     * 加密密钥
     */
    private static final String SECRET_KEY="cnwdi:190306";
    /**
     * token前缀
     */
    private  static final String TOKEN_PREFIX="sakta_wdi";
    /**
     * subject
     */
    private  static final String SUBJECT="cnwdi:666";

    public static String getJsonToken(Users user){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        String dateStr = formatter.format(user.getCreateTime());
        String token= Jwts.builder().setSubject(SUBJECT)
                .claim("id",user.getId()+190306)
                .claim("num",user.getNum())
                .claim("vip",user.getVip())
                .claim("name",user.getName())
                .claim("avatarUrl",user.getAvatarUrl())
                .claim("createTime",dateStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
        token=TOKEN_PREFIX+token;
        return token;
    }

    public static String getAdminToken(Admin admin){
        String token=Jwts.builder().setSubject(SUBJECT)
                .claim("id",admin.getId())
                .claim("num",admin.getNum())
                .claim("weight",admin.getWeight())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
        token="admin"+token;
        return token;
    }


    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try {
            Claims claim=Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claim;
        }catch(Exception e){
            return null;
        }
    }

}
