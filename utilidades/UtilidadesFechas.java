package utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import excepciones.ExcepcionFechaNoValida;

/**
 * 
 * @author alvaro mourazo
 *
 */
public class UtilidadesFechas {

	/**
	 * Retorna la fecha de tipo Calendar en una cadena de acuerdo al
	 * patron recibido como argumento
	 * 
	 * @param calendar
	 * @param pattern
	 * @return
	 */
	static Scanner in = new Scanner(System.in);

	public static String toString(Calendar calendar, String pattern) {
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}

	/**
	 * Retorna una fecha a partir de la cadena recibida en el formato especificado
	 * 
	 * @param string  fecha de entrada
	 * @param pattern patron de la fecha de entrada
	 * @return fecha asociada
	 * @throws ExcepcionFechaNoValida si el usuario no introduce la fecha correcta
	 */

	public static Calendar toCalendar(String string, String pattern)
			throws ExcepcionFechaNoValida {

		Calendar calendar = null;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		sdf.setLenient(false);
		Date date;

		try {
			date = sdf.parse(string);
			calendar = new GregorianCalendar();
			calendar.setTime(date);

		} catch (ParseException e) {
			throw new ExcepcionFechaNoValida(string);
		}
		return calendar;
	}

	/**
	 * metodo para obtener una fecha valida de la entrada del usuario
	 * 
	 * @return fecha valida de la entrada del usario
	 */
	public static Calendar leerCalendar() {

		Calendar calendar = null;
		boolean correct = false;
		while (!correct) {
			try {
				String entry = in.next();
				calendar = UtilidadesFechas.toCalendar(entry, Constantes.SIMPLE_DATE_PATTERN);
				System.out.println("Fecha Correcta");
				correct = true;

			} catch (ExcepcionFechaNoValida e) {
				System.out.println("Formato de fecha incorrecto, introduzca la fecha en formato [dd/MM/yyyy]");
			}
		}

		return calendar;

	}

}
