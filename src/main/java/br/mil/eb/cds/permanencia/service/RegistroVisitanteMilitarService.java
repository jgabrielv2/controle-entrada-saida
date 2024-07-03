package br.mil.eb.cds.permanencia.service;

import br.mil.eb.cds.permanencia.dto.VisitanteMilitarDto;
import br.mil.eb.cds.permanencia.model.VisitanteMilitar;
import br.mil.eb.cds.permanencia.repository.RegistroVisitanteMilitarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVisitanteMilitarService {

    private final RegistroVisitanteMilitarRepository militarRepository;

    public RegistroVisitanteMilitarService (RegistroVisitanteMilitarRepository militarRepository){
        this.militarRepository = militarRepository;
    }

    public void salvarRegistro(VisitanteMilitarDto registroDto){
        VisitanteMilitar registro = new VisitanteMilitar();
        registro.setOm(registroDto.om());
        registro.setPostoGrad(registroDto.postoGrad());
        registro.setNomeGuerra(registroDto.nomeGuerra().toUpperCase());
        registro.setRamal(registroDto.ramal());
        registro.setHoraEntrada(registroDto.horaEntrada());
        registro.setHoraSaida(registroDto.horaSaida());
        militarRepository.save(registro);

    }

    public List<VisitanteMilitar> listarRegistros(){
        return militarRepository.findAll();
    }

    public void atualizarRegistro(Long id, VisitanteMilitarDto registroDto){
        VisitanteMilitar registro = militarRepository.getReferenceById(id);
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
