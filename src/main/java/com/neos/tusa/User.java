package com.neos.tusa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String telegramId;

    @OneToMany(mappedBy = "admin")
    private List<Party> adminParties = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<Party> userParties = new ArrayList<>();
}
