package br.mil.eb.cds.controle_entrada_saida.controller;

import br.mil.eb.cds.controle_entrada_saida.dto.RegistroVisitanteCivilDTO;
import br.mil.eb.cds.controle_entrada_saida.dto.RegistroVisitanteMilitarDTO;
import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteCivil;
import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteMilitar;
import br.mil.eb.cds.controle_entrada_saida.service.RegistroVisitanteCivilService;
import br.mil.eb.cds.controle_entrada_saida.service.RegistroVisitanteMilitarService;
import br.mil.eb.cds.controle_entrada_saida.utils.GerarPlanilhaVisitantes;
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
@RequestMapping("/visitantes")
public class RegistroVisitantesController {

    private final RegistroVisitanteCivilService civilservice;
    private final RegistroVisitanteMilitarService militarService;


    public RegistroVisitantesController(RegistroVisitanteCivilService civilservice, RegistroVisitanteMilitarService militarService) {
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
    public String cadastrarMilitar(RegistroVisitanteMilitarDTO formulario) {
        militarService.salvarRegistro(formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/civil")
    public String cadastrarCivil(RegistroVisitanteCivilDTO formulario) {
        civilservice.salvarRegistro(formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/militar/editar/{id}")
    public String atualizarMilitar(@PathVariable Long id, RegistroVisitanteMilitarDTO formulario) {
        militarService.atualizarRegistro(id, formulario);
        return "redirect:/visitantes";
    }

    @PostMapping("/civil/editar/{id}")
    public String atualizarCivil(@PathVariable Long id, RegistroVisitanteCivilDTO formulario) {
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

    @GetMapping("/exportar")
    public ResponseEntity<byte[]> exportarDados() {
        List<RegistroVisitanteMilitar> militares = militarService.listarRegistros();
        List<RegistroVisitanteCivil> civis = civilservice.listarRegistros();

        byte[] planilha = GerarPlanilhaVisitantes.exportarDados(militares, civis);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=visitantes_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm")) + ".xlsx")
                .body(planilha);
    }
}
