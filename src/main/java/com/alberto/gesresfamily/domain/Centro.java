package com.alberto.gesresfamily.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "centros")
public class Centro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column(name = "num_registro")
    private String numRegistro;
    @Column
    private String email;
    @Column
    private String telefono;

}
