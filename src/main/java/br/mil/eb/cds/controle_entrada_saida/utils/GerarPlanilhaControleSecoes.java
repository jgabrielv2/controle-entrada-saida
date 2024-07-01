package br.mil.eb.cds.controle_entrada_saida.utils;

import br.mil.eb.cds.controle_entrada_saida.model.AcaoSecao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GerarPlanilhaControleSecoes {

    public static byte[] exportarDados(List<AcaoSecao> entradas, List<AcaoSecao> saidas) {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetEntradas = workbook.createSheet("Entradas");
            Sheet sheetSaidas = workbook.createSheet("Saidas");

            // estilos para o cabecalho
            CellStyle estiloCabecalho = workbook.createCellStyle();
            Font fonteCabecalho = workbook.createFont();
            fonteCabecalho.setBold(true);
            estiloCabecalho.setFont(fonteCabecalho);
            estiloCabecalho.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            estiloCabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            estiloCabecalho.setBorderBottom(BorderStyle.THIN);
            estiloCabecalho.setBorderTop(BorderStyle.THIN);
            estiloCabecalho.setBorderLeft(BorderStyle.THIN);
            estiloCabecalho.setBorderRight(BorderStyle.THIN);

            // estilos para  celulas
            CellStyle estiloCelula = workbook.createCellStyle();
            estiloCelula.setBorderTop(BorderStyle.THIN);
            estiloCelula.setBorderBottom(BorderStyle.THIN);
            estiloCelula.setBorderRight(BorderStyle.THIN);
            estiloCelula.setBorderLeft(BorderStyle.THIN);


            // cabecalhos
            Row headerEntradas = sheetEntradas.createRow(0);
            String[] titulosCabecalhoEntradas = {"Div/Sec", "Posto/Grad", "Nome de guerra", "Hora de entrada"};
            for (int i = 0; i < titulosCabecalhoEntradas.length; i++) {
                Cell celula = headerEntradas.createCell(i);
                celula.setCellValue(titulosCabecalhoEntradas[i]);
                celula.setCellStyle(estiloCabecalho);
            }

            Row headerSaidas = sheetSaidas.createRow(0);
            String[] titulosCabecalhoSaidas = {"Div/Sec", "Posto/Grad", "Nome de guerra", "Hora de saida"};
            for (int i = 0; i < titulosCabecalhoSaidas.length; i++) {
                Cell celula = headerSaidas.createCell(i);
                celula.setCellValue(titulosCabecalhoSaidas[i]);
                celula.setCellStyle(estiloCabecalho);
            }


            //populando dados das entradas
            int rowNum = 1;

            for (AcaoSecao entrada : entradas) {
                Row row = sheetEntradas.createRow(rowNum++);
                row.createCell(0).setCellValue(entrada.getDivSec());
                row.createCell(1).setCellValue(entrada.getPostoGrad());
                row.createCell(2).setCellValue(entrada.getNomeGuerra());
                row.createCell(3).setCellValue(entrada.getHorario().toString());
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    row.getCell(i).setCellStyle(estiloCelula);
                }
            }

            //populando dados das saidas
            rowNum = 1;

            for (AcaoSecao saida : saidas) {
                Row row = sheetSaidas.createRow(rowNum++);
                row.createCell(0).setCellValue(saida.getDivSec());
                row.createCell(1).setCellValue(saida.getPostoGrad());
                row.createCell(2).setCellValue(saida.getNomeGuerra());
                row.createCell(3).setCellValue(saida.getHorario().toString());
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    row.getCell(i).setCellStyle(estiloCelula);
                }
            }

            // Ajuste automático da largura das colunas
            for (int i = 0; i < titulosCabecalhoEntradas.length; i++) {
                sheetEntradas.autoSizeColumn(i);
            }

            for (int i = 0; i < titulosCabecalhoSaidas.length; i++) {
                sheetSaidas.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
