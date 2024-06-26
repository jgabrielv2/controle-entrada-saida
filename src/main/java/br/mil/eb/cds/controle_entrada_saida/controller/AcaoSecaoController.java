package br.mil.eb.cds.controle_entrada_saida.controller;

import br.mil.eb.cds.controle_entrada_saida.dto.AcaoSecaoDto;
import br.mil.eb.cds.controle_entrada_saida.model.AcaoSecao;
import br.mil.eb.cds.controle_entrada_saida.service.AcaoSecaoService;
import br.mil.eb.cds.controle_entrada_saida.utils.GerarPlanilhaControleSecoes;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/secoes")
public class AcaoSecaoController {

    private final AcaoSecaoService acaoService;

    public AcaoSecaoController(AcaoSecaoService acaoService) {
        this.acaoService = acaoService;
    }

    @GetMapping
    public String home(ModelMap model) {
        model.addAttribute("entradas", acaoService.listarEntradas());
        model.addAttribute("saidas", acaoService.listarSaidas());
        return "controle-secoes";
    }

    @PostMapping("/entrada")
    public String cadastrarEntrada(@Valid AcaoSecaoDto formulario) {
        acaoService.salvarEntrada(formulario);
        return "redirect:/secoes";
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

    @GetMapping("/exportar")
    public ResponseEntity<byte[]> exportarDados() {
        List<AcaoSecao> entradas = acaoService.listarEntradas();
        List<AcaoSecao> saidas = acaoService.listarSaidas();

        byte[] planilha = GerarPlanilhaControleSecoes.exportarDados(entradas, saidas);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registro_secoes_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm")) + ".xlsx")
                .body(planilha);
    }
}
