package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import modelo.Alumno;
import modelo.CodigoPostal;
import modelo.CodigosPostales;
import modelo.Direccion;
import modelo.Grupo;
import modelo.Utileria;

public class Section extends JPanel {
	private JTextField editNoControl;
	private JTextField editNombre;
	private JTextField editPaterno;
	private JTextField editMaterno;
	private JDateChooser editFechaNac;
	private JTextField editCarrera;
	private ButtonGroup grupoBotones;
	private JTextField editCalle;
	private JTextField editNumCasa;
	private JScrollPane editColonia;
	private JTextField editMunicipio;
	private JTextField editCP;
	private Utileria uti;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JComboBox<String> comboBoxSemestre;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JButton botonSalir;
	private String[] opciones;
	private JTextField editEstado;
	private CodigosPostales codigosPostales;
	private DefaultListModel<String> modeloLista;
	private JList<String> listColonias;
	private Grupo grupo;

	/**
	 * Create the panel.
	 */
	public Section(CodigosPostales cps, Grupo grupo) {
		codigosPostales = cps;
		this.grupo = grupo;
		grupoBotones = new ButtonGroup();
		uti = new Utileria();
		opciones = new String[] { "--Seleccionar--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		setLayout(new BorderLayout(0, 0));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setForeground(Color.WHITE);
		panelTitulo.setBackground(Color.DARK_GRAY);
		add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblEncabezado = new JLabel("Biblioteca ITZ");
		lblEncabezado.setForeground(Color.WHITE);
		lblEncabezado.setFont(new Font("Courier New", Font.ITALIC, 25));
		lblEncabezado.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(lblEncabezado);

		JLabel lblTipo = new JLabel("Captura de Datos del Alumno");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 17));
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(lblTipo);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.DARK_GRAY);
		add(panelBotones, BorderLayout.SOUTH);

		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alumno a = new Alumno();
				a.setNoControl(editNoControl.getText());
				a.setCarrera(editCarrera.getText());
				Direccion direccion = new Direccion(editCalle.getText(), editNumCasa.getText(),
						listColonias.getSelectedValue(), editMunicipio.getText(), editEstado.getText(),
						editCP.getText());

				a.setDireccion(direccion);

				DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("yyyy/LL/dd");
				// System.out.println(fechaFormateada.format(LocalDate.now()));
				// editFechaNac.setDateFormatString("yyyy/LL/dd");
				// LocalDate f = LocalDate.parse(editFechaNac.getDate().toString(),
				// fechaFormateada);
				LocalDate f = convertToLocalDateViaMilisecond(editFechaNac.getDate());

				a.setFechaNac(f);
				if (rdbtnFemenino.isSelected())
					a.setGenero('F');
				else
					a.setGenero('M');
				a.setMaterno(editMaterno.getText());
				a.setPaterno(editPaterno.getText());
				a.setNombre(editNombre.getText());
				a.setSemestre(Integer.parseInt(comboBoxSemestre.getSelectedItem().toString()));

				grupo.agregar(a);
				System.out.println(grupo.listar());

				editNoControl.setText(null);
				editNombre.setText(null);
				editPaterno.setText(null);
				editMaterno.setText(null);
				editCarrera.setText(null);
				modeloLista.removeAllElements();
				grupoBotones.clearSelection();
				editColonia.removeAll();
				comboBoxSemestre.setSelectedIndex(0);
				editCalle.setText(null);
				editNumCasa.setText(null);
				editMunicipio.setText(null);
				editEstado.setText(null);
				editCP.setText(null);
				repaint();

			}
		});
		panelBotones.add(botonAceptar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				editNoControl.setText(null);
				editNombre.setText(null);
				editPaterno.setText(null);
				editMaterno.setText(null);
				editCarrera.setText(null);
				modeloLista.removeAllElements();
				grupoBotones.clearSelection();
				editColonia.removeAll();
				comboBoxSemestre.setSelectedIndex(0);
				editCalle.setText(null);
				editNumCasa.setText(null);
				editMunicipio.setText(null);
				editEstado.setText(null);
				editCP.setText(null);
				repaint();
			}
		});
		panelBotones.add(botonCancelar);

		botonSalir = new JButton("Salir");
		panelBotones.add(botonSalir);

		JPanel panelDatos = new JPanel();
		add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelEscolares = new JPanel();
		panelEscolares.setBackground(Color.GRAY);
		panelEscolares.setBorder(
				new TitledBorder(null, "Datos Escolares", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelDatos.add(panelEscolares);
		panelEscolares.setLayout(new GridLayout(9, 2, 0, 0));

		JLabel lblNoControl = new JLabel("Ingrese el numero de control:");
		lblNoControl.setForeground(Color.BLACK);
		lblNoControl.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNoControl.setBackground(Color.WHITE);
		panelEscolares.add(lblNoControl);

		editNoControl = new JTextField();
		editNoControl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!uti.estaVacio(editNoControl)) {
					if (uti.validarCaracter(editNoControl, 8)) {
						editNombre.requestFocus();

					} else {
						uti.escribir("Ingrese los 8 caracteres solictados");
					}
				} else {
					uti.escribir("Llene el campo solicitado");
				}
			}
		});
		editNoControl.setColumns(10);
		panelEscolares.add(editNoControl);

		JLabel lblNombre = new JLabel("Ingrese su nombre(s):");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblNombre);

		editNombre = new JTextField();
		editNombre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editNombre)) {
					if (uti.isHombre(editNombre)) {
						rdbtnMasculino.setSelected(true);
					} else {
						rdbtnMasculino.setSelected(false);
						if (uti.isMujer(editNombre)) {
							rdbtnFemenino.setSelected(true);
						} else {
							rdbtnFemenino.setSelected(false);
						}
					}
					editPaterno.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		editNombre.setColumns(10);
		panelEscolares.add(editNombre);

		JLabel lblPaterno = new JLabel("Ingrese su apellido paterno:");
		lblPaterno.setForeground(Color.BLACK);
		lblPaterno.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblPaterno);

		editPaterno = new JTextField();
		editPaterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editPaterno)) {
					editMaterno.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		editPaterno.setColumns(10);
		panelEscolares.add(editPaterno);

		JLabel lblMaterno = new JLabel("Ingrese su apellido materno:");
		lblMaterno.setForeground(Color.BLACK);
		lblMaterno.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblMaterno);

		editMaterno = new JTextField();
		editMaterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editMaterno)) {
					editFechaNac.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		editMaterno.setColumns(10);
		panelEscolares.add(editMaterno);

		JLabel lblEdad = new JLabel("Ingrese su fecha de nacimiento:");
		lblEdad.setForeground(Color.BLACK);
		lblEdad.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblEdad);

		editFechaNac = new JDateChooser();

		panelEscolares.add(editFechaNac);

		JLabel lblGenero = new JLabel("Seleccione su genero:");
		lblGenero.setForeground(Color.BLACK);
		lblGenero.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblGenero);

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (uti.verificarRadioBottom(rdbtnFemenino) || uti.verificarRadioBottom(rdbtnMasculino)) {
					comboBoxSemestre.requestFocus();
				} else {
					uti.escribir("Seleccione una opcion");
				}

			}
		});
		panelEscolares.add(rdbtnMasculino);

		JLabel label_1 = new JLabel("");
		panelEscolares.add(label_1);

		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (uti.verificarRadioBottom(rdbtnFemenino) || uti.verificarRadioBottom(rdbtnMasculino)) {
					comboBoxSemestre.requestFocus();
				} else {
					uti.escribir("Seleccione una opcion");
				}

			}
		});
		panelEscolares.add(rdbtnFemenino);

		JLabel lblSemestre = new JLabel("Seleccione su semestre:");
		lblSemestre.setForeground(Color.BLACK);
		lblSemestre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblSemestre);

		comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setModel(new DefaultComboBoxModel(opciones));
		comboBoxSemestre.setSelectedIndex(0);
		comboBoxSemestre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (uti.validarCaja(comboBoxSemestre)) {
					editCarrera.requestFocus();
				}

			}
		});
		panelEscolares.add(comboBoxSemestre);

		JLabel lblCarrera = new JLabel("Ingrese la carrera que cursa:");
		lblCarrera.setForeground(Color.BLACK);
		lblCarrera.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblCarrera);

		editCarrera = new JTextField();
		editCarrera.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (uti.validarCaja(comboBoxSemestre)) {
					if (!uti.estaVacio(editCarrera)) {
						editCalle.requestFocus();
					} else {
						uti.escribir("Ingrese el dato solicitado");
					}

				} else {
					uti.escribir("Seleccione su semestre");
				}
			}
		});
		editCarrera.setColumns(10);
		panelEscolares.add(editCarrera);

		grupoBotones.add(rdbtnFemenino);
		grupoBotones.add(rdbtnMasculino);

		JPanel panelPersonales = new JPanel();
		panelPersonales.setBackground(Color.GRAY);
		panelPersonales.setBorder(
				new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelDatos.add(panelPersonales);
		panelPersonales.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel lblCalle = new JLabel("Ingrese el nombre de la calle:");
		lblCalle.setForeground(Color.BLACK);
		lblCalle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblCalle);

		editCalle = new JTextField();
		editCalle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editCalle)) {
					editNumCasa.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		editCalle.setColumns(10);
		panelPersonales.add(editCalle);

		JLabel lblNumCasa = new JLabel("Ingrese el numero de la casa:");
		lblNumCasa.setForeground(Color.BLACK);
		lblNumCasa.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblNumCasa);

		editNumCasa = new JTextField();
		editNumCasa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editNumCasa)) {
					editColonia.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		editNumCasa.setColumns(10);
		panelPersonales.add(editNumCasa);

		JLabel lblCP = new JLabel("Ingrese el Codigo Postal:");
		lblCP.setForeground(Color.BLACK);
		lblCP.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblCP);

		editCP = new JTextField();
		editCP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editCP) && editCP.getText().length() == 5) {
					CodigoPostal cp = codigosPostales.existe(editCP.getText());
					editEstado.setText(cp.getEstado().get(0));
					if (modeloLista != null) {
						modeloLista.removeAllElements();
						agregarColonias(editCP.getText());
					}
					editMunicipio.setText(cp.getCiudad().get(0));
					botonAceptar.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado la longitud debe ser 5 caracteres");

				}

			}
		});
		editCP.setColumns(10);
		panelPersonales.add(editCP);

		JLabel lblColonia = new JLabel("Ingrese el nombre de la colonia");
		lblColonia.setForeground(Color.BLACK);
		lblColonia.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblColonia);

		editColonia = new JScrollPane();
		panelPersonales.add(editColonia);

		modeloLista = new DefaultListModel<>();
		listColonias = new JList<>(modeloLista);
		editColonia.setViewportView(listColonias);

		JLabel lblEstado = new JLabel("Ingrese el Estado:");
		lblEstado.setForeground(Color.BLACK);
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblEstado);

		editEstado = new JTextField();
		editEstado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editEstado)) {
					editCP.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		panelPersonales.add(editEstado);
		editEstado.setColumns(10);

		JLabel lblMunicipio = new JLabel("Ingrese el nombre del municipio:");
		lblMunicipio.setForeground(Color.BLACK);
		lblMunicipio.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblMunicipio);

		editMunicipio = new JTextField();
		editMunicipio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editMunicipio)) {
					editEstado.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		editMunicipio.setColumns(10);
		panelPersonales.add(editMunicipio);

	}

	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private void agregarColonias(String codigo) {
		List<String> colonias = codigosPostales.getColonias(codigo);

		for (String colonia : colonias) {
			if (!modeloLista.contains(colonia))
				modeloLista.addElement(colonia);
		}
	}

	public void pedirFoco() {
		editNoControl.requestFocus();
	}

	private boolean validarCampos() {
		if (editNoControl.getText().isEmpty())
			return false;
		return true;
	}

	public JButton getBotonAceptar() {
		return botonAceptar;
	}

	public JButton getBotonCancelar() {
		return botonCancelar;
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}

	public JTextField getEditNoControl() {
		return editNoControl;
	}

	public JTextField getEditNombre() {
		return editNombre;
	}

	public JTextField getEditPaterno() {
		return editPaterno;
	}

	public JTextField getEditMaterno() {
		return editMaterno;
	}

	public JDateChooser getEditEdad() {
		return editFechaNac;
	}

	public JTextField getEditCarrera() {
		return editCarrera;
	}

	public ButtonGroup getGrupo() {
		return grupoBotones;
	}

	public JTextField getEditCalle() {
		return editCalle;
	}

	public JTextField getEditNumCasa() {
		return editNumCasa;
	}

	public JTextField getEditMunicipio() {
		return editMunicipio;
	}

	public JTextField getEditCP() {
		return editCP;
	}

	public JList getEditColonia() {
		return listColonias;
	}

	public JRadioButton getRdbtnMasculino() {
		return rdbtnMasculino;
	}

	public JRadioButton getRdbtnFemenino() {
		return rdbtnFemenino;
	}

	public JComboBox getComboBoxSemestre() {
		return comboBoxSemestre;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public JTextField getEditEstado() {
		return editEstado;
	}

}
