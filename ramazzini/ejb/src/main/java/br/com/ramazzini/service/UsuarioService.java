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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.dao.usuario.UsuarioDao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.util.AbstractServiceImpl;
import br.com.ramazzini.util.Md5;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class UsuarioService extends AbstractServiceImpl<Usuario> {

    @Inject
    private Logger log;
    
    @Inject
    private HttpSession session;    

    public Usuario recuperarPorLogin(String login) {
        
    	log.info("Verificando " + login);
    	
    	UsuarioDao dao = ((UsuarioDao) getDao());
    	
        return dao.recuperarPorLogin(login);
    }
    
    public boolean autenticar(String login, String senha) {
    	
    	Usuario usuario = recuperarPorLogin(login);
    	
    	if (usuario == null) {
    		log.info("Usuário " + login + " não encontrado.");
    		return false;
    	}
    	
    	if (!usuario.getSenha().equals(Md5.hashMd5(senha))) {
    		log.info("Senha do usuário " + login + " não confere.");
    		return false;
    	}
    	
    	if (!usuario.isAtivo()) {
    		log.info("Usuário " + login + " não está ativo no Sistema.");
    		return false;    		
    	}
    	
    	//----- Autenticação Ok. Gravando informações na sessão:
    	
    	// Em AcessoFilter, ao tentar acessar as telas do perfil, gera um
    	// LazyInitializationException, então carrego as telas por aqui. 
    	
    	List<Perfil> perfis = new ArrayList<Perfil>();
    	
    	for (Perfil p : usuario.getPerfis()) {
    		Perfil perfil = new Perfil();
    		perfil = p;
    		perfil.setTelas(p.getTelas());
    		perfis.add(perfil);
    	}

    	session.setAttribute("usuario", usuario);
    	session.setAttribute("usuarioPerfis", perfis);
    	
    	log.info("Usuário " + login + " autenticado com sucesso.");
    	
    	return true;
    }
    
    public List<Usuario> recuperarPorTrechoLogin(String login) {
        
    	log.info("recuerando usuario pelo login: " + login);
    	
    	UsuarioDao dao = ((UsuarioDao) getDao());
    	
        return dao.recuperarPorTrechoLogin(login);
    }	
}
