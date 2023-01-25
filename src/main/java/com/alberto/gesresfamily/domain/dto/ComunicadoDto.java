package com.alberto.gesresfamily.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ComunicadoDto {

    @NotNull(message="El nombre del centro es obligatorio")
    @NotBlank(message = "El nombre del centro no puede estar vacío")
    private String nombreComunicado;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fechaEnvio;
    @Length(min= 6)
    private String descripcion;
    private long profesional;


}
