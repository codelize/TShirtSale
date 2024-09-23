package br.com.fiap.apitshirtsale.domain.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String username;
    String password;
}
