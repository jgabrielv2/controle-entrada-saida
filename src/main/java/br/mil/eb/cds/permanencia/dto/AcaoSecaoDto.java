package br.mil.eb.cds.permanencia.dto;

import br.mil.eb.cds.permanencia.enums.TipoAcaoSecao;
import br.mil.eb.cds.permanencia.model.AcaoSecao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link AcaoSecao}
 */
public record AcaoSecaoDto(TipoAcaoSecao tipo, @Size(max = 16) @NotNull String divSec, @Size(max = 8) @NotNull String postoGrad,
                           @Size(max = 32) @NotNull String nomeGuerra, @NotNull LocalTime horario) implements Serializable {
}