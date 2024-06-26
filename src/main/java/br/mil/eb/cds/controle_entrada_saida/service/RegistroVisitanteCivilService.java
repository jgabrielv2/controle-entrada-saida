package br.mil.eb.cds.controle_entrada_saida.service;

import br.mil.eb.cds.controle_entrada_saida.dto.RegistroVisitanteCivilDTO;
import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteCivil;
import br.mil.eb.cds.controle_entrada_saida.repository.RegistroVisitanteCivilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVisitanteCivilService {

    private final RegistroVisitanteCivilRepository civilRepository;

    public RegistroVisitanteCivilService (RegistroVisitanteCivilRepository civilRepository){
        this.civilRepository = civilRepository;
    }

    public void salvarRegistro(RegistroVisitanteCivilDTO registroDto){
        RegistroVisitanteCivil registro = new RegistroVisitanteCivil();
        registro.setNomeCompleto(registroDto.nomeCompleto().toUpperCase());
        registro.setEmpresa(registroDto.empresa().toUpperCase());
        registro.setNumeroTelefone(registroDto.numeroTelefone());
        registro.setHoraEntrada(registroDto.horaEntrada());
        registro.setHoraSaida(registroDto.horaSaida());
        civilRepository.save(registro);
    }

    public List<RegistroVisitanteCivil> listarRegistros(){
        return civilRepository.findAll();
    }

    public void atualizarRegistro(Long id, RegistroVisitanteCivilDTO registroDto){
        RegistroVisitanteCivil registro = civilRepository.getReferenceById(id);
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
