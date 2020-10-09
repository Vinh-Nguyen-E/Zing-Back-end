package com.example.zingfakebackend.config;

import com.example.zingfakebackend.config.security.JwtAuthEntryPoint;
import com.example.zingfakebackend.config.security.JwtAuthTokenFilter;
import com.example.zingfakebackend.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
      @Autowired
      UserDetailsServiceImpl userDetailsService;

      @Autowired
      private JwtAuthEntryPoint jwtAuthEntryPoint;

      @Bean
      public JwtAuthTokenFilter authenticationJwtTokenFilter() {
            return new JwtAuthTokenFilter();
      }

      @Override
      public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
      }

      @Bean
      @Override
      public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
      }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }

//      @Override
//      protected void configure(HttpSecurity http) throws Exception {
//            http.cors().and().csrf().disable()
//                    .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                    .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//                    .antMatchers("/song/**").permitAll()
//                    .antMatchers("/user/**").permitAll()
//                    .anyRequest().authenticated();
//            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//      }

      @Override
      protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable().
                    authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
      }
}