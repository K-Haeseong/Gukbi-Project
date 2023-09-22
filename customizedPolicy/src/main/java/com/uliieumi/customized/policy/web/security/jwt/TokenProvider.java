package com.uliieumi.customized.policy.web.security.jwt;

import com.uliieumi.customized.policy.web.security.Account;
import com.uliieumi.customized.policy.web.security.UserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;


@PropertySource("classpath:jwt.yml")
@Service
public class TokenProvider {
    private final String secretKey;

    private final long accessTokenExpirationHours;

    private final String issuer;

    public TokenProvider(
            @Value("${secret-key}") String secretKey,
            @Value("${access-token-expiration-hours}") long accessTokenExpirationHours,
            @Value("${issuer}") String issuer
    ) {
        this.secretKey = secretKey;
        this.accessTokenExpirationHours = accessTokenExpirationHours;
        this.issuer = issuer;
    }

    public String createAccessToken(Account account) {
        Map<String, String> claims = new HashMap<>();
        UserInfo userInfo = account.getUserInfo();
        claims.put("id", String.valueOf(userInfo.getId()));
        claims.put("name", userInfo.getName());
        claims.put("role", userInfo.getRole().name());
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .setSubject("jwt-userinfo")  // JWT 토큰 제목
                .setClaims(claims)
                .setIssuer(issuer)  // JWT 토큰 발급자
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
                .setExpiration(Date.from(Instant.now().plus(accessTokenExpirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                .compact(); // JWT 토큰 생성
    }


    public Map<String, String> validateTokenAndGetClaims(String token) {
        HashMap<String, String> claims = new HashMap<>();

        String id = (String) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id");

        String name = (String) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("name");

        String role = (String) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");

        claims.put("id", id);
        claims.put("name", name);
        claims.put("role", role);

        return claims;
    }
}