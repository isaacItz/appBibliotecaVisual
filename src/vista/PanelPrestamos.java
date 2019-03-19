package vista;

import static modelox.Utileria.escribir;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.Alumno;
import modelo.ComparadorAlumnos;
import modelo.ComparadorLibros;
import modelo.ComparadorPrestamos;
import modelo.Estante;
import modelo.Grupo;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Prestamos;
import modelo.Utileria;

public class PanelPrestamos extends JPanel {
	private JTextField textFieldNumControl;
	private JTextField textFieldIsbn;
	private JButton btnOk;
	private JButton btnCancelar;
	private Prestamos prestamos;
	private JLabel labelNombreA;
	private JLabel labelNumeroCA;
	private JLabel labelEdad;
	private JLabel labelGenero;
	private JLabel labelSemestre;
	private JLabel labelCarrera;
	private JLabel labelPais;
	private JLabel labelIdioma;
	private JLabel labelAñoEdicion;
	private JLabel labelNoEdicion;
	private JLabel labelEditorial;
	private JLabel labelAutor;
	private JLabel labelIsbn;
	private JLabel labelTitulo;
	private Grupo grupo;
	private Estante estante;

	/**
	 * Create the panel.
	 */
	public PanelPrestamos(Prestamos prestamos, Grupo grupo, Estante es) {
		this.prestamos = prestamos;
		this.grupo = grupo;
		estante = es;

		JPanel panelCentro = new JPanel(new GridLayout(1, 2));

		JPanel panelIzq = new JPanel();
		JPanel panelDer = new JPanel();
		setLayout(new BorderLayout(0, 0));

		panelCentro.add(panelIzq);
		panelCentro.add(panelDer);
		add(panelCentro, BorderLayout.CENTER);
		panelDer.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Datos del Libro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDer.add(panel_2, BorderLayout.NORTH);

		JLabel label_1 = new JLabel("ISBN del libro:");
		panel_2.add(label_1);

		textFieldIsbn = new JTextField();
		textFieldIsbn.setColumns(10);
		panel_2.add(textFieldIsbn);

		JPanel panel_3 = new JPanel();
		panelDer.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(8, 2, 0, 0));

		JLabel lblTitulo = new JLabel("Titulo:");
		panel_3.add(lblTitulo);

		labelTitulo = new JLabel("");
		panel_3.add(labelTitulo);

		JLabel lblIsbn = new JLabel("Isbn:");
		panel_3.add(lblIsbn);

		labelIsbn = new JLabel("");
		panel_3.add(labelIsbn);

		JLabel lblAutor = new JLabel("Autor:");
		panel_3.add(lblAutor);

		labelAutor = new JLabel("");
		panel_3.add(labelAutor);

		JLabel lblEditorial = new JLabel("Editorial:");
		panel_3.add(lblEditorial);

		labelEditorial = new JLabel("");
		panel_3.add(labelEditorial);

		JLabel lblNoEdicion = new JLabel("No. Edicion:");
		panel_3.add(lblNoEdicion);

		labelNoEdicion = new JLabel("");
		panel_3.add(labelNoEdicion);

		JLabel lblAoDeEdicion = new JLabel("A\u00F1o de Edicion");
		panel_3.add(lblAoDeEdicion);

		labelAñoEdicion = new JLabel("");
		panel_3.add(labelAñoEdicion);

		JLabel lblIdioma = new JLabel("Idioma:");
		panel_3.add(lblIdioma);

		labelIdioma = new JLabel("");
		panel_3.add(labelIdioma);

		JLabel lblPais = new JLabel("Pais");
		panel_3.add(lblPais);

		labelPais = new JLabel("");
		panel_3.add(labelPais);
		panelIzq.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Datos del Alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelIzq.add(panel_1, BorderLayout.NORTH);

		JLabel lblNumeroDeControl = new JLabel("Numero de Control:");
		panel_1.add(lblNumeroDeControl);

		textFieldNumControl = new JTextField();
		textFieldNumControl.setColumns(10);
		panel_1.add(textFieldNumControl);

		JPanel panel = new JPanel();
		panelIzq.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel lblAlumno = new JLabel("Alumno:");
		panel.add(lblAlumno);

		labelNombreA = new JLabel("");
		panel.add(labelNombreA);

		JLabel lblNumeroDeControl_1 = new JLabel("Numero De Control:");
		panel.add(lblNumeroDeControl_1);

		labelNumeroCA = new JLabel("");
		panel.add(labelNumeroCA);

		JLabel lblEdad = new JLabel("Edad:");
		panel.add(lblEdad);

		labelEdad = new JLabel("");
		panel.add(labelEdad);

		JLabel lblGenero = new JLabel("Genero:");
		panel.add(lblGenero);

		labelGenero = new JLabel("");
		panel.add(labelGenero);

		JLabel lblSemestre = new JLabel("Semestre:");
		panel.add(lblSemestre);

