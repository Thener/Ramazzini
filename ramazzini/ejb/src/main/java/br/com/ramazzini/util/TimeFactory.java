package br.com.ramazzini.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import br.com.ramazzini.model.horarioAtendimento.DiaSemana;

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
	
	public static DiaSemana diaDaSemana(Date dt) {
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(dt);  
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		switch (day) {
			case 1:
				return DiaSemana.DOMINGO;
			case 2:
				return DiaSemana.SEGUNDA;
			case 3:
				return DiaSemana.TERCA;
			case 4:
				return DiaSemana.QUARTA;
			case 5:
				return DiaSemana.QUINTA;
			case 6:
				return DiaSemana.SEXTA;				
			default:
				return DiaSemana.SABADO;
		}
	}
	
	public static int diasEntreDuasDatas(Date dataInicial, Date dataFinal) {
		return Days.daysBetween(new DateTime(dataInicial), new DateTime(dataFinal)).getDays();
	}	
	
	public static Date somarDias(Date dt, int dias) {
		return new DateTime(dt).plusDays(dias).toDate();
	}
	
	public static Date somarMinutos(Date dataHora, int minutos) {
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(dataHora);
		cal.add(Calendar.MINUTE, minutos);
		
		return cal.getTime();
	}
}
