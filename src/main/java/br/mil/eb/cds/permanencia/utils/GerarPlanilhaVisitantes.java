package br.mil.eb.cds.permanencia.utils;

import br.mil.eb.cds.permanencia.model.VisitanteCivil;
import br.mil.eb.cds.permanencia.model.VisitanteMilitar;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GerarPlanilhaVisitantes {

    public static byte[] exportarDados(List<VisitanteMilitar> militares, List<VisitanteCivil> civis) {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetMilitares = workbook.createSheet("Militares");
            Sheet sheetCivis = workbook.createSheet("Civis");

            CellStyle estiloCabecalho = PlanilhaUtil.criarEstiloCabecalho(workbook);
            CellStyle estiloCelula = PlanilhaUtil.criarEstiloCelulaDados(workbook);

            String[] titulosCabecalhoTabelaCivis = PlanilhaUtil.criarCabecalho(VisitanteCivil.class);
            String[] titulosCabecalhoTabelaMilitares = PlanilhaUtil.criarCabecalho(VisitanteMilitar.class);


            PlanilhaUtil.criarCabecalhoTabela(sheetCivis, titulosCabecalhoTabelaCivis, estiloCabecalho);
            PlanilhaUtil.criarCabecalhoTabela(sheetMilitares, titulosCabecalhoTabelaMilitares, estiloCabecalho);

            PlanilhaUtil.listToPlanilha(civis, sheetCivis, estiloCelula);
            PlanilhaUtil.listToPlanilha(militares, sheetMilitares, estiloCelula);

            PlanilhaUtil.ajustarLarguraColunasAoConteudo(titulosCabecalhoTabelaCivis, sheetCivis);
            PlanilhaUtil.ajustarLarguraColunasAoConteudo(titulosCabecalhoTabelaMilitares, sheetMilitares);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
