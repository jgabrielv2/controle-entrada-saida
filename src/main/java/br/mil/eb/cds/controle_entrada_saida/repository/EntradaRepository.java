package br.mil.eb.cds.controle_entrada_saida.repository;

import br.mil.eb.cds.controle_entrada_saida.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
