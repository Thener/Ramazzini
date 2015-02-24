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

import br.com.ramazzini.dao.anamnese.AnamneseDao;
import br.com.ramazzini.model.anamnese.Anamnese;
import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class AnamneseService extends AbstractServiceImpl<Anamnese> {

    public List<Anamnese> recuperarPor(AvaliacaoClinica avaliacaoClinica) {
    	return (avaliacaoClinica != null && !avaliacaoClinica.isNovo()) ? ((AnamneseDao) getDao()).recuperarPor(avaliacaoClinica) : null;
    } 
    
    public Anamnese recuperarAnamneseEmAndamentoPor(AvaliacaoClinica avaliacaoClinica, Profissional medico) {
    	return (!avaliacaoClinica.isNovo()) ? ((AnamneseDao) getDao()).recuperarAnamneseEmAndamentoPor(avaliacaoClinica, medico) : null;
    } 
    
    public Anamnese recuperarAnamneseAnterior(Funcionario funcionario, Date dt) {
    	return (!funcionario.isNovo()) ? ((AnamneseDao) getDao()).recuperarAnamneseAnterior(funcionario, dt) : null;
    }    
}