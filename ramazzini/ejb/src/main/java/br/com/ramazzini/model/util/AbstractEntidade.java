package br.com.ramazzini.model.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.ramazzini.model.usuario.Usuario;

/**
 * <p>
 * Classe genérica que possui as propriedades comuns das entidades.
 * </p>
 */
@MappedSuperclass
public abstract class AbstractEntidade implements Serializable {

	private static final long serialVersionUID = 1L;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts_inclusao", columnDefinition = "Date")
	@Basic(fetch = FetchType.LAZY)
	private Date dataInclusao;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ts_alteracao", columnDefinition = "Date")
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

//	/**
//	 * Insere a data de inclusao e usuario de inclusão antes de persistir.
//	 */
//	@PrePersist
//	public void prePersist(Usuario usuarioLogado) {
//		setDataInclusao(TimeFactory.createDataHora());
//		if (getUsuarioInclusao() == null) {
//			setUsuarioInclusao(usuarioLogado);
//		}
//	}
//
//	/**
//	 * Insere a data e usuário de alteracao antes de um update. 
//	 */
//	@PreUpdate
//	public void preUpdate(Usuario usuarioLogado) {
//		setDataAlteracao(TimeFactory.createDataHora());
//		if (getUsuarioAlteracao() == null) {
//			setUsuarioAlteracao(usuarioLogado);
//		}
//
//	}	

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
		Long id = getId();
		StringBuilder s = new StringBuilder();
		s.append(getClass().getName()).append("[id = ").append(id).append(" ]");
		return s.toString();
	}

	/**
	 * Retorna true se os dois objetos passados sao iguais.
	 *
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean equals(final Object o1, final Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}	
}
