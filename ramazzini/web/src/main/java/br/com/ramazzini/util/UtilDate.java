package br.com.ramazzini.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class UtilDate {
	
	public static int diasEntreDuasDatas(Date dataInicial, Date dataFinal) {
		return Days.daysBetween(new DateTime(dataInicial), new DateTime(dataFinal)).getDays();
	}
	
}