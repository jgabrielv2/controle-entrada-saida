package br.mil.eb.cds.controle_entrada_saida.service;

import br.mil.eb.cds.controle_entrada_saida.dto.RegistroVisitanteMilitarDTO;
import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteMilitar;
import br.mil.eb.cds.controle_entrada_saida.repository.RegistroVisitanteMilitarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVisitanteMilitarService {

    private final RegistroVisitanteMilitarRepository militarRepository;

    public RegistroVisitanteMilitarService (RegistroVisitanteMilitarRepository militarRepository){
        this.militarRepository = militarRepository;
    }

    public void salvarRegistro(RegistroVisitanteMilitarDTO registroDto){
        RegistroVisitanteMilitar registro = new RegistroVisitanteMilitar();
        registro.setOm(registroDto.om());
        registro.setPostoGrad(registroDto.postoGrad());
        registro.setNomeGuerra(registroDto.nomeGuerra().toUpperCase());
        registro.setRamal(registroDto.ramal());
        registro.setHoraEntrada(registroDto.horaEntrada());
        registro.setHoraSaida(registroDto.horaSaida());
        militarRepository.save(registro);

    }

    public List<RegistroVisitanteMilitar> listarRegistros(){
        return militarRepository.findAll();
    }

    public void atualizarRegistro(Long id, RegistroVisitanteMilitarDTO registroDto){
        RegistroVisitanteMilitar registro = militarRepository.getReferenceById(id);
        registro.setOm(registroDto.om());
        registro.setPostoGrad(registroDto.postoGrad());
        registro.setNomeGuerra(registroDto.nomeGuerra().toUpperCase());
        registro.setRamal(registroDto.ramal());
        registro.setHoraEntrada(registroDto.horaEntrada());
        registro.setHoraSaida(registroDto.horaSaida());
        militarRepository.save(registro);

    }

    public void apagarRegistro(Long id){
        militarRepository.deleteById(id);
    }

    public void apagarTodos(){
        militarRepository.deleteAll();
    }

}
