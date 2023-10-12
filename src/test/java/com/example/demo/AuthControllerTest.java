package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    @WithMockUser(username = "user", roles = { "USER", "ADMIN" })
    public void testGetAuthorities() {
        // Set the expected authorities list.
        List<GrantedAuthority> expectedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));

        // Call the getAuthorities method on the controller.
        Collection<? extends GrantedAuthority> actualAuthorities = authController.getAuthorities();

        // Assert that the returned authorities list matches the expected authorities list.
        assertThat(actualAuthorities, containsInAnyOrder(expectedAuthorities.toArray(new GrantedAuthority[0])));
    }


    @Test
    @WithMockUser(username = "user", roles = { "USER", "ADMIN" })
    public void testGetAuthoritiesSortedList() {
        // Set the expected authorities list.
        List<GrantedAuthority> expectedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));

        // Call the getAuthorities method on the controller.
        Collection<? extends GrantedAuthority> actualAuthorities = authController.getAuthorities();

        // Convert both lists to lists of strings.
        List<String> expectedAuthoritiesStrings = expectedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> actualAuthoritiesStrings = actualAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // Sort both lists.
        Collections.sort(expectedAuthoritiesStrings);
        Collections.sort(actualAuthoritiesStrings);

        // Compare the sorted lists.
        assertThat(actualAuthoritiesStrings, is(equalTo(expectedAuthoritiesStrings)));
    }
}
