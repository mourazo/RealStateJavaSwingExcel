package interfaz;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import excepciones.ExcepcionFechaNoValida;
import excepciones.ExcepcionValorNoEsperado;
import modelo.Inmueble;
import modelo.Situacion;
import modelo.Tipo;
import utilidades.Constantes;
import utilidades.UtilidadesFechas;

/**
 * 
 * @author alvar
 *
 */
public class VentanaInm extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String TITULO = "Inmobiliaria UDIMA";

	private static String rutaFichero;
	private List<Inmueble> inmuebles;
	static DefaultTableModel modelo;
	static AltaInmueble alta = null;
	static BorrarInmueble borrar = null;

	/**
	 * 
	 * Inicializamos el frame con sus componentes y configuraciones
	 * 
	 * @param rutaFichero
	 * 
	 * @throws ExcepcionFechaNoValida
	 * 
	 * @throws ExcepcionValorNoEsperado
	 * @throws IOException
	 */
	public VentanaInm(String rutaFichero) throws ExcepcionFechaNoValida, ExcepcionValorNoEsperado, IOException {

		VentanaInm.rutaFichero = rutaFichero;// ruta del fichero excel
		this.inmuebles = new ArrayList<Inmueble>(); // array list que guardara los inmuebles
		cargarDatos(); // cargamos los datos del fichero en el programa
		setTitle(TITULO); // establecemos el titulo
		inicializarComponentes(); // inicializamos totos los componentes
		pack(); // establecemos los tamaños del frame
		setLocationRelativeTo(null); // indicamos que el frame se inicie centrado
		setVisible(true); // lo hacemos visible
		setResizable(false);// Desactivamos el botón maximizar
	}

	/*
	 * Metodo que contiene la inicializacion de todos los componentes
	 */
	private void inicializarComponentes() throws ExcepcionFechaNoValida, ExcepcionValorNoEsperado {

		// array string con el titulo de las columnas de la tabla
		String col[] = { "ID", "DES", "FECHA-CONS", "ANT", "TIPO", "POBLACION", "CP", "NH", "NB", "SUPERFICIE",
				"SITUACION", "EMAIL", "PRECIO", "FECHA-CRE" };
		// modelo para manejar la tabla
		modelo = new DefaultTableModel();
		// establecemos las columnas en el modelo
		modelo.setColumnIdentifiers(col);
		// objeto que guardara la informacion para establecerla en el modelo
		Object[] data;

		// recorremos los inmuebles y guardamos todos sus componentes en el objeto data
		for (Inmueble inmueble : inmuebles) {

			data = new Object[14];
			data[0] = inmueble.getIdentificador();
			data[1] = inmueble.getDescripcion();
			data[2] = inmueble.getFechaConstruccion();
			data[3] = inmueble.getAnt();
			data[4] = inmueble.getTipo();
			data[5] = inmueble.getPoblacion();
			data[6] = inmueble.getCodigoPostal();
			data[7] = inmueble.getNumeroHabitaciones();
			data[8] = inmueble.getNumeroBanos();
			data[9] = inmueble.getSuperficie();
			data[10] = inmueble.getSituacion();
			data[11] = inmueble.getEmailContacto();
			data[12] = inmueble.getPrecio();
			data[13] = inmueble.getFechaCreacion();

			// añadimos el objeto data como una fila
			modelo.addRow(data);

		}

		// creamos una tabla y le añadimos el modelo
		JTable tabla = new JTable(modelo);
		// le indicamos que el tamaño de las columnas se establezca solo en funcion del
		// contenido
		resizeColumnWidth(tabla);
		/*
		 * creamos un scrollpanel para la tabla por si es muy grande, y con el además
		 * nos muestra el nombre de las columnas
		 */
		JScrollPane scrollPanel = new JScrollPane();
		// le indicamos que pertenece a la tabla
		scrollPanel.setViewportView(tabla);
		scrollPanel.setBounds(12, 13, 758, 209);
		// lo añadimos al panel
		getContentPane().add(scrollPanel);

		// creamos un nuevo boton para dar de alta un inmueble
		JButton btnNewHouse = new JButton("Nuevo Inmueble");
		// le añadimos el actionListener
		btnNewHouse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					// abrimos le otro form cuando pinchemos en el botón
					alta = new AltaInmueble();

				} catch (ExcepcionFechaNoValida | ExcepcionValorNoEsperado e1) {

					e1.printStackTrace();
				}
				// le indicamos que sea visible
				alta.setVisible(true);
				// le indicamos que aparezca en el centro de la pantalla
				alta.setLocationRelativeTo(null);
				// impedimos que se pueda maximizar
				alta.setResizable(false);
			}
		});
		// establecemos el tamaño del botono
		btnNewHouse.setBounds(12, 235, 132, 25);
		// lo añadimos al form principal
		getContentPane().add(btnNewHouse);

		JButton btnDeleteHouse = new JButton("Borrar inmueble");
		btnDeleteHouse.setBounds(339, 235, 132, 25);
		getContentPane().add(btnDeleteHouse);
		// le añadimos el actionListener
		btnDeleteHouse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					// abrimos le otro form cuando pinchemos en el botón
					borrar = new BorrarInmueble();

				} catch (ExcepcionFechaNoValida | ExcepcionValorNoEsperado e1) {

					e1.printStackTrace();
				}
				// le indicamos que sea visible
				borrar.setVisible(true);
				// le indicamos que aparezca en el centro de la pantalla
				borrar.setLocationRelativeTo(null);
			}
		});

		// creamos un boton para guardar los datos
		JButton btnGuardarDatos = new JButton("Guardar datos");
		// establecemos su onclick
		btnGuardarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// lamamos al metodo guardar datos
				guardarDatos();
			}
		});

		// establecemos el tamaño de guardar los datos
		btnGuardarDatos.setBounds(644, 235, 126, 25);
		// añadimos el boton guardarDatos
		getContentPane().add(btnGuardarDatos);

		// le indicamos que no tenga ningun layout para posicionar los componentes donde
		getContentPane().setLayout(null);
		// que se cierre el programa al salir
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// tamaño del frame
		setPreferredSize(new Dimension(800, 340));

	}
	

	public static void addInmueble(int identificador, String descripcion, Calendar fechaConstruccion, int ant,
			Tipo tipo, String poblacion, int codigoPostal, int numeroHabitaciones, int numeroBanos,
			int superficie,
			Situacion situacion, String emailContacto, double precio, Calendar fechaCreacion) {
		Object[] data;

		data = new Object[14];
		data[0] = identificador;
		data[1] = descripcion;
		data[2] = UtilidadesFechas.toString(fechaConstruccion, Constantes.SIMPLE_DATE_PATTERN);
		data[3] = ant;
		data[4] = tipo;
		data[5] = poblacion;
		data[6] = codigoPostal;
		data[7] = numeroHabitaciones;
		data[8] = numeroBanos;
		data[9] = superficie;
		data[10] = situacion;
		data[11] = emailContacto;
		data[12] = precio;
		data[13] = UtilidadesFechas.toString(fechaCreacion, Constantes.SIMPLE_DATE_PATTERN);

		modelo.addRow(data);
		alta.dispose();
		alta.setVisible(false);

	}

	private void cargarDatos() throws ExcepcionFechaNoValida, ExcepcionValorNoEsperado, IOException {

		System.out.println();
		System.out.println("Antes de comenzar, permitame cargar los datos almacenados " + "en el fichero \'"
				+ rutaFichero + "\'.");
		FileReader fr;
		try {
			// abrimos el fichero
			fr = new FileReader(rutaFichero);
			BufferedReader bf = new BufferedReader(fr);
			// leemos la linea del fichero
			String texto = bf.readLine();
			// count para saber en que linea estamos
			int countMethod = 0;
			// cuenta total de los inmuebles que añadimos
			int countInmuebles = 0;
			while (texto != null) {
				// mientras halla lineas en el fichero
				if (Inmueble.fromStringCSV(texto, countMethod) != null) {
					inmuebles.add(Inmueble.fromStringCSV(texto, countMethod));
					countInmuebles++;
				}
				countMethod++;
				// leemos la siguiente linea
				texto = bf.readLine();
			}

			bf.close();

			// mostramos los inmuebles cargados
			JOptionPane.showMessageDialog(null, "El proceso de carga de datos ha concluido satisfactoriamente "
					+ countInmuebles + " inmuebles disponibles");

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(
					null, 
					"El proceso de carga de datos ha tenido problemas y no ha concluido satisfactoriamente: El fichero '"
							+ rutaFichero + "' no ha podido ser encontrado");
			System.out.println(e);
		} catch (Exception ex) {

		}
	}

	/**
	 * metodo que devuelve un array bidimensional para obtener los datos de la tabla
	 * para pasarle esta informacion al metodo guardarTabla
	 * 
	 * @return
	 */
	public static String[][] obtenerInformacionTable() {

		// almacenamos el numero de filas del modelo
		int numFilas = modelo.getRowCount();
		// almacenamos el numero de columnas del modelo
		int numColumnas = modelo.getColumnCount();

		/*
		 * creamos el array bidimensional estableciendo el numero de filas y de columnas
		 * almacenado
		 */
		String matriz[][] = new String[numFilas][numColumnas];

		/*
		 * recorremos la tabla guardando los datos en la matriz
		 */
		for (int rowIndex = 0; rowIndex < numFilas; rowIndex++) {
			for (int colIndex = 0; colIndex < numColumnas; colIndex++) {
				matriz[rowIndex][colIndex] = modelo.getValueAt(rowIndex, colIndex).toString();
			}
		}
		// devolvemos la matriz
		return matriz;
	}

	public void guardarDatos() {
		try {
			// creamos un printer para guardar los datos en el fichero
			PrintWriter salida = new PrintWriter(new FileWriter(rutaFichero));
			// guardamos en un array multidimensional la informacion de la tabla
			String data[][] = obtenerInformacionTable();

			// recorremos el array data
			for (int i = 0; i < data.length; i++) {
				// guardamos el dato en la posicion i de la primera columna
				salida.print(data[i][0]);
				// recorremos toda la fila guardando los datos menos la antiguedad que la
				// calcula automaticamente el programa
				for (int j = 1; j < data[i].length; j++) {
					if (j != 3) {
					// guardamos cada posicion de la celda en un string
					String word = data[i][j];
					/*
					 * si el string tiene contenido lo guardamos concatenado con un ; que es los que
					 * separa las celdas en excel
					 */
					if (word != null) {
						salida.print(";" + word);
					} else {
						// si no solo printamos el ; es decir, el salto de celda
						salida.print(";");
					}
				}
				}
				// indicamos el salto de linea para realizar el mismo proceso con la siguiente
				// fila
				salida.println();
			}
			// cerramos el fichero
			salida.close();
			JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");

		} catch (IOException io) {
			System.out.println(io.toString());
		}
	}

	/**
	 * metodo para establecer el tamaño automatico de las columnas segun su
	 * contenido
	 * 
	 * @param table
	 */
	public void resizeColumnWidth(JTable table) {
		// establecemos un modelo de columna con la tabla recibida
		final TableColumnModel columnModel = table.getColumnModel();
		// recorremos ls columnas de la tabla
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 0;
			// recorremos cada fila
			for (int row = 0; row < table.getRowCount(); row++) {
				// componente usado para el dibujo de la celda
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				// preparamos el renderer
				Component comp = table.prepareRenderer(renderer, row, column);
				/*
				 * establecemos el width de la columna obteniendo el maximo que ocupa el dato de
				 * la columna sumandole uno
				 */
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			// si es mayor que 300 lo dejamos asi
			if (width > 300) {
				width = 300;
			}
			// establecemos el modelo de la columa con el tamaño definido
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	public static void borrarFilas(int id) {
		// recorremos toda las filas del modelo
		boolean done = false;
		for (int f = 0; f < modelo.getRowCount(); f++) {
			// si en la primera columna el modelo contiene el id
			int compare = (int) modelo.getValueAt(f, 0);
			if (id == compare) {
				// borramos la fila
				modelo.removeRow(f);
				borrar.dispose();
				borrar.setVisible(false);
				done = true;
				JOptionPane.showMessageDialog(null, "Done\n Inmueble " + id + " borrado");
			}
		}
		if (!done) {
			JOptionPane.showMessageDialog(null, "Error \n no se ha encontrado el id en la base de datos");
		}
	}

}
