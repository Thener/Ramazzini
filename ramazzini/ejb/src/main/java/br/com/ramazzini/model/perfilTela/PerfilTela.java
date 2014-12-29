package br.com.ramazzini.model.perfilTela;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;

@Entity
@Table(name = "perfil_tela")
@IdClass(PerfilTelaId.class)
public class PerfilTela {
	
	@Id
    @ManyToOne
    @JoinColumn(name="cd_perfil")
    private Perfil perfil;
 
	@Id
    @ManyToOne
    @JoinColumn(name="cd_tela")
    private Tela tela;
    
	@ManyToMany(mappedBy="perfisTelas")
	private List<Acao> acoes;    

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
