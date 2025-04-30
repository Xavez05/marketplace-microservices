package com.milicom.auth_service.util;

import com.milicom.auth_service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private PrivateKey privateKey;

    @Value("${auth.jwt.private-key-path}")
    private String privateKeyPath;

    @Value("${auth.jwt.issuer}")
    private String issuer;

    @Value("${auth.jwt.expiration-ms}")
    private Long expirationTime;

    @PostConstruct
    public void loadPrivateKey() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(privateKeyPath)) {
            if (inputStream == null) {
                throw new RuntimeException("No se encontró el archivo de llave privada en: " + privateKeyPath);
            }
            String key = new String(inputStream.readAllBytes())
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = kf.generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando la llave privada", e);
        }
    }

    public String generateToken(User user) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getEmail())
                .issuer(issuer)
                .claim("user_id", user.getId())
                .claim("name", user.getFirstName() + " " + user.getLastName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationTime)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }
}