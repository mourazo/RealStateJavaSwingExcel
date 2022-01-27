package utilidades;

import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * 
 * @author alvaro mourazo
 *
 */
public class UtilidadesNumeros {

	/**
	 * Metodo que determina si la cadena que recibe como argumento se puede
	 * transformar a entero
	 * 
	 * @param string del dato a validar
	 * @return si es int o no
	 */
	static Scanner in = new Scanner(System.in);

	public static boolean esEntero(String string) {

		boolean esValido = true;

		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			esValido = false;
		}

		return esValido;

	}

	/**
	 * metodo que devuelve si un entero recibido de un componente swing es entero o
	 * no si no muestra un message box
	 * 
	 * @param string
	 * @return
	 */
	public static int esEnteroSwing(String string) {

		int response = 0;

		try {
			response = Integer.parseInt(string);
		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Error \n debe introducir un numero entero");
		}

		return response;

	}

	/**
	 * Metodo que determina si la cadena que recibe como argumento se puede
	 * transformar a double
	 * 
	 * @param string del dobule a validar
	 * @return si es dobule o no
	 */

	public static boolean esDouble(String string) {

		boolean esValido = true;

		try {
			Double.parseDouble(string);
		} catch (NumberFormatException e) {
			esValido = false;
		}

		return esValido;

	}

	/**
	 * Metodo que determina si la cadena que recibe como argumento se puede
	 * transformar a entero positivo
	 * 
	 * @param string a validar
	 * @return si es un entero positivo o no
	 */

	public static boolean esEnteroPositivo(String string) {

		boolean esValido = true;
		final int valor;

		try {
			valor = Integer.parseInt(string);
			if (valor <= 0) {
				throw new NumberFormatException();
			}

		} catch (NumberFormatException e) {
			esValido = false;
		}

		return esValido;

	}

	/**
	 * metodo para pedir un integer por consola hasta que introduzcamos uno
	 * correcto, si introducimos un dato que no es capaz de parsear nos volvera a
	 * pedir que introduzcamos el dato correctamente
	 * 
	 * @return int obteneido de la entrada del usuario
	 */

	public static int leerInt() {

		Integer y = 0;
		do {
			String x = in.nextLine();
			try {
				y = Integer.parseInt(x);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("ERROR :: El valor recibido [" + x
						+ "] no es correcto. Se esperaba un numero entero. Intentelo de nuevo");
			}

		} while (y instanceof Integer);

		return y;

	}

	/**
	 * metodo para pedir un integer por consola hasta que introduzcamos uno
	 * correcto, si introducimos un dato que no es capaz de parsear nos volvera a
	 * pedir que introduzcamos el dato correctamente
	 * 
	 * @return double de la entrada del usuario
	 */

	public static Double leerDouble() {

		Double y = 0.0;
		do {

			String x = in.nextLine();
			try {
				y = Double.parseDouble(x);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("ERROR :: El valor recibido [" + x
						+ "] no es correcto. Se esperaba un numero decimal. Intentelo de nuevo");
			}

		} while (y instanceof Double);

		return y;

	}

	/**
	 * metodo para pedir un codigoPostal por consola hasta que introduzcamos uno
	 * correcto, si introducimos un dato que no es capaz de parsear a integer y que
	 * tiene 5 cifras de longitud nos volvera a pedir que introduzcamos el dato
	 * correctamente
	 * 
	 * @return codigo postal valido de la entrada del usuario
	 */

	public static int leerCodigoPostal() {
		String entry = null;
		Integer y = 0;
		do {
			try {
				entry = in.nextLine();
				y = Integer.parseInt(entry);
				if (String.valueOf(entry).length() != 5 && y instanceof Integer) {
					System.out.println("ERROR :: El valor recibido [" + entry
							+ "] no es correcto. Se esperaba un numero entero de 5 cifras. Intentelo de nuevo");
				}
			} catch (NumberFormatException ex) {
				System.out.println("ERROR :: El valor recibido [" + entry
						+ "] no es correcto. Se esperaba un numero entero de 5 cifras. Intentelo de nuevo");
			}
		} while (String.valueOf(entry).length() != 5 && y instanceof Integer);
		return y;

	}

	/**
	 * metodo que devuelve si el string obtenido de un textField es un código postal
	 * o no
	 * 
	 * @param cp
	 * @return
	 */
	public static boolean tfToCodigoPostal(String cp) {
		boolean check = false;
		Integer y = 0;
		try {
			y = Integer.parseInt(cp);
			if (String.valueOf(cp).length() != 5 && y instanceof Integer) {
				JOptionPane.showMessageDialog(null, "Error \n el código postal no es numero entero de 5 cifras");
			} else {
				check = true;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Error \n el código postal no es numero entero de 5 cifras");
			check = false;
		}
		return check;

	}

	/**
	 * metodo que genera aleatoriamente el identificador de 3 cifras
	 * 
	 * @return entero aleatorio entre 1 y 999
	 */

	public static int generarIdentificador() {

		int number = (int) (Math.random() * 999 + 1);

		return number;
	}

}
