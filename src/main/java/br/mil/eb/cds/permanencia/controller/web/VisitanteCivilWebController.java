package br.mil.eb.cds.permanencia.controller.web;

import br.mil.eb.cds.permanencia.dto.VisitanteCivilDto;
import br.mil.eb.cds.permanencia.dto.VisitanteMilitarDto;
import br.mil.eb.cds.permanencia.service.RegistroVisitanteCivilService;
import br.mil.eb.cds.permanencia.service.RegistroVisitanteMilitarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitantes")
public class VisitanteCivilWebController {

    private final RegistroVisitanteCivilService civilservice;
    private final RegistroVisitanteMilitarService militarService;


    public VisitanteCivilWebController(RegistroVisitanteCivilService civilservice, RegistroVisitanteMilitarService militarService) {
        this.civilservice = civilservice;
        this.militarService = militarService;
    }

    @GetMapping
    public String visitantes(ModelMap model) {
        model.addAttribute("visitantesMilitares", militarService.listarRegistros());
        model.addAttribute("visitantesCivis", civilservice.listarRegistros());
        return "visitantes";
    }

    @PostMapping("/militar")
    public String cadastrarMilitar(VisitanteMilitarDto formulario) {
        militarService.salvarRegistro(formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/civil")
    public String cadastrarCivil(VisitanteCivilDto formulario) {
        civilservice.salvarRegistro(formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/militar/editar/{id}")
    public String atualizarMilitar(@PathVariable Long id, VisitanteMilitarDto formulario) {
        militarService.atualizarRegistro(id, formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/civil/editar/{id}")
    public String atualizarCivil(@PathVariable Long id, VisitanteCivilDto formulario) {
        civilservice.atualizarRegistro(id, formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/militar/remover/{id}")
    public String apagarEntrada(@PathVariable Long id) {
        militarService.apagarRegistro(id);
        return "redirect:/visitantes";
    }

    @PostMapping("/civil/remover/{id}")
    public String apagarCivil(@PathVariable Long id) {
        civilservice.apagarRegistro(id);
        return "redirect:/visitantes";
    }

    @PostMapping("/apagar-tudo")
    public String apagarTudo() {
        militarService.apagarTodos();
        civilservice.apagarTodos();
        return "redirect:/visitantes";
    }
}
