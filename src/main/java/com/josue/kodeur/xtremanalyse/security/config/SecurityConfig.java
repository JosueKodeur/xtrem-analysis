package com.josue.kodeur.xtremanalyse.security.config;

import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.entities.UserPrincipal;
import com.josue.kodeur.xtremanalyse.security.exceptions.AccountLockedException;
import com.josue.kodeur.xtremanalyse.security.exceptions.PasswordError;
import com.josue.kodeur.xtremanalyse.security.filters.AccessDeniedHandler;
import com.josue.kodeur.xtremanalyse.security.filters.AuthEntryPoint;
import com.josue.kodeur.xtremanalyse.security.filters.JwtAuthorizationFilter;
import com.josue.kodeur.xtremanalyse.security.services.UserService;
import com.josue.kodeur.xtremanalyse.application.utils.Constants;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author JosueKodeur
 */

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig{

    private final UserService userService;
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthEntryPoint authEntryPoint;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                 .antMatchers(Constants.PUBLIC_URL)
                .permitAll();
        http.csrf()
                .disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer securityCustomizer(){
        return (web) -> {
            web.ignoring().antMatchers(Constants.PUBLIC_URL);
        };
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
                User user = userService.loadUserByMatricule(matricule);
                UserPrincipal userPrincipal = new UserPrincipal(user);
                return new org.springframework.security.core.userdetails.User(
                        user.getUserMatricule(),
                        user.getPassword(),
                        userPrincipal.getAuthorities()
                );
            }
        };
    }


    @Bean
    AuthenticationManager authenticationManagerBean(UserDetailsService userDetailsService, PasswordEncoder encoder){
        return authentication -> {
            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";

            UserPrincipal userPrincipal = new UserPrincipal(userService.loadUserByMatricule(username));

            if (StringUtils.isNotEmpty(password)){
                if (!encoder.matches(password, userPrincipal.getPassword())) {
                    throw new PasswordError("");
                }

                if (!userPrincipal.isEnabled()) {
                    throw new DisabledException("User account is not active");
                }
                if (!userPrincipal.isAccountNonLocked()) {
                    throw new AccountLockedException("");
                }
                return new UsernamePasswordAuthenticationToken(username, null, userPrincipal.getAuthorities());
            }
            return null;
        };
    }

}
