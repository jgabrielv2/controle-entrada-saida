package br.mil.eb.cds.permanencia.controller.web;

import br.mil.eb.cds.permanencia.model.AcaoSecao;
import br.mil.eb.cds.permanencia.model.VisitanteCivil;
import br.mil.eb.cds.permanencia.model.VisitanteMilitar;
import br.mil.eb.cds.permanencia.service.AcaoSecaoService;
import br.mil.eb.cds.permanencia.service.RegistroVisitanteCivilService;
import br.mil.eb.cds.permanencia.service.RegistroVisitanteMilitarService;
import br.mil.eb.cds.permanencia.utils.GerarPlanilhaControleSecoes;
import br.mil.eb.cds.permanencia.utils.GerarPlanilhaVisitantes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/exportar")
public class ExportacaoWebController {

    private final AcaoSecaoService acaoService;
    private final RegistroVisitanteCivilService civilservice;
    private final RegistroVisitanteMilitarService militarService;


    public ExportacaoWebController(RegistroVisitanteCivilService civilservice, RegistroVisitanteMilitarService militarService, AcaoSecaoService acaoService) {
        this.civilservice = civilservice;
        this.militarService = militarService;
        this.acaoService = acaoService;
    }



    @GetMapping("/visitantes")
    public ResponseEntity<byte[]> exportarDadosVisitantes() {
        List<VisitanteMilitar> militares = militarService.listarRegistros();
        List<VisitanteCivil> civis = civilservice.listarRegistros();

        byte[] planilha = GerarPlanilhaVisitantes.exportarDados(militares, civis);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=visitantes_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm")) + ".xlsx")
                .body(planilha);
    }

    @GetMapping("/secoes")
    public ResponseEntity<byte[]> exportarDadosSecoes() {
        List<AcaoSecao> entradas = acaoService.listarEntradas();
        List<AcaoSecao> saidas = acaoService.listarSaidas();

        byte[] planilha = GerarPlanilhaControleSecoes.exportarDados(entradas, saidas);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registro_secoes_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm")) + ".xlsx")
                .body(planilha);
    }


}
