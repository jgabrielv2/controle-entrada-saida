package br.mil.eb.cds.controle_entrada_saida.service;

import br.mil.eb.cds.controle_entrada_saida.dto.AcaoSecaoDto;
import br.mil.eb.cds.controle_entrada_saida.enums.TipoAcaoSecao;
import br.mil.eb.cds.controle_entrada_saida.model.AcaoSecao;
import br.mil.eb.cds.controle_entrada_saida.repository.AcaoSecaoRepository;
import br.mil.eb.cds.controle_entrada_saida.repository.EntradaRepository;
import br.mil.eb.cds.controle_entrada_saida.repository.SaidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoSecaoService {
    private final EntradaRepository entradaRepository;
    private final SaidaRepository saidaRepository;
    private final AcaoSecaoRepository acaoRepository;

    public AcaoSecaoService(AcaoSecaoRepository acaoRepository, EntradaRepository entradaRepository, SaidaRepository saidaRepository) {
        this.acaoRepository = acaoRepository;
        this.entradaRepository = entradaRepository;
        this.saidaRepository = saidaRepository;
    }

    public void salvarEntrada(AcaoSecaoDto formulario) {
        AcaoSecao registro = new AcaoSecao();
        registro.setTipo(TipoAcaoSecao.ENTRADA);
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        acaoRepository.save(registro);
    }

    public void salvarSaida(AcaoSecaoDto formulario) {
        AcaoSecao registro = new AcaoSecao();
        registro.setTipo(TipoAcaoSecao.SAIDA);
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        acaoRepository.save(registro);
    }

    public void atualizarRegistro(Long id, AcaoSecaoDto formulario) {
        AcaoSecao registro = acaoRepository.getReferenceById(id);
        registro.setDivSec(formulario.divSec());
        registro.setPostoGrad(formulario.postoGrad());
        registro.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        registro.setHorario(formulario.horario());
        acaoRepository.save(registro);
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


    public void apagarTodos() {
        entradaRepository.deleteAll();
        saidaRepository.deleteAll();
    }

    public List<AcaoSecao> listarEntradas() {
        return acaoRepository.findByTipo(TipoAcaoSecao.ENTRADA);
    }

    public List<AcaoSecao> listarSaidas() {
        return acaoRepository.findByTipo(TipoAcaoSecao.SAIDA);
    }

}
