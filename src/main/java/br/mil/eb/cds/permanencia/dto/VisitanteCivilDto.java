package br.mil.eb.cds.permanencia.dto;

import java.time.LocalTime;

public record VisitanteCivilDto(String nomeCompleto, String empresa, String numeroTelefone, LocalTime horaEntrada, LocalTime horaSaida) {
}
