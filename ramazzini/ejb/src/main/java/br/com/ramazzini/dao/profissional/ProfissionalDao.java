package br.com.ramazzini.dao.profissional;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.util.TimeFactory;


public class ProfissionalDao extends AbstractDao<Profissional> {

	private static final String QUERY_RECUPERAR_POR_DIA_ATENDIMENTO = "Profissional.recuperarPorDiaAtendimento";
	private static final String QUERY_RECUPERAR_POR_NOME = "Profissional.recuperarPorNome";
	private static final String QUERY_RECUPERAR_POR_PAPEL_PROFISSIONAL = "Profissional.recuperarPorPapelProfissional";
	private static final String QUERY_RECUPERAR_POR_USUARIO = "Profissional.recuperarPorUsuario";

	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarPorDiaAtendimento(Date data) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_DIA_ATENDIMENTO);
		query.setParameter("diaSemana", TimeFactory.diaDaSemana(data).getValue());
		try {
			List<Profissional> lista = query.getResultList();
			if (lista.size() == 1 && lista.get(0) == null) {
				lista.clear();
			}
			return lista;
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Profissional> recuperarPorPapelProfissional(String papelProfissional, boolean ativo) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_PAPEL_PROFISSIONAL);
		query.setParameter("papelProfissional", papelProfissional);
		query.setParameter("ativo", ativo);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	public Profissional recuperarPorUsuario(Usuario usuario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_USUARIO);
		query.setParameter("usuario", usuario);
		try {
			return (Profissional) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		}
	}	
		
}