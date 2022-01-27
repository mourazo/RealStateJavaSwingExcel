package utilidades;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import modelo.Situacion;
import modelo.Tipo;


/**
 * @author alvaro mourazo Clase que contendra diferentes metodos de entrada
 *         comunes que nos serviran para reutilizarlos en el programa
 */

public class UtilidadesVarias {

	// declaramos el scanner para las entradas de los diferentes metodos
	static Scanner in = new Scanner(System.in);

	/**
	 * metodo que nos servira para pedir introducir el tipo por consola, admitiendo
	 * solo el tipo correcto
	 * 
	 * @return valor del enum definido
	 */
	public static Tipo leerTipo() {

		// boolean para gestionar el bucle de salida
		boolean exit = false;
		// string que almacenara la entrada
		String entrada = null;
		/*
		 * bucle que nos pide tantas veces como sea necesario la entrada por teclado
		 * hasta que el usuario introduzca una entrada la cual coincida con nuestro enum
		 */
		while (!exit) {
			System.out.println("Introduzca el tipo [PISO, APARTAMENTO, CHALE]");
			// metemos la entrada del usuario en la variable
			entrada = in.nextLine();
			// pasamos la entrada a mayusculas ya que en el enum todos estan en mayusculas
			entrada = entrada.toUpperCase();
			/*
			 * recorremos los tipos si encontramos nuestra entrada, salimos del bucle y
			 * retornamos la entrada ya que es correcta
			 */
			boolean coincidencia = false;
			for (Tipo t : Tipo.values()) {
				if (t.name().equals(entrada)) {
					coincidencia = true;
				}
			}
			if (coincidencia == true) {
				exit = true;

			} else {
				System.out.println("ERROR :: El valor recibido [" + entrada
						+ "] no es correcto. Se esperaba un enumerado valido dentro de los permitidos [PISO, APARTAMENTO, CHALE]");
			}
		}
		return Tipo.valueOf(entrada);

	}

	/**
	 * metodo que comprueba si el string recibido de un componente swing es tipo o
	 * no
	 * 
	 * @param entrada
	 * @return
	 */
	public static Tipo isTipoSwing(String entrada) {

		// pasamos la entrada a mayusculas ya que en el enum todos estan en mayusculas
		entrada = entrada.toUpperCase();
		/*
		 * recorremos los tipos si encontramos nuestra entrada, salimos del bucle y
		 * retornamos la entrada ya que es correcta
		 */
		boolean coincidencia = false;
		for (Tipo t : Tipo.values()) {
			if (t.name().equals(entrada)) {
				coincidencia = true;
			}
		}
		if (coincidencia == false) {
			JOptionPane.showMessageDialog(null, "Error \n debe introducir PISO, APARTAMENTO, CHALE en el campo");
		}
		return Tipo.valueOf(entrada);

	}

	/**
	 * metodo que nos sirve para pedir por consola la introduccion del enum
	 * Situacion
	 * 
	 * @return string valido definido en enum situacion
	 */

	public static Situacion leerSituacion() {

		// boolean para gestionar el bucle de salida
		boolean exit = false;
		// string que almacenara la entrada por consola
		String ent = null;
		/*
		 * bucle que nos pide tantas veces como sea necesario la entrada por teclado
		 * hasta que el usuario introduzca una entrada la cual coincida con nuestro enum
		 */
		while (!exit) {
			System.out.println("Introduzca la situacion [VENTA, ALQUILER]");
			// metemos la entrada por consola en la variable
			ent = in.nextLine();
			// pasamos la variable a mayusculas
			ent = ent.toUpperCase();
			/*
			 * recorremos el enum, si encontramos en el la entrada por consola, salimos del
			 * bucle y devolvemos la variable
			 */
			boolean coincidence = false;
			for (Situacion s : Situacion.values()) {
				if (s.name().equals(ent)) {
					coincidence = true;
				}
			}
			if (coincidence == true) {
				exit = true;
			} else {
				System.out.println("ERROR :: El valor recibido [" + ent
						+ "] no es correcto. Se esperaba un enumerado valido dentro de los permitidos [VENTA, ALQUILER]. Intentelo de nuevo");
			}
		}
		return Situacion.valueOf(ent);

	}

	/**
	 * metodo que devuelve si un string recibido de un componente swing es situacion
	 * o no
	 * 
	 * @param entrada
	 * @return
	 */
	public static Situacion isSituacionSwing(String entrada) {

		boolean coincidence = false;
		for (Situacion s : Situacion.values()) {
			if (s.name().equals(entrada)) {
				coincidence = true;
			}
		}
		if (coincidence == false) {
			JOptionPane.showMessageDialog(null, "Error \n debe introducir VENTA, ALQUILER en el campo Situacion");
		}
		return Situacion.valueOf(entrada);

	}

	/**
	 * metodo que nos sirve para pedir una entrada por teclado del usuario y
	 * almacenarlo en un string, no nos permite dejarlo vacio
	 * 
	 * @return string no vacio de la entrada del usuario
	 */

	public static String leerString() {
		/*
		 * pedimos la entrada por teclado al usuario mientras que no este vacio y lo
		 * devolvemos
		 */
		String x;
		do {
			in = new Scanner(System.in);
			x = in.nextLine();
			if (x.length() < 1) {
				System.out.println(
						"ERROR :: El valor recibido [] no es correcto. Se esperaba una cadena no vacia. Intentelo de nuevo");
			}

		} while (x.length() < 1);

		return x;

	}

	/**
	 * metodo que nos permite pedir la entrada de un email y validarlo si el mail no
	 * es correcto nos lo indicara y nos lo volvera a pedir
	 * 
	 * @return string con formato valido de un email de la entrada del usuario
	 */
	public static String leerEmail() {

		// variable que almacenara el email
		String email = null;
		/*
		 * patron que validara el mail estableciendo que pueden ser numeros o letras
		 * separados por un @
		 */
		Pattern pattern = Pattern
				.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		// boolean para la gestion del bucle
		boolean exit = false;
		// bucle que repite la accion hasta que la entrada sea correcta
		while (!exit) {
			System.out.println("Introduzca el email de contacto");
			// almacenamos la entrada en la variable
			email = in.next();
			// asignamos la validacion a la variable
			Matcher mather = pattern.matcher(email);
			/*
			 * si encuentra el patron de validacion nos valida el mail y salimos del bucle
			 * si no indicamos que no es valido y repetimos el ciclo
			 */
			if (mather.find() == true) {
				// System.out.println("El email ingresado es válido.");
				exit = true;
			} else {
				System.out.println("ERROR :: El valor recibido [" + email
						+ "] no es correcto. Se esperaba un correo electronico valido. Intentelo de nuevo");
			}

		}

		return email;

	}

	/**
	 * metodo que devuelve si el string recibido de un componente swing es un email
	 * o no
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailSwing(String email) {

		boolean check;
		/*
		 * patron que validara el mail estableciendo que pueden ser numeros o letras
		 * separados por un @
		 */
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		// asignamos la validacion a la variable
		Matcher mather = pattern.matcher(email);
		/*
		 * si encuentra el patron de validacion nos valida el mail y salimos del bucle
		 * si no indicamos que no es valido y repetimos el ciclo
		 */
		if (mather.find() == false) {
			JOptionPane.showMessageDialog(null, "Error \n el mail introducido es incorrecto");
			check = false;
		} else {
			check = true;
		}

		return check;

	}

}
