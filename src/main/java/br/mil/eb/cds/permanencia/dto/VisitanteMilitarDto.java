package br.mil.eb.cds.permanencia.dto;

import java.time.LocalTime;

public record VisitanteMilitarDto(String om, String postoGrad, String nomeGuerra, String ramal, LocalTime horaEntrada, LocalTime horaSaida) {
}
