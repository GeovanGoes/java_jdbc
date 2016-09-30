package br.com.penselink.ensino.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Classe com métodos uteis para manipulação de dados
 * @author geovan.goes
 * */
public class UtilDados 
{

	/**
	 * Método responsável por transformar uma string de data no formato
	 * dd/MM/yyyy em calendar
	 * @param date
	 * @return data no formato calendar
	 * @throws ParseException 
	 */
	public static Calendar stringToCalendar(String date) throws ParseException 
	{
		Locale locale = new Locale("pt", "BR");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", locale);
		cal.setTime(sdf.parse(date));
		
		return cal;
	}
}
