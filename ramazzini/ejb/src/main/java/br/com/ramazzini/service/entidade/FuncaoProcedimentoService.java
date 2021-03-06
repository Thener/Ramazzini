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

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import br.com.ramazzini.dao.funcaoProcedimento.FuncaoProcedimentoDao;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcaoProcedimento.FuncaoProcedimento;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class FuncaoProcedimentoService extends AbstractServiceImpl<FuncaoProcedimento> {

    public FuncaoProcedimento recuperarPor(Funcao funcao, Procedimento procedimento) {
    	return (!funcao.isNovo() && !procedimento.isNovo()) ? 
    			((FuncaoProcedimentoDao) getDao()).recuperarPor(funcao, procedimento) : null;
    }
    
    public List<FuncaoProcedimento> recuperarPorFuncao(Funcao funcao) {
    	return (funcao != null && !funcao.isNovo()) ? ((FuncaoProcedimentoDao) getDao()).recuperarPorFuncao(funcao) : null;
    }	

    public List<FuncaoProcedimento> recuperarPorFuncaoAnteriorAtual(Funcao funcaoAnterior, Funcao funcaoAtual) {
    	
    	List<FuncaoProcedimento> funcoesProcedimentos = (!funcaoAnterior.isNovo() && !funcaoAtual.isNovo()) ? 
    			((FuncaoProcedimentoDao) getDao()).recuperarPorFuncaoAnteriorAtual(funcaoAnterior, funcaoAtual) : null;
    	
    	// Eliminando repetições:
    	if (funcoesProcedimentos.size() >= 2) {
    		HashMap<Procedimento, FuncaoProcedimento> map = new HashMap<Procedimento, FuncaoProcedimento>();
    		for (FuncaoProcedimento fp : funcoesProcedimentos) {
    			map.put(fp.getProcedimento(), fp);
    		}
    		funcoesProcedimentos.clear();
    	    for (FuncaoProcedimento fp : map.values()) {
    	    	funcoesProcedimentos.add(fp);
    	    }
    		
    	}
    	
    	return funcoesProcedimentos;
    }	
    
    public List<FuncaoProcedimento> recuperarPorProcedimento(Funcao funcao, Procedimento procedimento) {
    	return ((FuncaoProcedimentoDao) getDao()).recuperarPorProcedimento(funcao, procedimento);
    }
    
    public List<Procedimento> recuperarProcedimentosDaFuncao(Funcao funcao, TipoExameClinico tipoExameClinico) {
    	return (!funcao.isNovo()) ? ((FuncaoProcedimentoDao) getDao()).recuperarProcedimentosDaFuncao(funcao, tipoExameClinico) : null;
    }
    
    public List<Procedimento> recuperarProcedimentosFuncaoAnteriorAtual(Funcao funcaoAnterior, Funcao funcaoAtual, TipoExameClinico tipoExameClinico) {
    	return (!funcaoAnterior.isNovo() && !funcaoAtual.isNovo()) ? 
    			((FuncaoProcedimentoDao) getDao()).recuperarProcedimentosFuncaoAnteriorAtual(funcaoAnterior, funcaoAtual, tipoExameClinico) : null;
    }
    
    public Integer recuperarRetornoPor(Funcao funcao, Procedimento procedimento, TipoExameClinico tipoExameClinico) {
    	return (!funcao.isNovo() && !procedimento.isNovo()) ? 
    			((FuncaoProcedimentoDao) getDao()).recuperarRetornoPor(funcao, procedimento, tipoExameClinico) : null;    	
    }
    
    public boolean verificarExigencia(Funcao funcao, Procedimento procedimento, TipoExameClinico tipoExameClinico) {
    	return (!funcao.isNovo() && !procedimento.isNovo()) ? 
    			((FuncaoProcedimentoDao) getDao()).verificarExigencia(funcao, procedimento, tipoExameClinico) : false;
    }

}