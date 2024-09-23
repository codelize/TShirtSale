package br.com.fiap.apitshirtsale.domain.TShirt;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "tshirts")
public class TShirt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
}
