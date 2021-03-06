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

import br.com.ramazzini.dao.guia.GuiaDao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.guia.Guia;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class GuiaService extends AbstractServiceImpl<Guia> {

    public List<Guia> recuperarPor(Funcionario funcionario, Date dataEmissao) {
    	return ((GuiaDao) getDao()).recuperarPor(funcionario, dataEmissao);
    }	
	
    public List<Guia> recuperarPorFuncionario(Funcionario funcionario) {
    	return ((GuiaDao) getDao()).recuperarPorFuncionario(funcionario);
    }	
}