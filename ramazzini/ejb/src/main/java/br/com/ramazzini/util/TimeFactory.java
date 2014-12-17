package br.com.ramazzini.util;

import java.util.Date;

import org.joda.time.DateTime;

public final class TimeFactory {

	/**
	 * Retorna a data corrente.
	 * @return 
	 */
	public static Date createDataHora() {
		long time = System.currentTimeMillis();		
		DateTime date = new DateTime(time);
		return date.toDate();
	}
}
