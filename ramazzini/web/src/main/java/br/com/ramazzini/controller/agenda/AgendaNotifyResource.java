package br.com.ramazzini.controller.agenda;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/agenda")
@Singleton
public class AgendaNotifyResource {
	
	@OnMessage(encoders = { JSONEncoder.class })
	public String onMessage(String msg) {
		return msg;
	}
}