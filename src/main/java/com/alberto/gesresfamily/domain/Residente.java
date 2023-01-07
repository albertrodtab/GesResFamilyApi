package com.alberto.gesresfamily.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "residentes")
public class Residente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String dni;
    @Column (name = "fecha_nacimiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    @Column
    private String sexo;
    @Column
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private float saldo;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    @JsonBackReference(value = "residenteCentro")
    private Centro centro;

    @ManyToMany (mappedBy = "residentes")
    //para evitar serializaciones pongo el backreference en el otro lado Pero tengo que mejorarlo todavía.
    private List<Familiar> familiares;

    //como ya está relacionado en el otro lado aquí solo indico por que objeto tiene mapearse
    // 1 residente varios planes, pero 1 plan solo 1 residente
    @ManyToMany(mappedBy = "residentes")
    @JsonBackReference(value = "residentePlan")
    private List<Plan> planes;

}
