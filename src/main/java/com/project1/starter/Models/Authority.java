package com.project1.starter.Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Let Hibernate generate IDs
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "auths") // ✅ Match the field name in Account
    private Set<Account> accounts = new HashSet<>();
}
