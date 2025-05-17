package sk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //marks security configuration
public class SecurityConfig {
    
    //Create "security filter chain" (access rules, configuration)
    @Bean //@Bean = method instantiates a new object to be managed by the Spring IoC container (context)
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception { //HttpSecurity = app's security configuartion object
        http
            //1. Access rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**").permitAll() //open for everyone
                .anyRequest().authenticated() //only authenticated users
            )
            //2. Login configuration
            //.formLogin(withDefaults())
            .formLogin(login -> login
                .loginPage("/login") //no need for "@GetMappin()", Spring presents view by itself
                .defaultSuccessUrl("/book-list", true)
                .permitAll()
            )
            //3. Logout configuration
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
            );
        
        return http.build();
    }

    //Create password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
