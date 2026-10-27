
package com.exemplo.seguranca.config;

import com.exemplo.seguranca.user.Role;
import com.exemplo.seguranca.user.User;
import com.exemplo.seguranca.user.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@Profile("dev")
public class DevDataSeeder {

    @Bean
    CommandLineRunner seedAdmin(
            UserRepository repository,
            PasswordEncoder encoder
    ) {
        return args -> {
            String email = "admin@exemplo.com";

            if (!repository.existsByEmail(email)) {
                User admin = new User(
                        email,
                        "Admin",
                        encoder.encode("Admin@12345"),
                        Set.of(Role.USER, Role.ADMIN)
                );

                repository.save(admin);

                System.out.println(
                        "Administrador de desenvolvimento criado."
                );
            }
        };
    }
}
