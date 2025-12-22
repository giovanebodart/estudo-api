package com.estudos.api.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private UserRole role;

    public User(String username, String password, UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
