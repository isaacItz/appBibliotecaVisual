package vista;

import static modelox.Utileria.escribir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import modelo.Alumno;
import modelo.CodigoPostal;
import modelo.CodigosPostales;
import modelo.Direccion;
import modelo.Grupo;
import modelo.Utileria;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

public class Section extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField editNoControl;
	private JTextField editNombre;
	private JTextField editPaterno;
	private JTextField editMaterno;
	private JDateChooser editFechaNac;
	private JList<String> editCarrera;
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
	private JList<String> listAlumnos;
	private DefaultListModel<String> modelAlumns;
	private Grupo grupo;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public Section(CodigosPostales cps, Grupo grupo) {
		codigosPostales = cps;
		this.grupo = grupo;
		grupoBotones = new ButtonGroup();
		uti = new Utileria();
		opciones = new String[] { "--Seleccionar--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		setLayout(new BorderLayout(0, 0));

		modelAlumns = new DefaultListModel<>();
		listAlumnos = new JList<>(modelAlumns);

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
				if (validarCampos()) {
					Alumno a = new Alumno();
					a.setNoControl(editNoControl.getText());
					a.setCarrera(editCarrera.getSelectedValue());
					Direccion direccion = new Direccion(editCalle.getText(), editNumCasa.getText(),
							listColonias.getSelectedValue(), editMunicipio.getText(), editEstado.getText(),
							editCP.getText());

					a.setDireccion(direccion);

					// DateTimeFormatter fechaFormateada =
					// DateTimeFormatter.ofPattern("yyyy/LL/dd");
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
					modelAlumns.addElement(a.getNoControl().concat(" ").concat(a.getNombreCompleto()));
					grupo.agregar(a);
					escribir("Alumno Registrado");

					limpiar();
				}
				// escribir("");

			}
		});
		panelBotones.add(botonAceptar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpiar();
			}
		});
		panelBotones.add(botonCancelar);

		botonSalir = new JButton("Salir");
		panelBotones.add(botonSalir);

		JPanel panelDatos = new JPanel();
		add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new GridLayout(0, 3, 0, 20));

		JPanel panelEscolares = new JPanel();
		panelEscolares.setBackground(Color.GRAY);
		panelEscolares.setBorder(
				new TitledBorder(null, "Datos Escolares", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelDatos.add(panelEscolares);
		panelEscolares.setLayout(new GridLayout(9, 2, 10, 10));

		JLabel lblNoControl = new JLabel("Ingrese el numero de control:");
		lblNoControl.setForeground(Color.BLACK);
		lblNoControl.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNoControl.setBackground(Color.WHITE);
		panelEscolares.add(lblNoControl);

		editNoControl = new JTextField();
		ValidacionTextField.soloNum(editNoControl);
		ValidacionTextField.limitarTextF(editNoControl, 8);

		editNoControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (valdarNumControl())
					editNombre.requestFocus();
			}
		});

		editNoControl.setColumns(10);
		panelEscolares.add(editNoControl);

		JLabel lblNombre = new JLabel("Ingrese su nombre(s):");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelEscolares.add(lblNombre);

		editNombre = new JTextField();
		hacerMayuscula(editNombre);
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
					editNombre.requestFocus();
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
		hacerMayuscula(editPaterno);
		editPaterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editPaterno)) {

					editMaterno.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
					editPaterno.requestFocus();
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
		hacerMayuscula(editMaterno);
		editMaterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editMaterno)) {
					editFechaNac.requestFocus();
				} else {
					uti.escribir("Ingrese el Apellido Materno");
					editMaterno.requestFocus();
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

		comboBoxSemestre = new JComboBox<>();
		comboBoxSemestre.setModel(new DefaultComboBoxModel<>(opciones));
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

		editCarrera = new JList<>();

		editCarrera.setModel(new AbstractListModel<String>() {
			String[] values = new String[] { "Seleccione", "Ingenieria en Sistemas Computacionales", "Ingenieria Civil",
					"Ingenieria Industrial", "Ingenieria Electromecanica", "Ingenieria en Gestion Empresarial",
					"Ingenieria en Alimentos", "Licenciatura en Administracion", "Contabilidad", "Arquitectura",
					"Ingenieria en Desarrollo Agricola Sustentable" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		JScrollPane scJL = new JScrollPane(editCarrera);
		panelEscolares.add(scJL);
		editCarrera.setSelectedIndex(0);
		grupoBotones.add(rdbtnFemenino);
		grupoBotones.add(rdbtnMasculino);

		JPanel panelPersonales = new JPanel();
		panelPersonales.setBackground(Color.GRAY);
		panelPersonales.setBorder(
				new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelDatos.add(panelPersonales);
		panelPersonales.setLayout(new GridLayout(6, 2, 10, 20));

		JLabel lblCalle = new JLabel("Ingrese el nombre de la calle:");
		lblCalle.setForeground(Color.BLACK);
		lblCalle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblCalle);

		editCalle = new JTextField();
		editCalle.setMinimumSize(new Dimension(60, 100));
		hacerMayuscula(editCalle);
		editCalle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editCalle)) {

					editNumCasa.requestFocus();
				} else {
					uti.escribir("Ingrese el dato solicitado");
					editNombre.requestFocus();
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
		editNumCasa.setMinimumSize(new Dimension(60, 100));
		editNumCasa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!uti.estaVacio(editNumCasa)) {
					if (ValidacionTextField.esNumerico(editNumCasa))
						editCP.requestFocus();
					else {
						uti.escribir("Escriba Solo Numeros");
						editNumCasa.requestFocus();
					}
				} else {
					uti.escribir("Ingrese el dato solicitado");
					editNumCasa.requestFocus();
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
		editCP.setMinimumSize(new Dimension(60, 100));
		RestrictedTextField rs = new RestrictedTextField(editCP);
		rs.setLimit(5);
		rs.setOnlyNums(true);
		editCP.getDocument().addDocumentListener(new DocumentListener() {
			void methodz() {
				if (editCP.getText().length() == 5) {
					CodigoPostal c = valdarCP();
					if (c != null)
						agregarCamposCP(c);
					else
						escribir("El Codigo Postal no Existe ");
				} else
					limpiarAreasCP();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				methodz();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				methodz();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				methodz();
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
		listColonias.setMinimumSize(new Dimension(60, 100));
		listColonias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		editColonia.setViewportView(listColonias);

		JLabel lblEstado = new JLabel("Ingrese el Estado:");
		lblEstado.setForeground(Color.BLACK);
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 13));
		panelPersonales.add(lblEstado);

		editEstado = new JTextField();
		editEstado.setMinimumSize(new Dimension(60, 100));
		editEstado.setEditable(false);
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
		editMunicipio.setMinimumSize(new Dimension(60, 100));
		editMunicipio.setEditable(false);
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

		JPanel panel = new JPanel();
		panelDatos.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ultimos Alumnos Registrados:", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane(listAlumnos);
		panel_1.add(scrollPane);

	}

	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private void agregarColonias(String codigo) {
		List<String> colonias = codigosPostales.getColonias(codigo);
		modeloLista.removeAllElements();
		colonias.stream().forEach(x -> modeloLista.addElement(x));
		if (modeloLista.getSize() == 1)
			listColonias.setSelectedIndex(0);
	}

	private void limpiarAreasCP() {
		if (!modeloLista.isEmpty())
			modeloLista.removeAllElements();
		if (!(editEstado.getText().isEmpty() && editMunicipio.getText().isEmpty())) {
			editEstado.setText("");
			editMunicipio.setText("");
		}
	}

	private boolean hacerMayuscula(JTextField t) {
		try {

			t.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!uti.estaVacio(t)) {
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

	public void pedirFoco() {
		editNoControl.requestFocus();
	}

	private boolean valdarNumControl() {
		if (!ValidacionTextField.estaVacio(editNoControl)) {
			if (uti.validarCaracter(editNoControl, 8)) {
				if (grupo.buscar(editNoControl.getText()) == null)
					return true;
				else
					escribir("El Alumno ya esta Registrado");
			} else {
				uti.escribir("Ingrese los 8 caracteres solictados");
				editNoControl.requestFocus();
			}
		} else {
			uti.escribir("Llene el campo solicitado");
			editNoControl.requestFocus();
		}
		return false;
	}

	private CodigoPostal valdarCP() {

		CodigoPostal cp = codigosPostales.existe(editCP.getText());

		if (cp == null) {

			editCP.requestFocus();
		}
		return cp;
	}

	private void agregarCamposCP(CodigoPostal cp) {
		editEstado.setText(cp.getEstado().get(0));
		if (modeloLista != null) {
			modeloLista.removeAllElements();
			agregarColonias(editCP.getText());
		}
		editMunicipio.setText(cp.getCiudad().get(0));
	}

	private boolean validarCampos() {

		if (!valdarNumControl())
			return false;
		if (!validacionB(editNombre)) {
			escribir("Escriba el Nombre");
			return false;
		}

		if (!validacionB(editPaterno)) {
			escribir("Escriba el Apellido Paterno");
			return false;
		}
		if (!validacionB(editMaterno)) {
			escribir("Escriba el Apellido Materno");
			return false;
		}
		if (editFechaNac.getDate() == null) {
			uti.escribir("Debe escribir una Fecha Valida");
			return false;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate f = LocalDate.parse(sd.format(editFechaNac.getDate()));
		if (LocalDate.now().isBefore(f)) {
			uti.escribir("Debe escribir una Fecha Valida");
			return false;
		}
		if (grupoBotones.getSelection() == null) {
			uti.escribir("Debe Seleccionar el Genero");
			return false;
		}
		if (comboBoxSemestre.getSelectedIndex() < 1) {
			uti.escribir("Seleccionar Un Semestre");
			return false;
		}
		if (editCarrera.getSelectedIndex() < 1) {
			uti.escribir("Seleccionar Una Carrera");
			return false;
		}
		if (!validacionB(editCalle)) {
			uti.escribir("Escribir El Nombre de la Calle");
			return false;
		}
		if (!validacionB(editNumCasa)) {
			uti.escribir("Escribir el Numero de la Casa");
			return false;
		}
		if (!ValidacionTextField.esNumerico(editNumCasa)) {
			uti.escribir("Escribir Numero de Casa Valido");
			return false;
		}

		if (valdarCP() == null) {
			escribir("El codigo Postal no Existe");
			return false;
		}

		if (listColonias.getSelectedIndex() == -1) {
			uti.escribir("Seleccionar Colonia");
			return false;
		}

		return true;
	}

	private boolean validacionB(JTextField t) {
		if (t.getText().isEmpty()) {
			t.requestFocus();
			return false;
		}
		return true;
	}

	private void limpiar() {
		editNoControl.setText(null);
		editNombre.setText(null);
		editPaterno.setText(null);
		editMaterno.setText(null);
		editCarrera.setSelectedIndex(0);
		modeloLista.removeAllElements();
		grupoBotones.clearSelection();
		comboBoxSemestre.setSelectedIndex(0);
		editCalle.setText(null);
		editNumCasa.setText(null);
		editMunicipio.setText(null);
		editEstado.setText(null);
		editCP.setText(null);
		editFechaNac.setDate(null);
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

	public JList<String> getEditCarrera() {
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

	public JList<String> getEditColonia() {
		return listColonias;
	}

	public JRadioButton getRdbtnMasculino() {
		return rdbtnMasculino;
	}

	public JRadioButton getRdbtnFemenino() {
		return rdbtnFemenino;
	}

	public JComboBox<String> getComboBoxSemestre() {
		return comboBoxSemestre;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public JTextField getEditEstado() {
		return editEstado;
	}

}
