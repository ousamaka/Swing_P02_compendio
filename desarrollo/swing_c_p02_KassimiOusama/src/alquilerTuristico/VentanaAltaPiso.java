/*
* Proyecto: swing_c_p02_KassimiOusama
* Paquete: alquilerTuristico
* Archivo: VentanaAltaPiso.java
* Autor/a: Ousama Kassimi
* Fecha: 23 nov 2025 17:20:32
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
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Diálogo modal para dar de alta nuevos apartamentos turísticos.
 * 
 * <p>
 * Esta clase es un {@link JDialog} que implementa una interfaz completa para
 * registrar nuevos pisos en el sistema. Incluye:
 * <ul>
 * <li>Datos del arrendador (nombre, apellido, DNI, teléfono)</li>
 * <li>Datos del inmueble (dirección, provincia, fechas, número de
 * habitaciones)</li>
 * <li>Galerías de imágenes con navegación</li>
 * <li>Resumen de información en pestañas</li>
 * <li>Validación de datos con entrada de usuario</li>
 * <li>Cálculo automático de precios según características</li>
 * </ul>
 * </p>
 * 
 * <p>
 * El diálogo utiliza {@link GridBagLayout} para un posicionamiento preciso y
 * {@link JDateChooser} para la selección de fechas.
 * </p>
 * 
 * @author Ousama Kassimi
 * @version 1.0
 * @since 23 de noviembre de 2025
 * @see JDialog
 * @see DatosInmueble
 * @see DatosArrendado
 * @see Resumen
 */

public class VentanaAltaPiso extends JDialog {

	// --- PALETA DE COLORES ---
	private static final Color COLOR_FONDO_PANELES = Color.decode("#DEE0E0");
	private static final Color COLOR_TEXTO_PRINCIPAL = Color.decode("#2C3E50");
	private static final Color COLOR_ACENTO = Color.decode("#2980B9");
	// usaremos por ejemplo datosArrendado.setBackground(COLOR_FONDO_PANELES);, pero
	// podriamos usar perfectamente hexadecimal y rgb
	// datosArrendado.setBackground(Color.decode("#C8C7C6"));
	// datosArrendado.setBackground(new Color(255, 0, 0));

	// -----------------------------------

	Cabecera cabecera;
	DatosInmueble datosInmueble;
	DatosArrendado datosArrendado;

	/**
	 * Constructor del diálogo de alta de pisos.
	 * 
	 * <p>
	 * Configura la ventana modal con tamaño máximo disponible en pantalla, crea los
	 * tres paneles principales y configura todos los oyentes de eventos.
	 * </p>
	 * 
	 * @param parent Ventana padre que abre este diálogo
	 * @param titulo Título del diálogo a mostrar en la barra de título
	 * @param modal  {@code true} para diálogo modal, {@code false} para no modal
	 * @param img1   Primera imagen del piso (JLabel ya escalada)
	 * @param img2   Segunda imagen del piso (JLabel ya escalada)
	 * @param img3   Tercera imagen del piso (JLabel ya escalada)
	 * 
	 * @see JDialog#JDialog(Frame, String, boolean)
	 */

