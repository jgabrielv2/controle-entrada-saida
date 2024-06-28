package br.mil.eb.cds.controle_entrada_saida.model;

import java.time.LocalTime;

public class RegistroVisitanteMilitar {

    private Long id;

    private String om;

    private String postoGrad;

    private String nomeGuerra;

    private String ramal;

    private LocalTime horaEntrada;

    private LocalTime horaSaida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
    }

    public String getPostoGrad() {
        return postoGrad;
    }

    public void setPostoGrad(String postoGrad) {
        this.postoGrad = postoGrad;
    }

    public String getNomeGuerra() {
        return nomeGuerra;
    }

    public void setNomeGuerra(String nomeGuerra) {
        this.nomeGuerra = nomeGuerra;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }
}
