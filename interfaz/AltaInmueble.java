package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import excepciones.ExcepcionFechaNoValida;
import excepciones.ExcepcionValorNoEsperado;
import modelo.Inmueble;
import modelo.Situacion;
import modelo.Tipo;
import utilidades.Constantes;
import utilidades.JCTextField;
import utilidades.UtilidadesFechas;
import utilidades.UtilidadesNumeros;
import utilidades.UtilidadesVarias;

/**
 * 
 * @author alvar
 *
 */
public class AltaInmueble extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCTextField tfDescripcion;
	private JCTextField tfFechaConstr;
	private JCTextField tfPoblacion;
	private JCTextField tfCodigoPostal;
	private JCTextField tfSuperficie;
	private JCTextField tfPrecio;
	private JCTextField tfMail;

	public AltaInmueble() throws ExcepcionFechaNoValida, ExcepcionValorNoEsperado {

		// indicamos que se cierre solo este frame y no el programa
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// le establecemos el tamaño
		setBounds(100, 100, 804, 345);
		// creamos el panel que contendra todo
		contentPane = new JPanel();
		// establecemos los bordes
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// lo metemos en el frame
		setContentPane(contentPane);
		// indicamos que aparezca centrado
		contentPane.setLayout(null);

		// label descripcion
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(85, 17, 73, 30);
		contentPane.add(lblDescripcion);

		// label cambio de nombre
		JLabel lblCambioDeNombre = new JLabel("Fecha Construcci\u00F3n");
		lblCambioDeNombre.setBounds(85, 64, 121, 30);
		contentPane.add(lblCambioDeNombre);

		// label para el tipo
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(85, 111, 73, 30);
		contentPane.add(lblTipo);

		// label poblacion
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n");
		lblPoblacin.setBounds(85, 158, 73, 30);
		contentPane.add(lblPoblacin);

		// label codigo postal
		JLabel lblCdigoPostal = new JLabel("C\u00F3digo Postal");
		lblCdigoPostal.setBounds(85, 205, 116, 30);
		contentPane.add(lblCdigoPostal);

		// label habitaciones
		JLabel lblHabitaciones = new JLabel("Habitaciones");
		lblHabitaciones.setBounds(85, 252, 73, 30);
		contentPane.add(lblHabitaciones);

		// label baños
		JLabel lblBanos = new JLabel("Ba\u00F1os");
		lblBanos.setBounds(419, 17, 73, 30);
		contentPane.add(lblBanos);

		// label superficie
		JLabel lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setBounds(419, 64, 73, 30);
		contentPane.add(lblSuperficie);

		// label situacion
		JLabel lblSituacion = new JLabel("Situaci\u00F3n");
		lblSituacion.setBounds(419, 111, 73, 30);
		contentPane.add(lblSituacion);

		// label mail
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(419, 158, 73, 30);
		contentPane.add(lblMail);

		// label precio
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(419, 205, 73, 30);
		contentPane.add(lblPrecio);

		// tf descripcion
		tfDescripcion = new JCTextField();
		tfDescripcion.setPlaceholder("string");
		tfDescripcion.setBounds(230, 24, 116, 22);
		contentPane.add(tfDescripcion);

		// tf fecha construccion
		tfFechaConstr = new JCTextField();
		tfFechaConstr.setPlaceholder("Formato dd/MM/yyyy");
		tfFechaConstr.setBounds(230, 70, 116, 22);
		contentPane.add(tfFechaConstr);
		// listener que solo admita cifras y el slash
		tfFechaConstr.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() <= '/'
						|| ke.isShiftDown()
						|| ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					tfFechaConstr.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error \n debe solo introducir una fecha en formato dd/MM/yyyy");
					tfFechaConstr.setText(null);
				}
			}
		});


		// combo box tipo
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(230, 116, 116, 22);
		comboBoxTipo.addItem("PISO");
		comboBoxTipo.addItem("APARTAMENTO");
		comboBoxTipo.addItem("CHALE");
		contentPane.add(comboBoxTipo);

		// tf poblacion
		tfPoblacion = new JCTextField();
		tfPoblacion.setColumns(10);
		tfPoblacion.setPlaceholder("string");
		tfPoblacion.setBounds(230, 162, 116, 22);
		contentPane.add(tfPoblacion);

		// tf codigo postal
		tfCodigoPostal = new JCTextField();
		tfCodigoPostal.setPlaceholder("entero 5 cifras");
		tfCodigoPostal.setColumns(10);
		tfCodigoPostal.setBounds(230, 208, 116, 22);
		contentPane.add(tfCodigoPostal);
		// listener para que solo nos deje introducir un codigo postal valido
		tfCodigoPostal.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				String value = tfCodigoPostal.getText();
				int l = value.length();
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					tfCodigoPostal.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error \n debe solo puede introducir cifras");
					tfCodigoPostal.setText(null);
				}
				if (l >= 5) {
					JOptionPane.showMessageDialog(null, "Error \n debe solo puede 5 cifras");
					tfCodigoPostal.setText(value);
				}
			}
		});

		// spinner habitacion
		JSpinner spnHabitacion = new JSpinner();
		spnHabitacion.setBounds(230, 254, 116, 22);
		contentPane.add(spnHabitacion);

		// spinner banos
		JSpinner spnBanos = new JSpinner();
		spnBanos.setBounds(564, 24, 116, 22);
		contentPane.add(spnBanos);

		// tf superficie
		tfSuperficie = new JCTextField();
		tfSuperficie.setPlaceholder("entero m²");
		tfSuperficie.setBounds(564, 70, 116, 22);
		contentPane.add(tfSuperficie);
		// listener que solo nos deje introducir cifras
		tfSuperficie.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					tfSuperficie.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error \n debe solo puede introducir cifras");
					tfSuperficie.setText(null);
				}
			}
		});

		// comboBox situacion
		JComboBox<String> comboBoxSituacion = new JComboBox<String>();
		comboBoxSituacion.setBounds(564, 116, 116, 22);
		comboBoxSituacion.addItem("VENTA");
		comboBoxSituacion.addItem("ALQUILER");
		contentPane.add(comboBoxSituacion);

		// tf mail
		tfMail = new JCTextField();
		tfMail.setPlaceholder("correo@correo.es");
		tfMail.setBounds(564, 162, 116, 22);
		contentPane.add(tfMail);

		// tf precio
		tfPrecio = new JCTextField();
		tfPrecio.setPlaceholder("double xx.xx");
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(564, 208, 116, 22);
		contentPane.add(tfPrecio);
		// listener que solo nos deje introducir cifras
		tfPrecio.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() <= '.'
						|| ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					tfPrecio.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error \n debe solo puede introducir cifras");
					tfPrecio.setText(null);
				}
			}
		});

		// button para dar de alta el inmueble
		JButton btnAlta = new JButton("DAR DE ALTA");
		contentPane.add(btnAlta);
		btnAlta.setBounds(620, 261, 116, 22);

		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// generamos un identificador aleatorio
				int identificador = UtilidadesNumeros.generarIdentificador();
				// descripcion a partir del tf
				String descripcion = null;
				if (!tfDescripcion.getText().isEmpty()) {
					descripcion = tfDescripcion.getText();
				} else {
					JOptionPane.showMessageDialog(null, "Error \n la descripcion esta vacia");
				}
				// guardamos en un string la fecha actual
				String auxFechaConstr = tfFechaConstr.getText();
				Calendar fechaConstruccion = null;
				int ant = 0;
				/*
				 * hacemos que si la fecha este vacia no realice las operaciones
				 */
				if (!tfFechaConstr.getText().equals("")) {
					try {
						// validamos la fecha
						fechaConstruccion = UtilidadesFechas.toCalendar(auxFechaConstr, Constantes.SIMPLE_DATE_PATTERN);
						// llamamos al metodo antiguedad de la clase inmueble para generarla
						ant = Inmueble.antiguedad(fechaConstruccion);
					} catch (Exception ex) {
						// mostramos un messageBox si no se ha introducido bien la fecha
						JOptionPane.showMessageDialog(null,
								"Error \n debe introducir una fecha en formato correcto dd/MM/yyyy");
						tfFechaConstr.setText("");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Error \n la fecha de construcción esta vacía");
				}
				// llamamos al metodo isTipoSwing
				Tipo tipo = UtilidadesVarias.isTipoSwing(comboBoxTipo.getSelectedItem().toString());
				// string poblacion pasandole el texto del texfield
				String poblacion = tfPoblacion.getText();
				int codigoPostal = 0;
				// si el codigo postal no esta vacio lo parseamos
				if (!tfCodigoPostal.equals("")) {
					if (UtilidadesNumeros.tfToCodigoPostal(tfCodigoPostal.getText())) {
						codigoPostal = Integer.parseInt(tfCodigoPostal.getText());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error \n el codigo postal esta vacío");
				}
				// introducimos el valor del spinner
				int numeroHabitaciones = (int) spnHabitacion.getValue();
				// introducimos el valor del spinner
				int numeroBanos = (int) spnBanos.getValue();
				int superficie = 0;
				// introducimos el texto del tf si no esta vacio
				if (!tfSuperficie.getText().isEmpty()) {
					superficie = Integer.parseInt(tfSuperficie.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Error \n la superficie está vacía");
				}
				// llamamos al metodo isSituacionSwing de la UtilidadesVarias
				Situacion situacion = UtilidadesVarias.isSituacionSwing(comboBoxSituacion.getSelectedItem().toString());
				// llamamos al metodo isEmailSwing de la UtilidadesVaris
				
				String emailContacto = null;
				if (UtilidadesVarias.isEmailSwing(tfMail.getText())) {
					emailContacto = tfMail.getText();
				}
				double precio = 0;
				// si el tf precio no esta vacio
				if (!tfPrecio.getText().isEmpty()) {
					precio = Double.parseDouble(tfPrecio.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Error \n el precio está vacío");
				}
				// obtenemos la fecha actual
				Calendar fechaCreacion = Calendar.getInstance();

				try {
					if (codigoPostal != 0 && emailContacto != null) {
					VentanaInm.addInmueble(identificador, descripcion, fechaConstruccion, ant, tipo, poblacion,
							codigoPostal, numeroHabitaciones, numeroBanos, superficie, situacion, emailContacto, precio,
							fechaCreacion);

				} else {
					JOptionPane.showMessageDialog(null, "Error \n le faltan datos por introducir");
				}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error \n le faltan datos por introducir");

				}

			}

		});

	}
}