	public VentanaAltaPiso(JFrame parent, String titulo, boolean modal, JLabel img1, JLabel img2, JLabel img3) {
		super(parent, titulo, modal);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Fondo General
		this.getContentPane().setBackground(COLOR_FONDO_PANELES);

		// Obtenemos el tamaño maximo de pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

		// Esto permite saber qué área de la pantalla está realmente disponible para tu
		// ventana.
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

		// Calcula el ancho útil, restando los márgenes izquierdo y derecho del total.
		int width = pantalla.width - screenInsets.left - screenInsets.right;
		// Calcula la altura útil, restando los márgenes superior e inferior del total.
		int height = pantalla.height - screenInsets.top - screenInsets.bottom;
//				setSize(width, height);
		// Y lo usamos
		setMinimumSize(new Dimension(width, height)); // ancho, alto
		pack();
		setLocationRelativeTo(parent);

		ImageIcon miIcono = new ImageIcon(getClass().getResource("/recursos/icono.jpg"));
		setIconImage(miIcono.getImage());

		cabecera = new Cabecera();
		this.add(cabecera, BorderLayout.NORTH);

		datosArrendado = new DatosArrendado();
		this.add(datosArrendado, BorderLayout.EAST);

		datosInmueble = new DatosInmueble();
		this.add(datosInmueble, BorderLayout.WEST);

		// MI VERSION QUE NO LOGRO QUE FUNCIONE:¨(de verdad que no se porque no
		// funciona, le he echado un buen rato y nada)

//		JPanel panelCentral = new JPanel();
//		panelCentral.setLayout(new BorderLayout());
//		JLabel lTituloImg = new JLabel("Imagenes del piso");
//
//		// lo busque para centrarlo y bien y mas cosas, solo estetica:
//		lTituloImg.setHorizontalAlignment(SwingConstants.CENTER);
//		lTituloImg.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//		lTituloImg.setFont(new Font("SansSerif", Font.BOLD, 22));
//
//		// imagenes
//
//		JLabel[] iconos = { img1, img2, img3 };
//		int[] indice = { 0 }; // si no la pongo como array no me funciona
//
//		// Esta parte me AYUDE EN GENERAL, porque no lograba sacarlo, parece ser que si usas
//		// una funcion landa no puedes declarar indice como variable local, tiene que
//		// ser si o si effectively final:
//		// al inicio solo se ve la primera image, y segun el boton va hacia delante o
//		// hacia atras:
//		for (int i = 0; i < iconos.length; i++) {
//			iconos[i].setVisible(i == indice[0]);
//			panelCentral.add(iconos[i], BorderLayout.CENTER);
//		}
//
//		JButton bSiguiente = new JButton(">");
//		bSiguiente.addActionListener(e -> {
//			iconos[indice[0]].setVisible(false);
//			indice[0]++;
//			if (indice[0] >= iconos.length)
//				indice[0] = 0;
//			iconos[indice[0]].setVisible(true);
//		});
//
//		JButton bAnterior = new JButton("<");
//
//		bAnterior.addActionListener(e -> {
//			iconos[indice[0]].setVisible(false);
//			indice[0]--;
//			if (indice[0] < 0)
//				indice[0] = iconos.length - 1;
//			iconos[indice[0]].setVisible(true);
//		});
//
//		panelCentral.add(bSiguiente, BorderLayout.EAST);
//		panelCentral.add(bAnterior, BorderLayout.WEST);
//
//		panelCentral.add(lTituloImg, BorderLayout.NORTH);
//		this.add(panelCentral, BorderLayout.CENTER);

		// CENTRO IMAGENES: -- Usando IA para arreglar mis errores:
		JPanel panelCentral = new JPanel(new BorderLayout());
		JLabel lTituloImg = new JLabel("Imagenes del piso");

		// titulo...
		lTituloImg.setHorizontalAlignment(SwingConstants.CENTER);
		lTituloImg.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		lTituloImg.setFont(new Font("SansSerif", Font.BOLD, 22));
		panelCentral.add(lTituloImg, BorderLayout.NORTH);

		// panel solo para las imágenes
		JPanel panelImagenes = new JPanel();
		panelCentral.add(panelImagenes, BorderLayout.CENTER);

		JLabel[] iconos = { img1, img2, img3 };
		final int[] indice = { 0 };

		// al inicio solo se ve la primera
		for (int i = 0; i < iconos.length; i++) {
			iconos[i].setVisible(i == indice[0]);
			panelImagenes.add(iconos[i]);
		}

		JButton bSiguiente = new JButton(">");
		JButton bAnterior = new JButton("<");

		bSiguiente.addActionListener(e -> {
			iconos[indice[0]].setVisible(false);
			indice[0]++;
			if (indice[0] >= iconos.length)
				indice[0] = 0;
			iconos[indice[0]].setVisible(true);
		});

		bAnterior.addActionListener(e -> {
			iconos[indice[0]].setVisible(false);
			indice[0]--;
			if (indice[0] < 0)
				indice[0] = iconos.length - 1;
			iconos[indice[0]].setVisible(true);
		});

		panelCentral.add(bSiguiente, BorderLayout.EAST);
		panelCentral.add(bAnterior, BorderLayout.WEST);

		datosArrendado.setBackground(COLOR_FONDO_PANELES);
		this.add(panelCentral, BorderLayout.CENTER);

		// SUR: Resumen con botones
		Resumen resumen = new Resumen();

		// ICONOS

		// Icono Imprimir
		ImageIcon iImprimir = new ImageIcon(getClass().getResource("/recursos/imprimir.png"));
		Image imagenRedimensionadaImprimir = iImprimir.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon iconoImprimir = new ImageIcon(imagenRedimensionadaImprimir);

		// Icono Nuevo
		ImageIcon iNuevo = new ImageIcon(getClass().getResource("/recursos/nuevo.png"));
		Image imagenRedimensionadaNuevo = iNuevo.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon iconoNuevo = new ImageIcon(imagenRedimensionadaNuevo);

		// Icono Guardar
		ImageIcon iGuardar = new ImageIcon(getClass().getResource("/recursos/guardar.png"));
		Image imagenRedimensionadaGuardar = iGuardar.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon iconoGuardar = new ImageIcon(imagenRedimensionadaGuardar);

		// BOTONES
		JButton bImprimir = new JButton("Imprimir a Documento", iconoImprimir);
		JButton bNuevo = new JButton("Nuevo", iconoNuevo);
		JButton bGuardar = new JButton("Guardar", iconoGuardar);

		JPanel panelBotonesResumen = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
		panelBotonesResumen.add(bImprimir);
		panelBotonesResumen.add(bNuevo);
		panelBotonesResumen.add(bGuardar);

		JPanel panelResumenZona = new JPanel(new BorderLayout());
		panelResumenZona.add(resumen, BorderLayout.CENTER);
		panelResumenZona.add(panelBotonesResumen, BorderLayout.SOUTH);

		this.add(panelResumenZona, BorderLayout.SOUTH);

		// LISTENER DEL BOTON IMPRIMIR
		bImprimir.addActionListener(e -> {
			if (validarFormularios()) {
				// Rellenar el Resumen
				resumen.setNombre(datosArrendado.txtNombre.getText());
				resumen.setApellido(datosArrendado.txtApellido.getText());
				resumen.setDni(datosArrendado.txtDni.getText());
				resumen.setTelefono(datosArrendado.txtTelefono.getText());

				resumen.setDireccion(datosInmueble.txtDireccion.getText());
				resumen.setProvincia((String) datosInmueble.cbProvincia.getSelectedItem());
				resumen.setNumHuespedes((int) datosInmueble.spNumHuespedes.getValue());
				resumen.setNumCamas((int) datosInmueble.spNumCamas.getValue());
				resumen.setTipoCama((String) datosInmueble.cbTipoCamas.getSelectedItem());
				resumen.setNumBanos((int) datosInmueble.spNumBanos.getValue());
				resumen.setPrecio(datosInmueble.txtPrecioMinimo.getText());

				JOptionPane.showMessageDialog(this, "Registro Imprimido", "Correcto", JOptionPane.INFORMATION_MESSAGE);
			}
		});


		//Boton nuevo
		bNuevo.addActionListener(e -> {

			// Rellenar el Resumen
			resumen.setNombre("-");
			resumen.setApellido("-");
			resumen.setDni("-");
			resumen.setTelefono("-");

			resumen.setDireccion("-");
			resumen.setProvincia("-");
			resumen.setNumHuespedes(1);
			resumen.setNumCamas(1);
			resumen.setTipoCama("-");
			resumen.setNumBanos(1);
			resumen.setPrecio(datosInmueble.txtPrecioMinimo.getText());

			// Datos Arrendador en blanco:
			datosArrendado.txtNombre.setText("");
			datosArrendado.txtApellido.setText("");
			datosArrendado.txtDni.setText("");
			datosArrendado.txtTelefono.setText("");

			// Datos Inmueble en blanco:

			// JTextField
			datosInmueble.txtDireccion.setText("");

			// JSpinner
			datosInmueble.spNumHuespedes.setValue(1);
			datosInmueble.spNumDormitorios.setValue(1);
			datosInmueble.spNumBanos.setValue(1);
			datosInmueble.spNumCamas.setValue(1);

			// JCheckBox
//			datosInmueble.chkNinos.setSelected(false);
			// al ponerlo a false esta bien, me lo desactiva, pero solo visual la logica no
			// funciona no se actualiza ni me deja de ocultar la parte de extra niños, la
			// forma mas sencilla de solucionarlo (que la busque en internet) es usar
			// "doClick()" que simula que el usuario le a dado click:
			if (datosInmueble.chkNinos.isSelected()) { // si esta seleccionado osea activo
				datosInmueble.chkNinos.doClick(); // lo ponemos a false y si no esta activo no lo tocamos
			}
			// JComboBox
			datosInmueble.cbProvincia.setSelectedItem("Álava");
			datosInmueble.cbTipoCamas.setSelectedItem("Cama Simple");

			// JDateChooser
			datosInmueble.miCalendar.setTime(datosInmueble.hoy);
			datosInmueble.miCalendar.add(Calendar.YEAR, 1);
			datosInmueble.dcFechaFinal.setMinSelectableDate(datosInmueble.miCalendar.getTime());
			datosInmueble.dcFechaFinal.setDate(datosInmueble.dcFechaFinal.getMinSelectableDate());
			datosInmueble.dcFechaAlta.setDate(datosInmueble.hoy);

			JOptionPane.showMessageDialog(this, "Registro Nuevo", "Nuevo Registro", JOptionPane.INFORMATION_MESSAGE);

		});

		// LISTENER DEL BOTÓN GUARDAR
		// por defecto
		getRootPane().setDefaultButton(bGuardar);
		bGuardar.addActionListener(e -> {
			if (validarFormularios()) {
				JOptionPane.showMessageDialog(this, "Registro Guardado", "Registro Guardado",
						JOptionPane.INFORMATION_MESSAGE);

			}

		});

	}

