package com.alberto.gesresfamily.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "planes")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre_plan")
    private String nombrePlan;
    @Column
    private String terapia;
    @Column (name = "fecha_inicio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @Column (name = "fecha_fin")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFin;
    @Column
    private Boolean importante;
    @Column
    private String descripcion;

}