		labelSemestre = new JLabel("");
		panel.add(labelSemestre);

		JLabel lblCarrera = new JLabel("Carrera:");
		panel.add(lblCarrera);

		labelCarrera = new JLabel("");
		panel.add(labelCarrera);

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnOk = new JButton("OK");

		panel_4.add(btnOk);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarCamposAlumno();
				textFieldNumControl.setText("");
				limpiarCamposLibro();
			}
		});
		panel_4.add(btnCancelar);

		textFieldNumControl.getDocument().addDocumentListener(new oyenteAlum());
		textFieldIsbn.getDocument().addDocumentListener(new oyenteLib());

	}

	private class oyenteAlum implements DocumentListener {

		ComparadorAlumnos c = new ComparadorAlumnos(ComparadorAlumnos.NOCONTROL);

		@Override
		public void changedUpdate(DocumentEvent e) {
			metodo();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			metodo();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			metodo();
		}

		void metodo() {
			if (textFieldNumControl.getText().length() == 8) {
				Alumno a = new Alumno(textFieldNumControl.getText());
				int pos = Utileria.linealSearch(grupo.getList(), a, c);
				if (pos != -1) {
					a = grupo.getAlumno(pos);
					labelNombreA.setText(a.getNombreCompleto());
					labelNumeroCA.setText(a.getNoControl());
					labelEdad.setText(a.getFechaNac().toString());
					labelGenero.setText(String.valueOf(a.getGenero()));
					labelSemestre.setText(String.valueOf(a.getSemestre()));
					labelCarrera.setText(a.getCarrera());
				}
			} else {
				limpiarCamposAlumno();
			}
		}

	}

	private class oyenteLib implements DocumentListener {

		ComparadorLibros c = new ComparadorLibros(ComparadorLibros.ISBN);

		@Override
		public void changedUpdate(DocumentEvent e) {
			metodo();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			metodo();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			metodo();
		}

		void metodo() {
			if (textFieldIsbn.getText().length() == 8) {
				Libro l = new Libro(textFieldIsbn.getText());
				int pos = Utileria.linealSearch(estante.getList(), l, c);
				if (pos != -1) {
					l = estante.getLibro(pos);
					labelTitulo.setText(l.getTitulo());
					labelIsbn.setText(l.getIsbn());
					labelAutor.setText(l.getAutor());
					labelAñoEdicion.setText(l.getAnioEdicion());
					labelEditorial.setText(l.getEditoria());
					labelIdioma.setText(l.getIdioma());
					labelPais.setText(l.getPais());
					labelNoEdicion.setText(l.getNumeroEdicion());
				}
			} else {
				labelTitulo.setText("");
				labelIsbn.setText("");
				labelAutor.setText("");
				labelAñoEdicion.setText("");
				labelEditorial.setText("");
				labelIdioma.setText("");
				labelPais.setText("");
				labelNoEdicion.setText("");
			}
		}

	}

	public void limpiarCamposLibro() {
		textFieldIsbn.setText("");
		labelTitulo.setText("");
		labelIsbn.setText("");
		labelAutor.setText("");
		labelAñoEdicion.setText("");
		labelEditorial.setText("");
		labelIdioma.setText("");
		labelPais.setText("");
		labelNoEdicion.setText("");

	}

	public void limpiarCamposAlumno() {
		labelSemestre.setText("");
		labelCarrera.setText("");
		labelEdad.setText("");
		labelGenero.setText("");
		labelNombreA.setText("");
		labelNumeroCA.setText("");

	}

	public void getFoco() {
		textFieldNumControl.requestFocus();
		if (textFieldNumControl.getText().length() == 8)
			textFieldIsbn.requestFocus();

	}

	public boolean validar() {
		if (textFieldNumControl.getText().isEmpty()) {
			escribir("El numero de control no puede estar vacio");
			return false;
		}
		if (textFieldNumControl.getText().length() != 8) {
			escribir("el numero de control debe tener de 8 caracteres no " + textFieldNumControl.getText().length());
			return false;
		}

		if (textFieldIsbn.getText().isEmpty()) {
			escribir("El Isbn no puede estar vacio");
			return false;
		}
		if (textFieldIsbn.getText().length() != 8) {
			escribir("el Isbn debe tener 8 caracteres no " + textFieldNumControl.getText().length());
			return false;
		}

		Prestamo p = new Prestamo();
		p.setLibro(new Libro(textFieldIsbn.getText()));

		if (Utileria.linealSearch(prestamos.getList(), p, (p1, p2) -> p1.getLibro().compareTo(p2.getLibro())) > -1) {
			escribir("el Libro ya esta Prestado ");
			return false;
		}

		return true;
	}

	public String getTextoNC() {
		return textFieldNumControl.getText();
	}

	public String getTextIsbn() {
		return textFieldIsbn.getText();
	}

	public JButton getBtnOk() {
		return btnOk;
	}

}