	// *************************************************************************************************************************************************************************************
	// VALIDAR FORMULARIO
	// *************************************************************************************************************************************************************************************
	/**
	 * Valida todos los formularios de entrada de datos.
	 * 
	 * <p>
	 * Verifica que todos los campos obligatorios estén rellenados y cumplan los
	 * requisitos de formato:
	 * <ul>
	 * <li><strong>Nombre arrendador:</strong> No vacío</li>
	 * <li><strong>Apellido arrendador:</strong> No vacío</li>
	 * <li><strong>DNI:</strong> Formato 8 dígitos + 1 letra mayúscula (ej:
	 * 12345678A)</li>
	 * <li><strong>Teléfono:</strong> 9 dígitos exactos</li>
	 * <li><strong>Dirección inmueble:</strong> No vacía</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Si algún campo es inválido, muestra un mensaje de error y establece el foco
	 * en el campo problemático.
	 * </p>
	 * 
	 * @return {@code true} si todos los formularios son válidos, {@code false} en
	 *         caso contrario
	 */

	// Valida formularios
	private boolean validarFormularios() {
		// Validar DatosArrendado
		String nombre = datosArrendado.txtNombre.getText().trim();
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre del arrendador es obligatorio.", "Error: Campo vacio",
					JOptionPane.ERROR_MESSAGE);
			datosArrendado.txtNombre.requestFocus();
			return false;
		}

		String apellido = datosArrendado.txtApellido.getText().trim();
		if (apellido.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El apellido del arrendador es obligatorio.", "Error: Campo vacio",
					JOptionPane.ERROR_MESSAGE);
			datosArrendado.txtApellido.requestFocus();
			return false;
		}

		String dni = datosArrendado.txtDni.getText().trim();
		if (dni.isEmpty() || !dni.matches("\\d{8}[A-Z]")) {
			JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 digitos y 1 letra MAYUSCULA.",
					"Error: DNI invalido", JOptionPane.ERROR_MESSAGE);
			datosArrendado.txtDni.requestFocus();
			return false;
		}

		String telefono = datosArrendado.txtTelefono.getText().trim();
		if (telefono.isEmpty() || !telefono.matches("\\d{9}")) {
			JOptionPane.showMessageDialog(this, "El telefono debe tener exactamente 9 digitos.",
					"Error: Telefono invalido", JOptionPane.ERROR_MESSAGE);
			datosArrendado.txtTelefono.requestFocus();
			return false;
		}

		// Validar DatosInmueble
		String direccion = datosInmueble.txtDireccion.getText().trim();
		if (direccion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "La direccion del inmueble es obligatoria.", "Error: Campo vacio",
					JOptionPane.ERROR_MESSAGE);
			datosInmueble.txtDireccion.requestFocus();
			return false;
		}

		return true;
	}

}

