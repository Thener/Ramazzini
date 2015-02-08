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

import br.com.ramazzini.dao.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimentoDao;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinicaProcedimento.AvaliacaoClinicaProcedimento;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class AvaliacaoClinicaProcedimentoService extends AbstractServiceImpl<AvaliacaoClinicaProcedimento> {

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
}