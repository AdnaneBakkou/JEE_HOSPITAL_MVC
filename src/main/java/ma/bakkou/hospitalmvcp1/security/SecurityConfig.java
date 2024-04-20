package ma.bakkou.hospitalmvcp1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }



//@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return  new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()

        );
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")   // Set the custom login page
                        .permitAll() // Permit everyone to see the login page. Don't require authentication.
                )
                .authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**").permitAll())
              //  .authorizeHttpRequests(ar->ar.requestMatchers("/deletePatient/**").hasRole("ADMIN"))
                // .authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                //.authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .exceptionHandling(eh->eh.accessDeniedPage("/notAuthorized"))
                .build();
    }
}
