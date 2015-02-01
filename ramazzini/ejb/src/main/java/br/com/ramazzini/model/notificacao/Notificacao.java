package br.com.ramazzini.model.notificacao;

import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import br.com.ramazzini.util.TimeFactory;

@Named
@ApplicationScoped
public class Notificacao {

	private static Date ultimaModificacaoAgenda = TimeFactory.createDataHora();

	public static Date getUltimaModificacaoAgenda() {
		return ultimaModificacaoAgenda;
	}

	public static void notificarModificacaoAgenda() {
		Notificacao.ultimaModificacaoAgenda = TimeFactory.createDataHora();
	}


}
