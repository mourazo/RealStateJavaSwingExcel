package modelo;

import java.io.IOException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import excepciones.ExcepcionFechaNoValida;
import excepciones.ExcepcionValorNoEsperado;
import utilidades.Constantes;
import utilidades.UtilidadesFechas;
import utilidades.UtilidadesNumeros;

/**
 * 
 * @author alvaro mourazo
 *
 */
public class Inmueble {

	/**
	 * Atributos
	 */
	private int identificador;
	private String descripcion;
	private Calendar fechaConstruccion;
	private Tipo tipo;
	private String poblacion;
	private int codigoPostal;
	private int numeroHabitaciones;
	private int numeroBanos;
	private double superficie;
	private Situacion situacion;
	private String emailContacto;
	private double precio;
	private Calendar fechaCreacion;
	private int ant;
	
	/**
	 * Metodo que calcula la antiguedad del inmueble en funcion del instante actual
	 * y de la fecha de construccion del inmueble
	 * 
	 * @return antiguedad del inmueble en anos
	 */
	public static int antiguedad(Calendar fecha_creacion) {

		Calendar today = Calendar.getInstance();
		int diff = today.get(Calendar.YEAR) - fecha_creacion.get(Calendar.YEAR);

		return diff;

	}

	/*
	 * constructor de la clase inmueble
	 */
	public Inmueble(int identificador, String descripcion, Calendar fechaConstruccion, int ant, Tipo tipo,
			String poblacion,
			int codigoPostal, int numeroHabitaciones, int numeroBanos, double superficie, Situacion situacion,
			String emailContacto, double precio, Calendar fechaCreacion) {
		super();
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.fechaConstruccion = fechaConstruccion;
		this.ant = ant;
		this.tipo = tipo;
		this.poblacion = poblacion;
		this.codigoPostal = codigoPostal;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroBanos = numeroBanos;
		this.superficie = superficie;
		this.situacion = situacion;
		this.emailContacto = emailContacto;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * devolvemos un objeto inmueble pasandole el texto leido
	 * 
	 * @param string cadena en formato CSV
	 * 
	 * @throws ExcepcionFechaNoValida
	 */


	/**
	 * método toString() que devuelve el objeto inmueble con salida formateada, con
	 * las fechas formateadas para poder visualizarlas en el formato definido desde
	 * las constantes
	 */
	@Override
	public String toString() {
		return "Inmueble [identificador=" + identificador + ", descripcion=" + descripcion + ", fechaConstruccion="
				+ UtilidadesFechas.toString(fechaConstruccion, Constantes.SIMPLE_DATE_PATTERN) + ", tipo=" + tipo
				+ ", poblacion=" + poblacion + ", codigoPostal=" + codigoPostal + ", numeroHabitaciones="
				+ numeroHabitaciones + ", numeroBanos=" + numeroBanos + ", superficie=" + superficie + ", situacion="
				+ situacion + ", emailContacto=" + emailContacto + ", precio=" + precio + ", fechaCreacion="
				+ UtilidadesFechas.toString(fechaCreacion, Constantes.SIMPLE_DATE_PATTERN) + "]";
	}

	/**
	 * Genera una cadena con los datos del inmueble en formato CSV para guardarlo en
	 * el fichero
	 * 
	 * @return del objeto formateado
	 */
	public String toStringCSV() {

		return identificador + ";" + descripcion + ";"
				+ UtilidadesFechas.toString(fechaConstruccion, Constantes.SIMPLE_DATE_PATTERN) + ";" + tipo + ";"
				+ poblacion + ";" + codigoPostal + ";" + numeroHabitaciones + ";" + numeroBanos + ";" + superficie + ";"
				+ situacion + ";" + emailContacto + ";" + precio
				+ ";"
				+ UtilidadesFechas.toString(fechaCreacion, Constantes.SIMPLE_DATE_PATTERN) + ";";
	}

	/**
	 * Metodo de clase que genera un objeto (instancia) de la clase Inmueble a
	 * partir del estado interno (linea en CSV)
	 * 
	 * @return instancia de la clase Inmueble
	 * @throws ExcepcionValorNoEsperado cuando alguno de los valores leidos no es el
	 *                                  esperado
	 * @throws ExcepcionFechaNoValida   cuando la fecha no es correcta
	 */
	public static Inmueble fromStringCSV(String linea, int count)
			throws ExcepcionFechaNoValida, ExcepcionValorNoEsperado, IOException {
		/*
		 * separamos el string recibido en un arraylist mediante los punto y coma y
		 * guardamos las diferentes posiciones del array en las distintas variables para
		 * construir el objeto, construimos el objeto y lo devolvemos, manejamos las
		 * distintas excepciones en la constitucion del objeto que pudiesemos tener
		 */
		try {
			// Separamos en tokens
			String[] split = linea.split(Constantes.CSV_SEPARATOR);

			// Validamos los campos
			// 1. identificador

			int identificador = 0;

			// validamos que sea entero y si no mandamos la excepcion
			if (UtilidadesNumeros.esEntero(split[0]) == false) {
				throw new ExcepcionValorNoEsperado(Integer.class, split[0]);
			}

			// asignamos el valor
			identificador = Integer.valueOf(split[0]);

			// 2. Descripcion, no hace falta validarlo por que en el string podemos insertar
			// cualquier dato
			String descripcion = split[1];

			/*
			 * 3. fechaConstruccion, lo validamos mediante el método utilidades fechas y si
			 * no es valido mandamos la excepcion
			 */

			Calendar fechaConstruccion = null;
			try {
				fechaConstruccion = UtilidadesFechas.toCalendar(split[2], Constantes.SIMPLE_DATE_PATTERN);
			} catch (ExcepcionFechaNoValida e) {
				throw new ExcepcionValorNoEsperado(Calendar.class, split[2]);
			}

			/*
			 * 4.Antiguedad, metodo que devuelve la antiguedad en base a la fecha de
			 * construccion en años le pasamos la fecha de construccion, solo le asignamos
			 * si no tenemos null en la fecha de construccion
			 * 
			 */

			int ant = 0;
			if (fechaConstruccion != null) {
				ant = antiguedad(fechaConstruccion);
			}

			// 4.Tipo, lo validamos, si no es valido mandamos la excepcion
			Tipo tipo = null;
			try {
				Tipo.valueOf(split[3]);
			} catch (Exception e) {
				throw new ExcepcionValorNoEsperado(Tipo.class, split[3]);
			}

			tipo = Tipo.valueOf(split[3]);

			// 5. Población, no hace falta que validemos por que es un string
			String poblacion = split[4];

			// 6. Codigo Postal, validamos si es un numero entero, si no mandamos la
			// excepcion
			int codigoPostal = 0;

			if (UtilidadesNumeros.esEntero(split[5]) == false) {
				throw new ExcepcionValorNoEsperado(Integer.class, split[5]);
			}

			codigoPostal = Integer.valueOf(split[5]);

			// 7.NumeroHabitaciones, validamos si es entero, si no mandamos la excepcion
			int numeroHabitaciones = 0;
			if (UtilidadesNumeros.esEntero(split[6]) == false) {
				throw new ExcepcionValorNoEsperado(Integer.class, split[6]);
			}

			numeroHabitaciones = Integer.valueOf(split[6]);

			// 8.NumeroBanos, validamos si es entero, si no mandamos la excepcion
			int numeroBanos = 0;

			if (UtilidadesNumeros.esEntero(split[7]) == false) {
				throw new ExcepcionValorNoEsperado(Integer.class, split[7]);
			}

			numeroBanos = Integer.valueOf(split[7]);

			// 9. Superficie, validamos si es double si no mandamos la excepcion
			double superficie = 0.0;

			if (UtilidadesNumeros.esDouble(split[8]) == false) {
				throw new ExcepcionValorNoEsperado(Double.class, split[8]);
			}
			superficie = Double.parseDouble(split[8]);

			// 10. Situacion, validamos si es una Situacion si no lanzamos la excepcion
			Situacion situacion = null;
			try {
				Situacion.valueOf(split[9]);
			} catch (Exception e) {
				throw new ExcepcionValorNoEsperado(Situacion.class, split[9]);
			}
			situacion = Situacion.valueOf(split[9]);

			// 11. emailContacto, al ser string no hace falta que validemos
			String emailContacto = split[10];

			// 12. precio, validamos que sea double si no mandamos la excepcion
			Double precio = 0.0;
			if (UtilidadesNumeros.esDouble(split[11]) == false) {
				throw new ExcepcionValorNoEsperado(Double.class, split[11]);
			}
			precio = Double.parseDouble(split[11]);

			// 13. fechaCreacion, validamos que sea una fecha correcta si no lanzamos la
			// excepcion
			Calendar fechaCreacion = null;
			try {
				fechaCreacion = UtilidadesFechas.toCalendar(split[12], Constantes.SIMPLE_DATE_PATTERN);
			} catch (ExcepcionFechaNoValida e) {
				throw new ExcepcionValorNoEsperado(Calendar.class, split[12]);
			}

			// si todo ha ido bien, creamos el objeto inmueble con los datos indicados
			Inmueble inmueble = new Inmueble(identificador, descripcion, fechaConstruccion, ant, tipo,
					poblacion,
					codigoPostal, numeroHabitaciones, numeroBanos, superficie, situacion, emailContacto, precio,
					fechaCreacion);

			// devolvemos el objeto
			return inmueble;

			/*
			 * si hemos lanzado la excepcion imprimimos el error el la linea leida del
			 * archivo csv, la clase en la que se ha producido el error y el valor con el
			 * que se ha producido
			 */
		} catch (ExcepcionValorNoEsperado e) {
			String format = "ERROR :: Línea %s :: Se esperaba un '%s' y se ha recibido '%s' no se añadirá el inmueble a la tabla\n revise el fichero";
			/*
			 * System.out.println( String.format(format, count + 1,
			 * e.getExpectedClass().getSimpleName(), e.getReceivedValue()));
			 */
			JOptionPane.showMessageDialog(null,
					String.format(format, count + 1, e.getExpectedClass().getSimpleName(), e.getReceivedValue()));
		}

		/*
		 * si se ha producido la excepción retornaremos null, en la llamada
		 * controlaremos esta respuesta para que no se añada al array list en este caso
		 */
		return null;
		
	}

	/*
	 * Getters y setters
	 */

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * devolvemos la fecha formateada, para no obtener la cadena calendar cuando
	 * accedamos
	 * 
	 * @return fecha formateada
	 */

	public String getFechaConstruccion() {
		return UtilidadesFechas.toString(fechaConstruccion, Constantes.SIMPLE_DATE_PATTERN);
	}

	public void setFechaConstruccion(Calendar fechaConstruccion) {
		this.fechaConstruccion = fechaConstruccion;
	}

	public int getAnt() {
		return ant;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public int getNumeroBanos() {
		return numeroBanos;
	}

	public void setNumeroBanos(int numeroBanos) {
		this.numeroBanos = numeroBanos;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public Situacion getSituacion() {
		return situacion;
	}

	public void setSituacion(Situacion situacion) {
		this.situacion = situacion;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * devolvemos la fecha formateada, para no obtener la cadena calendar cuando
	 * accedamos
	 * 
	 * @return fecha formateada
	 */
	public String getFechaCreacion() {
		return UtilidadesFechas.toString(fechaCreacion, Constantes.SIMPLE_DATE_PATTERN);
	}

	public void setFechaCreacion(Calendar fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}