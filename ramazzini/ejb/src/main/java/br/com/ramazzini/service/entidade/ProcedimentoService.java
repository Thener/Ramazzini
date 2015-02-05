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

import br.com.ramazzini.dao.procedimento.ProcedimentoDao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoExameClinico;
import br.com.ramazzini.model.procedimento.TipoProcedimento;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ProcedimentoService extends AbstractServiceImpl<Procedimento> {

    public List<Procedimento> recuperarPorNome(String nome) {
    	return ((ProcedimentoDao) getDao()).recuperarPorNome(nome);
    }	
    
    public List<Procedimento> recuperarPorTipoProcedimento(TipoProcedimento tipoProcedimento) {
    	return ((ProcedimentoDao) getDao()).recuperarPorTipoProcedimento(tipoProcedimento);
    }  
    
    public Procedimento recuperarPorTipoExameClinico(TipoExameClinico tipoExameClinico) {
    	return ((ProcedimentoDao) getDao()).recuperarPorTipoExameClinico(tipoExameClinico);
    }     

}