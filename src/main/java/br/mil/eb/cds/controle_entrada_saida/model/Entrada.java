package br.mil.eb.cds.controle_entrada_saida.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String divSec;

    private String postoGrad;

    private String nomeGuerra;

    private LocalTime horario;

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getDivSec() {
        return divSec;
    }

    public void setDivSec(String divSec) {
        this.divSec = divSec;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
