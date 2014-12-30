package br.com.ramazzini.model.perfilTela;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.util.AbstractEntidade;


@Entity
@XmlRootElement
@Table(name = "perfil_tela")
@IdClass(PerfilTelaId.class)
public class PerfilTela extends AbstractEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cd_perfil_tela")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="cd_perfil")
    private Perfil perfil;
 
	@ManyToOne
    @JoinColumn(name="cd_tela")
    private Tela tela;
    
	@ManyToMany(mappedBy="perfisTelas")
	private List<Acao> acoes;  
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
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
