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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

		JPanel PanelCapturar = new JPanel();
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
		PanelCapturar.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblIsbn);

		cajaISBN = new JTextField();
		ValidacionTextField.soloNum(cajaISBN);
		ValidacionTextField.limitarTextF(cajaISBN, 8);
		cajaISBN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaISBN);
		cajaISBN.setBounds(new Rectangle(0, 0, 30, 100));
		cajaISBN.setColumns(10);

		JLabel lblNumeroEdicion = new JLabel("Numero Edicion:");
		lblNumeroEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroEdicion.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		PanelCapturar.add(lblNumeroEdicion);

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
		PanelCapturar.add(cbEdicion);
		cbEdicion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cajaAnioEdicion.requestFocus();
			}
		});

		JLabel label = new JLabel("");
		PanelCapturar.add(label);

		JLabel label_1 = new JLabel("");
		PanelCapturar.add(label_1);

		JLabel label_2 = new JLabel("");
		PanelCapturar.add(label_2);

		JLabel label_3 = new JLabel("");
		PanelCapturar.add(label_3);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblTitulo);
		cajaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaTitulo);
		cajaTitulo.setColumns(10);

		JLabel lblAnioEdicion = new JLabel("A\u00F1o Edicion:");
		lblAnioEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnioEdicion.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblAnioEdicion);

		cajaAnioEdicion = new JYearChooser();
		cajaAnioEdicion.getSpinner().setPreferredSize(new Dimension(15, 30));
		cajaAnioEdicion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaAnioEdicion);

		JLabel label_4 = new JLabel("");
		PanelCapturar.add(label_4);

		JLabel label_5 = new JLabel("");
		PanelCapturar.add(label_5);

		JLabel label_6 = new JLabel("");
		PanelCapturar.add(label_6);

		JLabel label_7 = new JLabel("");
		PanelCapturar.add(label_7);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblAutor);
		cajaAutor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaAutor);
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

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdioma.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblIdioma);
		cajaIdioma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaIdioma);
		cajaIdioma.setColumns(10);

		JLabel label_8 = new JLabel("");
		PanelCapturar.add(label_8);

		JLabel label_9 = new JLabel("");
		PanelCapturar.add(label_9);

		JLabel label_10 = new JLabel("");
		PanelCapturar.add(label_10);

		JLabel label_11 = new JLabel("");
		PanelCapturar.add(label_11);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditorial.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblEditorial);

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
		PanelCapturar.add(cajaEditorial);
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

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblPais);
		cajaPais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaPais);
		cajaPais.setColumns(10);
		String[] values = new String[] { "1ra", "2da", "3ra", "4ta", "5ta", "6ta", "7ma", "8va", "9na" };
		for (String string : values)
			cbEdicion.addItem(string);

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
