package br.com.ramazzini.model.perfilTela;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.util.AbstractEntidade;


@Entity
@XmlRootElement
@Table(name = "perfil_tela")
public class PerfilTela extends AbstractEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_perfil_tela", sequenceName = "seq_perfil_tela", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil_tela")
    @Column(name = "cd_perfil_tela")
    private Long id;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name="cd_perfil")
    private Perfil perfil;
 
	@ManyToOne
	@NotNull
    @JoinColumn(name="cd_tela")
    private Tela tela;
    
	//http://www.developerscrappad.com/139/java/java-ee/ejb3-jpa-entity-many-to-many-relationship/
	//usando mappedBy, não funciona
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "perfil_tela_acao",
	joinColumns = {@JoinColumn(name = "cd_perfil_tela")},
	   inverseJoinColumns = {@JoinColumn(name = "cd_acao")})	
	private Set<Acao> acoes;  
	
	public Long getId() {
		return id;
	}

	public Set<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(Set<Acao> acoes) {
		this.acoes = acoes;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Tela getTela() {
		return tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
	}
	
	
}
