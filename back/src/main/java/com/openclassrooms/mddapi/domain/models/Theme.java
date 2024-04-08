package com.openclassrooms.mddapi.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.LAZY)
    private Set<User> subscribers = new HashSet<>();

    @ManyToMany(mappedBy = "themes", fetch = FetchType.LAZY)
    private Set<Article> articles = new HashSet<>();



    // Constructeurs, getters et setters
}
