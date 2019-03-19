package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.Estante;
import modelo.Libro;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCentralLibros extends JPanel {
	private Estante estante;
	private JTextField cajaISBN;
	private JTextField cajaTitulo;
	private JTextField cajaAnioEdicion;
	private JTextField cajaAutor;
	private JTextField cajaIdioma;
	private JTextField cajaEditorial;
	private JTextField cajaPais;
	private JButton BotonAceptar;
	private JButton botonSalir;
	private JComboBox cbEdicion;

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

	public JTextField getCajaAnioEdicion() {
		return cajaAnioEdicion;
	}

	public void setCajaAnioEdicion(JTextField cajaAnioEdicion) {
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
		cajaEditorial = cajaEditorial;
	}

	public JTextField getCajaPais() {
		return cajaPais;
	}

	public void setCajaPais(JTextField cajaPais) {
		this.cajaPais = cajaPais;
	}

	public JComboBox getCbEdicion() {
		return cbEdicion;
	}

	public void setCbEdicion(JComboBox cbEdicion) {
		this.cbEdicion = cbEdicion;
	}

	public void setBotonAceptar(JButton botonAceptar) {
		BotonAceptar = botonAceptar;
	}

	public void setBotonSalir(JButton botonSalir) {
		this.botonSalir = botonSalir;
	}

	/**
	 * Create the panel.
	 */
	public PanelCentralLibros() {
		setLayout(new BorderLayout(0, 0));
		estante = new Estante();
		JPanel PanelTitulo = new JPanel();
		add(PanelTitulo, BorderLayout.NORTH);
		PanelTitulo.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblBibliotecaCentralDe = new JLabel("Biblioteca Central de TecNM");
		lblBibliotecaCentralDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblBibliotecaCentralDe.setFont(new Font("Bodoni MT Poster Compressed", Font.PLAIN, 33));
		PanelTitulo.add(lblBibliotecaCentralDe);

		JLabel lblCapturaDeDatos = new JLabel("Captura de Datos de Libros");
		lblCapturaDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapturaDeDatos.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));
		PanelTitulo.add(lblCapturaDeDatos);

		JPanel PanelOpciones = new JPanel();
		PanelOpciones.setBackground(new Color(100, 149, 237));
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
		PanelCapturar.setBackground(new Color(238, 232, 170));
		add(PanelCapturar, BorderLayout.CENTER);
		PanelCapturar.setLayout(new GridLayout(4, 4, 0, 25));

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblIsbn);

		cajaISBN = new JTextField();
		
		cajaISBN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaISBN);
		cajaISBN.setColumns(10);

		JLabel lblNumeroEdicion = new JLabel("Numero Edicion:");
		lblNumeroEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroEdicion.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		PanelCapturar.add(lblNumeroEdicion);

		cbEdicion = new JComboBox();
		cbEdicion.setModel(new DefaultComboBoxModel(
				new String[] { "1ra", "2da", "3ra", "4ta", "5ta", "6ta", "7ma", "8va", "9na" }));
		PanelCapturar.add(cbEdicion);
		cbEdicion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cajaAnioEdicion.requestFocus();
			}
		});

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblTitulo);

		cajaTitulo = new JTextField();
		cajaTitulo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaTitulo.getText().isEmpty())) {
					cajaAutor.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});
		cajaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaTitulo);
		cajaTitulo.setColumns(10);

		JLabel lblAnioEdicion = new JLabel("A\u00F1o Edicion:");
		lblAnioEdicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnioEdicion.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblAnioEdicion);

		cajaAnioEdicion = new JTextField();
		cajaAnioEdicion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaAnioEdicion.getText().isEmpty())) {
					cajaIdioma.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");

			}
		});
		cajaAnioEdicion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaAnioEdicion);
		cajaAnioEdicion.setColumns(10);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblAutor);

		cajaAutor = new JTextField();
		cajaAutor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaAutor.getText().isEmpty())) {
					cajaEditorial.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});
		cajaAutor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaAutor);
		cajaAutor.setColumns(10);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdioma.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblIdioma);

		cajaIdioma = new JTextField();
		cajaIdioma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(cajaIdioma.getText().isEmpty())) {
					cajaPais.requestFocus();
				} else
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});
		cajaIdioma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaIdioma);
		cajaIdioma.setColumns(10);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditorial.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblEditorial);

		cajaEditorial = new JTextField();
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

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		PanelCapturar.add(lblPais);

		cajaPais = new JTextField();
		cajaPais.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((cajaPais.getText().isEmpty()))
					JOptionPane.showMessageDialog(null, "Esta vacia");
			}
		});
		cajaPais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PanelCapturar.add(cajaPais);
		cajaPais.setColumns(10);

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

	public boolean hayCajasVacias() {
		return cajaISBN.getText().isEmpty() || cajaTitulo.getText().isEmpty() || cajaAutor.getText().isEmpty()
				|| cajaIdioma.getText().isEmpty() || cajaAnioEdicion.getText().isEmpty() || cajaPais.getText().isEmpty()
				|| cajaEditorial.getText().isEmpty();
	}

	public Libro getLibro() {
		Libro libro = new Libro();
		libro.setIsbn(cajaISBN.getText());
		libro.setTitulo(cajaTitulo.getText());
		libro.setAnioEdicion(cajaAnioEdicion.getText());
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
		cajaAnioEdicion.setText("");
		cajaIdioma.setText("");
		cajaPais.setText("");
		cajaISBN.requestFocus();
	}

}
