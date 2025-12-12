package DeliverITContasAPagar.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "O valor original é obrigatório!")
    @Column(name = "valor_original",  nullable = false)
    private BigDecimal valorOriginal;

    @NotNull(message = "A data de vencimento é obrigatória!")
    @Column(name = "data_vencimento",  nullable = false)
    private LocalDate dataVencimento;

    @NotNull(message = "A data de pagamento é obrigatória!")
    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "valor_corrigido")
    private BigDecimal valorCorrigido;

    @Column(name = "dias_atraso")
    private Integer diasAtraso;

    @Column(name = "regra_calculo")
    private String regraCalculo;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorCorrigido() {
        return valorCorrigido;
    }

    public void setValorCorrigido(BigDecimal valorCorrigido) {
        this.valorCorrigido = valorCorrigido;
    }

    public Integer getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(Integer diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public String getRegraCalculo() {
        return regraCalculo;
    }

    public void setRegraCalculo(String regraCalculo) {
        this.regraCalculo = regraCalculo;
    }
}
