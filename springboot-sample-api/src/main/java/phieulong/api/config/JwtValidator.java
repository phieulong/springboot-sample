package phieulong.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final PublicKey publicKey;

    public Jws<Claims> validate(String token) {
        return Jwts.parser().verifyWith(publicKey).build().parseSignedClaims(token);
    }
}
