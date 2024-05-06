package phieulong.api.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(value = "test.app.security.enabled", havingValue = "true")
public class TokenUtil {
    private final PrivateKey privateKey;

    static final Map<String, Object> DEFAULT_JWT_PAYLOAD = new HashMap<>(){{put("role", "CUSTOMER");}};
    public static final String DEFAULT_USER_ID = "1";

    protected static final int DEFAULT_TTL_MILLIS = 300_000;


    public TokenUtil(TestTokenProperties testTokenProperties) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(testTokenProperties.getJwtPrivateKey()));
        this.privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public String createDefaultJwt() {
        return createJWT(
                DEFAULT_USER_ID,
                DEFAULT_JWT_PAYLOAD,
                DEFAULT_TTL_MILLIS);
    }

    public String createDefaultJwt(String userId, Map<String, Object> payload) {
        return createJWT(
                userId,
                payload,
                DEFAULT_TTL_MILLIS);
    }

    public String createJWT(String id, Map<String, Object> jwtPayload, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().subject(id)
                .issuedAt(now)
                .claims(jwtPayload)
                .signWith(privateKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.expiration(exp);
        }

        return builder.compact();
    }

}
