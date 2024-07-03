package br.mil.eb.cds.permanencia.repository;

import br.mil.eb.cds.permanencia.enums.TipoAcaoSecao;
import br.mil.eb.cds.permanencia.model.AcaoSecao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcaoSecaoRepository extends JpaRepository<AcaoSecao, Long> {
    List<AcaoSecao> findByTipo(TipoAcaoSecao tipo);

    void deleteByTipo(TipoAcaoSecao tipo);




}