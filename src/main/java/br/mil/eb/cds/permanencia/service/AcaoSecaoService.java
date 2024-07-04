package br.mil.eb.cds.permanencia.service;

import br.mil.eb.cds.permanencia.dto.AcaoSecaoDto;
import br.mil.eb.cds.permanencia.enums.TipoAcaoSecao;
import br.mil.eb.cds.permanencia.model.AcaoSecao;
import br.mil.eb.cds.permanencia.repository.AcaoSecaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoSecaoService {
    private final AcaoSecaoRepository acaoRepository;

    public AcaoSecaoService(AcaoSecaoRepository acaoRepository) {
        this.acaoRepository = acaoRepository;
    }

    public AcaoSecao salvarAcao(AcaoSecaoDto formulario) {
        AcaoSecao registro = new AcaoSecao();
        registro.setTipo(formulario.tipo());
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        return acaoRepository.save(registro);
    }

    @Deprecated
    public AcaoSecao salvarEntrada(AcaoSecaoDto formulario) {
        AcaoSecao registro = new AcaoSecao();
        registro.setTipo(TipoAcaoSecao.ENTRADA);
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        return acaoRepository.save(registro);
    }

    @Deprecated
    public AcaoSecao salvarSaida(AcaoSecaoDto formulario) {
        AcaoSecao registro = new AcaoSecao();
        registro.setTipo(TipoAcaoSecao.SAIDA);
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        return acaoRepository.save(registro);
    }

    public List<AcaoSecao> listarAcoes(){
        return acaoRepository.findAll();
    }

    public AcaoSecao atualizarRegistro(Long id, AcaoSecaoDto formulario) {
        AcaoSecao registro = acaoRepository.getReferenceById(id);
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        return acaoRepository.save(registro);
    }

    public void apagarRegistro(Long id) {
        acaoRepository.deleteById(id);
    }

    public void apagarTodosTipoEntrada() {
        acaoRepository.deleteByTipo(TipoAcaoSecao.ENTRADA);
    }

    public void apagarTodosTipoSaida() {
        acaoRepository.deleteByTipo(TipoAcaoSecao.SAIDA);
    }

    public void apagarPorTipo(TipoAcaoSecao tipo) {
        acaoRepository.deleteByTipo(tipo);
    }

    public void apagarTodos() {
        acaoRepository.deleteAll();
    }

    public List<AcaoSecao> listarEntradas() {
        return acaoRepository.findByTipo(TipoAcaoSecao.ENTRADA);
    }

    public List<AcaoSecao> listarSaidas() {
        return acaoRepository.findByTipo(TipoAcaoSecao.SAIDA);
    }
}