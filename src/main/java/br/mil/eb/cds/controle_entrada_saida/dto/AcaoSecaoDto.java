package br.mil.eb.cds.controle_entrada_saida.dto;

import br.mil.eb.cds.controle_entrada_saida.model.AcaoSecao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link AcaoSecao}
 */
public record AcaoSecaoDto(@Size(max = 16) @NotNull String divSec, @Size(max = 8) @NotNull String postoGrad,
                           @Size(max = 32) @NotNull String nomeGuerra, @NotNull LocalTime horario) implements Serializable {
}