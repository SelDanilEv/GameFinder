package com.defender.test.security;

import com.defender.test.models.Role;
import com.defender.test.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static User create(User user) {
        return new User(
                user.getIdUser(),
                user.getUsername(),
                user.getPassword(),
                true,
                user.getRoles()
        );
    }
}
