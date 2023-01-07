package com.alberto.gesresfamily.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResidenteDto {

   /* Es una especie de clon del plan pero no tiene correspondencia con la base de datos solo sirve para definir
    como quiero mostrar mis datos de una forma acotada
    Así envio los campos del residente y un campo a mayores que me servirá para saber con que familiar está relacionado.
    */
    //debo indicarle en que formato voy a introducir las fechas

    private String nombre;
    private String apellidos;
    private String dni;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    private String sexo;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private float saldo;
    private long centro;


}
