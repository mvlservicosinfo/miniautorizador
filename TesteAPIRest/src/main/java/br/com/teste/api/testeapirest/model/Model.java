package br.com.teste.api.testeapirest.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Table(name = "miniautorizador")
@EqualsAndHashCode
public class Model {
//Utilizo o MySQL
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nome", length = 255)
    private String Nome;

    @NonNull
    @Column(name = "nome", length = 12)
    private String numCartao;
    @NonNull
    @Column(name="senha", length = 4)
    private String senha;
    @NonNull
    @Column(name="usuario", length = 100)
    private String usuario;
    @NonNull
    @Column(name="saldo", length = 8)
    private BigDecimal saldo;


}
