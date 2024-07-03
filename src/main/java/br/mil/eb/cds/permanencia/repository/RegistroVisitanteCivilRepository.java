package br.mil.eb.cds.permanencia.repository;

import br.mil.eb.cds.permanencia.model.VisitanteCivil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroVisitanteCivilRepository extends JpaRepository<VisitanteCivil, Long> {
}