//*************************************************************************************************************************************************************************************
//CABECERA
//*************************************************************************************************************************************************************************************
/**
 * Panel de cabecera con título de la aplicación.
 * 
 * <p>
 * Componente interno que muestra el título "Gestión Apartamentos Turísticos -
 * SummerHeat" con formato profesional, fondo naranja y borde negro.
 * </p>
 */
class Cabecera extends JPanel {

	private static final Color COLOR_TEXTO_PRINCIPAL = Color.decode("#2C3E50");
	private static final Color COLOR_ACENTO = Color.decode("#2980B9");

	public Cabecera() {
		JLabel lTitulo = new JLabel("Gestion Apartamentos Turisticos - SummerHeat");
		lTitulo.setFont(new Font("Serif", Font.BOLD, 33));
		lTitulo.setForeground(Color.WHITE); // texto blanco
		setBackground(COLOR_ACENTO); // azul #2980B9
		this.add(lTitulo);
		setBorder(new LineBorder(COLOR_TEXTO_PRINCIPAL, 2));
		setVisible(true);
	}
}

//*************************************************************************************************************************************************************************************
//DATOS ARRENDADOR
//*************************************************************************************************************************************************************************************

/**
 * Panel para datos del arrendador.
 * 
 * <p>
 * Componente interno que contiene campos para registrar:
 * <ul>
 * <li>Nombre (JTextField)</li>
 * <li>Apellido (JTextField)</li>
 * <li>DNI (JTextField con validación InputVerifier)</li>
 * <li>Teléfono (JTextField con validación InputVerifier)</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Utiliza {@link GridBagLayout} para alineación precisa de componentes.
 * </p>
 */

class DatosArrendado extends JPanel {

	JLabel lNombre, lApellido, lDni, lTelefono;
	JTextField txtNombre, txtApellido, txtDni, txtTelefono;

	private static final Color COLOR_FONDO_PANELES = Color.decode("#DEE0E0");

	public DatosArrendado() {

		setLayout(new GridLayout(4, 2, 10, 10));
		setBackground(COLOR_FONDO_PANELES);

		lNombre = new JLabel("Nombre: ");
		lApellido = new JLabel("Apellido: ");
		lDni = new JLabel("DNI: ");
		lTelefono = new JLabel("Telefono: ");

		txtNombre = new JTextField(16);
		txtApellido = new JTextField(16);
		// VALIDACIONES

		// Dni
		txtDni = new JTextField(16);
		// Ayuda contextual
		txtDni.setToolTipText("Introduce un DNI valido, 8 Digitos y 1 Letra (MAYUSCULA)");
		// verificamos 8 Digitos y 1 Letra
		txtDni.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField dni = (JTextField) input;
				String texto = dni.getText().trim(); // Le quitamos los espacios
				// Solo permitir salir si son 8 digitos y 1 Letra exactos
				if (texto.matches("\\d{8}[A-Z]")) {
					// Restauramos el borde:
					dni.setBorder(UIManager.getBorder("TextField.border"));
					return true; // puedes salir
				} else {
					// Podriamos mostrar un mensaje
//		            JOptionPane.showMessageDialog(
//		                tf, "El DNI debe tener exactamente 8 digitos y 1 letra.",
//		                "Error de validación", JOptionPane.WARNING_MESSAGE
//		            );
					// Borde en Rojo
					dni.setBorder(BorderFactory.createLineBorder(Color.RED));
					return false; // No puede salir hasta corregir
				}
			}
		});

		// Telefono
		txtTelefono = new JTextField(16);
		// Ayuda contextual
		txtTelefono.setToolTipText("Introduce un numero de telefono 9 Digitos");
		// verificamos 9 digitos
		txtTelefono.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField tf = (JTextField) input;
				String texto = tf.getText().trim(); // Le quitamos los espacios
				// Solo permitir salir si son 9 digitos exactos
				if (texto.matches("\\d{9}")) {
					// Restauramos el borde:
					tf.setBorder(UIManager.getBorder("TextField.border"));
					return true; // puedes salir
				} else {
					// Podriamos mostrar un mensaje
//		            JOptionPane.showMessageDialog(
//		                tf, "El telefono debe tener exactamente 9 digitos.",
//		                "Error de validación", JOptionPane.WARNING_MESSAGE
//		            );
					tf.setBorder(BorderFactory.createLineBorder(Color.RED));
					return false; // No puede salir hasta corregir
				}
			}
		});

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		Insets insert = new Insets(5, 10, 5, 10);
		GridBagConstraints constraints;

		// titulo en el borde, queda regulin:
//		setBorder(BorderFactory.createTitledBorder("Datos Arrendador"));
		
		//Queda mucho mejor (lo busque):
		setBorder(
				BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Datos Arrendador",
				TitledBorder.CENTER,
				TitledBorder.TOP
				)
				);


		// Fila 1: Nombre
		constraints = new GridBagConstraints(0, // Columna
				0, // Fila
				1, // ancho columna
				1, // alto Fila
				0, // peso Horizontal
				0, // peso Vertical
				GridBagConstraints.EAST, // Alineacion
				GridBagConstraints.NONE, // fill -> Relleno
				insert, // margenes externos
				0, // relleno interno horizontal
				0// relleno interno vertical
		);
		layout.setConstraints(lNombre, constraints);
		add(lNombre);

		constraints = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(txtNombre, constraints);
		add(txtNombre);

		// Fila 2: Apellido
		constraints = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lApellido, constraints);
		add(lApellido);

		constraints = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(txtApellido, constraints);
		add(txtApellido);

		// Fila 3: DNI
		constraints = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lDni, constraints);
		add(lDni);

		constraints = new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(txtDni, constraints);
		add(txtDni);

		// Fila 4: Telefono
		constraints = new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lTelefono, constraints);
		add(lTelefono);

		constraints = new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(txtTelefono, constraints);
		add(txtTelefono);
	}
}
//*************************************************************************************************************************************************************************************
//DATOS INMUEBLE
//*************************************************************************************************************************************************************************************

/**
 * Panel para datos del inmueble.
 * 
 * <p>
 * Componente interno que gestiona:
 * <ul>
 * <li>Dirección y provincia</li>
 * <li>Fechas de alta y disponibilidad (JDateChooser)</li>
 * <li>Número de huéspedes, dormitorios, baños, camas (JSpinner)</li>
 * <li>Tipo de camas (JComboBox)</li>
 * <li>Extras para niños con panel dinámico (JCheckBox)</li>
 * <li>Valoración del piso (JSlider 1-5 estrellas) - COMPONENTE ADICIONAL</li>
 * <li>Precio mínimo calculado automáticamente</li>
 * </ul>
 * </p>
 * 
 * <p>
 * <strong>2.7 COMPONENTE ADICIONAL CON EVENTO:</strong> Se agregó un
 * {@link JSlider} para valoración del piso (1-5 estrellas) con un
 * {@link javax.swing.event.ChangeListener} que actualiza el tooltip
 * dinámicamente.
 * </p>
 */

class DatosInmueble extends JPanel {

	JLabel lDireccion, lProvincia, lFechaAlta, lFechaFinal, lNumHuespedes;
	JLabel lNumDormitorios, lNumBanos, lNumCamas, lTipoCamas, lNinos;
	JLabel lExtrasNinos, lEdadNinos, lImagenes, lPrecioMinimo;

	JTextField txtDireccion, txtExtrasNinos, txtPrecioMinimo;
	JComboBox<String> cbProvincia, cbTipoCamas;
	JDateChooser dcFechaAlta, dcFechaFinal;
	JSpinner spNumHuespedes, spNumDormitorios, spNumBanos, spNumCamas, spEdadNinos;
	JCheckBox chkNinos;
	JPanel pnlImagenes;

	int pCamaSimple, pCamaDoble, pSofaCama, pBano, pExtraNino;

	Calendar miCalendar;
	Date hoy;

	/**
	 * Constructor del panel de datos del inmueble.
	 * 
	 * <p>
	 * Inicializa todos los componentes incluyendo:
	 * <ul>
	 * <li>Validadores InputVerifier para dirección</li>
	 * <li>Configuración de rangos en JSpinner</li>
	 * <li>Panel dinámico para extras de niños controlado por checkbox</li>
	 * <li>JSlider para valoración (1-5)</li>
	 * <li>Listeners para cálculo automático de precios</li>
	 * </ul>
	 * </p>
	 */
	private static final Color COLOR_FONDO_PANELES = Color.decode("#DEE0E0");

	public DatosInmueble() {

		setBackground(COLOR_FONDO_PANELES);

		// ETIQUETAS
		lDireccion = new JLabel("Direccion: ");
		lProvincia = new JLabel("Provincia: ");
		lFechaAlta = new JLabel("Fecha de alta: ");
		lFechaFinal = new JLabel("Fecha final disponibilidad: ");
		lNumHuespedes = new JLabel("Numero de huespedes: ");
		lNumDormitorios = new JLabel("Numero de dormitorios: ");
		lNumBanos = new JLabel("Numero de baños: ");
		lNumCamas = new JLabel("Numero de camas: ");
		lTipoCamas = new JLabel("Tipo de camas: ");
		lNinos = new JLabel("¿Niños?: ");
		lExtrasNinos = new JLabel("Extras Niños: ");
		lEdadNinos = new JLabel("Edad: ");
		lImagenes = new JLabel("Imagenes: ");
		lPrecioMinimo = new JLabel("Precio Minimo (precio minimo/dia): ");

		// COMPONENTES

		// Direccion
		txtDireccion = new JTextField(16);
		txtDireccion.setToolTipText("Introduce la direccion del inmueble");

		// validar direccion no null
		txtDireccion.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField direccion = (JTextField) input;
				String di = direccion.getText().trim(); // Le quitamos los espacios
				// Solo permitir salir si son 8 digitos y 1 Letra exactos
				if (!di.isEmpty()) {
					// Restauramos el borde:
					direccion.setBorder(UIManager.getBorder("TextField.border"));
					return true; // puedes salir
				} else {
					// Podriamos mostrar un mensaje
//		            JOptionPane.showMessageDialog(
//		                tf, "El DNI debe tener exactamente 8 digitos y 1 letra.",
//		                "Error de validación", JOptionPane.WARNING_MESSAGE
//		            );
					// Borde en Rojo
					direccion.setBorder(BorderFactory.createLineBorder(Color.RED));
					return false; // No puede salir hasta corregir
				}
			}
		});

		// Provincia (ComboBox)
		String[] provincias = { "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz", "Barcelona",
				"Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ceuta", "Ciudad Real", "Córdoba", "La Coruña",
				"Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Jaén", "León", "Lérida",
				"Logroño", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Palencia", "Palmas (Las)",
				"Pontevedra", "La Rioja", "Salamanca", "Tarragona", "Santa Cruz de Tenerife", "Teruel", "Toledo",
				"Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza" };
		cbProvincia = new JComboBox<>(provincias);
		cbProvincia.setPreferredSize(new Dimension(200, 21));

		hoy = new Date();
		// Fecha de alta (JDateChooser)
		dcFechaAlta = new JDateChooser();
		dcFechaAlta.setPreferredSize(new Dimension(200, 21));
		dcFechaAlta.setDateFormatString("dd/MM/yyyy");
		dcFechaAlta.setMinSelectableDate(hoy);
		dcFechaAlta.setDate(dcFechaAlta.getMinSelectableDate());

		// Fecha final de disponibilidad (JDateChooser)
		dcFechaFinal = new JDateChooser();
		dcFechaFinal.setPreferredSize(new Dimension(200, 21));
		dcFechaFinal.setDateFormatString("dd/MM/yyyy");
		miCalendar = Calendar.getInstance();
		miCalendar.setTime(hoy);
		miCalendar.add(Calendar.YEAR, 1);
		dcFechaFinal.setMinSelectableDate(miCalendar.getTime());
		dcFechaFinal.setDate(dcFechaFinal.getMinSelectableDate());

		// Numero de huespedes (JSpinner)
		spNumHuespedes = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1)); // 1 valor inicial, 2 valor minimo, 3 valor
																			// maximo, 4 incremento (por ejemplo de 1 en
																			// 1, de 2 en 2 etc)
		spNumHuespedes.setPreferredSize(new Dimension(200, 21));
		// Guardar el valor de un JSpinner
