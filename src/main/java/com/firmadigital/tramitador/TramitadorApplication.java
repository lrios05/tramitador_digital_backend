package com.firmadigital.tramitador;

import com.firmadigital.tramitador.model.user.Role;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.model.user.UserRoles;
import com.firmadigital.tramitador.repository.user.RoleRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class TramitadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TramitadorApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            //Create Admin and Client Roles
            Role adminRole = roleRepository.findByRole(UserRoles.ADMIN);
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRole(UserRoles.ADMIN);
                adminRole.setStatus('V');
                roleRepository.save(adminRole);
            }

            Role userRole = roleRepository.findByRole(UserRoles.CLIENT);
            if (userRole == null) {
                userRole = new Role();
                userRole.setRole(UserRoles.CLIENT);
                userRole.setStatus('V');
                roleRepository.save(userRole);
            }

            //Create an Admin user  - $2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO
            User admin = userRepository.findByEmail("admin@gmail.com");
            if (admin == null) {
                admin = new User()
                        .setEmail("admin@gmail.com")
                        .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .setName("John")
                        .setPaternal("Doe")
                        .setMaternal("Mamani")
                        .setRoles(Arrays.asList(adminRole));
                userRepository.save(admin);
            }

            //Create a passenger user - $2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO
            User client = userRepository.findByEmail("omar@gmail.com");
            if (client == null) {
                client = new User()
                        .setEmail("omar@gmail.com")
                        .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .setName("Omar")
                        .setPaternal("Rios")
                        .setMaternal("I.")
                        .setRoles(Arrays.asList(userRole));
                userRepository.save(client);
            }
        };
    }
}
