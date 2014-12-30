package br.com.ramazzini.model.tela;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_tela", sequenceName = "seq_tela", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "tela", uniqueConstraints = @UniqueConstraint(columnNames = "nm_tela"))
public class Tela extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_tela")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_tela")
    private Long id;
    
    @Column(name = "nm_tela")
    @NotNull
    @Size(min = 1, max = 50)
    private String nome;
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = true;
	
	@Column(name = "ic_publico")
	@NotNull
	private boolean publico = true;
	
	//@ManyToMany(mappedBy="telas")
	//private List<Perfil> perfis;
	
	@OneToMany(mappedBy = "tela",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Collection<PerfilTela> perfis;	
	
	@OneToMany(mappedBy="tela")
	private List<Acao> acoes;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_modulo")
	private Modulo modulo;	
	
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	/*public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}*/

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

}