package phieulong.api.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
@ConfigurationProperties("test.app.security")
public class TestTokenProperties {
    private String jwtPrivateKey;
}


