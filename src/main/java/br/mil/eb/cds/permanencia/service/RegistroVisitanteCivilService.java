package br.mil.eb.cds.permanencia.service;

import br.mil.eb.cds.permanencia.dto.VisitanteCivilDto;
import br.mil.eb.cds.permanencia.model.VisitanteCivil;
import br.mil.eb.cds.permanencia.repository.RegistroVisitanteCivilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVisitanteCivilService {

    private final RegistroVisitanteCivilRepository civilRepository;

    public RegistroVisitanteCivilService (RegistroVisitanteCivilRepository civilRepository){
        this.civilRepository = civilRepository;
    }

    public void salvarRegistro(VisitanteCivilDto registroDto){
        VisitanteCivil registro = new VisitanteCivil();
        registro.setNomeCompleto(registroDto.nomeCompleto().toUpperCase());
        registro.setEmpresa(registroDto.empresa().toUpperCase());
        registro.setNumeroTelefone(registroDto.numeroTelefone());
        registro.setHoraEntrada(registroDto.horaEntrada());
        registro.setHoraSaida(registroDto.horaSaida());
        civilRepository.save(registro);
    }

    public List<VisitanteCivil> listarRegistros(){
        return civilRepository.findAll();
    }

    public void atualizarRegistro(Long id, VisitanteCivilDto registroDto){
        VisitanteCivil registro = civilRepository.getReferenceById(id);
        registro.setNomeCompleto(registroDto.nomeCompleto().toUpperCase());
        registro.setEmpresa(registroDto.empresa().toUpperCase());
        registro.setNumeroTelefone(registroDto.numeroTelefone());
        registro.setHoraEntrada(registroDto.horaEntrada());
        registro.setHoraSaida(registroDto.horaSaida());
        civilRepository.save(registro);
    }

    public void apagarRegistro(Long id){
        civilRepository.deleteById(id);
    }

    public void apagarTodos(){
        civilRepository.deleteAll();
    }
}