//		int valor = (int)spNumHuespedes.getValue();

		// Numero de dormitorios (JSpinner)
		spNumDormitorios = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
		spNumDormitorios.setPreferredSize(new Dimension(200, 21));

		// Numero de baños (JSpinner)
		spNumBanos = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
		spNumBanos.setPreferredSize(new Dimension(200, 21));

		// Numero de camas (JSpinner)
		spNumCamas = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
		spNumCamas.setPreferredSize(new Dimension(200, 21));

		// Tipo de camas (ComboBox)
		String[] tiposCamas = { "Cama Simple", "Cama Doble", "Sofá cama" };
		cbTipoCamas = new JComboBox<>(tiposCamas);
		cbTipoCamas.setPreferredSize(new Dimension(200, 21));

		// Guardar el valor de un JComboBox
//		String valor = (String)cbTipoCamas.getSelectedItem();

		// o bien usamos opciones separadas:
//		cbTipoCamas.addItem("Opción 1");
//      cbTipoCamas.addItem("Opción 2");
//      cbTipoCamas.addItem("Opción 3");

		// Esta parte la verdad no tiene mucho sentido lo de activar o desactivar numero
		// de camas, porque por defecto tenemos que minimo tenga 1 el JSlider de numero
		// de camas, entonces siempre va a ver una cama, te lo explicare mejor en el
		// leeme.txt

		// Niños (CheckBox)
		chkNinos = new JCheckBox();

		// Extras Niños
		txtExtrasNinos = new JTextField(16);
		txtExtrasNinos.setToolTipText("Extras para niños");
		txtExtrasNinos.setPreferredSize(new Dimension(200, 21));
		spEdadNinos = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

		// panel de niños:
		JPanel panelNinos = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
		panelNinos.add(lEdadNinos);
		panelNinos.add(spEdadNinos);
		panelNinos.add(lExtrasNinos);
		panelNinos.add(txtExtrasNinos);

		panelNinos.setVisible(false);

		chkNinos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JCheckBox valor = (JCheckBox) e.getSource();
				if (valor.isSelected()) {
					panelNinos.setVisible(true);
				} else {
					panelNinos.setVisible(false);
					// lo reiniciamos si no esta seleccionada:
					txtExtrasNinos.setText("");
					spEdadNinos.setValue(0);
				}
			}
		});

		txtExtrasNinos.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField txt = (JTextField) input;
				// Solo permitir salir si son 8 digitos y 1 Letra exactos
				if (((int) spEdadNinos.getValue()) < 4) {
					txtExtrasNinos.setText("Cuna");
					return true; // puedes salir
				} else {
					txtExtrasNinos.setText("Cama supletoria pequeña");

					return true; // puedes salir
				}
			}
		});
		// Imagenes (Panel)
		pnlImagenes = new JPanel();
		pnlImagenes.setPreferredSize(new Dimension(200, 100));
		pnlImagenes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pnlImagenes.setBackground(Color.GREEN);

		// Precio Minimo
		txtPrecioMinimo = new JTextField(16);
		txtPrecioMinimo.setToolTipText("El precio minimo por dia");
		txtPrecioMinimo.setEditable(false);

		pCamaSimple = 15;
		pCamaDoble = 20;
		pSofaCama = 15;
		pBano = 25;
		pExtraNino = 12;

		// Me gusta mas esta mera pero te exige cambiar el foco, y no habia leido lo de
		// que se actualice automaticamente:
