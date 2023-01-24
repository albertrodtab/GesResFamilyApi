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
public class ComunicadoDto {

    @NotNull(message="El nombre del centro es obligatorio")
    @NotBlank(message = "El nombre del centro no puede estar vacío")
    private String nombreComunicado;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaEnvio;
    @Length(min= 6)
    private String descripcion;
    private long profesional;


}
