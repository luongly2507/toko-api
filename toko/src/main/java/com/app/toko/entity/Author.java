package com.app.toko.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Author")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {
    @Id
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    
    @Column(nullable = false, length = 150)
    private String lastName;
    
    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = true, length = 150, unique = true)
    private String email;
    
    @Column(nullable = true, length= 11, unique = true)
    private String telephone;

    @Column(nullable = true)
    private LocalDate birthDate;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
