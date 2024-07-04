package br.mil.eb.cds.permanencia.controller.rest;

import br.mil.eb.cds.permanencia.dto.AcaoSecaoDto;
import br.mil.eb.cds.permanencia.enums.TipoAcaoSecao;
import br.mil.eb.cds.permanencia.model.AcaoSecao;
import br.mil.eb.cds.permanencia.service.AcaoSecaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/secoes")
public class AcaoSecaoController {

    private final AcaoSecaoService acaoService;

    public AcaoSecaoController(AcaoSecaoService acaoService) {
        this.acaoService = acaoService;
    }

    @PostMapping
    public ResponseEntity<AcaoSecao> cadastrarAcao(@RequestBody @Valid AcaoSecaoDto formulario, UriComponentsBuilder uriComponentsBuilder) {
        var acao = acaoService.salvarAcao(formulario);
        var uri = uriComponentsBuilder.path("/api/secoes/{id}")
                .buildAndExpand(acao.getId()).toUri();
        return ResponseEntity.created(uri).body(acao);
    }

    @GetMapping
    public ResponseEntity<List<AcaoSecao>> listarAcoes(){
        return ResponseEntity.ok(acaoService.listarAcoes());
    }


    @PutMapping("/{id}")
    public ResponseEntity<AcaoSecao> atualizarRegistro(@PathVariable Long id, @RequestBody AcaoSecaoDto formulario) {
        return ResponseEntity.ok(acaoService.atualizarRegistro(id, formulario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarRegistro(@PathVariable Long id) {
        acaoService.apagarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> apagarTodos() {
        acaoService.apagarTodos();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/tipo")
    public ResponseEntity<Void> apagarPorTipo(@RequestParam TipoAcaoSecao tipo) {
        acaoService.apagarPorTipo(tipo);
        return ResponseEntity.noContent().build();
    }
}
