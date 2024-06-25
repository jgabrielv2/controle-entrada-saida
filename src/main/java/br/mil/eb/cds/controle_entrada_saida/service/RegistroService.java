package br.mil.eb.cds.controle_entrada_saida.service;

import br.mil.eb.cds.controle_entrada_saida.dto.FormularioDTO;
import br.mil.eb.cds.controle_entrada_saida.model.Entrada;
import br.mil.eb.cds.controle_entrada_saida.model.Saida;
import br.mil.eb.cds.controle_entrada_saida.repository.EntradaRepository;
import br.mil.eb.cds.controle_entrada_saida.repository.SaidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    private final EntradaRepository entradaRepository;
    private final SaidaRepository saidaRepository;

    public RegistroService(EntradaRepository entradaRepository, SaidaRepository saidaRepository) {
        this.entradaRepository = entradaRepository;
        this.saidaRepository = saidaRepository;
    }

    public void salvarEntrada(FormularioDTO formulario) {
        Entrada entrada = new Entrada();
        entrada.setDivSec(formulario.divSec());
        entrada.setPostoGrad(formulario.postoGrad());
        entrada.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        entrada.setHorario(formulario.horario());
        entradaRepository.save(entrada);
    }

    public void salvarSaida(FormularioDTO formulario) {
        Saida saida = new Saida();
        saida.setDivSec(formulario.divSec());
        saida.setPostoGrad(formulario.postoGrad());
        saida.setNomeGuerra(formulario.nomeGuerra().toUpperCase());
        saida.setHorario(formulario.horario());
        saidaRepository.save(saida);
    }

    public void atualizarEntrada(Long id, FormularioDTO formularioDTO){
        Entrada entrada = entradaRepository.getReferenceById(id);
        entrada.setDivSec(formularioDTO.divSec());
        entrada.setPostoGrad(formularioDTO.postoGrad());
        entrada.setNomeGuerra(formularioDTO.nomeGuerra().toUpperCase());
        entrada.setHorario(formularioDTO.horario());
        entradaRepository.save(entrada);
    }

    public void atualizarSaida(Long id, FormularioDTO formularioDTO){
        Saida saida = saidaRepository.getReferenceById(id);
        saida.setDivSec(formularioDTO.divSec());
        saida.setPostoGrad(formularioDTO.postoGrad());
        saida.setNomeGuerra(formularioDTO.nomeGuerra().toUpperCase());
        saida.setHorario(formularioDTO.horario());
        saidaRepository.save(saida);
    }

    public Entrada buscarEntradaPorId(Long id){
        return entradaRepository.getReferenceById(id);
    }

    public void apagarEntrada(Long idEntrada) {
        entradaRepository.deleteById(idEntrada);
    }

    public void apagarSaida(Long idSaida) {
        saidaRepository.deleteById(idSaida);
    }

    public void apagarTodos() {
        entradaRepository.deleteAll();
        saidaRepository.deleteAll();
    }

    public List<Entrada> listarEntradas() {
        return entradaRepository.findAll();
    }

    public List<Saida> listarSaidas() {
        return saidaRepository.findAll();
    }


}
