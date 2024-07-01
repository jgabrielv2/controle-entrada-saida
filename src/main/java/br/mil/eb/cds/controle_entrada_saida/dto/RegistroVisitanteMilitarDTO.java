package br.mil.eb.cds.controle_entrada_saida.dto;

import java.time.LocalTime;

public record RegistroVisitanteMilitarDTO(String om, String postoGrad, String nomeGuerra, String ramal, LocalTime horaEntrada, LocalTime horaSaida) {
}
