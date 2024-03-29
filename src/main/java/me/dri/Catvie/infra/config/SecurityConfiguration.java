package me.dri.Catvie.infra.config;

import me.dri.Catvie.domain.consts.EndpointsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console())
                        .disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.POST, EndpointsConstants.ENDPOINT_AUTH + "/**")).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/v3/api-docs/**")).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/swagger-ui/**")).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/v2/api-docs/**")).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/swagger-resources/**")).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/oauth2/authorization/github")).permitAll()
                        .requestMatchers(mvc.pattern(HttpMethod.POST,  EndpointsConstants.ENDPOINT_FILMS+ "/create")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern(HttpMethod.DELETE,   EndpointsConstants .ENDPOINT_FILMS+ "/deleteById/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


