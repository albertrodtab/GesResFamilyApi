package com.alberto.gesresfamily.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SeguimientoDto {

    @NotNull(message="El tipo de seguimiento es obligatorio")
    @NotBlank(message = "El tipo de seguimiento no puede estar vacío")
    private String tipoSeguimiento;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSeguimiento;
    @Length(min= 6)
    private String descripcion;
    private long residente;


}
