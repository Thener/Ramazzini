package br.com.ramazzini.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import br.com.ramazzini.model.horarioAtendimento.DiaSemana;

public final class TimeFactory {

	public static int calcularIdade(Date dn) {
		LocalDate hoje = new LocalDate();
	    String[] dt = converterDataEmTexto(dn).split("/");
	    int dia = Integer.parseInt(dt[0]);
	    int mes = Integer.parseInt(dt[1]);
	    int ano = Integer.parseInt(dt[2]);
	    LocalDate dataNasc = new LocalDate(ano, mes, dia);
		return Years.yearsBetween(dataNasc, hoje).getYears();
	}
	
	public static Date createDataHora() {
		long time = System.currentTimeMillis();		
		DateTime date = new DateTime(time);
		return date.toDate();
	}
	
	public static String converterDataEmTexto(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		return formatador.format(data);
	}
	
	public static String converterDateTimeEmTexto(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		return formatador.format(data);
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

	public static Date somarMeses(Date dt, int meses) {
		return new DateTime(dt).plusMonths(meses).toDate();
	}	
	
	public static Date somarMinutos(Date dataHora, int minutos) {
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(dataHora);
		cal.add(Calendar.MINUTE, minutos);
		
		return cal.getTime();
	}
}
