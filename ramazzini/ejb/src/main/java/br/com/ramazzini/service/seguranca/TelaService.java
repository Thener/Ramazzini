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
package br.com.ramazzini.service.seguranca;

import java.util.List;

import javax.ejb.Stateless;

import br.com.ramazzini.dao.tela.TelaDao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class TelaService extends AbstractServiceImpl<Tela> {

    public Tela recuperarPorModuloTela(String nomeModulo, String nomeTela) {
        
    	TelaDao dao = ((TelaDao) getDao());
    	
        return dao.recuperarPorModuloTela(nomeModulo, nomeTela);
    }
    
    public List<Tela> recuperarTelasNaoPublicas(String... orderBy) {
    	return ((TelaDao) getDao()).recuperarTelasNaoPublicas(orderBy);
    }
    
    public List<Tela> recuperarTelasPublicas(String... orderBy) {
    	return ((TelaDao) getDao()).recuperarTelasPublicas(orderBy);
    }    
    
    public List<Tela> recuperarPorModulo(Modulo modulo, boolean publico, String orderBy) {
    	return ((TelaDao) getDao()).recuperarPorModulo(modulo, publico, orderBy);
    } 
    
    public List<Tela> recuperarPorPerfil(Perfil perfil) {
    	return ((TelaDao) getDao()).recuperarPorPerfil(perfil);
    } 
}