package br.com.ramazzini.util;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 * Classe que implementa o padrão Singleton para manter uma lista com o histórico de navegação
 * @author Thener
 *
 */
public class BreadCrumb{
	private static BreadCrumb instancia;	
	
	private MenuModel modelo =  new DefaultMenuModel();	
	
	public static synchronized BreadCrumb getInstance(){
		if (instancia == null) {
			instancia = new BreadCrumb();			
		} 
		return instancia;
	}
	
	public MenuModel getModelo() {
		return modelo;
	}

	public void setModelo(MenuModel modelo) {
		this.modelo = modelo;
	}
	
	public void addItem(DefaultMenuItem item) {
		removerExcesso();		
		if (!getUltimoElemento().getValue().equals(item.getValue())){
			modelo.addElement(item);
		}
	}

	private void removerExcesso() {
		int tamanho = modelo.getElements().size();		
		if (tamanho == 8){
			modelo.getElements().remove(1);
		}
	}
	public DefaultMenuItem getUltimoElemento() {
		int tamanho = modelo.getElements().size();		
		return (DefaultMenuItem)modelo.getElements().get(tamanho-1);
	}
	public void clear(){
		modelo.getElements().clear();
	}
	
	public void addItemInicial(){
		DefaultMenuItem itemInicial = new DefaultMenuItem("Home", "ui-icon-home", "/pages/home/home.jsf");
        modelo.addElement(itemInicial);
	}
}
