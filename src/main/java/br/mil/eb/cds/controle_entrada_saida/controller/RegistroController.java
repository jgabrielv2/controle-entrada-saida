package br.mil.eb.cds.controle_entrada_saida.controller;

import br.mil.eb.cds.controle_entrada_saida.dto.FormularioDTO;
import br.mil.eb.cds.controle_entrada_saida.model.Entrada;
import br.mil.eb.cds.controle_entrada_saida.model.Saida;
import br.mil.eb.cds.controle_entrada_saida.service.RegistroService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/registros")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public String home(ModelMap model) {
        model.addAttribute("entradas", registroService.listarEntradas());
        model.addAttribute("saidas", registroService.listarSaidas());
        return "home";
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

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetEntradas = workbook.createSheet("Entradas");
            Sheet sheetSaidas = workbook.createSheet("Saidas");

            // cabecalhos
            Row headerEntradas = sheetEntradas.createRow(0);
            headerEntradas.createCell(0).setCellValue("Div/Sec");
            headerEntradas.createCell(1).setCellValue("Posto/Grad");
            headerEntradas.createCell(2).setCellValue("Nome de guerra");
            headerEntradas.createCell(3).setCellValue("Hora de entrada");

            Row headerSaidas = sheetSaidas.createRow(0);
            headerSaidas.createCell(0).setCellValue("Div/Sec");
            headerSaidas.createCell(1).setCellValue("Posto/Grad");
            headerSaidas.createCell(2).setCellValue("Nome de guerra");
            headerSaidas.createCell(3).setCellValue("Hora de saida");

            //populando dados das entradas

            int rowNum = 1;

            for (Entrada entrada : entradas) {
                Row row = sheetEntradas.createRow(rowNum++);
                row.createCell(0).setCellValue(entrada.getDivSec());
                row.createCell(1).setCellValue(entrada.getPostoGrad());
                row.createCell(2).setCellValue(entrada.getNomeGuerra());
                row.createCell(3).setCellValue(entrada.getHorario().toString());
            }

            //populando dados das saidas

            rowNum = 1;

            for (Saida saida : saidas) {
                Row row = sheetSaidas.createRow(rowNum++);
                row.createCell(0).setCellValue(saida.getDivSec());
                row.createCell(1).setCellValue(saida.getPostoGrad());
                row.createCell(2).setCellValue(saida.getNomeGuerra());
                row.createCell(3).setCellValue(saida.getHorario().toString());
            }


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registro.xlsx")
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
