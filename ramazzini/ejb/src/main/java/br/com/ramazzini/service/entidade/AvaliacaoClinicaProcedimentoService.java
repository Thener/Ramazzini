/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.ramazzini.service.entidade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ramazzini.dao.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimentoDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.service.util.AbstractServiceImpl;
import br.com.ramazzini.util.TimeFactory;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class AvaliacaoClinicaProcedimentoService extends AbstractServiceImpl<AvaliacaoClinicaProcedimento> {
	
	@Inject private FuncaoProcedimentoService funcaoProcedimentoService;

    public AvaliacaoClinicaProcedimento recuperarMaisRecentePor(Funcionario funcionario, Procedimento procedimento) {
    	return (!funcionario.isNovo()) ? 
    		((AvaliacaoClinicaProcedimentoDao) getDao()).recuperarMaisRecentePor(funcionario, procedimento) : null;
    } 
    
    public List<AvaliacaoClinicaProcedimento> recuperarPorAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
    	return (!avaliacaoClinica.isNovo()) ? 
    		((AvaliacaoClinicaProcedimentoDao) getDao()).recuperarPorAvaliacaoClinica(avaliacaoClinica) : null;
    }	
 
    public List<AvaliacaoClinicaProcedimento> recuperarPorProcedimento(AvaliacaoClinica avaliacaoClinica, Procedimento procedimento) {
    	return (!avaliacaoClinica.isNovo()) ? 
    		((AvaliacaoClinicaProcedimentoDao) getDao()).recuperarPorProcedimento(avaliacaoClinica, procedimento) : null;
    }
    
    public boolean verificaValidadeDoProcedimento(Funcionario funcionario, Procedimento procedimento, Date dataReferencia) {
    	return (!funcionario.isNovo()) ? 
    		((AvaliacaoClinicaProcedimentoDao) getDao()).verificaValidadeDoProcedimento(funcionario, procedimento, dataReferencia) : null;
    } 
    
    public Date calcularDataRetornoProcedimento(AvaliacaoClinica avaliacaoClinica, 
    		AvaliacaoClinicaProcedimento avaliacaoClinicaProcedimento) {
    
    	if (avaliacaoClinicaProcedimento.getDataRealizacao() == null) {
    		return null;
    	}
    	
    	TipoExameClinico tpe = avaliacaoClinica.getProcedimento().getTipoExameClinicoEnum();
    	
    	if (tpe.equals(TipoExameClinico.DEMISSIONAL)) {
    		return null;
    	}
    	
    	FuncaoProcedimento funcaoProcedimento = 
    			funcaoProcedimentoService.recuperarPor(avaliacaoClinica.getFuncaoAtual(), avaliacaoClinicaProcedimento.getProcedimento());
    	
    	Integer retorno;
    	
    	if (tpe.equals(TipoExameClinico.ADMISSIONAL)) {
    		retorno = funcaoProcedimento.getRetornoAdmissional();
    	} else if (tpe.equals(TipoExameClinico.PERIODICO)) {
    		retorno = funcaoProcedimento.getRetornoPeriodico();
    	} else if (tpe.equals(TipoExameClinico.RETORNO_TRABALHO)) {
    		retorno = funcaoProcedimento.getRetornoRetornoTrabalho();
    	} else {
    		retorno = funcaoProcedimento.getRetornoMudancaFuncao();
    	}
    	
    	if (retorno == null || retorno == 0) {
    		retorno = 6;
    	}
    	
    	return TimeFactory.somarMeses(avaliacaoClinicaProcedimento.getDataRealizacao(), retorno);
    }
}