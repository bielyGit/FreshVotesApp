package com.freshvotes.services;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertThat;

public class UserDetailsServiceTest {
    @Test
    public void generate_encrypted_password() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123pwd";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
        assertThat(rawPassword, CoreMatchers.not(encodedPassword));

    }
}