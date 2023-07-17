package com.app.toko.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.toko.payload.request.CreateContactRequest;
import com.app.toko.payload.request.CreateOrderRequest;
import com.app.toko.payload.request.UpdateCartItemRequest;
import com.app.toko.payload.request.UpdateContactRequest;
import com.app.toko.payload.request.UpdateUserInfoRequest;
import com.app.toko.payload.response.CartResponse;
import com.app.toko.payload.response.ContactResponse;
import com.app.toko.payload.response.OrderResponse;
import com.app.toko.payload.response.UserResponse;
import com.app.toko.service.CartService;
import com.app.toko.service.ContactService;
import com.app.toko.service.OrderService;
import com.app.toko.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    // USER

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/{id}")

    public ResponseEntity<UserResponse> getUserDetail(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/")
    public ResponseEntity<Page<UserResponse>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // CONTACT
    @PreAuthorize("hasAuthority('contact:read')")
    @GetMapping("/{id}/contacts/")

    public ResponseEntity<List<ContactResponse>> getUserContacts(@PathVariable UUID id) {
        return ResponseEntity.ok(contactService.getAllContactsByUserId(id));
    }

    @PreAuthorize("hasAuthority('contact:read')")
    @GetMapping("/{userId}/contacts/{contactid}/")
    public ResponseEntity<ContactResponse> getUserContact(@PathVariable UUID userId,
            @PathVariable UUID contactId) {
        return ResponseEntity.ok(contactService.getContact(contactId));
    }

    @PreAuthorize("hasAuthority('contact:write')")
    @PostMapping("/{userId}/contacts/")
    public ResponseEntity<ContactResponse> createUserContact(@PathVariable UUID userId,
            @RequestBody @Valid CreateContactRequest createContactRequest) {
        System.out.println(createContactRequest);
        return ResponseEntity.ok(contactService.createContact(userId, createContactRequest));
    }

    @PreAuthorize("hasAuthority('contact:update')")
    @PutMapping("/{userId}/contacts/{contactId}/")
    public ResponseEntity<String> updateUserContact(@PathVariable UUID userId,
            @PathVariable UUID contactId, @RequestBody UpdateContactRequest updateContactRequest) {
        contactService.updateContact(contactId, updateContactRequest);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('contact:write')")
    @DeleteMapping("/{userId}/contacts/{contactId}/")
    public ResponseEntity<String> deleteUserContact(@PathVariable UUID userId,
            @PathVariable UUID contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }

    // CART
    @PreAuthorize("hasAuthority('cart:read')")
    @GetMapping("/{userId}/carts/")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable UUID userId) {
        return ResponseEntity.ok(cartService.getAllCartItemsByUserId(userId));
    }

    @PreAuthorize("hasAuthority('cart:update')")
    @PutMapping("/{userId}/carts/")
    public ResponseEntity<String> updateCartItem(@PathVariable UUID userId,
            @RequestBody UpdateCartItemRequest updateCartItemRequest) {
        cartService.updateCartItem(userId, updateCartItemRequest);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('cart:delete')")
    @DeleteMapping("/{userId}/carts/{bookId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable UUID userId,
            @PathVariable UUID bookId) {
        cartService.deleteCartItem(userId, bookId);
        return ResponseEntity.noContent().build();
    }

    // ORDER
    @PreAuthorize("hasAuthority('order:read')")
    @GetMapping("/{userId}/orders/")
    public ResponseEntity<List<OrderResponse>> getOrders(@PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(userId));
    }

    @PreAuthorize("hasAuthority('order:write')")
    @PostMapping("/{userId}/orders/")
    public ResponseEntity<String> createOrder(@PathVariable UUID userId,
            @RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(userId, createOrderRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserInfo(@PathVariable UUID id,
            @RequestBody UpdateUserInfoRequest userInfoRequest) {
        userService.updateUserInfo(id, userInfoRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/phone/{phone}")
    public ResponseEntity<UserResponse> updateUserPassword(@PathVariable String phone, @RequestParam String password) {
        userService.updateUserPassword(phone, password);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<UserResponse> isExistUserByPhone(@RequestParam String phone) {
        userService.getUserByPhone(phone);
        return ResponseEntity.noContent().build();
    }
}
