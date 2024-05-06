package phieulong.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final SecurityProperties securityProperties;
    @Bean
    public PublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(securityProperties.getJwtPublicKey()));
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}
