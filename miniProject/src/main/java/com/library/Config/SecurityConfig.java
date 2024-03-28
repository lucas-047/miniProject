package com.library.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration

public class SecurityConfig {
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }
    public static final String[] ENDPOINTS_WHITELIST = {
            "/",
            "/css/**",
            "/img/**",
            "/JS/**",
            "/library",
            "/About",
            "/signIn",
            "/password_reset",
            "/sendResetPasswordMail",
            "/ResetPassSuccess",
            "/resetPassword",
            "/processResetPassword",
            "/Registration",
            "/processRegistration",
            "expiry",
            "/validateOtp",
            "/upload",
            "book",
            "htmlfile",
            "verify",
            "/issue-return",
            "return",
            "returning",
            "search",
            "search/{query}",
            "search/result/{book}",
            "Issue"
//            "/user/**",
//            "/admin/**",
//            "/**"

    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/signIn")
                        .defaultSuccessUrl("/default")
                        .loginProcessingUrl("/signIn")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .permitAll()
                )

        ;

        return http.build();
    }
    
}
