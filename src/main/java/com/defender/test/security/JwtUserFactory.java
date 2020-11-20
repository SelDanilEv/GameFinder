package com.defender.test.security;

import com.defender.test.models.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static User create(User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                true,
                user.getRoles()
        );
    }
}
