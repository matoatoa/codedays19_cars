package de.matoatoa.demo.codedays19.cars.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Jan Hauer (EXXETA AG)
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(final PasswordEncoder encoder) {
        User.withUsername("admin").password("secret").roles("SALES", "SERVICE", "PROCUREMENT").build();
        UserDetails admin = User.withUsername("admin").password(encoder.encode("secret")).roles("SALES", "SERVICE", "PROCUREMENT").build();
        UserDetails service = User.withUsername("service").password(encoder.encode("password")).roles("SERVICE").build();
        UserDetails sales = User.withUsername("sales").password(encoder.encode("selas")).roles("SALES").build();
        UserDetails procurement = User.withUsername("procurement").password(encoder.encode("unknown")).roles("PROCUREMENT").build();
        return new MapReactiveUserDetailsService(admin, sales, service, procurement);
    }
}
