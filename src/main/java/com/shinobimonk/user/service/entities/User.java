package com.shinobimonk.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor@Builder
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "about")
    private String about;
    @Transient
    List<Rating> rating = new ArrayList<>();

}
