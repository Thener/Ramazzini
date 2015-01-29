package br.com.ramazzini.controller.seguranca;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;

@Named
@ConversationScoped
public class MenuController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @PostConstruct  
    public void init() {
    	beginConversation();
    }
	
}