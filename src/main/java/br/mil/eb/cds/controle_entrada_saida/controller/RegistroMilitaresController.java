package br.mil.eb.cds.controle_entrada_saida.controller;

import br.mil.eb.cds.controle_entrada_saida.dto.FormularioDTO;
import br.mil.eb.cds.controle_entrada_saida.model.Entrada;
import br.mil.eb.cds.controle_entrada_saida.model.Saida;
import br.mil.eb.cds.controle_entrada_saida.service.RegistroService;
import br.mil.eb.cds.controle_entrada_saida.utils.GerarPlanilhaMilitares;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/registros")
public class RegistroMilitaresController {

    private final RegistroService registroService;

    public RegistroMilitaresController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public String home(ModelMap model) {
        model.addAttribute("entradas", registroService.listarEntradas());
        model.addAttribute("saidas", registroService.listarSaidas());
        return "militares";
    }

    @PostMapping("/entrada")
    public String cadastrarEntrada(@Valid FormularioDTO formulario) {
        registroService.salvarEntrada(formulario);
        return "redirect:/registros";
    }

    @PostMapping("/saida")
    public String cadastrarSaida(FormularioDTO formulario) {
        registroService.salvarSaida(formulario);
        return "redirect:/registros";
    }

    @PostMapping("/entrada/editar/{id}")
    public String atualizarEntrada(@PathVariable Long id, FormularioDTO formulario) {
        registroService.atualizarEntrada(id, formulario);
        return "redirect:/registros";
    }

    @PostMapping("/saida/editar/{id}")
    public String atualizarSaida(@PathVariable Long id, FormularioDTO formulario) {
        registroService.atualizarSaida(id, formulario);
        return "redirect:/registros";
    }

    @PostMapping("/entrada/remover/{id}")
    public String apagarEntrada(@PathVariable Long id) {
        registroService.apagarEntrada(id);
        return "redirect:/registros";
    }

    @PostMapping("/saida/remover/{id}")
    public String apagarSaida(@PathVariable Long id) {
        registroService.apagarSaida(id);
        return "redirect:/registros";
    }

    @PostMapping("/apagar-tudo")
    public String apagarTudo() {
        registroService.apagarTodos();
        return "redirect:/registros";
    }

    @GetMapping("/exportar")
    public ResponseEntity<byte[]> exportarDados() {
        List<Entrada> entradas = registroService.listarEntradas();
        List<Saida> saidas = registroService.listarSaidas();

        byte[] planilha = GerarPlanilhaMilitares.exportarDados(entradas, saidas);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registro_secoes_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm")) + ".xlsx")
                .body(planilha);
    }
}
