package com.app.toko.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    CART_READ("cart:read"),
    CART_WRITE("cart:write"),
    CART_UPDATE("cart:update"),
    CART_DELETE("cart:delete"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    CATEGORY_UPDATE("category:update"),
    CATEGORY_DELETE("category:delete"),
    CONTACT_READ("contact:read"),
    CONTACT_WRITE("contact:write"),
    CONTACT_UPDATE("contact:update"),
    CONTACT_DELETE("contact:delete"),
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    BOOK_UPDATE("book:update"),
    BOOK_DELETE("book:delete"),
    ALBUM_READ("album:read"),
    ALBUM_WRITE("album:write"),
    ALBUM_UPDATE("album:update"),
    ALBUM_DELETE("album:delete"),
    ;

    @Getter
    private final String permission;
}