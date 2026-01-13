/*
* Proyecto: swing_c_p02_KassimiOusama
* Paquete: alquilerTuristico
* Archivo: Main.java
* Autor/a: Ousama Kassimi
* Fecha: 23 nov 2025 11:59:24
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

package alquilerTuristico;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * Clase principal que inicia la aplicación de Gestión de Apartamentos Turísticos.
 * 
 * <p>Esta clase es el punto de entrada de la aplicación. Crea una instancia de la ventana
 * principal {@link Ventana} y la hace visible. La aplicación gestiona el registro de
 * alquileres de apartamentos turísticos, incluyendo alta, baja y visualización de propiedades.</p>
 * 
 * @author Ousama Kassimi
 * @version 1.0
 * @since 23 de noviembre de 2025
 * @see Ventana
 */
public class Main {
	  /**
     * Método principal que inicia la aplicación.
     * 
     * <p>Crea la ventana principal de la aplicación y la establece como visible.
     * Este es el punto de entrada del programa.</p>
     * 
     * @param 
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Pone por defecto este tema
		FlatLightLaf.setup();
		Ventana miVentana = new Ventana();
		miVentana.setVisible(true);
		
	}

}
