//package com.exam10.config;
//
//import org.keycloak.adapters.KeycloakConfigResolver;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
////@EnableWebSecurity
//@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
//public class GlobalSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {
//
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//
//    @Bean
//    public KeycloakConfigResolver keycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) {
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//        auth.authenticationProvider(keycloakAuthenticationProvider);
//
//    }
//    private static final String[] AUTH_WHITELIST = {
//            "/authenticate",
//            "/swagger-resources/**",
//            "/swagger-ui/**",
//            "/v3/api-docs",
//            "/webjars/**",
//            "/h2/**",
//            "/actuator/**"
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.csrf().disable()
//                .cors().and()
//                .authorizeRequests()
//                .antMatchers(AUTH_WHITELIST)
//                .permitAll()
//                .anyRequest().authenticated();
//        http.headers().frameOptions().disable();
//    }
//}