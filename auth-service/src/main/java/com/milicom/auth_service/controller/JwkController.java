package com.milicom.auth_service.controller;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

@RestController
public class JwkController {

    @Value("${auth.jwt.public-key-path}")
    private String publicKeyPath;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getJwks() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(publicKeyPath)) {
            if (inputStream == null) {
                throw new RuntimeException("No se encontró el archivo de llave pública en: " + publicKeyPath);
            }
            String publicKeyContent = new String(inputStream.readAllBytes())
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(publicKeyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) publicKey)
                    .keyID("auth-service-key") // Puedes poner un kid personalizado
                    .build();

            return new JWKSet(rsaKey).toJSONObject();
        } catch (Exception e) {
            throw new RuntimeException("Error generando JWKS", e);
        }
    }
}