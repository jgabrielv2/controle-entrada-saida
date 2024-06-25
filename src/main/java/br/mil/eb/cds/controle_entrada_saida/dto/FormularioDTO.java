package br.mil.eb.cds.controle_entrada_saida.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record FormularioDTO(@NotBlank String divSec, @NotBlank String postoGrad, @NotBlank String nomeGuerra, @NotNull LocalTime horario) {
}
