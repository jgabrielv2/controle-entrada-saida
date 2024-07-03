package br.mil.eb.cds.permanencia.utils;

import br.mil.eb.cds.permanencia.model.AcaoSecao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GerarPlanilhaControleSecoes {

    public static byte[] exportarDados(List<AcaoSecao> dadosEntradas, List<AcaoSecao> dadosSaidas) {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetEntradas = workbook.createSheet("Entradas");
            Sheet sheetSaidas = workbook.createSheet("Saidas");

            CellStyle estiloCabecalho = PlanilhaUtil.criarEstiloCabecalho(workbook);
            CellStyle estiloCelula = PlanilhaUtil.criarEstiloCelulaDados(workbook);

            String[] titulosCabecalhoRegistro = PlanilhaUtil.criarCabecalho(AcaoSecao.class);

            PlanilhaUtil.criarCabecalhoTabela(sheetEntradas, titulosCabecalhoRegistro, estiloCabecalho);
            PlanilhaUtil.criarCabecalhoTabela(sheetSaidas, titulosCabecalhoRegistro, estiloCabecalho);

            PlanilhaUtil.listToPlanilha(dadosEntradas, sheetEntradas, estiloCelula);
            PlanilhaUtil.listToPlanilha(dadosSaidas, sheetSaidas, estiloCelula);
            PlanilhaUtil.ajustarLarguraColunasAoConteudo(titulosCabecalhoRegistro, sheetEntradas);
            PlanilhaUtil.ajustarLarguraColunasAoConteudo(titulosCabecalhoRegistro, sheetSaidas);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

