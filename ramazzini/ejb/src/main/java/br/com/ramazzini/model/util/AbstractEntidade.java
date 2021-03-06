package br.com.ramazzini.model.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.util.Contexto;
import br.com.ramazzini.util.TimeFactory;


/**
 * <p>
 * Classe genérica que possui as propriedades comuns das entidades.
 * </p>
 */
@MappedSuperclass
public abstract class AbstractEntidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts_inclusao", columnDefinition = "timestamp without time zone")
	@Basic(fetch = FetchType.LAZY)
	private Date dataInclusao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts_alteracao", columnDefinition = "timestamp without time zone")
	@Basic(fetch = FetchType.LAZY)
	private Date dataAlteracao;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_usuario_inclusao")
	private Usuario usuarioInclusao;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_usuario_alteracao")
	private Usuario usuarioAlteracao;


	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}


	public abstract Long getId();
	
	
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	/**
	 * Verifica se a entidade eh nova (se ainda nao foi persistida).
	 *
	 * @return <code>true</code> se o id da entidade for nulo ou igual a zero e
	 *         <code>false</code> caso contrario
	 */
	public boolean isNovo() {
		return (getId() == null) || Long.valueOf(0).equals(getId());
	}

	/**
	 * Insere a data de inclusao e usuario de inclusão antes de persistir.
	 */
	@PrePersist
	public void prePersist() {
		setDataInclusao(TimeFactory.createDataHora());
		if (getUsuarioInclusao() == null) {
			setUsuarioInclusao(getUsuarioLogado());
		}
	}

	/**
	 * Insere a data e usuário de alteracao antes de um update. 
	 */
	@PreUpdate
	public void preUpdate() {
		setDataAlteracao(TimeFactory.createDataHora());
		setUsuarioAlteracao(getUsuarioLogado());
	}	

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}	

	@Override
	public String toString() {
		return getId().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntidade other = (AbstractEntidade) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
	public Usuario getUsuarioLogado() {
		return (Usuario) getSession().getAttribute("usuarioLogado");
	}
	
	private HttpSession getSession() {
		 HttpServletRequest request = Contexto.getCurrentInstance().getRequest(); 
		 return request.getSession();
	}
}
