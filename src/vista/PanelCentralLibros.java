package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JYearChooser;

import modelo.Estante;
import modelo.Libro;

public class PanelCentralLibros extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estante estante;
	private JTextField cajaISBN;
	private JTextField cajaTitulo;
	private JYearChooser cajaAnioEdicion;
	private JTextField cajaAutor;
	private JTextField cajaIdioma;
	private JTextField cajaEditorial;
	private JTextField cajaPais;
	private JButton BotonAceptar;
	private JButton botonSalir;
	private JComboBox<String> cbEdicion;
	private Calendar cal;
	private JLabel lblIsbn;
	private JLabel lblTitulo;
	private JLabel lblAutor;
	private JLabel lblEditorial;
	private JLabel lblPais;
	private JLabel lblIdioma;
	private JLabel lblAnioEdicion;
	private JLabel lblNumeroEdicion;
	private JPanel panelDatosIzq;
	private JPanel panelDatosDer;
	private JPanel PanelCapturar;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JList<String> listL;
	private DefaultListModel<String> modeloListaLibros;

	/**
	 * Create the panel.
	 */
	public PanelCentralLibros() {
		setLayout(new BorderLayout(0, 0));
		estante = new Estante();
		JPanel PanelTitulo = new JPanel();
		PanelTitulo.setBackground(Color.DARK_GRAY);
		add(PanelTitulo, BorderLayout.NORTH);
		PanelTitulo.setLayout(new GridLayout(2, 1, 0, 0));

		cal = Calendar.getInstance();

		GridLayout gl_panelDatosDer = new GridLayout(0, 2);
		gl_panelDatosDer.setVgap(25);
		panelDatosDer = new JPanel(gl_panelDatosDer);
		GridLayout gl_panelDatosIzq = new GridLayout(0, 2);
		gl_panelDatosIzq.setVgap(25);
		panelDatosIzq = new JPanel(gl_panelDatosIzq);

		JLabel lblBibliotecaCentralDe = new JLabel("Biblioteca Central de TecNM");
		lblBibliotecaCentralDe.setForeground(Color.WHITE);
		lblBibliotecaCentralDe.setBackground(Color.WHITE);
		lblBibliotecaCentralDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblBibliotecaCentralDe.setFont(new Font("Bodoni MT Poster Compressed", Font.PLAIN, 33));
		PanelTitulo.add(lblBibliotecaCentralDe);

		JLabel lblCapturaDeDatos = new JLabel("Captura de Datos de Libros");
		lblCapturaDeDatos.setForeground(Color.WHITE);
		lblCapturaDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapturaDeDatos.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));
		PanelTitulo.add(lblCapturaDeDatos);

		JPanel PanelOpciones = new JPanel();
		PanelOpciones.setBackground(Color.DARK_GRAY);
		add(PanelOpciones, BorderLayout.SOUTH);

		BotonAceptar = new JButton("Aceptar");

		BotonAceptar.setFocusPainted(false);
		BotonAceptar.setContentAreaFilled(false);
		BotonAceptar.setBorderPainted(false);
		BotonAceptar.setForeground(new Color(255, 255, 0));
		BotonAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		BotonAceptar.setIcon(new ImageIcon(PanelCentralLibros.class.getResource("/Recursos/botonAzul.gif")));
		PanelOpciones.add(BotonAceptar);

		botonSalir = new JButton("Salir");
		botonSalir.setFocusPainted(false);
		botonSalir.setContentAreaFilled(false);
		botonSalir.setBorderPainted(false);
		botonSalir.setForeground(new Color(255, 255, 0));
		botonSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		botonSalir.setIcon(new ImageIcon(PanelCentralLibros.class.getResource("/Recursos/botonAzul.gif")));
		PanelOpciones.add(botonSalir);

		PanelCapturar = new JPanel();
		PanelCapturar.setMaximumSize(new Dimension(15, 32767));
		PanelCapturar.setBackground(Color.GRAY);
		add(PanelCapturar, BorderLayout.CENTER);

		cajaAutor = new JTextField();
		cajaAutor.setMinimumSize(new Dimension(15, 0));
		cajaAutor.setPreferredSize(new Dimension(15, 100));
		ValidacionTextField.soloTexto(cajaAutor);
		hacerMayuscula(cajaAutor);
		cajaAutor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaAutor.getText().isEmpty())) {

					cajaEditorial.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});
		PanelCapturar.setLayout(new GridLayout(0, 3, 0, 0));

		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosIzq.add(lblIsbn);

		cajaISBN = new JTextField();
		ValidacionTextField.soloNum(cajaISBN);
		ValidacionTextField.limitarTextF(cajaISBN, 8);
		cajaISBN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosIzq.add(cajaISBN);
		cajaISBN.setBounds(new Rectangle(0, 0, 30, 100));
		cajaISBN.setColumns(10);

		lblNumeroEdicion = new JLabel("Numero Edicion:");
		lblNumeroEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroEdicion.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		panelDatosDer.add(lblNumeroEdicion);

		cajaTitulo = new JTextField();
		cajaTitulo.setMinimumSize(new Dimension(15, 0));
		cajaTitulo.setPreferredSize(new Dimension(15, 30));
		hacerMayuscula(cajaTitulo);
		cajaTitulo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaTitulo.getText().isEmpty())) {

					cajaAutor.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});

		cbEdicion = new JComboBox<>();
		cbEdicion.setMinimumSize(new Dimension(15, 0));
		cbEdicion.setPreferredSize(new Dimension(15, 100));
		panelDatosDer.add(cbEdicion);
		cbEdicion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cajaAnioEdicion.requestFocus();
			}
		});

		lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosIzq.add(lblTitulo);
		cajaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosIzq.add(cajaTitulo);
		cajaTitulo.setColumns(10);

		lblAnioEdicion = new JLabel("Anio de Edicion:");
		lblAnioEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnioEdicion.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosDer.add(lblAnioEdicion);

		cajaAnioEdicion = new JYearChooser();
		cajaAnioEdicion.getSpinner().setPreferredSize(new Dimension(15, 30));
		cajaAnioEdicion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosDer.add(cajaAnioEdicion);

		lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosIzq.add(lblAutor);
		cajaAutor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosIzq.add(cajaAutor);
		cajaAutor.setColumns(10);

		cajaIdioma = new JTextField();
		cajaIdioma.setMinimumSize(new Dimension(15, 0));
		cajaIdioma.setPreferredSize(new Dimension(15, 100));
		hacerMayuscula(cajaIdioma);
		ValidacionTextField.soloTexto(cajaIdioma);
		cajaIdioma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaIdioma.getText().isEmpty())) {

					cajaPais.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});

		lblIdioma = new JLabel("Idioma");
		lblIdioma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdioma.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosDer.add(lblIdioma);
		cajaIdioma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosDer.add(cajaIdioma);
		cajaIdioma.setColumns(10);

		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditorial.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosIzq.add(lblEditorial);

		cajaEditorial = new JTextField();
		cajaEditorial.setMinimumSize(new Dimension(15, 0));
		cajaEditorial.setPreferredSize(new Dimension(15, 100));
		hacerMayuscula(cajaEditorial);
		cajaEditorial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaEditorial.getText().isEmpty())) {

					cbEdicion.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");

			}
		});
		cajaEditorial.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosIzq.add(cajaEditorial);
		cajaEditorial.setColumns(10);

		cajaPais = new JTextField();
		cajaPais.setMinimumSize(new Dimension(15, 0));
		cajaPais.setPreferredSize(new Dimension(15, 100));
		hacerMayuscula(cajaPais);
		ValidacionTextField.soloTexto(cajaPais);
		cajaPais.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaPais.getText().isEmpty())) {

				} else
					JOptionPane.showMessageDialog(null, "Ingrese un Pais");
			}
		});

		lblPais = new JLabel("Pais:");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		panelDatosDer.add(lblPais);
		cajaPais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelDatosDer.add(cajaPais);
		cajaPais.setColumns(10);
		String[] values = new String[] { "1ra", "2da", "3ra", "4ta", "5ta", "6ta", "7ma", "8va", "9na" };
		for (String string : values)
			cbEdicion.addItem(string);

		PanelCapturar.add(panelDatosIzq);
		PanelCapturar.add(panelDatosDer);

		panel = new JPanel();
		PanelCapturar.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ultimos Libros Registrados:", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.add(panel_1);

		modeloListaLibros = new DefaultListModel<>();
		panel_1.setLayout(new BorderLayout(0, 0));
		listL = new JList<>(modeloListaLibros);
		scrollPane = new JScrollPane(listL);
		panel_1.add(scrollPane);
	}

	public JButton getBotonAceptar() {
		return BotonAceptar;
	}

	public boolean contLetras(String cadena) {
		for (int i = 0; i < cadena.length(); i++)
			if (Character.isAlphabetic(cadena.charAt(i)))
				return true;
		return false;
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}

	public void fijarFoco() {
		cajaISBN.requestFocus();
	}

	public void enfocarTitulo() {
		cajaTitulo.requestFocus();
	}

	private boolean validarAñoE() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return cajaAnioEdicion.getYear() < year;

	}

	private boolean hacerMayuscula(JTextField t) {
		try {

			t.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!t.getText().isEmpty()) {
						String cad = t.getText();
						char[] array = cad.toCharArray();

						array[0] = Character.toUpperCase(array[0]);

						for (int i = 1; i < cad.length() - 1; i++) {
							if (array[i] == ' ') {
								array[i + 1] = Character.toUpperCase(array[i + 1]);
							}
						}
						t.setText(new String(array));
					}

				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean hayCajasVacias() {
		return cajaISBN.getText().isEmpty() || cajaTitulo.getText().isEmpty() || cajaAutor.getText().isEmpty()
				|| cajaIdioma.getText().isEmpty() || validarAñoE() || cajaPais.getText().isEmpty()
				|| cajaEditorial.getText().isEmpty();
	}

	void agregarLibroModel(Libro l) {
		modeloListaLibros.addElement(l.getIsbn().concat(" ").concat(l.getTitulo()));
	}

	public Libro getLibro() {
		Libro libro = new Libro();
		libro.setIsbn(cajaISBN.getText());
		libro.setTitulo(cajaTitulo.getText());
		libro.setAnioEdicion(String.valueOf(cajaAnioEdicion.getYear()));
		libro.setAutor(cajaAutor.getText());
		libro.setIdioma(cajaIdioma.getText());
		libro.setEditoria(cajaEditorial.getText());
		libro.setPais(cajaPais.getText());
		libro.setNumeroEdicion(cbEdicion.getSelectedItem().toString());
		return libro;
	}

	public void vaciarCajas() {
		cajaISBN.setText("");
		cajaTitulo.setText("");
		cajaAutor.setText("");
		cajaEditorial.setText("");
		cajaAnioEdicion.setYear(cal.get(Calendar.YEAR));
		cajaIdioma.setText("");
		cajaPais.setText("");
		cajaISBN.requestFocus();
	}

	public Estante getEstante() {
		return estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}

	public JTextField getCajaISBN() {
		return cajaISBN;
	}

	public void setCajaISBN(JTextField cajaISBN) {
		this.cajaISBN = cajaISBN;
	}

	public JTextField getCajaTitulo() {
		return cajaTitulo;
	}

	public void setCajaTitulo(JTextField cajaTitulo) {
		this.cajaTitulo = cajaTitulo;
	}

	public JYearChooser getCajaAnioEdicion() {
		return cajaAnioEdicion;
	}

	public void setCajaAnioEdicion(JYearChooser cajaAnioEdicion) {
		this.cajaAnioEdicion = cajaAnioEdicion;
	}

	public JTextField getCajaAutor() {
		return cajaAutor;
	}

	public void setCajaAutor(JTextField cajaAutor) {
		this.cajaAutor = cajaAutor;
	}

	public JTextField getCajaIdioma() {
		return cajaIdioma;
	}

	public void setCajaIdioma(JTextField cajaIdioma) {
		this.cajaIdioma = cajaIdioma;
	}

	public JTextField getCajaEditorial() {
		return cajaEditorial;
	}

	public void setCajaEditorial(JTextField cajaEditorial) {
		this.cajaEditorial = cajaEditorial;
	}

	public JTextField getCajaPais() {
		return cajaPais;
	}

	public void setCajaPais(JTextField cajaPais) {
		this.cajaPais = cajaPais;
	}

	public JComboBox<String> getCbEdicion() {
		return cbEdicion;
	}

	public void setCbEdicion(JComboBox<String> cbEdicion) {
		this.cbEdicion = cbEdicion;
	}

	public void setBotonAceptar(JButton botonAceptar) {
		BotonAceptar = botonAceptar;
	}

	public void setBotonSalir(JButton botonSalir) {
		this.botonSalir = botonSalir;
	}

}