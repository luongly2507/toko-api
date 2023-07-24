package com.app.toko.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 11)
    private String telephone;

    private String city;

    private String district;

    private String ward;

    private String line;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String receiver;
}
