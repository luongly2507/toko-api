package com.app.toko.entity;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.app.toko.entity.Permission.*;

@RequiredArgsConstructor
public enum Role {

        ADMIN(Set.of(
                        ADMIN_READ,
                        ADMIN_UPDATE,
                        ADMIN_DELETE,
                        ADMIN_CREATE,
                        MANAGER_READ,
                        MANAGER_UPDATE,
                        MANAGER_DELETE,
                        MANAGER_CREATE,
                        USER_READ,
                        USER_WRITE,
                        USER_UPDATE,
                        USER_DELETE,
                        CART_READ,
                        CART_WRITE,
                        CART_UPDATE,
                        CART_DELETE)),
        MANAGER(Set.of(
                        MANAGER_READ,
                        MANAGER_UPDATE,
                        MANAGER_DELETE,
                        MANAGER_CREATE,
                        USER_READ,
                        USER_WRITE,
                        USER_UPDATE,
                        USER_DELETE,
                        CART_READ,
                        CART_WRITE,
                        CART_UPDATE,
                        CART_DELETE)

        ),
        USER(Set.of(
                        USER_READ,
                        USER_WRITE,
                        USER_UPDATE,
                        USER_DELETE,
                        CART_READ,
                        CART_WRITE,
                        CART_UPDATE,
                        CART_DELETE

        )),;

        @Getter
        private final Set<Permission> permissions;

        public List<SimpleGrantedAuthority> getAuthorities() {
                var authorities = getPermissions()
                                .stream()
                                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                                .collect(Collectors.toList());
                authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
                return authorities;
        }
}