package br.com.ramazzini.controller.seguranca;

import java.io.Serializable;

import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuModel;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.util.RamazziniMenu;

@Named
public class MenuController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public DefaultMenuModel getMenuModel() {		
		return (RamazziniMenu)getControleAcesso().getSession().getAttribute("ramazziniMenu");
	}
	
}