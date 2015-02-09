package br.com.ramazzini.dao.empresa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import br.com.ramazzini.model.grupo.Grupo;


public class EmpresaDao extends AbstractDao<Empresa> {

	private static final String QUERY_RECUPERAR_POR_NOME = "Empresa.recuperarPorNome";
	
	@SuppressWarnings("unchecked")
	public List<Empresa> recuperarPorNome(String nome) {
		Query query = createNamedQuery(QUERY_RECUPERAR_POR_NOME);
		query.setParameter("nome", "%"+nome+"%");
		try {
			return query.getResultList();
		} catch (NoResultException nr) {
			return null;
		}
	}	
	
	public List<Empresa> recuperarPor(List<String> situacoes, Grupo grupo, Date dataSelecionada) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Empresa> criteria = cb.createQuery(Empresa.class);
        Root<Empresa> empresa = criteria.from(Empresa.class);
        
        List<Predicate> condicoes = new ArrayList<Predicate>();
        
        if(grupo != null){
        	Path<Funcao> atributoEmpresa = empresa.get("grupo");
        	Predicate where = cb.equal(atributoEmpresa, grupo);
        	condicoes.add(where);
        }
        if(!situacoes.isEmpty()){
        	Expression<String> atributoSituacao = empresa.get("situacaoEmpresa");
            Predicate where = atributoSituacao.in(situacoes);
        	condicoes.add(where);
        }
        if(dataSelecionada != null){
        	Calendar data =  Calendar.getInstance();
        	data.setTime(dataSelecionada);
	        Path<Date> dataInclusao = empresa.get("dataInclusao");
	        Predicate whereDataInclusao = cb.and(
	                cb.equal(cb.function("year", Integer.class, dataInclusao), data.get(Calendar.YEAR)),
	                cb.equal(cb.function("month", Integer.class, dataInclusao), data.get(Calendar.MONTH)+1),
	                cb.equal(cb.function("day", Integer.class, dataInclusao), data.get(Calendar.DATE)));
	        condicoes.add(whereDataInclusao);
        }
        Predicate[] condicoesComoArray =
    	condicoes.toArray(new Predicate[condicoes.size()]);
    	Predicate todasCondicoes = cb.and(condicoesComoArray);
    	criteria.where(todasCondicoes);
    	TypedQuery<Empresa> query = getEntityManager().createQuery(criteria);
        
		return query.getResultList();        
	}	
		
}