package br.mil.eb.cds.controle_entrada_saida.dto;

import java.time.LocalTime;

public record RegistroVisitanteCivilDTO(String nomeCompleto, String empresa, String numeroTelefone, LocalTime horaEntrada, LocalTime horaSaida) {
}
