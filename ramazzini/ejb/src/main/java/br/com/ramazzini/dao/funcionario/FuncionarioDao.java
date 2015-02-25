package br.com.ramazzini.dao.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ramazzini.dao.util.AbstractDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;


public class FuncionarioDao extends AbstractDao<Funcionario> {

	private static final String QUERY_RECUPERAR_POR_EMPRESA = "Funcionario.recuperarPorEmpresa";
	private static final String QUERY_RECUPERAR_POR_NOME_EMPRESA = "Funcionario.recuperarPorNomeEmpresa";
//	private static final String QUERY_RECUPERAR_POR_NOME = "Funcionario.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperarPorEmpresa(Empresa empresa) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_EMPRESA);
		query.setParameter("empresa", empresa);
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> recuperarPorNome(Empresa empresa, String nomeFuncionario) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME_EMPRESA);
		query.setParameter("empresa", empresa);
		query.setParameter("nomeFuncionario", "%"+nomeFuncionario+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}
//	@SuppressWarnings("unchecked")
//	public List<Funcionario> recuperarPorNome(String nomeFuncionario) {
//		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
//		query.setParameter("nomeFuncionario", "%"+nomeFuncionario+"%");
//		try {
//			return query.getResultList();
//		} catch (NoResultException nr) {
//			return null;
//		}
//	}	
	
	public List<Funcionario> recuperarPor(Funcao funcao, List<String> situacoes, Empresa empresa) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Funcionario> criteria = cb.createQuery(Funcionario.class);
        Root<Funcionario> funcionario = criteria.from(Funcionario.class);
        
        List<Predicate> condicoes = new ArrayList<Predicate>();
        
        if(funcao != null){
        	Path<Funcao> atributoFuncao = funcionario.get("funcao");
        	Predicate where = cb.equal(atributoFuncao, funcao);
        	condicoes.add(where);
        }
        if(empresa != null){
        	Path<Funcao> atributoEmpresa = funcionario.get("empresa");
        	Predicate where = cb.equal(atributoEmpresa, empresa);
        	condicoes.add(where);
        }
        if(!situacoes.isEmpty()){
        	Expression<String> atributoSituacao = funcionario.get("situacaoFuncionario");
            Predicate where = atributoSituacao.in(situacoes);
        	condicoes.add(where);
        }
        Predicate[] condicoesComoArray =
    	condicoes.toArray(new Predicate[condicoes.size()]);
    	Predicate todasCondicoes = cb.and(condicoesComoArray);
    	criteria.where(todasCondicoes);
    	TypedQuery<Funcionario> query = getEntityManager().createQuery(criteria);
        
		return query.getResultList();        
	}	
		
}