//		txtPrecioMinimo.setInputVerifier(new InputVerifier() {
//			@Override
//			public boolean verify(JComponent input) {
//				JTextField txt = (JTextField) input;
//				
//				String tipoCama = (String)cbTipoCamas.getSelectedItem();
//				
//				int precioCama = 0;
//				int precioNino = 0;
//				
//				if (tipoCama.equals("Cama Simple") ||(tipoCama.equals("Sofá cama" ))) {		
//					precioCama = pCamaSimple;
//				}
//				if (tipoCama.equals("Cama Doble")) {			
//					precioCama = pCamaDoble;
//				}
//				if (chkNinos.isSelected()) {
//					precioNino = pExtraNino;
//				}
//				txtPrecioMinimo.setText(String.valueOf(precioCama + (int)spNumBanos.getValue() * pBano + precioNino));
//				return true; // puedes salir
//
//			}
//		});

		// Calcular Precio, lo llamamos en los 3 casos que tenemos:
		cbTipoCamas.addActionListener(e -> calcularPrecio());
		spNumBanos.addChangeListener(e -> calcularPrecio());
		chkNinos.addActionListener(e -> calcularPrecio());
		spNumCamas.addChangeListener(e -> calcularPrecio());

		// para ponerle los valores por defecto llamare al metodo si no, no me muestra
		// nada:
		calcularPrecio();

		// Creamos el layout y añadimos los elementos
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		Insets insert = new Insets(5, 10, 5, 10);
		GridBagConstraints constraints;

		// Borde con titulo porque a horas de entregar el proyecto, recuerdo que no puse
		// titulo y para no cambiar toda la estructura se me ocurrio meterle el titulo
		// en el borde que queda de 10:
		setBorder(
				BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Datos Inmueble",
				TitledBorder.CENTER,
				TitledBorder.TOP
				)
				);
		
		// Fila 0: Direccion
		constraints = new GridBagConstraints(0, // Columna
				0, // Fila
				1, // ocupa 1 columna
				1, // ocupa 1 Fila
				0, // peso Horizontal
				0, // peso Vertical
				GridBagConstraints.WEST, // Alineacion
				GridBagConstraints.NONE, // fill -> Relleno
				insert, // margenes externos
				0, // relleno interno horizontal
				0 // relleno interno vertical
		);
		layout.setConstraints(lDireccion, constraints);
		add(lDireccion);

		constraints = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(txtDireccion, constraints);
		add(txtDireccion);

		// Fila 1: Provincia
		constraints = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lProvincia, constraints);
		add(lProvincia);

		constraints = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(cbProvincia, constraints);
		add(cbProvincia);

		// Fila 2: Fecha de alta
		constraints = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lFechaAlta, constraints);
		add(lFechaAlta);

		constraints = new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(dcFechaAlta, constraints);
		add(dcFechaAlta);

		// Fila 3: Fecha final de disponibilidad
		constraints = new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lFechaFinal, constraints);
		add(lFechaFinal);

		constraints = new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(dcFechaFinal, constraints);
		add(dcFechaFinal);

		// Fila 4: Numero de huespedes
		constraints = new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lNumHuespedes, constraints);
		add(lNumHuespedes);

		constraints = new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(spNumHuespedes, constraints);
		add(spNumHuespedes);

		// Fila 5: Numero de dormitorios
		constraints = new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lNumDormitorios, constraints);
		add(lNumDormitorios);

		constraints = new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(spNumDormitorios, constraints);
		add(spNumDormitorios);

		// Fila 6: Numero de baños
		constraints = new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lNumBanos, constraints);
		add(lNumBanos);

		constraints = new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(spNumBanos, constraints);
		add(spNumBanos);

		// Fila 7: Numero de camas
		constraints = new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lNumCamas, constraints);
		add(lNumCamas);

		constraints = new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(spNumCamas, constraints);
		add(spNumCamas);

		// Fila 8: Tipo de camas
		constraints = new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lTipoCamas, constraints);
		add(lTipoCamas);

		constraints = new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(cbTipoCamas, constraints);
		add(cbTipoCamas);

		// Fila 9: Niños
		constraints = new GridBagConstraints(0, 9, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(lNinos, constraints);
		add(lNinos);

		constraints = new GridBagConstraints(1, 9, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, insert,
				0, 0);
		layout.setConstraints(chkNinos, constraints);
		add(chkNinos);

		// Fila 10: Extras Niños
		constraints = new GridBagConstraints(0, 10, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				insert, 0, 0);
		layout.setConstraints(panelNinos, constraints);
		add(panelNinos);

//		constraints = new GridBagConstraints(1, 10, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
//				insert, 0, 0);
//		layout.setConstraints(txtExtrasNinos, constraints);
//		add(txtExtrasNinos);

		// Fila 11: Imagenes
//		constraints = new GridBagConstraints(0, 11, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
//				insert, 0, 0);
//		layout.setConstraints(lImagenes, constraints);
//		add(lImagenes);
//
//		constraints = new GridBagConstraints(1, 11, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
//				insert, 0, 0);
//		layout.setConstraints(pnlImagenes, constraints);
//		add(pnlImagenes);

		// Fila 12: Precio Minimo
		constraints = new GridBagConstraints(0, 12, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				insert, 0, 0);
		layout.setConstraints(lPrecioMinimo, constraints);
		add(lPrecioMinimo);

		constraints = new GridBagConstraints(0, 13, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				insert, 0, 0);
		layout.setConstraints(txtPrecioMinimo, constraints);
		add(txtPrecioMinimo);

		// VALORACION DEL PISO (componente adicional con evento)
		JLabel lValoracion = new JLabel("Valoracion Piso:");
		JSlider slValoracion = new JSlider(1, 5, 3);

		// Propiedades para que se vea mejor
		slValoracion.setMajorTickSpacing(1);
		slValoracion.setPaintTicks(true);
		slValoracion.setPaintLabels(true);
		slValoracion.setPreferredSize(new Dimension(150, 40)); // Un poco más pequeño
		slValoracion.setBackground(new Color(240, 240, 240)); // Mismo color de fondo que el panel

		// Tooltip inicial
		slValoracion.setToolTipText("Valoración actual: 3 estrellas");

		// Evento: Actualiza el tooltip dinámicamente
		slValoracion.addChangeListener(e -> {
			slValoracion.setToolTipText("Valoración actual: " + slValoracion.getValue() + " estrellas");
		});

		// lo añadimos al layout
		constraints = new GridBagConstraints(0, 14, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				insert, 0, 0);
		layout.setConstraints(lValoracion, constraints);
		add(lValoracion);

		constraints = new GridBagConstraints(1, 14, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				insert, 0, 0);
		layout.setConstraints(slValoracion, constraints);
		add(slValoracion);

	}
	// *************************************************************************************************************************************************************************************
	// CALCULAR PRECIO
	// *************************************************************************************************************************************************************************************

	// Metodo para calcular el precio:
	private void calcularPrecio() {
		String tipoCama = (String) cbTipoCamas.getSelectedItem();

		int precioCama = 0;
		int precioNino = 0;
		int numCamas = (int) spNumCamas.getValue();

		if (tipoCama.equals("Cama Simple") || tipoCama.equals("Sofá cama")) {
			precioCama = pCamaSimple;
		}
		if (tipoCama.equals("Cama Doble")) {
			precioCama = pCamaDoble;
		}
		if (chkNinos.isSelected()) {
			precioNino = pExtraNino;
		}

		int precioTotal = ((precioCama * numCamas) + ((int) (spNumBanos.getValue()) * pBano) + precioNino);
		txtPrecioMinimo.setText(String.valueOf(precioTotal));
	}
}

