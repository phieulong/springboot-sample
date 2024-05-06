package phieulong.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    private final JwtValidator jwtValidator;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getToken(request);
            if (StringUtils.isEmpty(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            final Jws<Claims> tokenParsed = jwtValidator.validate(token);
            final String userId = tokenParsed.getPayload().getSubject();
            final String role = (String) tokenParsed.getPayload().get("role");

            final List<SimpleGrantedAuthority> roles = singletonList(new SimpleGrantedAuthority(role));
            UserDetails user = new User(userId, "", roles);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Error: ", e);
            authenticationEntryPoint.commence(request, response, new BadCredentialsException(format("Invalid JWT, %s", e.getMessage()), e));
        }
    }

    private String getToken(HttpServletRequest request) {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(token);
        if (StringUtils.isNotEmpty(token) && StringUtils.startsWithIgnoreCase(token, JWT_TOKEN_PREFIX)) {
            return token.replace(JWT_TOKEN_PREFIX, "");
        }
        return null;
    }
}
