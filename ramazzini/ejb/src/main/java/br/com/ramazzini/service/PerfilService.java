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

import javax.ejb.Stateless;

import br.com.ramazzini.dao.perfil.PerfilDao;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.util.AbstractServiceImpl;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class PerfilService extends AbstractServiceImpl<Perfil> {

	public List<Perfil> recuperarTudoPorUsuario(Usuario usuario) {
		return ((PerfilDao) getDao()).recuperarTudoPorUsuario(usuario);
	}
	
	public List<Tela> recuperarTelas(Perfil perfil) {
        return ((PerfilDao) getDao()).recuperarTelasPorPerfil(perfil);
	}
	
	public List<PerfilTela> recuperarPerfilTelaPor(Perfil perfil) {
        return ((PerfilDao) getDao()).recuperarPerfilTelaPor(perfil);
	}	

	public boolean incluirTelaVerificandoExistencia(Perfil perfil, Tela tela) {

		List<PerfilTela> perfisTelas = recuperarPerfilTelaPor(perfil);
		
    	boolean ok = true;

    	for (PerfilTela pt : perfisTelas) {
    		if (pt.getTela().getModulo().getNome().equals(tela.getModulo().getNome())
    				&& pt.getTela().getNome().equals(tela.getNome())) {
    			ok = false;
    		}
    	}

    	if (ok) {
    		PerfilTela perfilTela = new PerfilTela();
    		perfilTela.setPerfil(perfil);
    		perfilTela.setTela(tela);
    		perfil.getPerfisTelas().clear();
    		perfil.getPerfisTelas().add(perfilTela);
    		salvar(perfil);
    		return true;
    	} else {
    		return false;
    	}
	}
	
	public List<Perfil> recuperarPerfisDisponiveisPorUsuario(Usuario usuario) {
		return ((PerfilDao) getDao()).recuperarPerfisDisponiveisPorUsuario(usuario);
	}  
}