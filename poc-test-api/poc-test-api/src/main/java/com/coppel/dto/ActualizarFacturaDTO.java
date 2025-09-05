package com.coppel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarFacturaDTO {

    // ID de solo lectura - NO se usa para actualización
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idt;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "Formato de monto inválido")
    private BigDecimal monto;

    @NotBlank(message = "El NIT del cliente es obligatorio")
    @Pattern(regexp = "^[0-9]{8,15}$", message = "NIT debe contener entre 8 y 15 dígitos")
    private String nitCliente;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreCliente;

    @Valid
    @NotEmpty(message = "Debe incluir al menos un detalle de factura")
    private List<FacturasDetalleDTO> facturaDetalleList;
}
