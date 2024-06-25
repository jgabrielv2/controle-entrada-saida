package br.mil.eb.cds.controle_entrada_saida.repository;

import br.mil.eb.cds.controle_entrada_saida.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaidaRepository extends JpaRepository<Saida, Long> {
}
