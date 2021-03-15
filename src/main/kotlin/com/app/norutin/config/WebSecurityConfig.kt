package com.app.norutin.config

import com.app.norutin.security.FirebaseAuthTokenFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    fun firebaseAuthenticationFilterBean(): FirebaseAuthTokenFilter? {
        return FirebaseAuthTokenFilter()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/users/signup").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(
            firebaseAuthenticationFilterBean(),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }
}