package br.mil.eb.cds.permanencia.controller.web;

import br.mil.eb.cds.permanencia.dto.AcaoSecaoDto;
import br.mil.eb.cds.permanencia.service.AcaoSecaoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secoes")
public class AcaoSecaoWebController {

    private final AcaoSecaoService acaoService;

    public AcaoSecaoWebController(AcaoSecaoService acaoService) {
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
}
