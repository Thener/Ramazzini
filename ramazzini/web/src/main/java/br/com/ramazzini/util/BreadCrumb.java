package br.com.ramazzini.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import br.com.ramazzini.controller.util.AbstractBean;

import java.io.Serializable;

@Named
@ConversationScoped
public class BreadCrumb extends AbstractBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuModel modelo;
	
	@PostConstruct  
    public void init() {
		modelo = new DefaultMenuModel();
		DefaultMenuItem item = new DefaultMenuItem("Home", "ui-icon-home", "/pages/home/home.jsf");
        modelo.addElement(item);
    }

	public MenuModel getModelo() {
		return modelo;
	}

	public void setModelo(MenuModel modelo) {
		this.modelo = modelo;
	}
	
	public void addItem(DefaultMenuItem item) {
		modelo.addElement(item);;
	}
}
