package br.com.ramazzini.model.feriado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_feriado", sequenceName = "seq_feriado", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "feriado", uniqueConstraints = @UniqueConstraint(columnNames = "nm_feriado"))
public class Feriado extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_feriado")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_feriado")
    private Long id;
    
    @Column(name = "nm_feriado", length = 100)
    @NotNull
    private String nome;
    
    @Column(name = "no_dia")
    @NotNull
    private Integer dia;
    
    @Column(name = "no_mes")
    @NotNull
    private Integer mes;
    
    @Column(name = "ic_movel")
    @NotNull
    private boolean movel;    
    
    @Column(name = "no_ano_referencia")
    private Integer anoReferencia;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	public DiasMes getDiaEnum() {
		return (this.dia != null) ? DiasMes.parse(this.dia) : null;
	}

	public void setDiaEnum(DiasMes dia) {
		setDia(dia.getValue());
	}	

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public Mes getMesEnum() {
		return (this.mes != null) ? Mes.parse(this.mes) : null;
	}

	public void setMesEnum(Mes mes) {
		setMes(mes.getValue());
	}	

	public boolean isMovel() {
		return movel;
	}

	public void setMovel(boolean movel) {
		this.movel = movel;
	}

	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public Long getId() {
		return id;
	}
}