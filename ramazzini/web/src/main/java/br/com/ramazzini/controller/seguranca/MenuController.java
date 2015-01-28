package br.com.ramazzini.controller.seguranca;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;

@Named
public class MenuController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Inject Conversation conversation;
	
    @PostConstruct  
    public void init() {

    	if (conversation.isTransient()) {
			conversation.begin();
		}
    }
	
}