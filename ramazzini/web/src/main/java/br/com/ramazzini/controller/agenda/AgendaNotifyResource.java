package br.com.ramazzini.controller.agenda;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;
 
@PushEndpoint("/agenda")
@Singleton
public class AgendaNotifyResource {
         
    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message) {
        return message;
        /*
         * não consegui alterar este método,
         * como por ex retirar a passagem de parametros...
         * messages, no caso da agenda, não está servindo
         * pra nada. 
         */
    }
 
}