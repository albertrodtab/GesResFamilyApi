package com.alberto.gesresfamily.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comunicados")
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message="El nombre del centro es obligatorio")
    @NotBlank(message = "El nombre del centro no puede estar vacío")
    @Column(name = "nombre_comunicado")
    private String nombreComunicado;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column (name = "fecha_envio")
    private LocalDateTime fechaEnvio;
    @Column
    @Length(min= 6)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesional_id")
    @JsonBackReference(value = "comunicadoProfesional")
    private Profesional profesional;
}
