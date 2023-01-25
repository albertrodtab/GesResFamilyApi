package com.alberto.gesresfamily.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "seguimientos")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message="El tipo de seguimiento es obligatorio")
    @NotBlank(message = "El tipo de seguimiento no puede estar vacío")
    @Column(name = "tipo_seguimiento")
    private String tipoSeguimiento;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column (name = "fecha_seguimiento")
    private LocalDateTime fechaSeguimiento;
    @Column
    @Length(min= 6)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "residente_id")
    @JsonBackReference(value = "seguimientoResidente")
    private Residente residente;
}
