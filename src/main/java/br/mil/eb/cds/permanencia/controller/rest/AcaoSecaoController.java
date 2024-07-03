package br.mil.eb.cds.permanencia.controller.rest;

import br.mil.eb.cds.permanencia.dto.AcaoSecaoDto;
import br.mil.eb.cds.permanencia.model.AcaoSecao;
import br.mil.eb.cds.permanencia.service.AcaoSecaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/secoes")
public class AcaoSecaoController {

    private final AcaoSecaoService acaoService;

    public AcaoSecaoController(AcaoSecaoService acaoService) {
        this.acaoService = acaoService;
    }


    @PostMapping("/entrada")
    public ResponseEntity<AcaoSecao> cadastrarEntrada(@RequestBody @Valid AcaoSecaoDto formulario, UriComponentsBuilder uriComponentsBuilder) {
        var acao = acaoService.salvarEntrada(formulario);
        var uri = uriComponentsBuilder.path("/api/secoes/{id}")
                .buildAndExpand(acao.getId()).toUri();
        return ResponseEntity.created(uri).body(acao);
    }

    @PostMapping("/saida")
    public String cadastrarSaida(@Valid AcaoSecaoDto formulario) {
        acaoService.salvarSaida(formulario);
        return "redirect:/secoes";
    }

    @PostMapping("/editar/{id}")
    public String atualizarRegistro(@PathVariable Long id, AcaoSecaoDto formulario) {
        acaoService.atualizarRegistro(id, formulario);
        return "redirect:/secoes";
    }

    @PostMapping("/remover/{id}")
    public String apagarRegistro(@PathVariable Long id) {
        acaoService.apagarRegistro(id);
        return "redirect:/secoes";
    }

    @PostMapping("/entradas/remover")
    public String apagarRegistrosEntradas() {
        acaoService.apagarTodosTipoEntrada();
        return "redirect:/secoes";
    }

    @PostMapping("/saidas/remover")
    public String apagarRegistrosSaidas() {
        acaoService.apagarTodosTipoSaida();
        return "redirect:/secoes";
    }

    @PostMapping("/remover-tudo")
    public String apagarTudo() {
        acaoService.apagarTodos();
        return "redirect:/secoes";
    }

}
