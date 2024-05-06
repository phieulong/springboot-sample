package phieulong.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Configuration
@ConfigurationProperties("app.security")
public class SecurityProperties {

    private Set<String> publicUrls = new HashSet<>();
    private Set<ProtectedUrl> protectedUrls = new HashSet<>();
    private String jwtPublicKey;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class ProtectedUrl {
        private HttpMethod method;
        private String url;
    }
}
