package br.mil.eb.cds.controle_entrada_saida.utils;

import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteCivil;
import br.mil.eb.cds.controle_entrada_saida.model.RegistroVisitanteMilitar;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GerarPlanilhaVisitantes {

    public static byte[] exportarDados(List<RegistroVisitanteMilitar> militares, List<RegistroVisitanteCivil> civis) {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetMilitares = workbook.createSheet("Militares");
            Sheet sheetCivis = workbook.createSheet("Civis");

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
            Row headerMilitares = sheetMilitares.createRow(0);
            String[] titulosCabecalhoMilitares = {"OM", "Posto/Grad", "Nome de guerra", "Ramal", "Hora de entrada", "Hora de saída"};
            for (int i = 0; i < titulosCabecalhoMilitares.length; i++) {
                Cell celula = headerMilitares.createCell(i);
                celula.setCellValue(titulosCabecalhoMilitares[i]);
                celula.setCellStyle(estiloCabecalho);
            }

            Row headerCivis = sheetCivis.createRow(0);
            String[] titulosCabecalhoCivis = {"Nome completo", "Empresa", "Telefone", "Hora de entrada", "Hora de saida"};
            for (int i = 0; i < titulosCabecalhoCivis.length; i++) {
                Cell celula = headerCivis.createCell(i);
                celula.setCellValue(titulosCabecalhoCivis[i]);
                celula.setCellStyle(estiloCabecalho);
            }


            //populando dados de militares
            int rowNum = 1;

            for (RegistroVisitanteMilitar militar : militares) {
                Row row = sheetMilitares.createRow(rowNum++);
                row.createCell(0).setCellValue(militar.getOm());
                row.createCell(1).setCellValue(militar.getPostoGrad());
                row.createCell(2).setCellValue(militar.getNomeGuerra());
                row.createCell(3).setCellValue(militar.getRamal());
                row.createCell(4).setCellValue(militar.getHoraEntrada().toString());
                row.createCell(5).setCellValue(militar.getHoraSaida().toString());
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    row.getCell(i).setCellStyle(estiloCelula);
                }
            }

            //populando dados de civis
            rowNum = 1;
// "Nome completo", "Empresa", "Telefone", "Hora de entrada", "Hora de saida"
            for (RegistroVisitanteCivil civil : civis) {
                Row row = sheetCivis.createRow(rowNum++);
                row.createCell(0).setCellValue(civil.getNomeCompleto());
                row.createCell(1).setCellValue(civil.getEmpresa());
                row.createCell(2).setCellValue(civil.getNumeroTelefone());
                row.createCell(3).setCellValue(civil.getHoraEntrada().toString());
                row.createCell(4).setCellValue(civil.getHoraSaida().toString());
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    row.getCell(i).setCellStyle(estiloCelula);
                }
            }

            // Ajuste automático da largura das colunas
            for (int i = 0; i < titulosCabecalhoMilitares.length; i++) {
                sheetMilitares.autoSizeColumn(i);
            }

            for (int i = 0; i < titulosCabecalhoCivis.length; i++) {
                sheetCivis.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
