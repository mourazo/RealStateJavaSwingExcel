package aa2;

import java.io.IOException;

import excepciones.ExcepcionFechaNoValida;
import excepciones.ExcepcionValorNoEsperado;
import interfaz.VentanaInm;

/**
 * Clase principal del sistema. Se ocupa de la instanciacion de la ventana
 * principal.
 * 
 */

public class Lanzador {

	public static void main(String[] args) throws ExcepcionValorNoEsperado, ExcepcionFechaNoValida {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				final String DEFAULT_DATA_FILE = "inmuebles2.csv";

				String rutaFichero = DEFAULT_DATA_FILE;
				if (args.length == 1) {
					// Si se recibe como argumento el nombre del fichero se
					// utilizara como almacen de datos en lugar del fichero
					// por defecto
					rutaFichero = args[0];
				}

				try {
					new VentanaInm(rutaFichero);
				} catch (ExcepcionFechaNoValida | ExcepcionValorNoEsperado | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

}
