package com.markdown.auth.config;

import com.markdown.auth.config.security.AuthFilter;
import com.markdown.auth.config.security.MarkdownAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    MarkdownAuthProvider markdownAuthProvider(){

        return new MarkdownAuthProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no need to create the session. JWT handles all the task.
                .and()              // builder patter to chain the command
                .authenticationProvider(markdownAuthProvider())
                .addFilterBefore(authFilter(), AnonymousAuthenticationFilter.class) // custom filter to handle authentication
                .authorizeRequests()
                .anyRequest().authenticated() // every request is authenticated
                .and().csrf().disable()  // disable csrf since no track of cookies kept
                .httpBasic().disable()  // no need
                .logout().disable()////
                .cors() ; // allows restricted resource to be requested
                ///////
    }

    public AuthFilter authFilter() throws Exception {

        OrRequestMatcher orRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/user/**"),
                new AntPathRequestMatcher("/token/**"),
                new AntPathRequestMatcher("/role/**")
        );

        AuthFilter authFilter = new AuthFilter(orRequestMatcher);
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }

}
