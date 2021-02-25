package com.fa.ultilities;

import com.fa.model.User;
import com.fa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public DbInit(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        this.userRepository.deleteAll();

        User sa = new User("sa", passwordEncoder.encode("123"), "USER", "");
        User admin = new User("admin", passwordEncoder.encode("123"), "ADMIN", "ACCESS_TEST1, ACCESS_TEST2");
        User manager = new User("manager", passwordEncoder.encode("123"), "MANAGER", "ACCESS_TEST1");

        List<User> users = Arrays.asList(sa, admin, manager);

        this.userRepository.saveAll(users);
    }
}