//*************************************************************************************************************************************************************************************
//RESUMEN
//*************************************************************************************************************************************************************************************

/**
 * Panel de resumen en pestañas (JTabbedPane).
 * 
 * <p>
 * Muestra un resumen de los datos registrados en dos pestañas:
 * <ul>
 * <li><strong>Pestaña 1 - Arrendador:</strong> Nombre, Apellido, DNI,
 * Teléfono</li>
 * <li><strong>Pestaña 2 - Inmueble:</strong> Dirección, Provincia, Huéspedes,
 * Camas, Baños, Precio</li>
 * </ul>
 * </p>
 * 
 * @see JTabbedPane
 */

class Resumen extends JTabbedPane {

	private JLabel lNombreValor, lApellidoValor, lDniValor, lTelefonoValor;
	private JLabel lDireccionValor, lProvinciaValor, lHuespedesValor, lCamasValor, lBanosValor, lPrecioValor;

	public Resumen() {
		// Pestaña Arrendador
		JPanel pArrendador = new JPanel(new GridLayout(4, 2, 5, 5));

		pArrendador.add(new JLabel("Nombre:"));
		lNombreValor = new JLabel("-");
		pArrendador.add(lNombreValor);

		pArrendador.add(new JLabel("Apellido:"));
		lApellidoValor = new JLabel("-");
		pArrendador.add(lApellidoValor);

		pArrendador.add(new JLabel("DNI:"));
		lDniValor = new JLabel("-");
		pArrendador.add(lDniValor);

		pArrendador.add(new JLabel("Teléfono:"));
		lTelefonoValor = new JLabel("-");
		pArrendador.add(lTelefonoValor);

		// Pestaña Inmueble
		JPanel pInmueble = new JPanel(new GridLayout(6, 2, 5, 5));
		pInmueble.add(new JLabel("Dirección:"));
		lDireccionValor = new JLabel("-");
		pInmueble.add(lDireccionValor);

		pInmueble.add(new JLabel("Provincia:"));
		lProvinciaValor = new JLabel("-");
		pInmueble.add(lProvinciaValor);

		pInmueble.add(new JLabel("Huéspedes:"));
		lHuespedesValor = new JLabel("-");
		pInmueble.add(lHuespedesValor);

		pInmueble.add(new JLabel("Camas:"));
		lCamasValor = new JLabel("-");
		pInmueble.add(lCamasValor);

		pInmueble.add(new JLabel("Baños:"));
		lBanosValor = new JLabel("-");
		pInmueble.add(lBanosValor);

		pInmueble.add(new JLabel("Precio mínimo:"));
		lPrecioValor = new JLabel("-");
		pInmueble.add(lPrecioValor);

		// Añadir pestañas
		this.addTab("Arrendador", pArrendador);
		this.addTab("Inmueble", pInmueble);
	}

	// SETTERS PARA ARRENDADOR
	public void setNombre(String nombre) {
		lNombreValor.setText(nombre);
	}

	public void setApellido(String apellido) {
		lApellidoValor.setText(apellido);
	}

	public void setDni(String dni) {
		lDniValor.setText(dni);
	}

	public void setTelefono(String telefono) {
		lTelefonoValor.setText(telefono);
	}

	// SETTERS PARA INMUEBLE
	public void setDireccion(String direccion) {
		lDireccionValor.setText(direccion);
	}

	public void setProvincia(String provincia) {
		lProvinciaValor.setText(provincia);
	}

	public void setNumHuespedes(int num) {
		lHuespedesValor.setText(String.valueOf(num));
	}

	public void setNumCamas(int num) {
		lCamasValor.setText(String.valueOf(num));
	}

	public void setTipoCama(String tipo) {
		String camasActual = lCamasValor.getText();
		lCamasValor.setText(camasActual + " - " + tipo);
	}

	public void setNumBanos(int num) {
		lBanosValor.setText(String.valueOf(num));
	}

	public void setPrecio(String precio) {
		lPrecioValor.setText(precio);
	}
}
