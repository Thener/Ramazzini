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

import br.com.ramazzini.dao.avaliacaoClinica.AvaliacaoClinicaDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.service.util.AbstractServiceImpl;
import br.com.ramazzini.util.TimeFactory;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class AvaliacaoClinicaService extends AbstractServiceImpl<AvaliacaoClinica> {
	
	@Inject private AvaliacaoClinicaProcedimentoService avaliacaoClinicaProcedimentoService;
	@Inject private FuncaoService funcaoService;

    public AvaliacaoClinica recuperarAvaliacaoClinicaEmAndamentoPor(Funcionario funcionario) {
    	return (!funcionario.isNovo()) ? ((AvaliacaoClinicaDao) getDao()).recuperarAvaliacaoClinicaEmAndamentoPor(funcionario) : null;
    }
    
    public List<AvaliacaoClinica> recuperarPorFuncionario(Funcionario funcionario) {
    	return (!funcionario.isNovo()) ? ((AvaliacaoClinicaDao) getDao()).recuperarPorFuncionario(funcionario) : null;
    }	
    
    public List<Procedimento> recuperarProcedimentosPor(AvaliacaoClinica avaliacaoClinica) {
    	return (!avaliacaoClinica.isNovo()) ? ((AvaliacaoClinicaDao) getDao()).recuperarProcedimentosPor(avaliacaoClinica) : null;
    }
    
    public AvaliacaoClinica recuperarUltimaValidaPorFuncionario(Funcionario funcionario) {
    	return (!funcionario.isNovo()) ? ((AvaliacaoClinicaDao) getDao()).recuperarUltimaValidaPorFuncionario(funcionario) : null;
    }
    
    public Date calcularDataRetornoAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica, List<AvaliacaoClinicaProcedimento> procedimentosAvClinica) {
    	
    	List<AvaliacaoClinicaProcedimento> procedimentos;
    	
    	if (procedimentosAvClinica != null && procedimentosAvClinica.size() > 0) {
    		procedimentos = procedimentosAvClinica;
    	} else {
    		procedimentos = avaliacaoClinicaProcedimentoService.recuperarPorAvaliacaoClinica(avaliacaoClinica);
    	}
    	
    	/*
    	if (avaliacaoClinica.getProcedimentos() != null && avaliacaoClinica.getProcedimentos().size() > 0) {
    		procedimentos = avaliacaoClinica.getProcedimentos(); 
    	} else {
    		procedimentos = avaliacaoClinicaProcedimentoService.recuperarPorAvaliacaoClinica(avaliacaoClinica);
    	}
    	*/
    	
    	Date menorData = null;
    	
    	if (procedimentos != null && procedimentos.size() > 0) {
    		for (AvaliacaoClinicaProcedimento acp : procedimentos) {
    			if (menorData == null || acp.getDataRetorno().before(menorData)) {
    				menorData = acp.getDataRetorno();
    			}
    		}
    	} else if (funcaoService.existeRiscoErgonomico(avaliacaoClinica.getFuncaoAtual())){
    		menorData = TimeFactory.somarMeses(avaliacaoClinica.getDataRealizacao(), 12);
    	} else {
    		Integer idade = avaliacaoClinica.getFuncionario().getIdade();
    		Integer retorno = (idade < 18 || idade > 44) ? 12 : 24; 
   			menorData = TimeFactory.somarMeses(avaliacaoClinica.getDataRealizacao(), retorno);
    	}
    	
    	return menorData;
    }
}