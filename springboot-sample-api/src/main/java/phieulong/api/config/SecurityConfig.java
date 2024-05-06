package phieulong.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityProperties securityProperties;

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            handlerExceptionResolver.resolveException(request, response, null, authException);
        };
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter(JwtValidator jwtValidator, AuthenticationEntryPoint authenticationEntryPoint) {
        return new JwtTokenFilter(jwtValidator, authenticationEntryPoint);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationEntryPoint authenticationEntryPoint,
                                                   JwtTokenFilter jwtTokenFilter) throws Exception {
        http.cors().and().csrf().disable();

        for (String uri : securityProperties.getPublicUrls()) {
            http.authorizeHttpRequests().antMatchers(uri).permitAll();
        }

        for (SecurityProperties.ProtectedUrl url : securityProperties.getProtectedUrls()) {
            http.authorizeHttpRequests().antMatchers(url.getMethod(), url.getUrl()).authenticated();
        }

        http.authorizeHttpRequests().anyRequest().denyAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().defaultAuthenticationEntryPointFor(authenticationEntryPoint, AnyRequestMatcher.INSTANCE);

        return http.build();
    }
}
