package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Utileria;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private Image image;
	private JTextField editNoControl;
	private Utileria uti;
	private JLabel lblNombre;
	private JTextField editNombre;
	private JLabel lblPaterno;
	private JTextField editPaterno;
	private JLabel lblMaterno;
	private JLabel lblEdad;
	private JTextField editMaterno;
	private JTextField editEdad;
	private JRadioButton rdbtnFemenino;
	private JLabel lblSeleccioneSuGenero;
	private JRadioButton rdbtnMasculino;
	private ButtonGroup grupo ;
	private JLabel lblSemestre;
	private JTextField editSemestre;
	private Dimension dimension;
	private JLabel lblIngreseLaCarrera;
	private JTextField editCarrera;
	private JLabel lblDatosPersonales;
	private JLabel lblDatosEscolares;
	private JLabel lblCalle;
	private JTextField editCalle;
	private JLabel lblNumeroCasa;
	private JTextField editNumeroCasa;
	private JLabel lblColonia;
	private JTextField EditColonia;
	private JLabel lblMunicipio;
	private JTextField editMunicipio;
	private JLabel lblIngreseElCodigo;
	private JTextField editCP;

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		uti=new Utileria();
		grupo =  new ButtonGroup();
		dimension = new Dimension(0, 10);
		//image=new ImageIcon(getClass().getResource("/Recursos/tecITZ.jpg")).getImage();
		setTitle("Instituto Tecnológico de Zitacuaro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		editNoControl = new JTextField();
		editNoControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!uti.estaVacio(editNoControl)  ) {
					if(uti.validarCaracter(editNoControl, 8)) {
						editNombre.requestFocus();

					}else {
						uti.escribir("Ingrese los 8 caracteres solictados");
					}
				}else {
					uti.escribir("Llene el campo solicitado");
				}
			}
		});
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblDatosEscolares = new JLabel("Datos Escolares:");
		lblDatosEscolares.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 16));
		contentPane.add(lblDatosEscolares);

		JLabel lblControl = new JLabel("Ingrese el numero de control:");
		lblControl.setForeground(Color.BLACK);
		lblControl.setLabelFor(contentPane);
		lblControl.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblControl.setBackground(Color.WHITE);
		contentPane.add(lblControl);
		contentPane.add(editNoControl);
		editNoControl.setColumns(10);

		lblNombre = new JLabel("Ingrese su nombre(s):");
		lblNombre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNombre.setForeground(Color.BLACK);
		contentPane.add(lblNombre);

		editNombre = new JTextField();
		editNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!uti.estaVacio(editNombre)) {
					if(uti.isHombre(editNombre)) {
						rdbtnMasculino.setSelected(true);
					}else {
						rdbtnMasculino.setSelected(false);
						if(uti.isMujer(editNombre)) {
							rdbtnFemenino.setSelected(true);
						}else {
						rdbtnFemenino.setSelected(false);
						}
					}
					editPaterno.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
			}
		});
		contentPane.add(editNombre);
		editNombre.setColumns(10);

		lblPaterno = new JLabel("Ingrese su apellido paterno:");
		lblPaterno.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblPaterno.setForeground(Color.BLACK);
		contentPane.add(lblPaterno);

		editPaterno = new JTextField();
		editPaterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!uti.estaVacio(editPaterno)) {
					editMaterno.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		contentPane.add(editPaterno);
		editPaterno.setColumns(10);

		lblMaterno = new JLabel("Ingrese su apellido materno:");
		lblMaterno.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblMaterno.setForeground(Color.BLACK);
		contentPane.add(lblMaterno);	

		editMaterno = new JTextField();
		editMaterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!uti.estaVacio(editMaterno)) {
					editEdad.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}

			}
		});
		contentPane.add(editMaterno);
		editMaterno.setColumns(10);

		lblEdad = new JLabel("Ingrese su edad:");
		lblEdad.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblEdad.setForeground(Color.BLACK);
		contentPane.add(lblEdad);

		editEdad = new JTextField();
		editEdad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(uti.estaVacio(editEdad)) {
					uti.escribir("Ingrese el dato solictado");
				}else {
					if(uti.verificarRadioBottom(rdbtnFemenino)|| uti.verificarRadioBottom(rdbtnMasculino)) {
						editSemestre.requestFocus();
						
					}else {
							uti.escribir("Seleccione una opcion");
						}
					
					}
						
				}
			
		});
		contentPane.add(editEdad);
		editEdad.setColumns(10);

		lblSeleccioneSuGenero = new JLabel("Seleccione su genero:");
		lblSeleccioneSuGenero.setFont(new Font("Tahoma", Font.ITALIC,13));
		lblSeleccioneSuGenero.setForeground(Color.BLACK);
		contentPane.add(lblSeleccioneSuGenero);

		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uti.verificarRadioBottom(rdbtnFemenino)|| uti.verificarRadioBottom(rdbtnMasculino)) {
					editSemestre.requestFocus();
				}else {
					uti.escribir("Seleccione una opcion");
				}
				
				
			}
		});
		contentPane.add(rdbtnFemenino);

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uti.verificarRadioBottom(rdbtnFemenino)|| uti.verificarRadioBottom(rdbtnMasculino)) {
					editSemestre.requestFocus();
				}else {
					uti.escribir("Seleccione una opcion");
				}
				
			}
		});
		contentPane.add(rdbtnMasculino);
		setVisible(true);
		grupo.add(rdbtnFemenino);
		grupo.add(rdbtnMasculino);
		
		lblSemestre = new JLabel("Ingrese su semestre:");
		lblSemestre.setForeground(Color.BLACK);
		lblSemestre.setFont(new Font("Tahoma", Font.ITALIC, 13));
		contentPane.add(lblSemestre);
		
		editSemestre = new JTextField();
		editSemestre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!uti.estaVacio(editSemestre)) {
					editCarrera.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
				
			}
		});
		contentPane.add(editSemestre);
		editSemestre.setColumns(10);
		
		lblIngreseLaCarrera = new JLabel("Ingrese la carrera que cursa:");
		lblIngreseLaCarrera.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblIngreseLaCarrera.setForeground(Color.BLACK);
		contentPane.add(lblIngreseLaCarrera);
		
		editCarrera = new JTextField();
		editCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!uti.estaVacio(editCarrera)) {
					editCalle.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
			}
		});
		contentPane.add(editCarrera);
		editCarrera.setColumns(10);
		
		lblDatosPersonales = new JLabel("Datos Personales:");
		lblDatosPersonales.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 16));
		contentPane.add(lblDatosPersonales);
		
		lblCalle = new JLabel("Ingrese el nombre de la calle:");
		lblCalle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblCalle.setForeground(Color.BLACK);
		contentPane.add(lblCalle);
		
		editCalle = new JTextField();
		editCalle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!uti.estaVacio(editCalle)) {
					editNumeroCasa.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
				
			}
		});
		contentPane.add(editCalle);
		editCalle.setColumns(10);
		
		lblNumeroCasa = new JLabel("Ingrese el numero de la casa:");
		lblNumeroCasa.setFont(new Font("Tahoma", Font.ITALIC, 13));
		contentPane.add(lblNumeroCasa);
		
		editNumeroCasa = new JTextField();
		editNumeroCasa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(!uti.estaVacio(editNumeroCasa)) {
					EditColonia.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
			}
		});
		contentPane.add(editNumeroCasa);
		editNumeroCasa.setColumns(10);
		
		lblColonia = new JLabel("Ingrese el nombre de la colonia");
		lblColonia.setFont(new Font("Tahoma", Font.ITALIC, 13));
		contentPane.add(lblColonia);
		
		EditColonia = new JTextField();
		EditColonia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!uti.estaVacio(EditColonia)) {
					editMunicipio.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
				
			}
		});
		contentPane.add(EditColonia);
		EditColonia.setColumns(10);
		
		lblMunicipio = new JLabel("Ingrese el nombre del municipio:");
		lblMunicipio.setFont(new Font("Tahoma", Font.ITALIC, 13));
		contentPane.add(lblMunicipio);
		
		editMunicipio = new JTextField();
		editMunicipio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!uti.estaVacio(editMunicipio)) {
					editCP.requestFocus();
				}else {
					uti.escribir("Ingrese el dato solicitado");
				}
				
			}
		});
		contentPane.add(editMunicipio);
		editMunicipio.setColumns(10);
		
		lblIngreseElCodigo = new JLabel("Ingrese el Codigo Postal:");
		lblIngreseElCodigo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		contentPane.add(lblIngreseElCodigo);
		
		editCP = new JTextField();
		contentPane.add(editCP);
		editCP.setColumns(10);

	}
	//	@Override
	//	public void paint(Graphics g) {
	//		g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),null);
	//		
	//	}

	

}
