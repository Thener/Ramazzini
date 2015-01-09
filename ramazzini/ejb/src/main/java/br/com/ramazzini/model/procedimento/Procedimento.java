package br.com.ramazzini.model.procedimento;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_procedimento", sequenceName = "seq_procedimento", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "procedimento", uniqueConstraints = @UniqueConstraint(columnNames = "nm_procedimento"))
public class Procedimento extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_procedimento")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_procedimento")
    private Long id;
    
    @Column(name = "nm_procedimento")
    @NotNull
    @Size(min = 1, max = 100)
    private String nome;
    
    @Column(name = "nm_abreviado")
    @Size(max = 50)
    private String nomeAbreviado;
    
    @Column(name = "sg_procedimento")
    @Size(max = 10)
    private String sigla;    
    
	@Column(name = "tp_procedimento", length = 5)
    @NotNull 
    private String tipoProcedimento;
    
    @Column(name = "ic_sistema")
    @NotNull    
    private boolean sistema = false;

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

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	// Não colocar o get com o mesmo nome do atributo int ou string
	public TipoProcedimento getTipoProcedimentoEnum() {
		if (this.tipoProcedimento != null) {
			return TipoProcedimento.valueOf(this.tipoProcedimento);
		}
		return null;
	}

	// Não colocar o set com o mesmo nome do atributo int ou string	
	public void setTipoProcedimentoEnum(TipoProcedimento tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento.getValue();
	}

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public boolean isSistema() {
		return sistema;
	}

	public void setSistema(boolean sistema) {
		this.sistema = sistema;
	}
    
    

}