package br.mil.eb.cds.controle_entrada_saida.repository;

import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteCivil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroVisitanteCivilRepository extends JpaRepository<RegistroVisitanteCivil, Long> {
}
