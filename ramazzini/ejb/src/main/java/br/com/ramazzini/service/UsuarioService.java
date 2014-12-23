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
package br.com.ramazzini.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ramazzini.dao.usuario.UsuarioDao;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class UsuarioService extends AbstractServiceImpl<Usuario> {

    @Inject
    private Logger log;

    public Usuario recuperarPorLogin(String login) {
        
    	log.info("Verificando " + login);
    	
    	UsuarioDao dao = ((UsuarioDao) getDao());
    	
        return dao.recuperarPorLogin(login);
    }
    
    public Usuario autenticar(String login, String senha) {
    	
    	Usuario usuario = recuperarPorLogin(login);
    	
    	if (usuario == null) {
    		log.info("Usuário " + login + " não encontrado.");
    		return null;
    	}
    	
    	if (!usuario.getSenha().equals(senha)) {
    		log.info("Senha do usuário " + login + " não confere.");
    		return null;
    	}
    	
    	if (!usuario.isAtivo()) {
    		log.info("Usuário " + login + " não está ativo no Sistema.");
    		return null;    		
    	}
    	
    	log.info("Usuário " + login + " autenticado com sucesso.");
    	return usuario;
    }
    public List<Usuario> recuperarPorTrechoLogin(String login) {
        
    	log.info("recuerando usuario pelo login: " + login);
    	
    	UsuarioDao dao = ((UsuarioDao) getDao());
    	
        return dao.recuperarPorTrechoLogin(login);
    }
}
