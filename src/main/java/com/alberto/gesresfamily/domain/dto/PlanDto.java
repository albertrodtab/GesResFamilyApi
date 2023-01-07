package com.alberto.gesresfamily.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PlanDto {

   /* Es una especie de clon del plan pero no tiene correspondencia con la base de datos solo sirve para definir
    como quiero mostrar mis datos de una forma acotada
    Así envio los campos del plan y dos campos a mayores que me servirán para saber con que residente y profesional está relacionado.
    */
    //debo indicarle en que formato voy a introducir las fechas

    private String nombrePlan;
    private String terapia;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFin;
    private Boolean importante;
    private String descripcion;
    private long profesional;


}
