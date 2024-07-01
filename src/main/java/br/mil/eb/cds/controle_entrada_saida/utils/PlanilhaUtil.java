package br.mil.eb.cds.controle_entrada_saida.utils;

import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

public class PlanilhaUtil {
    public static CellStyle criarEstiloCabecalho(Workbook workbook) {
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
        return estiloCabecalho;
    }

    public static CellStyle criarEstiloCelulaDados(Workbook workbook) {
        CellStyle estiloCelula = workbook.createCellStyle();
        estiloCelula.setBorderTop(BorderStyle.THIN);
        estiloCelula.setBorderBottom(BorderStyle.THIN);
        estiloCelula.setBorderRight(BorderStyle.THIN);
        estiloCelula.setBorderLeft(BorderStyle.THIN);
        return estiloCelula;
    }

    public static void ajustarLarguraColunasAoConteudo(String[] titulosCabecalho, Sheet planilha) {
        for (int i = 0; i < titulosCabecalho.length; i++) {
            planilha.autoSizeColumn(i);
        }
    }

    public static void criarCabecalhoTabela(Sheet planilha, String[] titulosCabecalho, CellStyle estiloCelula) {
        Row cabecalho = planilha.createRow(0);
        for (int i = 0; i < titulosCabecalho.length; i++) {
            Cell celula = cabecalho.createCell(i);
            celula.setCellValue(titulosCabecalho[i]);
            celula.setCellStyle(estiloCelula);
        }
    }

    public static <T> void listToPlanilha(List<T> dados, Sheet planilha, CellStyle estiloCelula) {
        int numeroLinha = 1;
        for (T dado : dados) {
            Row linha = planilha.createRow(numeroLinha++);
            Field[] campos = dado.getClass().getDeclaredFields();
            int indiceCelula = 0;

            for (Field campo : campos) {
                if (!campo.getName().equals("id")) {
                    campo.setAccessible(true);
                    try {
                        Object valor = campo.get(dado);
                        if (valor != null) {
                            linha.createCell(indiceCelula++).setCellValue(valor.toString());
                        } else {
                            linha.createCell(indiceCelula++).setCellValue("");
                        }
                    } catch (IllegalAccessException e) {
                        linha.createCell(indiceCelula++).setCellValue("");
                    }
                }
            }
            for (int i = 0; i < linha.getLastCellNum(); i++) {
                linha.getCell(i).setCellStyle(estiloCelula);
            }
        }
    }

    public static String[] criarCabecalho(Class<?> entidade) {
        // .filter(campo -> !campo.getName().equals("id")) trecho responsavel por ignorar campo chamado 'id' da entidade, ou seja,
        // o atributo 'id' não vai entrar na planilha
        return Stream.of(entidade.getDeclaredFields())
                .filter(campo -> !campo.getName().equals("id"))
                .map(campo -> formatarNomeCampo(campo.getName()))
                .toArray(String[]::new);
    }

    /**
     * Método responsável por converter de 'camelCase' para 'camel Case' (acrescentando espaços antes de cada letra maiúscula) e tornando o texto em maiúsculas
     */
    private static String formatarNomeCampo(String nomeCampo) {
        StringBuilder nomeFormatado = new StringBuilder();
        char[] caracteres = nomeCampo.toCharArray();

        for (char c : caracteres) {
            if (Character.isUpperCase(c)) {
                nomeFormatado.append(" ");
            }
            nomeFormatado.append(c);
        }
        return nomeFormatado.toString().trim().toUpperCase();
    }

}
