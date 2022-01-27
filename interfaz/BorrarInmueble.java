package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.ExcepcionFechaNoValida;
import excepciones.ExcepcionValorNoEsperado;
import utilidades.JCTextField;

/**
 * 
 * @author alvar
 *
 */
public class BorrarInmueble extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCTextField tfId;

	public BorrarInmueble() throws ExcepcionFechaNoValida, ExcepcionValorNoEsperado {

		// le decimos que no cierre el programa al cerrar el frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// establecemos los tamaños
		setBounds(100, 100, 459, 184);
		// Desactivamos el botón maximizar
		setResizable(false);
		// creamos un nuevo panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// text fiel para introducir el id
		tfId = new JCTextField();
		tfId.setBounds(162, 52, 116, 22);
		tfId.setPlaceholder("1-999");
		contentPane.add(tfId);
		/*
		 * listener para que solo admita caracteres numericos y nos muestre error en
		 * caso de introducir mas de 3
		 */
		tfId.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				String value = tfId.getText();
				int l = value.length();
				if (ke.getKeyChar() >= '0' && ke
						.getKeyChar() <= '9'
						|| ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					tfId.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error \n debe solo puede introducir cifras");
					tfId.setText(null);
				}
				if (l >= 3) {
					JOptionPane.showMessageDialog(null, "Error \n debe solo puede 3 cifras");
					tfId.setText(value);
				}
			}
		});

		// button borrar el inmueble
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(172, 92, 97, 25);
		contentPane.add(btnBorrar);
		// llamamos al método borrar filas pasandole el id
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInm.borrarFilas(Integer.parseInt(tfId.getText()));

			}
		});

		// label indicativo
		JLabel lblNewLabel = new JLabel("Introduzca el id del inmueble a borrar");
		lblNewLabel.setBounds(108, 18, 224, 16);
		contentPane.add(lblNewLabel);

	}
}
