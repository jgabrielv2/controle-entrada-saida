package br.mil.eb.cds.permanencia.model;

import br.mil.eb.cds.permanencia.enums.TipoAcaoSecao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "acao_secao")
public class AcaoSecao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoAcaoSecao tipo;

    @Size(max = 16)
    @Column(name = "div_sec", length = 16)
    private String divSec;

    @Size(max = 8)
    @Column(name = "posto_grad", length = 8)
    private String postoGrad;

    @Size(max = 32)
    @Column(name = "nome_guerra", length = 32)
    private String nomeGuerra;

    @Column(name = "horario")
    private LocalTime horario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAcaoSecao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcaoSecao tipo) {
        this.tipo = tipo;
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
        this.nomeGuerra = nomeGuerra.toUpperCase();
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "AcaoSecao{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcaoSecao acaoSecao)) return false;
        return Objects.equals(id, acaoSecao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}