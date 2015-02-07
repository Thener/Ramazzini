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

import java.util.List;

import javax.ejb.Stateless;

import br.com.ramazzini.dao.funcao.FuncaoDao;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.riscoOcupacional.RiscoOcupacional;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class FuncaoService extends AbstractServiceImpl<Funcao> {

    public List<Funcao> recuperarPorEmpresa(Empresa empresa) {
    	return (!empresa.isNovo()) ? ((FuncaoDao) getDao()).recuperarPorEmpresa(empresa) : null;
    }	
    
    public List<Funcao> recuperarPorNome(Empresa empresa, String nomeFuncao) {
    	return ((FuncaoDao) getDao()).recuperarPorNomeEmpresa(empresa, nomeFuncao);
    }    
    
    public List<Funcao> recuperarPorNome(String nomeFuncao) {
    	return ((FuncaoDao) getDao()).recuperarPorNome(nomeFuncao);
    } 
    
    public List<Procedimento> recuperarProcedimentosPor(Funcao funcao) {
    	return ((FuncaoDao) getDao()).recuperarProcedimentosPor(funcao);
    }     

    public List<RiscoOcupacional> recuperarRiscosOcupacionais(Funcao funcao) {
    	return ((FuncaoDao) getDao()).recuperarRiscosOcupacionais(funcao);
    }    
}