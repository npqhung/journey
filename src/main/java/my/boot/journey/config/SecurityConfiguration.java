package my.boot.journey.config;

import my.boot.journey.security.AppAuthenticationFailureHandler;
import my.boot.journey.security.AppAuthenticationSuccessHandler;
import my.boot.journey.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
    @Autowired
    AppAuthenticationFailureHandler appAuthenticationFailHandler;
    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/h2-console*").permitAll()
                .antMatchers("/rest*").hasRole("USER")
                .anyRequest().authenticated()
                .filterSecurityInterceptorOncePerRequest(true)
                .and().formLogin()
//                .successHandler(appAuthenticationSuccessHandler)
//                .failureHandler(appAuthenticationFailHandler)
                .and()
                .logout().permitAll();

        //to allow H2
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().disable();

//        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
