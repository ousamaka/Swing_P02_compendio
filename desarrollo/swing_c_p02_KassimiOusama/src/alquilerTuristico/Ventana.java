/*
* Proyecto: swing_c_p02_KassimiOusama
* Paquete: alquilerTuristico
* Archivo: Ventana.java
* Autor/a: Ousama Kassimi
* Fecha: 23 nov 2025 11:47:03
*
* Descripción:
* [Resumen del propósito del archivo/clase.]
*
* Licencia:
* [Condiciones de uso/licencia.]
*/

package alquilerTuristico;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Ventana principal de la aplicación de Gestión de Apartamentos Turísticos -
 * SummerHeat.
 * 
 * <p>
 * Esta clase es responsable de crear la interfaz gráfica principal que
 * contiene:
 * <ul>
 * <li>Barra de menú con opciones de Archivo, Registro y Ayuda</li>
 * <li>Panel de cabecera con botones para Alta y Baja de pisos</li>
 * <li>Apertura de diálogos secundarios para gestión de datos</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Las imágenes de los pisos se precargan en el constructor para optimizar el
 * rendimiento al abrir el diálogo de Alta de Pisos.
 * </p>
 * 
 * @author Ousama Kassimi
 * @version 1.0
 * @since 23 de noviembre de 2025
 * @see VentanaAltaPiso
 */
public class Ventana extends JFrame {

	private JMenuBar miBarra;
	private JMenu archivo, registro, ayuda;
	private JMenuItem salir, altaPiso, bajaPiso, acercaDe;

	private JButton bAnadir, bEliminar;
	private JLabel img1, img2, img3;

	// Lo creamos como JPanel y no directamente como FlowLayout para no crear una
	// "class Cabecera extend JPanel" si no directamente new JPanel(new
	// FlowLayout...
	JPanel cabecera;

	public Ventana() {
		super("Gestion Apartamentos Turisticos - SummerHeat");

		// 1. Obten el tamaño de nuestra pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

		// 2. Calcula la mitad
		int ancho = pantalla.width / 2;
		int alto = pantalla.height / 2;

		// 3. Asignamos el tamaño a la ventana
		this.setSize(ancho, alto);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 4.Y la centramos
		this.setLocationRelativeTo(null);

		// COLORES PALETTE
		Color colorFondoPrincipal = new Color(255, 255, 255);// Blanco
		Color colorTextoPrincipal = new Color(13, 27, 42);// Azul muy oscuro
		Color colorAcentoPrimario = new Color(29, 73, 119);// Azul profesional
		Color colorAcentoHover = new Color(43, 90, 150);// Azul más claro
		Color colorFondoAlt = new Color(240, 240, 240);// Gris claro

		ImageIcon miIcono = new ImageIcon(getClass().getResource("/recursos/icono.jpg"));
		setIconImage(miIcono.getImage());

		// Aplicar colores a la ventana
		this.getContentPane().setBackground(colorFondoPrincipal);

		// 5. La hacemos visible
		this.setVisible(true);

		this.setLayout(new BorderLayout());

		// Reescalo las imagenes aqui porque si lo hago en el JDialog como ya lo hice,
		// tarda muchisimo en abrir al darle al boton de alta piso
		img1 = crearImagenEscalada("/recursos/piso1.png");
		img2 = crearImagenEscalada("/recursos/piso2.png");
		img3 = crearImagenEscalada("/recursos/piso3.png");

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		// MENU
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		miBarra = new JMenuBar();

		archivo = new JMenu("Archivo");
		registro = new JMenu("Registro");
		ayuda = new JMenu("Ayuda");

		salir = new JMenuItem("Salir");
		altaPiso = new JMenuItem("Alta Piso");
		bajaPiso = new JMenuItem("Baja Piso");
		acercaDe = new JMenuItem("Acerca De");

		archivo.add(salir);
		registro.add(altaPiso);
		registro.add(bajaPiso);
		ayuda.add(acercaDe);

		miBarra.add(archivo);
		miBarra.add(registro);
		miBarra.add(ayuda);

		this.setJMenuBar(miBarra);

		archivo.setMnemonic(KeyEvent.VK_A); // Alt + A
		registro.setMnemonic(KeyEvent.VK_R); // Alt + R
		ayuda.setMnemonic(KeyEvent.VK_Y); // Alt + Y

		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));// Control + F4
		altaPiso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));// Control + A
		bajaPiso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));// Control + B
		acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));// Control + D

		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Salir?", "Salir",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		altaPiso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				abrirDialogoAltaPiso();
			}
		});

		bajaPiso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Esta opcion sigue en desarrollo pronto estara disponible.",
						"Proximamente", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		acercaDe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"Aplicación: Gestión Apartamentos Turísticos - SummerHeat\n" + "Empresa: SummerHeat\n"
								+ "Version: 1.0\n" + "Autor: Ousama Kassimi\n" + "Fecha Creacion: Noviembre 2025",
						"Acerca De", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		// CABECERA
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		// Botones
		// Cargamos y redimensiona los iconos
		ImageIcon iAnadir = new ImageIcon(getClass().getResource("/recursos/mas.png"));
		ImageIcon iEliminar = new ImageIcon(getClass().getResource("/recursos/eliminar.png"));

		Image imagenRedimensionadaAnadir = iAnadir.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image imagenRedimensionadaEliminar = iEliminar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// Crea ImageIcon a partir de la imagen redimensionada para poder meterlos
		// dentro de los botones:
		ImageIcon iconoAnadir = new ImageIcon(imagenRedimensionadaAnadir);
		ImageIcon iconoEliminar = new ImageIcon(imagenRedimensionadaEliminar);

		// Y los metemos:
		bAnadir = new JButton("Alta Piso", iconoAnadir);
		bEliminar = new JButton("Baja Piso", iconoEliminar);

		cabecera = new JPanel(new FlowLayout());
		cabecera.setBackground(colorAcentoPrimario);// Cambio el naranja por el azul cro que se ve menos cantoso, duele
													// menos en la vista, aunque me gustaba el naranja pero no pasa la
													// prueba
		cabecera.add(bAnadir);
		cabecera.add(bEliminar);

		// Boton por defecto de la ventana - al darle a Enter se abre sol:
		getRootPane().setDefaultButton(bAnadir);

		this.add(cabecera, BorderLayout.NORTH);

		// lo busque, para que quede mas bonito,
		cabecera.setBorder(new EmptyBorder(40, 0, 0, 0)); // arriba, izquierda, abajo, derecha

		bAnadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				abrirDialogoAltaPiso();
			}
		});

		bEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Esta opcion sigue en desarrollo pronto estara disponible.",
						"Proximamente", JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}

	// creo un metodo para reescalar las imagenes:
	public JLabel crearImagenEscalada(String ruta) {
		int ancho = 550; // ancho fijo para todas
		int alto = 450; // alto fijo para todas

		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(ruta));
		Image img = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(img);
		return new JLabel(iconoEscalado);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------
	// VENTANA ALTA PISO
	// ----------------------------------------------------------------------------------------------------------
	public void abrirDialogoAltaPiso() {
		// Si lo ponemos a TRUE, nos dejara solo abrir uno, osea seria modal, pero si lo
		// ponemos a FALSE, podremos abrir la que queramos,
		// tipo como el foco, no nos deja salir de la venana.
		VentanaAltaPiso miDialogoSecundaria = new VentanaAltaPiso(this, "Alta Pisos", true, img1, img2, img3);
		miDialogoSecundaria.setVisible(true);
	}
}
