package com.app.toko.entity;

import static com.app.toko.entity.Permission.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

        ADMIN(Set.of(
                        ADMIN_READ,
                        ADMIN_UPDATE,
                        ADMIN_CREATE,
                        ADMIN_DELETE,
                        MANAGER_READ,
                        MANAGER_UPDATE,
                        MANAGER_CREATE,
                        MANAGER_DELETE,
                        USER_READ,
                        USER_WRITE,
                        USER_UPDATE,
                        USER_DELETE,
                        CART_READ,
                        CART_WRITE,
                        CART_UPDATE,
                        CART_DELETE,
                        ORDER_READ,
                        ORDER_WRITE,
                        CATEGORY_READ,
                        CATEGORY_WRITE,
                        CATEGORY_UPDATE,
                        CATEGORY_DELETE,
                        CONTACT_READ,
                        CONTACT_WRITE,
                        CONTACT_UPDATE,
                        CONTACT_DELETE,
                        BOOK_READ,
                        BOOK_WRITE,
                        BOOK_UPDATE,
                        BOOK_DELETE,
                        ALBUM_READ,
                        ALBUM_WRITE,
                        ALBUM_UPDATE,
                        ALBUM_DELETE)),
        MANAGER(Set.of(
                        MANAGER_READ,
                        MANAGER_UPDATE,
                        USER_READ,
                        USER_WRITE,
                        USER_UPDATE,
                        USER_DELETE,
                        CART_READ,
                        CART_WRITE,
                        CART_UPDATE,
                        CART_DELETE,
                        ORDER_READ,
                        ORDER_WRITE,
                        CATEGORY_READ,
                        CATEGORY_WRITE,
                        CATEGORY_UPDATE,
                        CATEGORY_DELETE,
                        CONTACT_READ,
                        CONTACT_WRITE,
                        CONTACT_UPDATE,
                        CONTACT_DELETE,
                        BOOK_READ,
                        BOOK_WRITE,
                        BOOK_UPDATE,
                        BOOK_DELETE,
                        ALBUM_READ,
                        ALBUM_WRITE,
                        ALBUM_UPDATE,
                        ALBUM_DELETE)

        ),
        USER(Set.of(
                        USER_READ,
                        USER_WRITE,
                        CART_READ,
                        CART_WRITE,
                        CART_UPDATE,
                        CART_DELETE,
                        ORDER_READ,
                        CATEGORY_READ,
                        CONTACT_READ,
                        CONTACT_WRITE,
                        CONTACT_UPDATE,
                        CONTACT_DELETE,
                        BOOK_READ,
                        ALBUM_READ

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