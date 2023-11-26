package com.virtuallotto.virtuallottosimulator.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String password;

    @Builder
    private User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    public static User createUser(String id, String password) {
        return builder()
                .id(id)
                .password(password)
                .build();
    }
}
