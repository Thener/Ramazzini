package br.com.ramazzini.filters;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.util.RamazziniMenu;

public class PhaseListnerRamazzini implements PhaseListener {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		if (getSession(event) != null) {
			addMenuItem(event);	   
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;		
	}
	
	private void addMenuItem(PhaseEvent event) {		
		RamazziniMenu menu = (RamazziniMenu)getSession(event).getAttribute("ramazziniMenu");		
		if (menu == null) {
			menu = new RamazziniMenu();
		}
		menu.addMenuItem();
		getSession(event).setAttribute("ramazziniMenu", menu);		
	}
	
	private HttpSession getSession(PhaseEvent event) {
		return (HttpSession) event.getFacesContext().getExternalContext().getSession(false);
	}
}
