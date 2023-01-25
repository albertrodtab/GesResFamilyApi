package com.alberto.gesresfamily.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SeguimientoDto {

    @NotNull(message="El tipo de seguimiento es obligatorio")
    @NotBlank(message = "El tipo de seguimiento no puede estar vacío")
    private String tipoSeguimiento;

    @Length(min= 6)
    private String descripcion;
    private long residente;


}
