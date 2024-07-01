package br.mil.eb.cds.controle_entrada_saida.repository;

import br.mil.eb.cds.controle_entrada_saida.enums.TipoAcaoSecao;
import br.mil.eb.cds.controle_entrada_saida.model.AcaoSecao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcaoSecaoRepository extends JpaRepository<AcaoSecao, Long> {
    List<AcaoSecao> findByTipo(TipoAcaoSecao tipo);

    void deleteByTipo(TipoAcaoSecao tipo);




}