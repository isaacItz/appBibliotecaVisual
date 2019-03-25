package vista;

import static modelox.Utileria.escribir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Cargador;
import modelo.Alumno;
import modelo.CodigosPostales;
import modelo.ComparadorAlumnos;
import modelo.ComparadorInicioAlumnos;
import modelo.ComparadorInicioPrestamos;
import modelo.ComparadorLibros;
import modelo.ComparadorPrestamos;
import modelo.DAOCodigoPostal;
import modelo.Devoluciones;
import modelo.Estante;
import modelo.Grupo;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Prestamos;
import modelo.Utileria;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MenuLateral menuLateral;
	private Footer footer;
	private JButton botonRegistrar;
	private JButton botonConsultar;
	private JButton botonModificar;
	private JButton botonEliminar;
	private JButton botonOrdenar;
	private JButton botonRegistrarLibro;
	private JButton botonConsultarLibro;
	private JButton botonModificarLibro;
	private JButton botonEliminarLibro;
	private JButton botonOrdenarLibro;
	private JButton botonAceptar;
	private JButton botonSalir;
	private Grupo grupo;
	private JButton botonSalirEstudiante;
	private PanelCentralLibros panelCentralLibros;
	private Section section;
	private ObjectOutputStream creadorDeFlujo;
	private ObjectInputStream lectorDeFlujo;
	private Estante estante;
	private CodigosPostales codP;
	private PanelBusquedaAlumnos panelBusquedaAlumnos;
	private JButton okButton;
	private JButton btnBusq;
	private JButton cancelButton;
	private DialogEliminacion dialogE;
	private JButton okButtonOrdenacion;
	private DialogOrdenacion or;
	private PanelBusquedaLibros panelBusquedaLibros;
	private Prestamos prestamos;
	private PanelPrestamos p;
	private PanelBusqueda pBP;
	private PanelBusquedaDevoluciones pBD;
	private Devoluciones devoluciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Recursos/icono.png")));

		codP = leerStream("codP", codP);
		estante = leerStream("estante", estante);
		prestamos = leerStream("prestamos", prestamos);
		grupo = leerStream("grupo", grupo);
		devoluciones = leerStream("devoluciones", devoluciones);
		if (grupo == null)
			grupo = new Grupo();
		if (codP == null)
			codP = DAOCodigoPostal.getCodigos("src\\extras/CPdescarga.txt");
		if (estante == null)
			estante = new Estante();
		if (prestamos == null)
			prestamos = new Prestamos();
		if (devoluciones == null)
			devoluciones = new Devoluciones();

		setTitle("Biblioteca Visual");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(900, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// ---- BOTONES ALUMNOS

		JMenu menuUsuarios = new JMenu("Gestion de usuarios");
		menuUsuarios.setForeground(new Color(139, 0, 0));
		menuUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 22));
		menuUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// contentPane.removeAll();
				// menuLateral = null;
				// section = null;
				// contentPane.add(footer, BorderLayout.SOUTH);
				// repaint();
			}
		});
		menuBar.add(menuUsuarios);

		JMenuItem actualizacionesAlumnos = new JMenuItem("Actualizaciones");
		actualizacionesAlumnos.setForeground(new Color(0, 0, 128));
		actualizacionesAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 27));
		actualizacionesAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vincularBotonesAlumnos();
			}
		});
		menuUsuarios.add(actualizacionesAlumnos);

		JMenuItem opcionGraficosAlumnos = new JMenuItem("Agregar Mediante Archivo");
		opcionGraficosAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ruta = Cargador.getSelectorRuta();
				if (ruta != null) {
					grupo.agregar(Cargador.getAlumnosArchivo(ruta));
					escribir("Alumnos Agregados");
					actualizarTablaA();
				}

			}
		});
		opcionGraficosAlumnos.setForeground(new Color(0, 0, 128));
		opcionGraficosAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 27));
		menuUsuarios.add(opcionGraficosAlumnos);

		// ---- BOTONES LIBROS

		JMenu mnGestionDeLibros = new JMenu("Gestion de Libros");
		mnGestionDeLibros.setForeground(new Color(139, 0, 0));
		mnGestionDeLibros.setFont(new Font("Tahoma", Font.PLAIN, 22));
		menuBar.add(mnGestionDeLibros);

		JMenuItem actualizacionesLibros = new JMenuItem("Actualizaciones");
		actualizacionesLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vincularBotonesLibros();
			}
		});
		actualizacionesLibros.setForeground(new Color(0, 0, 128));
		actualizacionesLibros.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mnGestionDeLibros.add(actualizacionesLibros);

		JMenuItem graficasLibros = new JMenuItem("Agregar Mediante Archivo");
		graficasLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ruta = Cargador.getSelectorRuta();
				if (ruta != null) {
					estante.agregar(Cargador.getLibrosArchivo(ruta));
					escribir("Libros Agregados");
					actualizarTablaL();
				}
			}
		});
		graficasLibros.setForeground(new Color(0, 0, 128));
		graficasLibros.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mnGestionDeLibros.add(graficasLibros);

		JMenu mnGestionDePrestamos = new JMenu("Gestion de Prestamos");
		mnGestionDePrestamos.setForeground(new Color(139, 0, 0));
		mnGestionDePrestamos.setFont(new Font("Tahoma", Font.PLAIN, 22));
		menuBar.add(mnGestionDePrestamos);

		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (p == null) {
					p = new PanelPrestamos(prestamos, grupo, estante);

					p.getBtnOk().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							Alumno a = grupo.buscar(p.getTextoNC());
							Libro l = estante.getLibro(p.getTextIsbn());
							if (a != null) {
								if (l != null) {
									ComparadorPrestamos cP = new ComparadorPrestamos(ComparadorPrestamos.ISBN);
									Prestamo pres = new Prestamo();
									pres.setLibro(l);
									if (Utileria.linealSearch(prestamos.getList(), pres, cP) == -1) {
										// Collections.sort(prestamos.getList(),
										// (p1, p2) -> p1.getLibro().compareTo(p2.getLibro()));
										pres.setAlumno(a);
										LocalDate fecha;
										do {
											DialogFecha d = new DialogFecha(DialogFecha.PRESTAMO);
											fecha = d.getFecha();
											if (fecha != null && fecha.compareTo(LocalDate.now()) > 0)
												escribir("la fecha no puede ser posterior a la fecha actual");
											else
												break;
										} while (true);

										if (fecha != null) {
											pres.setFechaDePrestamo(fecha);

											if (fecha.plusDays(1).getDayOfWeek() == DayOfWeek.SATURDAY) {
												pres.setFechaDeEntrega(fecha.plusDays(4));
											} else if (fecha.plusDays(2).getDayOfWeek() == (DayOfWeek.SUNDAY))
												pres.setFechaDeEntrega(fecha.plusDays(3));
											else
												pres.setFechaDeEntrega(fecha.plusDays(2));

											prestamos.agregar(pres);
											escribir("Prestamo Realizado");
											p.limpiarCamposLibro();
										} else
											escribir("Cancelado");

									} else
										escribir("El Libro esta Prestado");

								} else
									escribir("El Libro no Existe");
							} else
								escribir("El Alumno no Existe");
						}
					});

				}

				contentPane.removeAll();
				contentPane.add(p, BorderLayout.CENTER);
				contentPane.add(footer, BorderLayout.SOUTH);
				repaint();
				p.getFoco();
				setVisible(true);

			}
		});
		mntmCrear.setForeground(new Color(0, 0, 128));
		mntmCrear.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mnGestionDePrestamos.add(mntmCrear);

		JMenuItem menuItem_1 = new JMenuItem("Reportes");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pBP = new PanelBusqueda(prestamos, new String[] { "Numero de Control", "Nombre", "Isbn", "Titulo",
						"Fecha de salida", "Fecha en que debe ser devolvido" },
						ComparadorInicioPrestamos.getCriterios());
				contentPane.removeAll();
				actualizarTablaP();
				contentPane.add(pBP);
				pBP.pedirFoco();
				setVisible(true);

			}
		});

		menuItem_1.setForeground(new Color(0, 0, 128));
		menuItem_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mnGestionDePrestamos.add(menuItem_1);

		JMenu mnGestionDeDevoluciones = new JMenu("Gestion de Devoluciones");
		mnGestionDeDevoluciones.setForeground(new Color(139, 0, 0));
		mnGestionDeDevoluciones.setFont(new Font("Tahoma", Font.PLAIN, 22));
		menuBar.add(mnGestionDeDevoluciones);

		JMenuItem menuItem_2 = new JMenuItem("Devolver");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String isbn = Utileria.leerString("digita el isbn del libro a devolver");
				if (isbn != null) {
					Libro l = new Libro(isbn);
					ComparadorLibros c = new ComparadorLibros(ComparadorLibros.ISBN);
					int pos = Utileria.linealSearch(estante.getList(), l, c);

					if (pos != -1) {
						l = estante.getLibro(pos);
						ComparadorPrestamos cm = new ComparadorPrestamos(ComparadorPrestamos.ISBN);
						Prestamo p = new Prestamo();
						p.setLibro(l);
						pos = Utileria.linealSearch(prestamos.getList(), p, cm);
						if (pos != -1) {
							p = prestamos.getPrestamo(pos);
							LocalDate f;
							do {
								DialogFecha d = new DialogFecha(DialogFecha.DEVOLUCION);
								f = d.getFecha();
								if (f != null && p.getFechaDePrestamo().compareTo(f) > 0)
									escribir("la fecha no puede ser anterior a la fecha del prestamo");
								else
									break;
							} while (true);

							if (f != null) {
								long dias = ChronoUnit.DAYS.between(p.getFechaDeEntrega(), f);
								if (dias > 0)
									escribir("El Alumno " + p.getAlumno().getNombreCompleto()
											+ " ha entregado el libro " + dias + " dias tarde");

								p.setFechaDeEntrega(f);
								prestamos.eliminar(pos);
								devoluciones.agregar(p);
								actualizarTablaD();
								actualizarTablaP();
							} else
								escribir("Cancelado");

						} else
							escribir("El Libro no ha sido Prestado");
					} else
						escribir("El Libro no Existe");
				}

			}
		});
		menuItem_2.setForeground(new Color(0, 0, 128));
		menuItem_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mnGestionDeDevoluciones.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("Reportes");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pBD = new PanelBusquedaDevoluciones(devoluciones, new String[] { "Numero de Control", "Nombre", "Isbn",
						"Titulo", "Fecha de salida", "Dias de Prestamo", "Fecha de Devuelto" },
						ComparadorInicioPrestamos.getCriterios());
				contentPane.removeAll();
				actualizarTablaD();
				contentPane.add(pBD);
				pBD.pedirFoco();
				setVisible(true);
			}
		});
		menuItem_3.setForeground(new Color(0, 0, 128));
		menuItem_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		mnGestionDeDevoluciones.add(menuItem_3);

		// --- CONTENT PANE Y CREACION DEL FOOTER
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		footer = new Footer();

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				setVisible(false);

				if (!new File("src\\extras/codP").exists())
					crearStream("codP", codP);
				crearStream("estante", estante);
				crearStream("prestamos", prestamos);
				crearStream("grupo", grupo);
				crearStream("devoluciones", devoluciones);
				System.exit(0);

			}
		});

		setLocationRelativeTo(null);

	}// ---FIN DEL CONSTRUCTOR

	// ---- VINCULACION DE BOTONES DE ALUMNOS

	private void vincularBotonesAlumnos() {
		menuLateral = new MenuLateral();
		botonRegistrar = menuLateral.getBotonRegistrar();
		botonRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (section == null) {
					section = new Section(codP, grupo);
					botonSalirEstudiante = section.getBotonSalir();
					botonSalirEstudiante.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							contentPane.remove(section);
							section = null;
							repaint();
						}
					});
					section.getBotonAceptar().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							actualizarTablaA();
						}
					});
				}

				focoPantalla(section);
				section.pedirFoco();
			}
		});

		botonConsultar = menuLateral.getBotonConsultar();
		botonConsultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Object[] titulos = { "Numero de Control", "Nombre", "Edad", "Genero", "Semestre", "Carrera",
						"Direccion" };
				if (panelBusquedaAlumnos == null) {
					panelBusquedaAlumnos = new PanelBusquedaAlumnos(grupo, titulos,
							ComparadorInicioAlumnos.getCriterios());
					panelBusquedaAlumnos.agregarAlumnos(grupo.getList());

					// BorderLayout layout = (BorderLayout)
					// contentPane.getLayout();
					// contentPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				}

				actualizarTablaA();
				focoPantalla(panelBusquedaAlumnos);
				panelBusquedaAlumnos.pedirFoco();
			}
		});

		botonEliminar = menuLateral.getBotonEliminar();
		botonEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (panelBusquedaAlumnos != null) {
					if (panelBusquedaAlumnos.hayFilaSeleccionada()) {
						String numC = panelBusquedaAlumnos.getNumControlSeleccionado();
						Alumno a = grupo.buscar(numC);
						if (!prestamos.existeNumControl(a)) {
							dialogE = new DialogEliminacion(a.getNombreCompleto(), Alumno.class);
							vincularBotonesDialogE(a);
						} else
							escribir("No se Puede Borrar un Alumno con Libros Prestados");

					} else
						eliminar();

				} else
					eliminar();

				actualizarTablaA();

			}
		});

		botonModificar = menuLateral.getBotonModificar();
		botonModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panelBusquedaAlumnos != null) {

					if (panelBusquedaAlumnos.hayFilaSeleccionada()) {
						String numC = panelBusquedaAlumnos.getNumControlSeleccionado();
						Alumno a = grupo.buscar(numC);
						if (!prestamos.existeNumControl(a)) {
							new ModAlumn(codP, a);
						} else
							escribir("No se Puede Modificar un Alumno con Libros Prestados");

					} else
						actualizarAlumno();
				} else
					actualizarAlumno();
				actualizarTablaA();
			}
		});

		botonOrdenar = menuLateral.getBotonOrdenar();
		botonOrdenar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				or = new DialogOrdenacion(ComparadorInicioAlumnos.getCriterios());
				okButtonOrdenacion = or.getOkButton();
				okButtonOrdenacion.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						int orden = or.getSeleccion()[1].equals("Ascendente") ? 1 : -1;

						Collections.sort(grupo.getList(), new ComparadorAlumnos(or.getSeleccion()[0], orden));
						actualizarTablaA();
						or.dispose();
						System.out.println("Fin Ordenacion");
					}
				});

			}
		});

		// AGREGANDO ELEMENTOS SIEMPRE VISIBLES
		menusSiempreVis();
	}

	// ---- VINCULACION DE BOTONES DE LOS LIBROS LOKOCHONES

	private void vincularBotonesLibros() {

		menuLateral = new MenuLateral();
		botonRegistrarLibro = menuLateral.getBotonRegistrar();
		botonRegistrarLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (panelCentralLibros == null) {
					panelCentralLibros = new PanelCentralLibros();
					JTextField cajaISBN = panelCentralLibros.getCajaISBN();
					cajaISBN.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (!(cajaISBN.getText().isEmpty()) && cajaISBN.getText().length() == 8) {
								if (!estante.existeLibro(cajaISBN.getText())) {
									if (!contLetras(panelCentralLibros.getCajaISBN().getText()))
										panelCentralLibros.enfocarTitulo();
									else
										escribir("No puede contener letras");
								} else {
									escribir("El libro ya existe");
									cajaISBN.setText("");
									cajaISBN.requestFocus();
								}
							} else {
								escribir("Ingrese el isbn correcto");
								cajaISBN.setText("");
								cajaISBN.requestFocus();
							}
						}
					});
					botonSalir = panelCentralLibros.getBotonSalir();
					botonAceptar = panelCentralLibros.getBotonAceptar();
					botonSalir.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							contentPane.remove(panelCentralLibros);
							panelCentralLibros = null;
							repaint();

						}
					});
					botonAceptar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (!panelCentralLibros.hayCajasVacias()) {
								if (!(estante.existeLibro(panelCentralLibros.getCajaISBN().getText()))) {
									estante.agregar(panelCentralLibros.getLibro());
									escribir("Libro registrado con exito");
									panelCentralLibros.vaciarCajas();
								} else
									escribir("El libro ya existe");
							} else {
								escribir("Hay cajas vacias, favor de proporcionar todos los datos");
							}
						}
					});

				}
				focoPantalla(panelCentralLibros);
			}
		});
		botonConsultarLibro = menuLateral.getBotonConsultar();
		botonConsultarLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panelBusquedaLibros == null)
					panelBusquedaLibros = new PanelBusquedaLibros(estante, ComparadorLibros.getCriterios(),
							ComparadorLibros.getCriterios());
				actualizarTablaL();
				focoPantalla(panelBusquedaLibros);
				panelBusquedaLibros.pedirFoco();
			}
		});

		botonModificarLibro = menuLateral.getBotonModificar();
		botonModificarLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (panelBusquedaLibros != null) {
					if (panelBusquedaLibros.hayFilaSeleccionada()) {
						String isbn = panelBusquedaLibros.getClaveSeleccionada();
						Libro a = estante.getLibro(isbn);
						if (!prestamos.existeLbro(a))
							new ModLibro(a);
						else
							escribir("No se Puede Modificar Un Libro Prestado");
					} else
						actualizarLibro();
				} else
					actualizarLibro();
				actualizarTablaL();
			}
		});

		botonEliminarLibro = menuLateral.getBotonEliminar();
		botonEliminarLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (panelBusquedaLibros != null) {
					if (panelBusquedaLibros.hayFilaSeleccionada()) {
						String numC = panelBusquedaLibros.getClaveSeleccionada();
						Libro a = estante.getLibro(numC);
						if (!prestamos.existeLbro(a)) {
							dialogE = new DialogEliminacion(a.getTitulo(), Libro.class);
							vincularBotonesDialogEL(a);
						} else
							escribir("No se Puede Modificar Un Libro Prestado");

					} else
						eliminarL();

				} else
					eliminarL();

				actualizarTablaL();

			}
		});

		botonOrdenarLibro = menuLateral.getBotonOrdenar();
		botonOrdenarLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				or = new DialogOrdenacion(ComparadorLibros.getCriterios());
				okButtonOrdenacion = or.getOkButton();
				okButtonOrdenacion.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Collections.sort(estante.getList(), new ComparadorLibros(or.getSeleccion()[0],
								or.getSeleccion()[1].equals("Ascendente") ? 1 : -1));
						actualizarTablaL();
						or.dispose();
						System.out.println("Fin Ordenacion");
					}
				});

			}
		});

		menusSiempreVis();
	}

	private void vincularBotonesDialogE(Alumno a) {
		okButton = dialogE.getOkButton();
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogE.dispose();
				grupo.eliminar(a);
				escribir("Libro Eliminado");
			}
		});
		cancelButton = dialogE.getCancelButton();
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogE.dispose();
			}
		});
		btnBusq = dialogE.getBtnBusq();
		btnBusq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (panelBusquedaAlumnos != null) {
					dialogE.dispose();
					focoPantalla(panelBusquedaAlumnos);
					panelBusquedaAlumnos.pedirFoco();
				}
			}
		});
	}

	private void vincularBotonesDialogEL(Libro l) {
		okButton = dialogE.getOkButton();
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogE.dispose();
				estante.eliminar(l);
				escribir("Libro Eliminado");
			}
		});
		cancelButton = dialogE.getCancelButton();
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogE.dispose();
			}
		});
		btnBusq = dialogE.getBtnBusq();
		btnBusq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (panelBusquedaLibros != null) {
					dialogE.dispose();
					focoPantalla(panelBusquedaLibros);
					panelBusquedaLibros.pedirFoco();
				}
			}
		});
	}

	public boolean contLetras(String cadena) {
		for (int i = 0; i < cadena.length(); i++)
			if (Character.isAlphabetic(cadena.charAt(i)))
				return true;
		return false;
	}

	private void eliminar() {
		String noControl = JOptionPane.showInputDialog("Ingrese el Numero de Control a Eliminar");
		if (noControl != null) {
			Alumno a = grupo.buscar(noControl);
			if (a != null) {
				if (!prestamos.existeNumControl(a)) {
					dialogE = new DialogEliminacion(a.getNombreCompleto(), Alumno.class);
					vincularBotonesDialogE(a);
				} else
					escribir("No se Puede Borrar un Alumno con Libros Prestados");

			} else
				escribir("El Alumno no Existe");
		}
	}

	private void eliminarL() {
		String noControl = JOptionPane.showInputDialog("Ingrese el Numero isbn del libro a Eliminar");
		if (noControl != null) {
			Libro a = estante.getLibro(noControl);
			if (a != null) {
				if (!prestamos.existeLbro(a)) {
					dialogE = new DialogEliminacion(a.getTitulo(), Libro.class);
					vincularBotonesDialogEL(a);
				} else
					escribir("No se Puede Eliminar Un Libro Prestado");
			} else
				escribir("El libro no Existe");
		}
	}

	private void actualizarAlumno() {
		String noControl = JOptionPane.showInputDialog("Ingrese el Numero de Control del Alumno");
		if (noControl != null) {
			Alumno a = grupo.buscar(noControl);
			if (a != null) {
				new ModAlumn(codP, a);
			} else
				escribir("El Alumno no Existe");
		}
	}

	private void actualizarLibro() {
		String noControl = JOptionPane.showInputDialog("Ingrese el isbn del Libro");
		if (noControl != null) {
			Libro a = estante.getLibro(noControl);
			if (a != null) {
				if (!prestamos.existeLbro(a))
					new ModLibro(a);
				else
					escribir("No se Puede Modificar Un Libro Prestado");
			} else
				escribir("El Libro no Existe");
		}
	}

	private void menusSiempreVis() {
		contentPane.removeAll();
		contentPane.add(menuLateral, BorderLayout.WEST);
		contentPane.add(footer, BorderLayout.SOUTH);
		repaint();
		setVisible(true);
	}

	private void actualizarTablaA() {
		if (panelBusquedaAlumnos != null) {
			panelBusquedaAlumnos.actualizarTabla();
		}
	}

	private void actualizarTablaL() {
		if (panelBusquedaLibros != null) {
			panelBusquedaLibros.actualizarTabla();
		}
	}

	private void actualizarTablaP() {
		if (pBP != null) {
			pBP.actualizarTabla();
		}
	}

	private void actualizarTablaD() {
		if (pBD != null) {
			pBD.actualizarTabla();
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T leerStream(String nom, T coleccion) {
		File archivo = new File("src\\extras/" + nom + "");
		if (archivo.exists()) {
			try {
				lectorDeFlujo = new ObjectInputStream(new FileInputStream(archivo));
				coleccion = ((T) lectorDeFlujo.readObject());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return coleccion;
	}

	private void crearStream(String nombre, Object o) {
		try {
			File archivo = new File("src\\extras/" + nombre + "");
			archivo.createNewFile();
			creadorDeFlujo = new ObjectOutputStream(new FileOutputStream(archivo));
			creadorDeFlujo.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void focoPantalla(JComponent compoente) {
		contentPane.removeAll();
		contentPane.add(menuLateral, BorderLayout.WEST);
		contentPane.add(compoente, BorderLayout.CENTER);
		contentPane.add(footer, BorderLayout.SOUTH);
		repaint();
		setVisible(true);
	}

}
